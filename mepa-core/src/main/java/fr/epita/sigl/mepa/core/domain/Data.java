package fr.epita.sigl.mepa.core.domain;

import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    @MongoObjectId
    private ObjectId _id;
    private Map<String, List<String>> data;

    public Data() {
        this.data = new HashMap<>();
    }

    public Data(String _id, Map<String, List<String>> data) {
        this._id = new ObjectId(_id);
        this.data = data;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public ObjectId get_id() {
        return _id;
    }
}
