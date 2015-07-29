package fr.epita.sigl.mepa.core.domain;

import org.bson.types.ObjectId;

public class Graph {

    private ObjectId _id;
    private String grapheType;
    private String grapheColor1;
    private String grapheColor2;
    private String grapheJson;

    public Graph() {
    }

    public Graph(String grapheType, String grapheColor1, String grapheColor2, String grapheJson) {
        this.grapheType = grapheType;
        this.grapheColor1 = grapheColor1;
        this.grapheColor2 = grapheColor2;
        this.grapheJson = grapheJson;
    }

    public Graph(ObjectId _id, String grapheType, String grapheColor1, String grapheColor2, String grapheJson) {
        this._id = _id;
        this.grapheType = grapheType;
        this.grapheColor1 = grapheColor1;
        this.grapheColor2 = grapheColor2;
        this.grapheJson = grapheJson;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getGrapheType() {
        return grapheType;
    }

    public String getGrapheColor1() {
        return grapheColor1;
    }

    public String getGrapheColor2() {
        return grapheColor2;
    }

    public String getGrapheJson() {
        return grapheJson;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setGrapheType(String grapheType) {
        this.grapheType = grapheType;
    }

    public void setGrapheColor1(String grapheColor1) {
        this.grapheColor1 = grapheColor1;
    }

    public void setGrapheColor2(String grapheColor2) {
        this.grapheColor2 = grapheColor2;
    }

    public void setGrapheJson(String grapheJson) {
        this.grapheJson = grapheJson;
    }
}
