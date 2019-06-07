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
    private String[] doctor_name;
    private String[] visit_date;
    private int[] visit_period;
    private String[] office;
    private String[] division_name;
    private String[] division_department;



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
        return emergency_relationship;
    }

    public String getPassword() {
        return password;
    }

    public String[] getReservation() {
        return reservation;
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
