package in.shramishkafle.pojo;

import in.shramishkafle.util.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by mayur on 25-10-2016.
 */
public class Class {
    private String name;
    private Properties properties;
    private List<Method> methods;

    public Class(String packageName , String className){
        properties = new Properties();
        this.setName(packageName,className);
        properties.put(Const.name, this.getName());
        methods = new ArrayList<Method>();
    }

    public String getName() {
        return name;
    }

    private void setName(String packageName , String name) {
        this.name = packageName.trim() +'.'+ name.trim();
    }

    public Properties getProperties() {
        return properties;
    }

    private void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(String name){
        Method method = new Method(name);
        this.getMethods().add(method);
    }
}
