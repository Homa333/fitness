package com.consult.fitness;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView txt_join;
    private TextView txt_email, txt_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

        clickAbleSpan();

        DatabaseHelper db = new DatabaseHelper(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                String id = "";
                String pass = "";
                String name = "";

                if (db.CheckIsDataAlreadyInDBorNot("login_table", "email", email)) {
                    String query = "SELECT password, id FROM login_table WHERE email  = \'" + email + "\' ";
                    Cursor cursor = db.alldata(query);
                    if (cursor != null && cursor.getCount() > 0) {
                        if (cursor.moveToFirst()) {
                            do {
                                pass = cursor.getString(0);
                                id = cursor.getString(1);
                            } while (cursor.moveToNext());
                        }
                        if (password.equals(pass)) {
                            query = "SELECT id, name FROM user_profile WHERE id  = \'" + id + "\' ";
                            cursor = db.alldata(query);
                            if (cursor != null && cursor.getCount() > 0) {
                                if (cursor.moveToFirst()) {
                                    do {
                                        id = cursor.getString(0);
                                        name = cursor.getString(1);
                                    } while (cursor.moveToNext());
                                }

                            }
                            Intent i = new Intent(LoginActivity.this, NavigationDrawer.class);
                            Bundle mBundle = new Bundle();
                            mBundle.putString("id", id);
                            mBundle.putString("name", name);
                            mBundle.putString("email", email);

                            i.putExtras(mBundle);
                            finishAffinity();
                            startActivity(i);

                        }else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "User not Found", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void initViews() {
        txt_join = findViewById(R.id.txt_join);
        btn_login = findViewById(R.id.btnlogin);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_pass);

    }

    void clickAbleSpan() {
        String text = "Not a Member? Join Us. ";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        };

        ss.setSpan(clickableSpan, 14, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_join.setText(ss);

        txt_join.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}