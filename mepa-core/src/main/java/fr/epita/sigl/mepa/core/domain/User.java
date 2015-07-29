package fr.epita.sigl.mepa.core.domain;


import org.bson.types.ObjectId;

public class User {

    private ObjectId _id;
    private String name;
    private String password;

    public User() {
    }

    public User(ObjectId _id, String name, String password) {
        this._id = _id;
        this.name = name;
        this.password = password;
    }

    public  User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
