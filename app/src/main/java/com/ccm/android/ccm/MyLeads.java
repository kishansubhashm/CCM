package com.ccm.android.ccm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class MyLeads extends Fragment {
    private View rootView;
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_my_leads, container, false);
// Find the ListView resource.
        mainListView = (ListView) rootView.findViewById(R.id.mainListView);

        // Create and populate a List of planet names.
        String[] planets = new String[] { "Willam Butler", "John K Smith", "Ian S Butler", "Robstark",
                "Serdavos", "Johnny Paul", "Pop", "Martyn","Williamson", "Soda Stephenson", "Richardson", "Max",
                "Serdavos", "Johnny Paul", "Tyrion Lannister"};

        Integer[] imageId = {
                R.drawable.online_final,
                R.drawable.temperory_final,
                R.drawable.online_final,
                R.drawable.offline_final,
                R.drawable.online_final,
                R.drawable.offline_final,
                R.drawable.temperory_final,
                R.drawable.online_final,
                R.drawable.online_final,
                R.drawable.temperory_final,
                R.drawable.online_final,
                R.drawable.offline_final,
                R.drawable.temperory_final,
                R.drawable.online_final,
                R.drawable.temperory_final,
                R.drawable.online_final

        };
        CustomList adapter = new
                CustomList(getActivity(), planets, imageId);
        mainListView =(ListView)rootView.findViewById(R.id.mainListView);
        mainListView .setAdapter(adapter);
        mainListView .setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

        return rootView;
    }
}
