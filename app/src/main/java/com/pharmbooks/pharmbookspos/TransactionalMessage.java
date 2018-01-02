package com.pharmbooks.pharmbookspos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.pharmbooks.pharmbookspos.Login.MyPREFERENCES;


public class TransactionalMessage extends AppCompatActivity {

    EditText Name;
    EditText MobileNo;
    EditText BillAmount;
    Button Next;
    String Name_var, MobileNo_var, BillAmount_var;
    int result;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactional_message);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        String restoredText = sharedpreferences.getString("username", null);
        if (restoredText != null) {
            username = sharedpreferences.getString("username", "No name defined");//"No name defined" is the default value.
        }
        Name = (EditText) findViewById(R.id.Name);
        MobileNo = (EditText) findViewById(R.id.MobileNo);
        BillAmount = (EditText) findViewById(R.id.BillAmount);
        Next = (Button) findViewById(R.id.Next);
        getSupportActionBar().setTitle("Transactional Messages");

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((!(TextUtils.isEmpty(Name.getText())) && !(TextUtils.isEmpty(MobileNo.getText().toString().trim()))&& !(TextUtils.isEmpty(BillAmount.getText().toString().trim()) ))) {
                    Name_var = Name.getText().toString();
                    MobileNo_var = MobileNo.getText().toString();
                    BillAmount_var = BillAmount.getText().toString();
                    //Toast.makeText(getActivity().getApplicationContext(), username, Toast.LENGTH_LONG).show();
                    sendR(Name_var, MobileNo_var, BillAmount_var);
                    // Sending the sms here
                    SendMsg sendMsg = new SendMsg(Name_var,MobileNo_var,BillAmount_var);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Enter Some Values!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void sendR(final String Name_var, final String MobileNo_var, final String BillAmount_var){


        String url = "https://pharmcrm.herokuapp.com/api/transactional/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String msg = null;
                        try {
                            JSONObject object = new JSONObject(response);
                            msg = object.getString("msg");
                            result = object.getInt("res");
                            if(result == 1)
                            {

                                //SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                //SharedPreferences.Editor editor = sharedpreferences.edit();

                                //editor.putString("Name", String.valueOf(Name));
                                //editor.commit();

                                //Intent i = new Intent(getApplicationContext(),MedicineData.class);
                                //startActivity(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getActivity().getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        //Toast.makeText(getActivity().getApplicationContext(),msg+""+ result +"",Toast.LENGTH_LONG).show();


                        //Dashboard switcing from here
                        //MainActivity.LOAD_FRAG_TAG="";
                        finish();
                        startActivity(new Intent(TransactionalMessage.this,TransactionalMessage.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Something went wrong!\nCheck your Internet connection and try again..", Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String,String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("name",Name_var);
                params.put("number",MobileNo_var);
                params.put("amount",BillAmount_var);
                params.put("chemist",username);

                return params;
            }

        };

        int MY_SOCKET_TIMEOUT_MS = 50000;
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}