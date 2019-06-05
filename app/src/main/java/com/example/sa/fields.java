package com.example.sa;

public class fields {
    public String getId() {
        return id;
    }

    private String id;

    //patient
    private String name;
    private String gender;
    private String birthday;
    private String phone;
    private String emergency_name;
    private String emergency_phone;
    private String emergency_relationship;
    private String password;
    private String[] reservation;

    public String[] getDoctor_name() {
        return doctor_name;
    }

    public String[] getVisit_date() {
        return visit_date;
    }

    public int[] getVisit_period() {
        return visit_period;
    }

    private String[] doctor_name;
    private String[] visit_date;
    private int[] visit_period;

    public String[] getDivision_name() {
        return division_name;
    }

    public String[] getDivision_department() {
        return division_department;
    }

    private String[] division_name;
    private String[] division_department;


    public String[] getRes() {
        return reservation;
    }

    public void setRes(String[] res) {
        this.reservation = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergency_name() {
        return emergency_name;
    }

    public void setEmergency_name(String emergency_name) {
        this.emergency_name = emergency_name;
    }

    public String getEmergency_phone() {
        return emergency_phone;
    }

    public void setEmergency_phone(String emergency_phone) {
        this.emergency_phone = emergency_phone;
    }

    public String getEmergency_relationship() {
        return emergency_relationship;
    }

    public void setEmergency_relationship(String emergency_relationship) {
        this.emergency_relationship = emergency_relationship;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
