package com.example.studentsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SuccedDialogFragment extends DialogFragment {
    public static SuccedDialogFragment newInstance(String content) {
        SuccedDialogFragment frag = new SuccedDialogFragment();
        Bundle args = new Bundle();
        args.putString("content", content);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        String content = getArguments().getString("content");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("operation was completed successfully!!!");
        builder.setMessage(content);
        builder.setPositiveButton("OK", (DialogInterface dialogInterface, int i)->{
            Toast.makeText(getContext(),content,Toast.LENGTH_LONG).show();
        });
        return builder.create();
    }
}