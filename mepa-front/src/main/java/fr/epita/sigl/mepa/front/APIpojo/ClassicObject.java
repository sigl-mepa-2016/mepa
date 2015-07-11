package fr.epita.sigl.mepa.front.APIpojo;

/**
 * Created by Calu on 11/07/2015.
 */
public class ClassicObject {
    private String id;
    private String name;

    public ClassicObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
