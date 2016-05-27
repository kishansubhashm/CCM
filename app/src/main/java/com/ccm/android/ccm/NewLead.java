package com.ccm.android.ccm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

/**
 * Created by KishanSubhashMiryala on 5/11/2016.
 */
public class NewLead extends Fragment {
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_new_lead, container, false);

        final RadioGroup radioOccupationGroup=(RadioGroup)rootView.findViewById(R.id.radioOccupationGroup);
        final RadioGroup radioInnerGroup=(RadioGroup)rootView.findViewById(R.id.radioInnerGroup);
        RadioButton setting_self_employee = (RadioButton) rootView.findViewById(R.id.setting_self_employee);
        RadioButton setting_employee = (RadioButton) rootView.findViewById(R.id.setting_employee_edit);
        RadioButton setting_profesional = (RadioButton) rootView.findViewById(R.id.setting_profesional_edit);
        final RadioButton one = (RadioButton) rootView.findViewById(R.id.one);
        final RadioButton two = (RadioButton) rootView.findViewById(R.id.two);
        final RadioButton three = (RadioButton) rootView.findViewById(R.id.three);

        final MultiStateToggleButton customerIncome = (MultiStateToggleButton) rootView.findViewById(R.id.mstb_customer_income);

        final TextView acceptCardsLabel = (TextView) rootView.findViewById(R.id.acceptCardsLabel);
        final LinearLayout acceptCardsView = (LinearLayout) rootView.findViewById(R.id.acceptCardsView);
        final LinearLayout cardsTxVolumeView = (LinearLayout) rootView.findViewById(R.id.cardsTxVolumeView);

        final RadioButton acceptCardsYes = (RadioButton) rootView.findViewById(R.id.cards_yes);
        final RadioButton acceptCardsNo = (RadioButton) rootView.findViewById(R.id.cards_no);

        final RadioGroup radioAcceptCards = (RadioGroup) rootView.findViewById(R.id.radioAcceptCards);

        customerIncome.setElements(R.array.income_array, 2);

        radioOccupationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.setting_self_employee:
                        one.setText("Proprietary company");
                        two.setText("Partnership company");
                        three.setText("Private Limited company");
                        //Show Cards Acceptance field
                        acceptCardsLabel.setVisibility(View.VISIBLE);
                        acceptCardsView.setVisibility(View.VISIBLE);

                        if(acceptCardsYes.isChecked()){
                            cardsTxVolumeView.setVisibility(View.VISIBLE);
                        } else {
                            cardsTxVolumeView.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.setting_employee_edit:
                        one.setText("Proprietary company");
                        two.setText("Private Limited company");
                        three.setText("Government");

                        acceptCardsLabel.setVisibility(View.GONE);
                        acceptCardsView.setVisibility(View.GONE);
                        cardsTxVolumeView.setVisibility(View.GONE);
                        break;
                    case R.id.setting_profesional_edit:
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


        radioAcceptCards.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId){
                    case R.id.cards_yes:
                        cardsTxVolumeView.setVisibility(View.VISIBLE);
                        break;
                    case R.id.cards_no:
                        cardsTxVolumeView.setVisibility(View.GONE);
                        break;
                }
            }
        });


        int selectedId=radioInnerGroup.getCheckedRadioButtonId();

        return rootView;
    }
}
