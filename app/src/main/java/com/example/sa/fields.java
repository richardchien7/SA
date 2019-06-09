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
    private String emergency_relation;
    private String password;
    private String[] res_id;
    private String[] doctor_name;
    private String[] visit_date;
    private int[] visit_period;
    private String[] office;
    private String[] division_name;
    private String[] division_department;

    public fields(String id, String name, String gender, String birthday, String phone, String emergency_name, String emergency_phone, String emergency_relation, String password) {
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



    public String[] getDivision_department() {
        return division_department;
    }
    public String[] getDivision_name() {
        return division_name;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmergency_name() {
        return emergency_name;
    }

    public String getEmergency_phone() {
        return emergency_phone;
    }

    public String getEmergency_relationship() {
        return emergency_relation;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRes_id() {
        return res_id;
    }

    public String[] getDoctor_name() {
        return doctor_name;
    }

    public String[] getVisit_date() {
        return visit_date;
    }

    public int[] getVisit_period() {
        return visit_period;
    }

    public String[] getOffice() {
        return office;
    }


}