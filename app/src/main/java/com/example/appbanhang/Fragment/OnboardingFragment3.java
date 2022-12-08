package com.example.appbanhang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.appbanhang.Activity.DangkiActivity;
import com.example.appbanhang.Activity.LoginActivity;
import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.R;


public class OnboardingFragment3 extends Fragment {

    private Button btngetstart;
    private View view;

    public OnboardingFragment3(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_onboarding3xml, container, false);

        btngetstart = view.findViewById(R.id.btn_get_start);

        btngetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;

    }
}