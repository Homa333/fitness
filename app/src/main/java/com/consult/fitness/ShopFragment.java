package com.consult.fitness;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    private RecyclerView view_cate;
    private String id ="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Category");
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        view_cate = view.findViewById(R.id.view_cate);

        Bundle bundle = this.getArguments();
        if (bundle != null){
            id = bundle.getString("id");
        }

        ArrayList<CategoryShop> categoryShops = new ArrayList<>();
        categoryShops.add(new CategoryShop("Shoes", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));
        categoryShops.add(new CategoryShop("Accessories", "https://images.pexels.com/photos/19090/pexels-photo.jpg"));


        CategoryRecViewAdapter adapter = new CategoryRecViewAdapter(getContext());
        adapter.setCategoryShops(categoryShops);

        view_cate.setLayoutManager(new LinearLayoutManager(getContext()));
        view_cate.setAdapter(adapter);

        return view;
    }


}
