package fr.epita.sigl.mepa.front.dataSet;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class AddCustomColumnFormBean {

    @Size(max = 128)
    @NotBlank
    private String name;

    @Size(max = 32)
    @NotBlank
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
