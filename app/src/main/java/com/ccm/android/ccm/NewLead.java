package com.ccm.android.ccm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
        RadioButton setting_self_employee;
        RadioButton setting_employee;
        RadioButton setting_profesional;

        final RadioGroup radioOccupationGroup=(RadioGroup)rootView.findViewById(R.id.radioOccupationGroup);
        final RadioGroup radioInnerGroup=(RadioGroup)rootView.findViewById(R.id.radioInnerGroup);
        setting_self_employee = (RadioButton) rootView.findViewById(R.id.setting_self_employee);
        setting_employee = (RadioButton) rootView.findViewById(R.id.setting_employee_edit);
        setting_profesional = (RadioButton) rootView.findViewById(R.id.setting_profesional_edit);
        final RadioButton one = (RadioButton) rootView.findViewById(R.id.one);
        final RadioButton two = (RadioButton) rootView.findViewById(R.id.two);
        final RadioButton three = (RadioButton) rootView.findViewById(R.id.three);
        final MultiStateToggleButton button = (MultiStateToggleButton) rootView.findViewById(R.id.mstb_multi_id);
        button.setElements(R.array.income_array,2);

        radioOccupationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId){
                    case R.id.setting_self_employee:
                        one.setText("Proprietary company");
                        two.setText("Partnership company");
                        three.setText("Private Limited company");
                        break;
                    case R.id.setting_employee_edit:
                        one.setText("Proprietary company");
                        two.setText("Private Limited company");
                        three.setText("Government");
                        break;
                    case R.id.setting_profesional_edit:
                        one.setText("Doctor");
                        two.setText("Architect");
                        three.setText("CA");
                        break;
                }
            }
        });


        int selectedId=radioInnerGroup.getCheckedRadioButtonId();

        return rootView;
    }
}
