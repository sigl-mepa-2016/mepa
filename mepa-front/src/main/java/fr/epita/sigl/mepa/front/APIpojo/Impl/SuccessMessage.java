package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SuccessMessage implements Pojo{

    private String message;

    public SuccessMessage() {
    }

    public SuccessMessage(String message) {
        this.message = message;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
