package com.consult.fitness;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlaceOrder extends AppCompatActivity {

    private TextView item_name, item_price, txt_total;
    private Button btn_order;
    private EditText txt_addr1, txt_addr2, txt_city, txt_state, txt_country;
    String addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

        String item_name1 = getIntent().getStringExtra("item_name");
        String item_price1 = getIntent().getStringExtra("item_price");

        item_name.setText(item_name1);
        item_price.setText(item_price1);
        txt_total.setText(item_price1);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((txt_addr1.getText().toString().isEmpty()||txt_addr2.getText().toString()
                        .isEmpty())&&txt_city.getText().toString().isEmpty()&&txt_state.getText()
                        .toString().isEmpty()&&txt_country.getText().toString().isEmpty()){
                    Toast.makeText(PlaceOrder.this, "Provide full address", Toast
                            .LENGTH_LONG).show();
                }else {
                    addr = txt_addr1.getText().toString()+","+txt_addr2.getText().toString()
                            +","+txt_city.getText().toString()+","+txt_state.getText().toString()
                            +","+txt_country.getText().toString();

                }


            }
        });


    }

    void initViews() {
        item_name = findViewById(R.id.txt_name);
        item_price = findViewById(R.id.txt_price);
        btn_order = findViewById(R.id.btn_order);
        txt_addr1 = findViewById(R.id.txt_addr1);
        txt_addr2 = findViewById(R.id.txt_addr2);
        txt_city = findViewById(R.id.txt_city);
        txt_state = findViewById(R.id.txt_state);
        txt_country = findViewById(R.id.txt_country);

    }
}