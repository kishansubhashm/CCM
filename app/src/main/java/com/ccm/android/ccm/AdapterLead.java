package com.ccm.android.ccm;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public static List<Lead> leadsBundle;
    private Context mContext;
    private Activity mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout min_details;
        private LinearLayout more_details;
        private ImageView work_status;
        private TextView name;
        private TextView address;
        private TextView pan_card;
        private TextView occupation;
        private TextView income;
        private TextView aadhar_card;

        ViewHolder(final View itemView) {
            super(itemView);
            min_details= (RelativeLayout) itemView.findViewById(R.id.min_details);
            more_details= (LinearLayout) itemView.findViewById(R.id.more_details);
            work_status= (ImageView) itemView.findViewById(R.id.work_status);
            name= (TextView) itemView.findViewById(R.id.name);
            occupation= (TextView) itemView.findViewById(R.id.occupation);
            income= (TextView) itemView.findViewById(R.id.income);
            address= (TextView) itemView.findViewById(R.id.addr);
            aadhar_card= (TextView) itemView.findViewById(R.id.aadhaar_card);
            pan_card= (TextView) itemView.findViewById(R.id.pan_card);
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
        holder.occupation.setText(leadsBundle.get(i).getSELF_EMP()[leadsBundle.get(i).getOccupation()]);
        holder.income.setText(leadsBundle.get(i).getIncome());

    }

    private void setVisibilityStatus(LinearLayout more_details, int i){
        if(leadsBundle.get(i).getIsOpened()){
            more_details.setVisibility(View.VISIBLE);
        }else {
            more_details.setVisibility(View.GONE);
        }
    }
    private void setWorkStatus(ImageView work_status, int i){
        int status=leadsBundle.get(i).getStatus();
        switch(status){
            case 0:
            default:
                work_status.setImageResource(R.drawable.pending);
                break;
            case 1:
                work_status.setImageResource(R.drawable.approved);
                break;
            case 2:
                work_status.setImageResource(R.drawable.rejected);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return leadsBundle.size();
    }
}
