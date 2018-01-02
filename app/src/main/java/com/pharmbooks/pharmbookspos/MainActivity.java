package com.pharmbooks.pharmbookspos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import Model.MedicineDetailModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Declaration of icons
    ImageView createInvoice,invoiceHistory,transactionalMessage,refilling,loyalty,customerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createInvoice = (ImageView)findViewById(R.id.createInvoice);
        invoiceHistory = (ImageView)findViewById(R.id.invoiceHistory);
        transactionalMessage = (ImageView)findViewById(R.id.transactional);
        refilling = (ImageView)findViewById(R.id.refilling);
        loyalty = (ImageView)findViewById(R.id.loyalty);
        customerInfo = (ImageView)findViewById(R.id.customerInfo);

        createInvoice.setOnClickListener(this);
        invoiceHistory.setOnClickListener(this);
        transactionalMessage.setOnClickListener(this);
        refilling.setOnClickListener(this);
        loyalty.setOnClickListener(this);
        customerInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view==createInvoice){
            startActivity(new Intent(MainActivity.this,CreateInvoice.class));
        }
        else if(view == invoiceHistory){

        }
        else if(view == transactionalMessage){
            startActivity(new Intent(MainActivity.this,TransactionalMessage.class));
        }
        else if(view == refilling){
            startActivity(new Intent(MainActivity.this,RefillListActivity.class));
        }
        else if(view == loyalty){

        }
        else if(view == customerInfo){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            // Get the layout inflater
            LayoutInflater linf = LayoutInflater.from(getApplicationContext());
            final View inflator = linf.inflate(R.layout.customer_selection, null);
            alertDialog.setView(inflator);
            LinearLayout existing,newCustomer;

            existing = (LinearLayout)inflator.findViewById(R.id.existing);
            newCustomer = (LinearLayout) inflator.findViewById(R.id.newCustomer);
            existing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,PrescriptionListActivity.class));
                }
            });
            newCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,CustomerDetail.class));
                }
            });
            alertDialog.show();
        }
    }
}
