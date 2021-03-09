package com.consult.fitness;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private Button btn_acc;
    private String id;
    private Bundle bundle1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Settings");
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        btn_acc = (Button) view.findViewById(R.id.btn_acc);



        Bundle bundle = this.getArguments();
        if (bundle != null){
            id = bundle.getString("id");
        }
        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountSettingsActivity.class);
                bundle1 = new Bundle();
                bundle1.putString("id", id);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });


        return view;
    }
}
