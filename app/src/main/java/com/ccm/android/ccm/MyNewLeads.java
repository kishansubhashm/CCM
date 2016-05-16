package com.ccm.android.ccm;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KishanSubhashMiryala on 5/16/2016.
 */
public class MyNewLeads extends Fragment {

    private View root_view;
    private static List<Lead> myBundle;
    private AdapterLead mAdapter;
    private RecyclerView rv;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_my_new_leads, container, false);
        mContext=getContext();
        myBundle= new ArrayList<>();
        rv=(RecyclerView) root_view.findViewById(R.id.my_leads_rv);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeAdapter();
        loadDetails();
        return root_view;
    }

    public void loadDetails(){
        myBundle.add(new Lead("Martyn", "pittsburgh, 407 S Craig St", 2, 1, 1, "RSC DA3 F3A 44A", "D5A3ASSDSD533AASD", "68 Kane Peter St.",0,false));
        myBundle.add(new Lead("John","New York, Street 2",0,1,2,"CD ASDS 454A A3F3","1A2ASD2ASD33ASD5ASD","253, Joseph P Church",0,false));
        myBundle.add(new Lead("Mark","Washington, Lane 16",1,2,2,"CD 454A ASDS A3F3","2ASD31A2ASD3ASD5ASD","8-2D Kane Straut Road",0,false));
        myBundle.add(new Lead("Cooper", "London, Road 52", 2, 1, 1, "CD A3F3 ASDS 454A", "3ASD5ASD33ASD5ASD", "68/215, Oakland View",0,false));
        myBundle.add(new Lead("Martyn", "pittsburgh, 407 S Craig St", 2, 1, 1, "RSC DA3 F3A 44A", "D5A3ASSDSD533AASD", "68 Kane Peter St.",0,false));
        myBundle.add(new Lead("Jessica","New York, Street 2",1,0,1,"CD F3AS 454A A3F3","1A2ASD2ASD33ASD5ASD","253, Joseph P Church",0,false))
        ;myBundle.add(new Lead("Martyn", "pittsburgh, 407 S Craig St", 2, 1, 1, "RSC DA3 F3A 44A", "D5A3ASSDSD533AASD", "68 Kane Peter St.",0,false));
        myBundle.add(new Lead("Adam's","New York, Street 2",0,0,0,"A3 F3AS 454A A3F3","1A2ASD2ASD33ASD5ASD","253, Joseph P Church",0,false));
        myBundle.add(new Lead("Boucher","Washington, Lane 16",2,2,2,"CD 454A ASDS A3F3","2ASD31A2ASD3ASD5ASD","8-2D Kane Straut Road",0,false));
        myBundle.add(new Lead("Huggins", "London, Road 52", 2, 2, 1, "CD 454A ASDS 454A", "3ASD5ASD33ASD5ASD", "68/215, Oakland View",0,false));
        initializeAdapter();
    }
    private void initializeAdapter(){
        mAdapter = new AdapterLead(myBundle,getContext(),getActivity());
        rv.setAdapter(mAdapter);
    }

}
