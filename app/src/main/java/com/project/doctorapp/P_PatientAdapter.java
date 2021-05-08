package com.project.doctorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class P_PatientAdapter extends RecyclerView.Adapter<com.project.doctorapp.P_PatientAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<Patient> patient1;
    public P_PatientAdapter(Context context, ArrayList<Patient> patient1){
        this.context = context;
        this.patient1 = patient1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.p_patient_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Patient patient1 = this.patient1.get(position);
        myViewHolder.p_patientName.setText(patient1.getPatientName());




    }

    @Override
    public int getItemCount() {
        return patient1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView p_patientName;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            p_patientName= itemView.findViewById(R.id.p_patientName);




        }
    }
}
