package Adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.pharmbooks.pharmbookspos.CustomerPrescription;
import com.pharmbooks.pharmbookspos.R;

import java.util.ArrayList;

import Model.PresciptionModel;

/**
 * Created by hp on 28-Aug-17.
 */

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.MyViewHolder> {

    private ArrayList<PresciptionModel> presciptionList;


    public PrescriptionAdapter(ArrayList<PresciptionModel> presciptionList) {
        this.presciptionList = presciptionList;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView medName;
        public TextView medDose;
        public TextView lastRefill;
        public TextView endDate;
        public LinearLayout base;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            medName = (TextView) view.findViewById(R.id.med_name);
            medDose= (TextView) view.findViewById(R.id.med_dose);
            lastRefill= (TextView) view.findViewById(R.id.med_last_refill);
            endDate= (TextView) view.findViewById(R.id.med_end);
            base=(LinearLayout)view.findViewById(R.id.pres_list_row);
            cardView = (CardView)view.findViewById(R.id.card);
        }
    }


    @Override
    public PrescriptionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_prescription_list_row, parent, false);

        return new PrescriptionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrescriptionAdapter.MyViewHolder holder, int position) {


        PresciptionModel customerDetail = presciptionList.get(position);
        holder.medName.setText(customerDetail.getMedName());
        holder.medDose.setText(customerDetail.getDosage());
        holder.lastRefill.setText(customerDetail.getRefillDate());
        holder.endDate.setText(customerDetail.getEndDate());
        if(CustomerPrescription.LONG_CLICK_FLAG==1){
            if(CustomerPrescription.pos==position){
                holder.base.setBackgroundColor(Color.parseColor("#BBDEFB"));
                holder.cardView.setCardBackgroundColor(Color.parseColor("#BBDEFB"));
            }
        }else{
            holder.base.setBackgroundColor(Color.TRANSPARENT);
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }


    }

    @Override
    public int getItemCount()
    {
        return presciptionList.size();
    }



}