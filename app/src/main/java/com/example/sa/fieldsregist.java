package com.example.sa;

public class fieldsregist {
    private String id;

    //patient
    private String name;
    private String gender;
    private String birthday;
    private String phone;
    private String emergency_name;
    private String emergency_phone;
    private String emergency_relation;
    private String password;
    public fieldsregist(String id, String name, String gender, String birthday, String phone, String emergency_name, String emergency_phone, String emergency_relation, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.emergency_name = emergency_name;
        this.emergency_phone = emergency_phone;
        this.emergency_relation = emergency_relation;
        this.password = password;
    }
}
