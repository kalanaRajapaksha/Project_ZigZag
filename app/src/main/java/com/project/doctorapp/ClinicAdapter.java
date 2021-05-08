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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ClinicAdapter extends RecyclerView.Adapter<com.project.doctorapp.ClinicAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Clinic> clinic1;
    public ClinicAdapter(Context context,ArrayList<Clinic> clinic1){
        this.context = context;
        this.clinic1 = clinic1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clinic_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Clinic clinic1 = this.clinic1.get(position);
        myViewHolder.clinicName.setText(clinic1.getClinicName());
        myViewHolder.docName.setText(clinic1.getDocName());
        myViewHolder.hosName.setText(clinic1.getHospName());
        myViewHolder.dtTime.setText(clinic1.getDateTime());
            myViewHolder.del1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                  alertDlg.setMessage("Are you sure?");
                  alertDlg.setCancelable(false);
                  alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Clinic Data").child(clinic1.getId());
                          databaseReference.removeValue();
                          Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT).show();
                          Intent i = new Intent(v.getContext(), com.project.doctorapp.Doc_dashboard.class);
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
        myViewHolder.editBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),EditClinicActivity.class);
                i.putExtra("id",clinic1.getId());
                i.putExtra("clinicName",clinic1.getClinicName());
                i.putExtra("docName",clinic1.getDocName());
                i.putExtra("hospName",clinic1.getHospName());
                i.putExtra("desc",clinic1.getDesc());
                i.putExtra("dateTime",clinic1.getDateTime());
                v.getContext().startActivity(i);
            }
        });
        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),DoctorViewActivity.class);
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
        ImageButton del1,editBtn1;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clinicName= itemView.findViewById(R.id.patientName);
            hosName= itemView.findViewById(R.id.hosName);
            docName= itemView.findViewById(R.id.doctorName);
            dtTime = itemView.findViewById(R.id.dtTime);
            del1 = itemView.findViewById(R.id.delBtn3);
            editBtn1 = itemView.findViewById(R.id.editBtn3);
            card = itemView.findViewById(R.id.cardView);
        }
    }
}
