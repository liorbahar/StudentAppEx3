package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentsListFragment extends Fragment {
    List<Student> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students_list, container, false);
        data = Model.instance().getAllStudents();
        RecyclerView list = view.findViewById(R.id.recyclerView);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(getLayoutInflater(), data);
        list.setAdapter(adapter);
        adapter.setOnItemClickListener(new StudentRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.d("TAG", "Row was clicked " + pos);
                StudentsListFragmentDirections.ActionStudentsListFragmentToViewStudentFragment action = StudentsListFragmentDirections.actionStudentsListFragmentToViewStudentFragment(pos);
                Navigation.findNavController(view).navigate(action);
            }
        });

        NavDirections action = StudentsListFragmentDirections.actionGlobalAddStudentFragment();
        Navigation.createNavigateOnClickListener(action);

        return view;
    }
}