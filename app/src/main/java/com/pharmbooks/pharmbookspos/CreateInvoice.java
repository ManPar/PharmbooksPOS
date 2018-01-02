package com.pharmbooks.pharmbookspos;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hp on 1/1/2018.
 */
public class CreateInvoice extends AppCompatActivity{

    EditText name,phone,doctor,address;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_invoice);
        name = (EditText)findViewById(R.id.Name);
        phone = (EditText)findViewById(R.id.MobileNo);
        doctor = (EditText)findViewById(R.id.doctor);
        address = (EditText)findViewById(R.id.address);
        next = (Button)findViewById(R.id.createInvoicebtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Name cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phone.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Phone Number cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(CreateInvoice.this,InvoiceDetails.class);
                    i.putExtra("CustomerName",name.getText().toString().trim());
                    i.putExtra("CustomerPhone",phone.getText().toString().trim());
                    i.putExtra("CustomerAddress",address.getText().toString().trim());
                    i.putExtra("DoctorName",doctor.getText().toString().trim());
                    startActivity(i);
                }
            }
        });
    }
}
