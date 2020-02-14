package com.xander.fileupload;


public class User {
    private String sid;
    private String name;

    public User() {
    }

    public User(String sid, String name) {
        this.sid = sid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String toSidName(){
        return this.sid + "<br>" + this.name;
    }

    public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
