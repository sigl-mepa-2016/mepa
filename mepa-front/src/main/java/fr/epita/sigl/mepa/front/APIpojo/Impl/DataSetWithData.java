package fr.epita.sigl.mepa.front.APIpojo.Impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataSetWithData extends DataSet {
    private Data dataInDataSet;

    public DataSetWithData() {
        super();
    }

    public DataSetWithData(DataSet dataSet, Data data) {
        super(dataSet.name, dataSet.owner, dataSet.theme, dataSet.lastModified, dataSet.isCarto, dataSet.isGraphic, dataSet.fieldMap);
        this.dataInDataSet = data;
    }

    @XmlElement
    public Data getDataInDataSet() {
        return dataInDataSet;
    }

    @Override
    public String toString() {
        return "DataSetWithData{" +
                "dataInDataSet=" + dataInDataSet +
                '}';
    }
}
