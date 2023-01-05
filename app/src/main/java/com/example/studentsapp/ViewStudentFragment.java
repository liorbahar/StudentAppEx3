package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class ViewStudentFragment extends Fragment  {
    private int studentPosition;
    public StudentDetailsForm studentDetailsForm;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_view_student, container, false);
        this.initForm();
        return view;
    }

    private void initForm(){
        this.studentDetailsForm = new StudentDetailsForm(view,getContext());
        studentPosition = ViewStudentFragmentArgs.fromBundle(getArguments()).getPosition();

        this.studentDetailsForm.nameEt.setFocusable(false);
        this.studentDetailsForm.nameEt.setEnabled(false);
        this.studentDetailsForm.nameEt.setCursorVisible(false);
        this.studentDetailsForm.nameEt.setKeyListener(null);
        this.studentDetailsForm.nameEt.setBackgroundColor(Color.TRANSPARENT);

        this.studentDetailsForm.idEt.setFocusable(false);
        this.studentDetailsForm.idEt.setEnabled(false);
        this.studentDetailsForm.idEt.setCursorVisible(false);
        this.studentDetailsForm.idEt.setKeyListener(null);
        this.studentDetailsForm.idEt.setBackgroundColor(Color.TRANSPARENT);

        this.studentDetailsForm.addressEt.setFocusable(false);
        this.studentDetailsForm.addressEt.setEnabled(false);
        this.studentDetailsForm.addressEt.setCursorVisible(false);
        this.studentDetailsForm.addressEt.setKeyListener(null);
        this.studentDetailsForm.addressEt.setBackgroundColor(Color.TRANSPARENT);

        this.studentDetailsForm.phoneEt.setFocusable(false);
        this.studentDetailsForm.phoneEt.setEnabled(false);
        this.studentDetailsForm.phoneEt.setCursorVisible(false);
        this.studentDetailsForm.phoneEt.setKeyListener(null);
        this.studentDetailsForm.phoneEt.setBackgroundColor(Color.TRANSPARENT);

        this.studentDetailsForm.checkedCB.setEnabled(false);
        this.studentDetailsForm.dataBirthdayEditText.setEnabled(false);
        this.studentDetailsForm.dataBirthTimeEditText.setEnabled(false);

        this.initStudentDetails();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem editStudent= menu.findItem(R.id.editStudent);
        editStudent.setVisible(true);
        MenuItem addStudent= menu.findItem(R.id.addStudentFragment);
        addStudent.setVisible(false);
    }

    public void initStudentDetails() {
        List<Student> data = Model.instance().getAllStudents();
        Student student = data.get(this.studentPosition);
        this.studentDetailsForm.injectStudentToForm(student);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editStudent) {
            ViewStudentFragmentDirections.ActionViewStudentFragmentToEditStudentFragment action = ViewStudentFragmentDirections.actionViewStudentFragmentToEditStudentFragment(studentPosition);
            Navigation.findNavController(view).navigate(action);
        }
        return false;
    }

    @Override
    public void onResume() {
        this.initForm();
        super.onResume();
    }
}