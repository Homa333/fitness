package com.consult.fitness;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupActivity extends AppCompatActivity {

    private TextView txtalready;
    private EditText txt_email, txt_pass, txt_fname, txt_lname, txt_age;
    private Spinner spin_country;
    private Button btn_signup;
    private static String id;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

        clickAbleSpan();
        DatabaseHelper db = new DatabaseHelper(this);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Cursor cursor = db.allData("login_table");
//                if(cursor.getCount()!=0){
//                    while (cursor.moveToNext()){
//                        Log.e("ok", "onClick: "+cursor.getString(0));
//                        Log.e("ok", "onClick: "+cursor.getString(1));
//                        Log.e("ok", "onClick: "+cursor.getString(2));
//                    }
//                }

                String email = txt_email.getText().toString().replaceAll("\\s", "");
                String password = txt_pass.getText().toString().replaceAll("\\s", "");
                String name = txt_fname.getText().toString().replaceAll("\\s", "") + " " + txt_lname.getText().toString().replaceAll("\\s", "");
                String age = txt_age.getText().toString().replaceAll("\\s", "").replaceAll("[^\\d.]", "");
                String country = spin_country.getSelectedItem().toString().replaceAll("\\s", "");
                String gender = "M";


                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
                String datetime = myDateObj.format(myFormatObj);
                id = "FIT"+datetime;

                if(name.length() == 0|| email.length() == 0|| password.length() == 0|| age.length() == 0){
                    Toast.makeText(SignupActivity.this, "Data incomplete", Toast.LENGTH_LONG).show();
                }
                else if(db.CheckIsDataAlreadyInDBorNot("login_table", "email", email)){
                    Toast.makeText(SignupActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                }
                else{
                    if(db.insertlogin(id, email, password) & db.insertUser(id, name, age, country, gender)){
                        finishAffinity();
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }

    void initViews() {

        btn_signup = findViewById(R.id.btn_signup);
        txt_email = findViewById(R.id.txt_email);
        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_age = findViewById(R.id.txt_age);
        txt_pass = findViewById(R.id.txt_pass);
        txtalready = findViewById(R.id.txt_already);
        spin_country = findViewById(R.id.spin_country);
    }

    void clickAbleSpan() {
        String text = "Already a Member? Sign in. ";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        };

        ss.setSpan(clickableSpan, 18, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtalready.setText(ss);

        txtalready.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(i);
    }
}