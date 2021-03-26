package in.shramishkafle.util;

import in.shramishkafle.pojo.Browser;
import in.shramishkafle.pojo.Method;
import in.shramishkafle.pojo.Test;
import org.testng.reporters.XMLStringBuffer;

import java.io.File;
import java.util.*;


public class EnvVarToFile {
    private Map<String,String> suiteParameters;
    private Map<String,String> testParameters;
    private String annotationType;
    private Properties suiteAttributes;
    private int logLevel;
    private List<Test> tests;
    private HashMap<String, List<Method>> classesAndMethods;

    public EnvVarToFile(){
        setTests(new ArrayList<Test>());
        setSuiteParameters(new HashMap<String, String>());
        setTestParameters(new HashMap<String, String>());
        setSuiteAttributes(new Properties());
        annotationType = "true";
        logLevel = 3;
        classesAndMethods = new HashMap<String, List<Method>>();
        init();
    }

    private void setClassesAndMethods(String[] tests){
        for(String test : tests){
            String[] testSplit = test.split("\\.");
            if(!getClassesAndMethods().containsKey(testSplit[0]))
                getClassesAndMethods().put(testSplit[0],new ArrayList<Method>());
            if(testSplit.length > 1)
                if(getClassesAndMethods().get(testSplit[0]).contains(testSplit[1]))
                    getClassesAndMethods().get(testSplit[0]).add(new Method(testSplit[1]));
        }
    }

    private HashMap<String,List<Method >> getClassesAndMethods(){
        return this.classesAndMethods;
    }

    public void write(){
        File file = new File(".");
        CustomSuiteGenerator customSuiteGenerator = new CustomSuiteGenerator(true);
        XMLStringBuffer xmlStringBuffer = customSuiteGenerator.getSuiteBuffer();
        xmlStringBuffer.push(Const.suite, suiteAttributes);

        for (String parameter : this.getSuiteParameters().keySet()) {
            Properties paramAttrs = new Properties();
            paramAttrs.setProperty(Const.name, parameter);
            paramAttrs.setProperty(Const.value, this.getSuiteParameters().get(parameter));
            xmlStringBuffer.push(Const.parameter, paramAttrs);
            xmlStringBuffer.pop(Const.parameter);
        }

        for(Test test : this.getTests()){
            xmlStringBuffer.push(Const.test , test.getProperties());
            {
                Browser browser = test.getBrowser();
                xmlStringBuffer.push(Const.parameter,browser.getNameAsProperty());
                xmlStringBuffer.pop();
                xmlStringBuffer.push(Const.parameter,browser.getVersionAsProperty());
                xmlStringBuffer.pop();
                xmlStringBuffer.push(Const.parameter,browser.getOSAsProperty());
                xmlStringBuffer.pop();

                xmlStringBuffer.push(Const.classes);
                {
                    xmlStringBuffer.push(Const.myClass , test.getMyClass().getProperties());
                    {
                        if(test.getMyClass().getMethods().size() > 0){
                            xmlStringBuffer.push(Const.methods);
                            {
                                for (Method method : test.getMyClass().getMethods()){
                                    xmlStringBuffer.push(Const.include, method.getProperties());
                                    xmlStringBuffer.pop();
                                }
                            }
                            xmlStringBuffer.pop();
                        }
                    }
                    xmlStringBuffer.pop();
                }
                xmlStringBuffer.pop();
            }
            xmlStringBuffer.pop();
        }
        xmlStringBuffer.pop();
        String xmlOP = xmlStringBuffer.toXML();
        //System.out.print(xmlOP);
        customSuiteGenerator.save(file);
    }

    private void init(){
        this.addSuiteAttribute(Const.name, System.getenv(Const.suiteName));
        if(System.getenv(Const.parallel) != null || !("".equals(System.getenv(Const.parallel))))
            this.addSuiteAttribute(Const.parallel, System.getenv(Const.parallel));
        if(System.getenv(Const.thread_count) != null || !("".equals(System.getenv(Const.thread_count))))
            this.addSuiteAttribute(Const.thread_count, System.getenv(Const.thread_count));

        this.addSuiteParameter(Const.remoteURL, System.getenv(Const.remoteURL));
        this.addSuiteParameter(Const.ReportLocation, System.getenv(Const.ReportLocation));
        this.addSuiteParameter(Const.baseURL, System.getenv(Const.baseURL));
        this.addSuiteParameter(Const.internal, System.getenv(Const.internal));

        String packageName = System.getenv(Const.packageName);
        String tests = System.getenv(Const.tests);
        String browsersCombo = System.getenv(Const.browsers);

        this.setClassesAndMethods(tests.split(","));
        String[] arrBrowsersCombo = browsersCombo.split(",");

        Set<String> classes = this.getClassesAndMethods().keySet();

        for (String eachClass : classes){
            for(String browser : arrBrowsersCombo){
                Test test = new Test(packageName, eachClass,new Browser(browser));
                test.getMyClass().setMethods(this.getClassesAndMethods().get(eachClass));
                this.getTests().add(test);
            }
        }
    }

    public Map<String, String> getSuiteParameters() {
        return suiteParameters;
    }

    public void setSuiteParameters(Map<String, String> suiteParameters) {
        this.suiteParameters = suiteParameters;
    }
    public void addSuiteParameter(String key, String value){
        this.suiteParameters.put(key,value);
    }
    public void addTestParameter(String key, String value){
        this.testParameters.put(key,value);
    }
    public void addSuiteAttribute(String key,String value){this.suiteAttributes.put(key,value);}
    public Map<String, String> getTestParameters() {
        return testParameters;
    }

    public void setTestParameters(Map<String, String> testParameters) {
        this.testParameters = testParameters;
    }

    public Properties getSuiteAttributes() {
        return suiteAttributes;
    }

    public void setSuiteAttributes(Properties suiteAttributes) {
        this.suiteAttributes = suiteAttributes;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    public void addTest(Test test){
        this.tests.add(test);
    }
}