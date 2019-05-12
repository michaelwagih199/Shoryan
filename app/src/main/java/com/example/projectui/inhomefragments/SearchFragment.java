package com.example.projectui.inhomefragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.projectui.DonnersLists;
import com.example.projectui.DonorsMap;
import com.example.projectui.R;
import com.example.projectui.base.BaseFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {



    public SearchFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        // Create request to get image from filesystem when button clicked
        view.findViewById(R.id.iv__menus)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Intent i = new Intent(getContext(), DonnersLists.class);
                       startActivity(i);

                    }
                });
        view.findViewById(R.id.iv_map)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), DonorsMap.class);
                        startActivity(i);

                    }
                });


        return view;
    }



}
