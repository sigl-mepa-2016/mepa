package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Error")
public class ErrorMessage implements Pojo {

    private String errorName;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorName) {
        this.errorName = errorName;
    }

    @XmlElement
    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
}
