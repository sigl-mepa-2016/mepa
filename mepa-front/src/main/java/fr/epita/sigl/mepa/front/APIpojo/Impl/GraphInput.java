package fr.epita.sigl.mepa.front.APIpojo.Impl;


public class GraphInput {
    private String id;
    private String grapheType;
    private String grapheColor1;
    private String grapheColor2;
    private String grapheJson;

    public GraphInput() {
    }

    public GraphInput(String id, String grapheType, String grapheColor1, String grapheColor2, String grapheJson) {
        this.id = id;
        this.grapheType = grapheType;
        this.grapheColor1 = grapheColor1;
        this.grapheColor2 = grapheColor2;
        this.grapheJson = grapheJson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrapheType() {
        return grapheType;
    }

    public void setGrapheType(String grapheType) {
        this.grapheType = grapheType;
    }

    public String getGrapheColor1() {
        return grapheColor1;
    }

    public void setGrapheColor1(String grapheColor1) {
        this.grapheColor1 = grapheColor1;
    }

    public String getGrapheColor2() {
        return grapheColor2;
    }

    public void setGrapheColor2(String grapheColor2) {
        this.grapheColor2 = grapheColor2;
    }

    public String getGrapheJson() {
        return grapheJson;
    }

    public void setGrapheJson(String grapheJson) {
        this.grapheJson = grapheJson;
    }

    @Override
    public String toString() {
        return "GraphInput{" +
                "id='" + id + '\'' +
                ", grapheType='" + grapheType + '\'' +
                ", grapheColor1='" + grapheColor1 + '\'' +
                ", grapheColor2='" + grapheColor2 + '\'' +
                ", grapheJson='" + grapheJson + '\'' +
                '}';
    }
}
