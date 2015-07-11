package fr.epita.sigl.mepa.front.APIpojo;


import java.util.ArrayList;
import java.util.List;

public class ListSimpleObject {
    private List<SimpleObject> items;

    public ListSimpleObject() {
        items = new ArrayList<>();
    }

    public void addSimpleObject(String id, String name)
    {
        items.add(new SimpleObject(id, name));
    }

    public List<SimpleObject> getItems() {
        return items;
    }
}
class SimpleObject{
    private String id;
    private String name;

    public SimpleObject(String id, String name) {
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
