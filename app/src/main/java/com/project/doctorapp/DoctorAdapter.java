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

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DoctorNotices> notes;
    DoctorAdapter myAdapter;
    SwipeRefreshLayout swiperefreshlayout;

    public DoctorAdapter(Context context,ArrayList<DoctorNotices> notes){
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dnoteitem, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        DoctorNotices notes = this.notes.get(position);
        myViewHolder.name.setText(notes.getName());
        myViewHolder.note.setText(notes.getNote());
        myViewHolder.date.setText(notes.getDate());

        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setMessage("Are you sure you want to delete?");
                alert.setCancelable(false);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DoctorNotices").child(notes.getId());
                        databaseReference.removeValue();
                        Toast.makeText(context,"Note Deleted! "+notes.getName(),Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), DoctorNoticesView.class);
                        v.getContext().startActivity(i);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.create().show();
            }
            private void finish() {
            }
        });

        myViewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditDoctorNotice.class);
                i.putExtra("id",notes.getId());
                i.putExtra("name",notes.getName());
                i.putExtra("note",notes.getNote());
                i.putExtra("date",notes.getDate());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, note, date;
        ImageButton btnEdit,btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            note = itemView.findViewById(R.id.note_text);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            date = itemView.findViewById(R.id.date_text);

        }
    }
}