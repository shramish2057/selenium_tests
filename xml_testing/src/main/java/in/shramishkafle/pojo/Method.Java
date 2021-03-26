package in.shramishkafle.pojo;

import in.shramishkafle.util.Const;

import java.util.Properties;

/**
 * Created by mayur on 25-10-2016.
 */
public class Method {
    private String name;
    private Properties properties;

    public Method(String name){
        this.setName(name);
        this.setProperties(new Properties());
        getProperties().put(Const.name, this.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
