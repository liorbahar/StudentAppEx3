package com.example.studentsapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.StudentBirthDate;
import com.example.studentsapp.model.StudentBirthTime;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class StudentDetailsForm {
    public EditText nameEt;
    public EditText idEt;
    public EditText phoneEt;
    public EditText addressEt;
    public CheckBox checkedCB;
    public StudentBirthDate birthDate;
    public StudentBirthTime birthTime;

    public TextInputEditText dataBirthdayEditText;
    public TextInputEditText dataBirthTimeEditText;

    public StudentDetailsForm(View view, Context context){
        this.nameEt = view.findViewById(R.id.addstudent_name_et);
        this.idEt = view.findViewById(R.id.addstudent_id_et);
        this.phoneEt = view.findViewById(R.id.addstudent_phone_et);
        this.addressEt = view.findViewById(R.id.addstudent_adress_et);
        this.checkedCB = view.findViewById(R.id.addstudent_checkbox);
        this.birthDate = new StudentBirthDate(2023,0,1);
        this.birthTime = new StudentBirthTime(23,23);

        this.dataBirthdayEditText = view.findViewById(R.id.date_input_edit_text);
        this.dataBirthTimeEditText = view.findViewById(R.id.data_birth_time_edit_text);

        this.dataBirthdayEditText.setOnTouchListener((view1, motionEvent) -> this.onTouchBirthday(view1,motionEvent,context));
        this.dataBirthTimeEditText.setOnTouchListener((view1, motionEvent) -> this.onTouchBirthTime(view1,motionEvent,context));
    }

    public Boolean onTouchBirthday(View view,MotionEvent motionEvent, Context context){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Dialog dialog = new DatePickerDialog(context, (datePicker, year, monthOfYear, dayOfMonth) -> {
               this.setDate(year,monthOfYear,dayOfMonth);
            }, this.birthDate.year, this.birthDate.mouth, this.birthDate.day);
            dialog.show();
            return true;
        }
        return false;
    }

    public Boolean onTouchBirthTime(View view,MotionEvent motionEvent, Context context){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(context, (TimePicker timePicker, int hour, int minute) -> {
                this.setBirthTime(hour,minute);
            }, this.birthTime.hour, this.birthTime.minute, false
            );

            timePickerDialog.show();
            return true;
        }
        return false;
    }

    public void setBirthTime(int hour,int minute){
        this.birthTime = new StudentBirthTime(hour,minute);
        this.dataBirthTimeEditText.setText(getBirtTimeFormat(hour,minute));
    }

    public void setDate(int year,int monthOfYear, int dayOfMonth){
        this.birthDate = new StudentBirthDate(year, monthOfYear, dayOfMonth);
        this.dataBirthdayEditText.setText(getBirthdayFormat(dayOfMonth,monthOfYear,year));
    }

    public void injectStudentToForm(Student student){
        this.nameEt.setText(student.name);
        this.idEt.setText(student.id);
        this.phoneEt.setText(student.phone);
        this.addressEt.setText(student.address);
        this.checkedCB.setChecked(student.cb);
        this.dataBirthdayEditText.setText(getBirthdayFormat(student.birthDate.day,student.birthDate.mouth,student.birthDate.year));
        this.dataBirthTimeEditText.setText(getBirtTimeFormat(student.birthTime.hour,student.birthTime.minute));
    }

    public Student getStudent(){
        String name = this.nameEt.getText().toString();
        String id = this.idEt.getText().toString();
        String phone = this.phoneEt.getText().toString();
        String address = this.addressEt.getText().toString();
        Boolean checked = this.checkedCB.isChecked();
        return new Student(name, id, phone, address, checked, this.birthDate, this.birthTime);
    }

    public String getBirthdayFormat(int day,int mount,int year){
        return "" + day + "/" + (mount + 1) +  "/" + year;
    }

    public String getBirtTimeFormat(int hour, int minute){
        return "" + hour + ":" + minute;
    }
}
