package in.shramishkafle.pojo;

import in.shramishkafle.util.Const;

import java.util.Properties;

public class Browser {
    private String name;
    private String version;
    private String OS;

    public Browser(String browserInfo){
        String[] browserSplit = browserInfo.split("::");
        if(browserSplit.length > 2){
            setName(browserSplit[0]);
            setVersion(browserSplit[1]);
            setOS(browserSplit[2]);
        }
    }

    public String getName() {
        return name;
    }

    public Properties getNameAsProperty(){
        return getParameterProperty(Const.browser,getName());
    }

    private Properties getParameterProperty(String name, String value){
        Properties properties = new Properties();
        properties.put(Const.name,name);
        properties.put(Const.value , value);
        return properties;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }
    public Properties getVersionAsProperty(){
        return getParameterProperty(Const.version,getVersion());
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOS() {
        return OS;
    }

    public Properties getOSAsProperty(){
        return getParameterProperty(Const.OS,getOS());
    }

    public void setOS(String OS) {
        this.OS = OS;
    }
}
