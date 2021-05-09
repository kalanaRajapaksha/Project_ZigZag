package com.project.doctorapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class PatientNoticesAdapter extends RecyclerView.Adapter<PatientNoticesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DoctorNote> notes;
    PatientNoticesAdapter myAdapter;
    SwipeRefreshLayout swiperefreshlayout;

    public PatientNoticesAdapter(Context context,ArrayList<DoctorNote> notes){
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pnotices_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        DoctorNote notes = this.notes.get(position);
        myViewHolder.name.setText(notes.getName());
        myViewHolder.note.setText(notes.getNote());
        myViewHolder.date.setText(notes.getDate());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, note, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            note = itemView.findViewById(R.id.note_text);
            date = itemView.findViewById(R.id.date_text);

        }
    }
}