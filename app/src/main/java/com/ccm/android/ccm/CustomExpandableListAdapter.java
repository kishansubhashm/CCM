package com.ccm.android.ccm;
/**
 * Created by KishanSubhashMiryala on 05/05/16.
 */
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       Map<String, List<String>> expandableListDetail) {
        mContext = context;
        mExpandableListTitle = expandableListTitle;
        mExpandableListDetail = expandableListDetail;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.nav_list_item, null);
        }
        Log.d(" expandedListText ", expandedListText);

        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
//        if(expandedListText!=null && !expandedListText.equalsIgnoreCase(null)){
            expandedListTextView.setText(expandedListText);
//        }else{
//            expandedListTextView.setText(mContext.getResources().getString(R.string.drawer_default_no_child));
//        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

//    int icons[]={R.drawable.ic_white_person_48dp,R.drawable.ic_white_layers_36dp,R.drawable.ic_white_rating_36dp,
//    R.drawable.ic_white_share_24dp,R.drawable.ic_white_info_filled_24dp,R.drawable.ic_white_info_filled_24dp
//    };
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.nav_list_group, null);
        }
        TextView groupTitle = (TextView) convertView.findViewById(R.id.listTitle);
//        ImageView img = (ImageView) convertView.findViewById(R.id.grp_status);
//        ImageView icon = (ImageView) convertView.findViewById(R.id.grp_icon);
        View line=(View)convertView.findViewById(R.id.line);
//        icon.setImageResource(icons[listPosition]);
//        icon.setColorFilter(ContextCompat.getColor(mContext, R.color.cards_peripherals_color), PorterDuff.Mode.SRC_IN);
        groupTitle.setText(listTitle);
//        if (isExpanded) {
//            img.setImageResource(R.drawable.ic_white_arrow_up_24dp);
//        } else {
//            img.setImageResource(R.drawable.ic_white_arrow_down_24dp);
//        }
//        img.setColorFilter(ContextCompat.getColor(mContext, R.color.cards_peripherals_color), PorterDuff.Mode.SRC_IN);

        /*
        * to make sure only genre and language are dropdowns
        * and reduce non-list item sizes
        * */
//        if(listPosition>1){
//            img.setVisibility(View.GONE);
//            groupTitle.setTextSize(14);
//        }else{
//            groupTitle.setTextSize(16);
//            img.setVisibility(View.VISIBLE);
//        }
        if(listPosition==0 || listPosition==1){
            line.setVisibility(View.GONE);
        }else if(listPosition==3){
            line.setVisibility(View.GONE);
        }else if(listPosition==5){
            line.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
