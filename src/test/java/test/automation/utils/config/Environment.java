package test.automation.utils.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class Environment {

    private Set<Parameter> param = new HashSet<Parameter>();

    private String name;

    @XmlElement(name = "Parameter", type = Parameter.class)
    public Set<Parameter> getParam() {
        return param;
    }

    public void setParam(Set<Parameter> param) {
        this.param = param;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}