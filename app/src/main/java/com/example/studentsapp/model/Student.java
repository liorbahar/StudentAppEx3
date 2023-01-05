package com.example.studentsapp.model;
import java.io.Serializable;
import java.util.Calendar;



public class Student implements Serializable {
    public String name;
    public String id;
    public String phone;
    public String address;
    public Boolean cb;
    public StudentBirthDate birthDate;
    public StudentBirthTime birthTime;


    public Student(String name, String id, String phone, String address, Boolean cb, StudentBirthDate birthDate, StudentBirthTime birthTime) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.cb = cb;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
    }


}