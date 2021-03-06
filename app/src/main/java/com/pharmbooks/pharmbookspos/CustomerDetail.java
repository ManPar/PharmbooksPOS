package com.pharmbooks.pharmbookspos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pharmbooks.pharmbookspos.Login.MyPREFERENCES;


public class CustomerDetail extends AppCompatActivity {

    EditText Name;
    EditText MobileNo;
    EditText Address;
    Button Next;
    String Name_var, MobileNo_var, Address_var;
    TextInputLayout txtname,txtmobile,txtaddress;
    int result;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        Name = (EditText)findViewById(R.id.Name);
        MobileNo = (EditText) findViewById(R.id.MobileNo);
        Address = (EditText) findViewById(R.id.Address);
        Next = (Button) findViewById(R.id.Next);
        txtname=  (TextInputLayout)findViewById(R.id.txtInputName);
        txtmobile = (TextInputLayout) findViewById(R.id.txtInputMobile);
        txtaddress = (TextInputLayout) findViewById(R.id.txtInputAddress);

        //Name.addTextChangedListener(new );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView title = (TextView)toolbar.findViewById(R.id.title);
        SharedPreferences sharedpreferences = this.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);

        String restoredText = sharedpreferences.getString("username", null);
        if (restoredText != null) {
            username = sharedpreferences.getString("username", "No name defined");//"No name defined" is the default value.
            Log.d("usernamecheck",username);
        }

       Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!(TextUtils.isEmpty(Name.getText())) && !(TextUtils.isEmpty(MobileNo.getText().toString().trim())) && !(TextUtils.isEmpty(Address.getText().toString().trim())))) {
                    Name_var = Name.getText().toString();
                    MobileNo_var = MobileNo.getText().toString();
                    Address_var = Address.getText().toString();


                    Pattern p = Pattern.compile("\\d{10}");
                    Matcher m = p.matcher(MobileNo_var);
                    boolean b = m.matches();
                    if (b) {
                        //Toast.makeText(CustomerDetail.this, "10 digits bingo", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerDetail.this, MedicineData.class);
                        intent.putExtra("Name", String.valueOf(Name_var));
                        intent.putExtra("MobileNo", String.valueOf(MobileNo_var));
                        intent.putExtra("Address", String.valueOf(Address_var));
                        Log.d("mytag", "b is true");
                        //Intent i = new Intent(getApplicationContext(),MedicineData.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CustomerDetail.this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                        Log.d("mytag", "b is false");
                    }

                    //sendR(Name_var, MobileNo_var, Address_var);
                } else {
                    Toast.makeText(CustomerDetail.this, "Please Enter Some Values!!!", Toast.LENGTH_SHORT).show();
                }
            }
       });

        Address.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_GO){
                    NextFunction();
                    return true;
                }
                else{
                    return false;
                }
            }
        });
    }

    public void NextFunction(){
        Name_var = Name.getText().toString();
        MobileNo_var = MobileNo.getText().toString();
        Address_var = Address.getText().toString();

        Pattern p = Pattern.compile("\\d{10}");
        Matcher m = p.matcher(MobileNo_var);
        boolean b = m.matches();
        if(b){
            //Toast.makeText(CustomerDetail.this,"10 digits bingo",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CustomerDetail.this,MedicineData.class);
            intent.putExtra("Name", String.valueOf(Name_var));
            intent.putExtra("MobileNo", String.valueOf(MobileNo_var));
            intent.putExtra("Address", String.valueOf(Address_var));
            Log.d("mytag","b is true");
            startActivity(intent);
        }
        else {
            Toast.makeText(CustomerDetail.this, "Please enter valid number", Toast.LENGTH_SHORT).show();
            Log.d("mytag","b is false");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        ActionBar actionBar = getSupportActionBar();;
        actionBar.setDisplayHomeAsUpEnabled(true);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customerdetailactionbar, menu);
        return true;
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