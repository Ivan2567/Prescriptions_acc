package com.example.entities;

public class User {
    public int id;
    public String login;
    public String pass;
    public String role;
    public User(int id, String login, String pass, String role){
        this.id =id;
        this.login = login;
        this.pass = pass;
        this.role = role;
    }
    @Override
    public String toString(){
        return String.format("ID:%s | login:%s | pass:%s | role:%s ",
                this.id,this.login,this.pass,this.role);
    }
}
