package com.example.studentsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;
import androidx.navigation.Navigation;

public class AddStudentFragment extends Fragment {
    public Button saveStudentBtn;
    public Button cancelBtn;
    public StudentDetailsForm studentDetailsForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        this.studentDetailsForm = new StudentDetailsForm(view,getContext());
        this.saveStudentBtn = view.findViewById(R.id.addstudent_save_btn);
        this.cancelBtn = view.findViewById(R.id.addstudent_cancell_btn);
        this.saveStudentBtn.setOnClickListener(view1 -> this.onSaveStudentClick(view));

        cancelBtn.setOnClickListener(view1 -> Navigation.findNavController(view1).popBackStack(R.id.studentsListFragment, false));
        setHasOptionsMenu(true);

        return view;
    }

    public void onSaveStudentClick(View view){
        Student newStudent = this.studentDetailsForm.getStudent();
        Model.instance().addStudent(newStudent);

        DialogFragment successDialog = new SuccedDialogFragment().newInstance("add new student");
        successDialog.show(getFragmentManager(), "dialog");

        Navigation.findNavController(view).popBackStack();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
    }

}