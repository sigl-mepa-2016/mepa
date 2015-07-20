package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

/**
 * Created by Calu on 20/07/2015.
 */
public class ErrorMessage implements Pojo {

    private String errorName;

    public ErrorMessage(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
}
