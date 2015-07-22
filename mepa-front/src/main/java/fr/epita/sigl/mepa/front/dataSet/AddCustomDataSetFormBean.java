package fr.epita.sigl.mepa.front.dataSet;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class AddCustomDataSetFormBean {

    @Size(max = 256)
    @NotBlank
    private String name;

    @Size(max = 32)
    @NotBlank
    private String owner;

    @Size(max = 32)
    @NotBlank
    private String theme;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
