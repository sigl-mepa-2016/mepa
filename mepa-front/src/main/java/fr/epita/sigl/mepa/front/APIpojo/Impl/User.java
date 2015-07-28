package fr.epita.sigl.mepa.front.APIpojo.Impl;


import fr.epita.sigl.mepa.front.APIpojo.Pojo;

public class User implements Pojo {

    private String name;
    private String password;

    public User() {
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
