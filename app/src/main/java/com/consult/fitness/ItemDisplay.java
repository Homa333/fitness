package com.consult.fitness;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemDisplay extends AppCompatActivity {
    RecyclerView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_display);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        view1 = findViewById(R.id.view);

        ArrayList<items> itemsA = new ArrayList<>();
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        itemsA.add(new items("Nike", "200", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));

        ItemRecViewAdapter adapter = new ItemRecViewAdapter(this);
        adapter.setItems(itemsA);

        view1.setLayoutManager(new LinearLayoutManager(this));
        view1.setAdapter(adapter);
    }
}