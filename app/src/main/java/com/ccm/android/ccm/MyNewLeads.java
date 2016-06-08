package com.ccm.android.ccm;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        HashMap<String, String> leadOwner = new HashMap<String, String>();
        leadOwner.put("ccmid", SessionHandler.getUserFullName());
        leadOwner.put("MS", "0586");
        String getleadsurl = "http://ccm.viveninfomedia.com/mgtld";
        CustomRequest getmyleads;
        getmyleads = new CustomRequest(Request.Method.POST, getleadsurl, leadOwner, getleadsSuccessListener(), getleadsErrorListener());
        Volley.newRequestQueue(mContext).add(getmyleads);
    }

    private Response.Listener<JSONObject> getleadsSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject getleadsResponse) {
                Integer status;
                try {
                    if (getleadsResponse.getInt("status") == 0){
                        Toast.makeText(mContext, "No leads available for you.", Toast.LENGTH_SHORT).show();
                    }
                    else if (getleadsResponse.getInt("status") == 1){
                        Toast.makeText(mContext, "Got leads. Now Parsing them", Toast.LENGTH_SHORT).show();
                        JSONArray leads = getleadsResponse.getJSONArray("leads");
                        JSONObject lead;
                        for(int i =0; i < leads.length(); i++){
                            lead = leads.getJSONObject(i);
                            myBundle.add(new Lead(lead.getString("name"), lead.getString("phone"), lead.getString("pan"),
                                    lead.getString("aadhaar"),lead.getString("addr"), lead.getInt("occupation"),
                                    lead.getInt("occupationdetail"), lead.getString("income"), new JSONObject(lead.getString("biz")),
                                    lead.getInt("sts"),false));
                        }
                        initializeAdapter();
                    }
                }catch(Exception e){
                    Log.e("Parse Leads","exception",e);
                    Toast.makeText(mContext, "Failed to get your leads. Please contact admin: " + SessionHandler.getUserFullName(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Response.ErrorListener getleadsErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NoConnectionError) {
                    Toast.makeText(mContext, "Could not connect to Internet. Check connection.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Failed to get your leads. Please contact admin.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void initializeAdapter(){
        mAdapter = new AdapterLead(myBundle,getContext(),getActivity());
        rv.setAdapter(mAdapter);
    }

}
