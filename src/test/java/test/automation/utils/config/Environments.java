package test.automation.utils.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Environments")
public class Environments {

    @XmlElement(name = "Environment", type = Environment.class)
    private List<Environment> models = new ArrayList<Environment>();

    public Environments() {

    }

    public Environments(List<Environment> models) {
        this.models = models;
    }

    public List<Environment> getEnvironments() {
        return models;
    }

    public void setEnvironments(List<Environment> models) {
        this.models = models;
    }

}
