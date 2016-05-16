package com.ccm.android.ccm;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.List;

/**
 * Created by KishanSubhashMiryala on 5/16/2016.
 */
public class AdapterLead extends RecyclerView.Adapter<AdapterLead.ViewHolder> {
    protected static List<Lead> leadsBundle;
    private Context mContext;
    private Activity mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout min_details;
        private LinearLayout more_details;
        private TextView work_status;
        private TextView name;
        private TextView address;
        private TextView pan_card;
        private TextView occupation;
        private TextView income;
        private TextView aadhar_card;
        private TextView ofc_address;
        private MultiStateToggleButton button;
        ViewHolder(final View itemView) {
            super(itemView);
            min_details= (RelativeLayout) itemView.findViewById(R.id.min_details);
            more_details= (LinearLayout) itemView.findViewById(R.id.more_details);
            work_status= (TextView) itemView.findViewById(R.id.work_status);
            name= (TextView) itemView.findViewById(R.id.name);
            occupation= (TextView) itemView.findViewById(R.id.occupation);
            income= (TextView) itemView.findViewById(R.id.income);
            address= (TextView) itemView.findViewById(R.id.address);
            aadhar_card= (TextView) itemView.findViewById(R.id.aadhar_card);
            pan_card= (TextView) itemView.findViewById(R.id.pan_card);
            ofc_address= (TextView) itemView.findViewById(R.id.ofc_address);

            button=(MultiStateToggleButton) itemView.findViewById(R.id.mstb_multi_id);
        }
    }

    public AdapterLead(List<Lead> subscription, Context mContext, Activity mActivity){
        this.leadsBundle =subscription;
        this.mContext=mContext;
        this.mActivity=mActivity;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_new_each_lead, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int i) {

        setVisibilityStatus(holder.more_details, i);
        setWorkStatus(holder.work_status, i);
//        if(leadsBundle.get(i).getStatus()>0) {
//            holder.button.setElements(R.array.status_array, leadsBundle.get(i).getStatus());
//        }

        holder.min_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leadsBundle.get(i).getIsOpened()) {
                    leadsBundle.get(i).setIsOpened(false);
                } else {
                    leadsBundle.get(i).setIsOpened(true);
                }
                notifyDataSetChanged();
            }
        });

        holder.name.setText(leadsBundle.get(i).getName());
        holder.address.setText(leadsBundle.get(i).getAddress());
        holder.pan_card.setText(leadsBundle.get(i).getPanCard());
        holder.aadhar_card.setText(leadsBundle.get(i).getAadharCard());
        holder.ofc_address.setText(leadsBundle.get(i).getOfficeOrBusinessAddress());
        holder.occupation.setText(leadsBundle.get(i).getSELF_EMP()[leadsBundle.get(i).getOccupationPos()]);
        holder.income.setText(leadsBundle.get(i).getANUAL_INCOME()[leadsBundle.get(i).getIncomePos()]);

        holder.button.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                    leadsBundle.get(i).setStatus(position+1);
                    leadsBundle.get(i).setIsOpened(true);
                    notifyDataSetChanged();
                    setWorkStatus(holder.work_status, i);
            }
        });
    }

    private void setVisibilityStatus(LinearLayout more_details, int i){
        if(leadsBundle.get(i).getIsOpened()){
            more_details.setVisibility(View.VISIBLE);
        }else {
            more_details.setVisibility(View.GONE);
        }
    }
    private void setWorkStatus(TextView work_status, int i){
        int status=leadsBundle.get(i).getStatus();


        int color;
        String workStatus="";
        switch(status){
            case 0:
            default:
                workStatus="Pending";
                color=R.color.orange;
                break;
            case 1:
                workStatus="Approved";
                color=R.color.green;
                break;
            case 2:
                workStatus="Rejected";
                color=R.color.red;
                break;
        }

        work_status.setTextColor(ContextCompat.getColor(mContext, color));
        work_status.setText(workStatus);
    }
    @Override
    public int getItemCount() {
        return leadsBundle.size();
    }
}
