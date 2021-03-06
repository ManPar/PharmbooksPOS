package Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.pharmbooks.pharmbookspos.PrescriptionRefillActivity;
import com.pharmbooks.pharmbookspos.R;

import java.util.ArrayList;

import Model.RefillModel;

/**
 * Created by hp on 28-Aug-17.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private ArrayList<RefillModel> refillList;


    public TestAdapter(ArrayList<RefillModel> refillList) {
        this.refillList = refillList;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView medName;
        public TextView medDose;
        public TextView lastRefill;
        public TextView endDate;
        public LinearLayout base;
        public CheckBox checkBox;
        public MyViewHolder(View view) {
            super(view);
            medName = (TextView) view.findViewById(R.id.med_name);
            medDose= (TextView) view.findViewById(R.id.med_dose);
            lastRefill= (TextView) view.findViewById(R.id.med_last_refill);
            endDate= (TextView) view.findViewById(R.id.med_end);
            base=(LinearLayout)view.findViewById(R.id.refill_list_row_id);
            checkBox=(CheckBox)view.findViewById(R.id.checkboxitem);

        }
    }


    @Override
    public TestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.refill_list_row, parent, false);

        return new TestAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TestAdapter.MyViewHolder holder, int position) {


        final RefillModel customerDetail = refillList.get(position);
        holder.medName.setText(customerDetail.getMedName());
        holder.medDose.setText(customerDetail.getDosage());
        holder.lastRefill.setText(customerDetail.getRefillDate());
        holder.endDate.setText(customerDetail.getEndDate());
        holder.checkBox.setChecked(customerDetail.getCheck());
        Log.d("Test tag",customerDetail.getMedName());
//            holder.checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(!(customerDetail.getCheck())){
//                        customerDetail.setCheck(true);
//                    }
//                    else{
//                        customerDetail.setCheck(false);
//                    }
//                }
//            });
        if(PrescriptionRefillActivity.LONG_CLICK_FLAG==0){
            holder.checkBox.setVisibility(View.GONE);


        }else{
            holder.checkBox.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount()
    {
        return refillList.size();
    }



}