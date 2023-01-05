package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_recycler_list);
//
//        data = Model.instance().getAllStudents();
//        RecyclerView list = findViewById(R.id.studentrecycler_list);
//        list.setHasFixedSize(true);
//
//
//        list.setLayoutManager(new LinearLayoutManager(this));
//        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter();
//        list.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view,  int pos) {
//            }
//        });
//    }

class StudentViewHolder extends RecyclerView.ViewHolder {
    TextView nameTv;
    TextView idTv;
    CheckBox cb;
    List<Student> data;

    public StudentViewHolder(@NonNull View itemView, StudentRecyclerAdapter.OnItemClickListener listener, List<Student> data) {
        super(itemView);
        this.data = data;
        nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
        idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
        cb = itemView.findViewById(R.id.studentlistrow_cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) cb.getTag();
                Student st = data.get(pos);
                st.cb = cb.isChecked();
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                listener.onItemClick(pos);
            }
        });
    }

    public void bind(Student st, int pos) {
        nameTv.setText(st.name);
        idTv.setText(st.id);
        cb.setChecked(st.cb);
        cb.setTag(pos);
    }
}

class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    OnItemClickListener listener;

    public static interface OnItemClickListener {
        void onItemClick(int pos);
    }

    LayoutInflater inflater;
    List<Student> data;

    public StudentRecyclerAdapter(LayoutInflater inflater, List<Student> data) {
        this.inflater = inflater;
        this.data = data;
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.student_list_row, parent, false);
        return new StudentViewHolder(view, listener, data);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student st = data.get(position);
        holder.bind(st, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
