package com.consult.fitness;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountSettingsActivity extends AppCompatActivity {

    private TextView txt_name, txt_password, txt_age;
    private EditText txt_name_edit, txt_pass_edit, txt_age_edit;
    private Button btn_name_edit, btn_pass_edit, btn_age_edit;
    private String id, name, password, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

        id = getIntent().getStringExtra("id");

        DatabaseHelper db = new DatabaseHelper(this);
        String query = "SELECT u.name, l.password, u.age FROM user_profile u, login_table l WHERE u.id  = \'" + id + "\' and u.id=l.id";
        Cursor cursor = db.alldata(query);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0);
                    password = cursor.getString(1);
                    age = cursor.getString(2);
                } while (cursor.moveToNext());
            }

        }
        txt_name.setText(name);
        txt_password.setText(password);
        txt_age.setText(age);

        btn_name_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_name_edit.getText().toString().equals("Edit")) {

                    btn_name_edit.setText("Done");
                    txt_name.setVisibility(View.INVISIBLE);
                    txt_name_edit.setVisibility(View.VISIBLE);

                } else if (btn_name_edit.getText().toString().equals("Done")) {
                    btn_name_edit.setText("Edit");
                    String name = txt_name_edit.getText().toString();
                    txt_name.setVisibility(View.VISIBLE);
                    txt_name_edit.setVisibility(View.INVISIBLE);
                }

            }
        });

        btn_pass_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_pass_edit.getText().toString().equals("Edit")) {

                    btn_pass_edit.setText("Done");
                    txt_password.setVisibility(View.INVISIBLE);
                    txt_pass_edit.setVisibility(View.VISIBLE);

                } else if (btn_pass_edit.getText().toString().equals("Done")) {

                    btn_pass_edit.setText("Edit");
                    String pass = txt_pass_edit.getText().toString();
                    txt_password.setVisibility(View.VISIBLE);
                    txt_pass_edit.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_age_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_age_edit.getText().toString().equals("Edit")) {

                    btn_age_edit.setText("Done");
                    txt_age.setVisibility(View.INVISIBLE);
                    txt_age_edit.setVisibility(View.VISIBLE);

                } else if (btn_age_edit.getText().toString().equals("Done")) {

                    btn_age_edit.setText("Edit");
                    String age = txt_age_edit.getText().toString();
                    txt_age.setVisibility(View.VISIBLE);
                    txt_age_edit.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    void initViews() {
        txt_name = findViewById(R.id.txt_name);
        txt_password = findViewById(R.id.txt_password);
        txt_age = findViewById(R.id.txt_age);
        txt_name_edit = findViewById(R.id.txt_name_edit);
        txt_pass_edit = findViewById(R.id.txt_pass_edit);
        txt_age_edit = findViewById(R.id.txt_age_edit);
        btn_name_edit = findViewById(R.id.btn_name_edit);
        btn_pass_edit = findViewById(R.id.btn_pass_edit);
        btn_age_edit = findViewById(R.id.btn_age_edit);
    }
}