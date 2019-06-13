package com.example.sa;

public class fieldsconfirm {
    private String[] visit_time_id;
    private String[] doctor;
    private String[] patient_id;
    private int Number;
    private String condition ;
    public fieldsconfirm(String[] doctor_id, String[] visit_time_id, String[] patient_id, int number, String condition) {
        this.doctor = doctor_id;
        this.visit_time_id = visit_time_id;
        this.patient_id = patient_id;
        this.Number = number;
        this.condition = condition;
    }
}
