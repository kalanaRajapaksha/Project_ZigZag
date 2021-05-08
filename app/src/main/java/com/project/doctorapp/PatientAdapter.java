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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<com.project.doctorapp.PatientAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Patient> patient1;
    public PatientAdapter(Context context,ArrayList<Patient> patient1){
        this.context = context;
        this.patient1 = patient1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Patient patient1 = this.patient1.get(position);
        myViewHolder.patientEmail.setText(patient1.getPatientName());

        myViewHolder.del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                alertDlg.setMessage("Are you sure?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Patient").child(patient1.getPatientName());
                        databaseReference.removeValue();
                        Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), com.project.doctorapp.DoctorViewActivity.class);
                        v.getContext().startActivity(i);
                    }
                });
                alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDlg.create().show();
            }
        });

//        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(),DoctorViewActivity.class);
//                v.getContext().startActivity(i);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return patient1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView patientEmail;
        ImageButton del1;
//        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patientEmail = itemView.findViewById(R.id.patientName);
            del1 = itemView.findViewById(R.id.delBtn3);
//            card = itemView.findViewById(R.id.cardView);
        }
    }
}
