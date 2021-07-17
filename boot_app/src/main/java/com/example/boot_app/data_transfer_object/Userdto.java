package com.example.boot_app.data_transfer_object;

public class Userdto {

    private String username;
    private String email;

    public Userdto() {
        super();
    }

    public Userdto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Userdto [email=" + email + ", username=" + username + "]";
    }
    
    
}
