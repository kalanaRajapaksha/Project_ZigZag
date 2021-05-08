package com.project.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class P_ClinicAdapter extends RecyclerView.Adapter<com.project.doctorapp.P_ClinicAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<com.project.doctorapp.Clinic> clinic1;
    public P_ClinicAdapter(Context context,ArrayList<com.project.doctorapp.Clinic> clinic1){
        this.context = context;
        this.clinic1 = clinic1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.p_clinic_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        com.project.doctorapp.Clinic clinic1 = this.clinic1.get(position);
        myViewHolder.clinicName.setText(clinic1.getClinicName());
        myViewHolder.docName.setText(clinic1.getDocName());
        myViewHolder.hosName.setText(clinic1.getHospName());
        myViewHolder.dtTime.setText(clinic1.getDateTime());
        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),PatientViewActivity.class);
                i.putExtra("clinicName",clinic1.getClinicName());
                v.getContext().startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return clinic1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView clinicName,hosName,docName,dtTime;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clinicName= itemView.findViewById(R.id.patientName);
            hosName= itemView.findViewById(R.id.hosName);
            docName= itemView.findViewById(R.id.doctorName);
            dtTime = itemView.findViewById(R.id.dtTime);
            card = itemView.findViewById(R.id.cardView1);
        }
    }
}

