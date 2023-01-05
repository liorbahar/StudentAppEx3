package com.example.studentsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class EditStudentFragment extends AddStudentFragment {
    private Student student;
    private int studentPosition;
    public StudentDetailsForm studentDetailsForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        this.studentDetailsForm = new StudentDetailsForm(view,getContext());

        Button saveBtn = view.findViewById(R.id.editstudent_save_btn);
        Button deleteBtn = view.findViewById(R.id.editstudent_delete_btn);
        Button cancelBtn = view.findViewById(R.id.editstudent_cancel_btn);

        this.studentPosition  = ViewStudentFragmentArgs.fromBundle(getArguments()).getPosition();
        List<Student> data = Model.instance().getAllStudents();
        this.student = data.get(this.studentPosition);

        this.studentDetailsForm.injectStudentToForm(student);

        saveBtn.setOnClickListener(view1 -> this.saveStudent(view1));
        deleteBtn.setOnClickListener(view1 -> this.removeStudent(view1));
        cancelBtn.setOnClickListener(view1 -> Navigation.findNavController(view1).popBackStack());

        setHasOptionsMenu(true);
        return view;
    }

    public void saveStudent(View view){
        this.student.name = this.studentDetailsForm.nameEt.getText().toString();
        this.student.id = this.studentDetailsForm.idEt.getText().toString();
        this.student.phone = this.studentDetailsForm.phoneEt.getText().toString();
        this.student.address = this.studentDetailsForm.addressEt.getText().toString();
        this.student.cb = this.studentDetailsForm.checkedCB.isChecked();
        this.student.birthDate = this.studentDetailsForm.birthDate;
        this.student.birthTime = this.studentDetailsForm.birthTime;

        Model.instance().editStudent(this.student);

        DialogFragment newFragment = new SuccedDialogFragment().newInstance("the student was successfully edit");
        newFragment.show(getFragmentManager(), "dialog");

        Navigation.findNavController(view).popBackStack();
    }

    public void removeStudent(View view){
        Model.instance().removeStudent(this.student);
        Navigation.findNavController(view).popBackStack(R.id.studentsListFragment, false);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
    }


}