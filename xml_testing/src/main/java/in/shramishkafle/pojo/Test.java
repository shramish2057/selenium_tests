package in.shramishkafle.pojo;

import in.shramishkafle.util.Const;

import java.util.Properties;

/**
 * Created by mayur on 25-10-2016.
 */
public class Test {
    private String name;
    private Class myClass;
    private Browser browser;
    private Properties properties;

    public Test(String packageName, String className,Browser browser){
        properties = new Properties();
        setName(className,browser);
        setMyClass(new Class(packageName,className));
        setBrowser(browser);
    }

    public String getName() {
        return name;
    }

    private void setName(String className, Browser browser) {
        this.name = className.trim() + "_" + browser.getName() + "_" + browser.getVersion() + "_" + browser.getOS();
        this.properties.put(Const.name, this.name);
    }

    public Properties getProperties(){
        return this.properties;
    }

    public Browser getBrowser() {
        return browser;
    }

    private void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Class getMyClass() {
        return myClass;
    }

    private void setMyClass(Class myClass) {
        this.myClass = myClass;
    }
}
