package fr.epita.sigl.mepa.front.dataSet;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

    private boolean isCarto;
    private boolean isGraphic;

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

    public boolean isCarto() {
        return isCarto;
    }

    public void setIsCarto(boolean isCarto) {
        this.isCarto = isCarto;
    }

    public boolean isGraphic() {
        return isGraphic;
    }

    public void setIsGraphic(boolean isGraphic) {
        this.isGraphic = isGraphic;
    }
}
