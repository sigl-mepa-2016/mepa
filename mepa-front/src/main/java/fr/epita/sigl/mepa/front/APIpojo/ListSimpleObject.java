package fr.epita.sigl.mepa.front.APIpojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Items")
public class ListSimpleObject {
    private List<SimpleObject> items;

    public ListSimpleObject() {
        items = new ArrayList<>();
    }

    public void addSimpleObject(String id, String name) {
        items.add(new SimpleObject(id, name));
    }

    @XmlElement
    public List<SimpleObject> getItems() {
        return items;
    }
}

class SimpleObject {
    private String id;
    private String name;

    public SimpleObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleObject() {
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}

