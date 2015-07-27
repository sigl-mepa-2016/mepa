package fr.epita.sigl.mepa.front.APIpojo.Impl;

import fr.epita.sigl.mepa.front.APIpojo.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Items")
public class ListSimpleDataSet implements Pojo {
    private List<SimpleDataSet> item;

    public ListSimpleDataSet() {
        item = new ArrayList<>();
    }

    public void addSimpleObject(String id, String name) {
        item.add(new SimpleDataSet(id, name));
    }

    @XmlElement
    public List<SimpleDataSet> getItem() {
        return item;
    }
}

class SimpleDataSet {
    private String id;
    private String name;

    public SimpleDataSet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleDataSet() {
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

