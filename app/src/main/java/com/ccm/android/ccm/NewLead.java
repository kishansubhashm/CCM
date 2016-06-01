package com.ccm.android.ccm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class NewLead extends Fragment {
    private View rootView;
    private Context mContext;
    private Activity mActivity;

    private String[] occupation = {"customer_self", "customer_employed", "customer_professional"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_new_lead, container, false);
        mContext = this.getContext();
        mActivity = this.getActivity();
        final RadioGroup radioOccupationGroup=(RadioGroup)rootView.findViewById(R.id.occupationGroup);
        final RadioGroup radioInnerGroup=(RadioGroup)rootView.findViewById(R.id.occupationDetailGroup);
        RadioButton setting_self_employee = (RadioButton) rootView.findViewById(R.id.customer_self);
        RadioButton setting_employee = (RadioButton) rootView.findViewById(R.id.customer_employed);
        RadioButton setting_profesional = (RadioButton) rootView.findViewById(R.id.customer_professional);
        final RadioButton one = (RadioButton) rootView.findViewById(R.id.one);
        final RadioButton two = (RadioButton) rootView.findViewById(R.id.two);
        final RadioButton three = (RadioButton) rootView.findViewById(R.id.three);

        final MultiStateToggleButton customerIncome = (MultiStateToggleButton) rootView.findViewById(R.id.mstb_customer_income);

        final TextView acceptCardsLabel = (TextView) rootView.findViewById(R.id.acceptCardsLabel);
        final LinearLayout acceptCardsView = (LinearLayout) rootView.findViewById(R.id.acceptCardsView);
        final LinearLayout cardsTxVolumeView = (LinearLayout) rootView.findViewById(R.id.cardsTxVolumeView);

        final RadioButton acceptCardsYes = (RadioButton) rootView.findViewById(R.id.cards_yes);
        final RadioButton acceptCardsNo = (RadioButton) rootView.findViewById(R.id.cards_no);

        final RadioGroup radioAcceptCards = (RadioGroup) rootView.findViewById(R.id.bizcards);

        final Button submitLeadButton = (Button) rootView.findViewById(R.id.submitlead);



        customerIncome.setElements(R.array.income_array, 2);

        submitLeadButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Boolean submissionValid = true;

                /**
                 * All the variables to handle form submission.
                 */
                final EditText cname = (EditText) rootView.findViewById(R.id.customer_name);
                final EditText cphone = (EditText) rootView.findViewById(R.id.customer_phone);
                final EditText cpan = (EditText) rootView.findViewById(R.id.customer_pan_num);
                final EditText caddr = (EditText) rootView.findViewById(R.id.customer_addr);
                final EditText caadhaar = (EditText) rootView.findViewById(R.id.customer_aadhaar);
                final MultiStateToggleButton cincome = (MultiStateToggleButton) rootView.findViewById(R.id.mstb_customer_income);

                final RadioGroup coccupation = (RadioGroup) rootView.findViewById(R.id.occupationGroup);
                final RadioGroup coccupationdetail = (RadioGroup) rootView.findViewById(R.id.occupationDetailGroup);

                final RadioGroup ccards = (RadioGroup) rootView.findViewById(R.id.bizcards);
                final RadioGroup cbizpremises = (RadioGroup) rootView.findViewById(R.id.bizpremises);

                final EditText cbizsales = (EditText) rootView.findViewById(R.id.bizsales);
                final EditText cbizsince = (EditText) rootView.findViewById(R.id.bizsince);
                final EditText cbiznature = (EditText) rootView.findViewById(R.id.biznature);
                final EditText cbizphone = (EditText) rootView.findViewById(R.id.bizphone);

                String name = cname.getText().toString().trim();
                String phone = cphone.getText().toString().trim();
                String addr = caddr.getText().toString().trim();
                String pan = cpan.getText().toString().trim();
                String aadhaar = caadhaar.getText().toString().trim();

                if (name.isEmpty() || phone.isEmpty() || addr.isEmpty() || pan.isEmpty() || aadhaar.isEmpty())
                    submissionValid = false;

                String income = "N/A";
                switch (cincome.getValue()) {
                    case 0:
                        income = "0-5";
                        break;
                    case 1:
                        income = "5-10";
                        break;
                    case 2:
                        income = "10-25";
                        break;
                    case 3:
                        income = "25-50";
                        break;
                    case 4:
                        income = ">50";
                        break;
                    default:
                        income = "N/A";
                        break;
                }

                Integer coccupationChecked = coccupation.getCheckedRadioButtonId();
                View coccupationSelected = coccupation.findViewById(coccupationChecked);
                Integer coccupationIndex = coccupation.indexOfChild(coccupationSelected);

                Integer coccupationDetailChecked = coccupationdetail.getCheckedRadioButtonId();
                View coccupationDetailSelected = coccupationdetail.findViewById(coccupationDetailChecked);
                Integer coccupationDetailIndex = coccupationdetail.indexOfChild(coccupationDetailSelected);

                Integer ccardsChecked = ccards.getCheckedRadioButtonId();
                View ccardsSelected = ccards.findViewById(ccardsChecked);
                Integer ccardsIndex = ccards.indexOfChild(ccardsSelected);

                Integer cbizpremisesChecked = cbizpremises.getCheckedRadioButtonId();
                View cbizpremisesSelected = cbizpremises.findViewById(cbizpremisesChecked);
                Integer cbizpremisesIndex = cbizpremises.indexOfChild(cbizpremisesSelected);

                Integer occupation, occupationdetail = 0;
                String bizcards = "n";
                String bizsales = cbizsales.getText().toString().trim();
                String bizsince = cbizsince.getText().toString().trim();
                String bizpremises;
                String biznature = cbiznature.getText().toString().trim();
                String bizphone = cbizphone.getText().toString().trim();

                switch (coccupationIndex) {
                    case 0:
                        occupation = 1;
                        occupationdetail = coccupationDetailIndex + 4;

                        switch (ccardsIndex) {
                            case 0:
                                bizcards = "y";
                                if (bizsales.isEmpty() || bizsince.isEmpty() || biznature.isEmpty() || bizphone.isEmpty())
                                    submissionValid = false;
                                switch (cbizpremisesIndex) {
                                    case 0:
                                        bizpremises = "Owned";
                                        break;
                                    case 1:
                                        bizpremises = "Rented";
                                        break;
                                    default:
                                        bizpremises = "N/A";
                                        break;
                                }
                                break;
                            case 1:
                            default:
                                bizcards = "n";
                                bizsales = "N/A";
                                bizsince = "N/A";
                                biznature = "N/A";
                                bizphone = "N/A";
                                bizpremises = "N/A";
                                break;
                        }
                        break;
                    case 1:
                        occupation = 2;
                        occupationdetail = coccupationDetailIndex + 7;
                        bizcards = "n";
                        bizsales = "N/A";
                        bizsince = "N/A";
                        biznature = "N/A";
                        bizphone = "N/A";
                        bizpremises = "N/A";
                        break;
                    case 2:
                        occupation = 3;
                        occupationdetail = coccupationDetailIndex + 10;
                        bizcards = "n";
                        bizsales = "N/A";
                        bizsince = "N/A";
                        biznature = "N/A";
                        bizphone = "N/A";
                        bizpremises = "N/A";
                        break;
                    default:
                        occupation = 0;
                        occupationdetail = 0;
                        bizcards = "n";
                        bizsales = "N/A";
                        bizsince = "N/A";
                        biznature = "N/A";
                        bizphone = "N/A";
                        bizpremises = "N/A";
                        break;
                }

                if (submissionValid) {
                    HashMap<String, String> leadParam = new HashMap<String, String>();


                    leadParam.put("name", name);
                    leadParam.put("phone", phone);

                    leadParam.put("pan", pan);
                    leadParam.put("aadhaar", aadhaar);
                    leadParam.put("addr", addr);
                    leadParam.put("income", income);

                    leadParam.put("occupation", occupation.toString());
                    leadParam.put("occupationdetail", occupationdetail.toString());

                    leadParam.put("cards", bizcards);
                    leadParam.put("sales", bizsales);
                    leadParam.put("since", bizsince);
                    leadParam.put("premises", bizpremises);
                    leadParam.put("nature", biznature);
                    leadParam.put("phone", bizphone);
                    leadParam.put("ccmid", SessionHandler.getUserFullName());

                    leadParam.put("MS", "0586");

                    String leadsubmiturl = "http://ccm.viveninfomedia.com/madld";
                    CustomRequest submitlead;
                    submitlead = new CustomRequest(Request.Method.POST, leadsubmiturl, leadParam, submitleadSuccessListener(), submitleadErrorListener());
                    Volley.newRequestQueue(mContext).add(submitlead);
                } else {
                    Toast.makeText(mContext, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioOccupationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.customer_self:
                        one.setText("Proprietary company");
                        two.setText("Partnership company");
                        three.setText("Private Limited company");
                        //Show Cards Acceptance field
                        acceptCardsLabel.setVisibility(View.VISIBLE);
                        acceptCardsView.setVisibility(View.VISIBLE);
                        cardsTxVolumeView.setVisibility(View.VISIBLE);
                        break;
                    case R.id.customer_employed:
                        one.setText("Proprietary company");
                        two.setText("Private Limited company");
                        three.setText("Government");

                        acceptCardsLabel.setVisibility(View.GONE);
                        acceptCardsView.setVisibility(View.GONE);
                        cardsTxVolumeView.setVisibility(View.GONE);
                        break;
                    case R.id.customer_professional:
                        one.setText("Doctor");
                        two.setText("Architect");
                        three.setText("CA");

                        acceptCardsLabel.setVisibility(View.GONE);
                        acceptCardsView.setVisibility(View.GONE);
                        cardsTxVolumeView.setVisibility(View.GONE);
                        break;
                }
            }
        });

//        radioAcceptCards.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//                switch(checkedId){
//                    case R.id.cards_yes:
//                        cardsTxVolumeView.setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.cards_no:
//                        cardsTxVolumeView.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });



//        int selectedId=radioInnerGroup.getCheckedRadioButtonId();
        return rootView;
    }

    private Response.Listener<JSONObject> submitleadSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject loginResponse) {
                Log.d("response", loginResponse.toString());
                try {
                    if (loginResponse.getInt("status") == 0){
                        Toast.makeText(mContext, "Could not submit lead. Please check again.", Toast.LENGTH_SHORT).show();
                    }
                    else if (loginResponse.getInt("status") == 1){
                        Toast.makeText(mContext, "Successfully submitted your lead", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(mContext, "Server error. Please contact admin.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Response.ErrorListener submitleadErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NoConnectionError) {
                    Toast.makeText(mContext, "Could not connect to Internet. Check connection.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Server error. Please contact admin.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
