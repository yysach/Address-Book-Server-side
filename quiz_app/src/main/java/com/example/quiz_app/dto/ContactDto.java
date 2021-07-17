package com.example.quiz_app.dto;

public class ContactDto {

    private String name;
    private String email;
    private String phone;
    private String about;

    
    public ContactDto() {
        super();
    }

    
    public ContactDto(String name, String email, String phone, String about) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.about = about;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }

    
    
}
