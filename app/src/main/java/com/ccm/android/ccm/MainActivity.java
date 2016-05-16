package com.ccm.android.ccm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private LinkedHashMap<String, List<String>> mExpandableListData;
    private Context mContext;
    private Activity mActivity;
    private SessionHandler ssn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mActivity = (Activity) this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CCM");
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_white_menu_24dp);
//        getSupportActionBar().show();
        ssn = new SessionHandler(mContext, mActivity);

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        setupDrawer();
//            setNavBar(new String[]{"English", "Telugu", "Hindi"}, new String[]{"comedy", "folkfore", "trending"});
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setNavBar(final String languagesArr[], final String genresArr[]) {
//        mExpandableListView = (ExpandableListView) findViewById(R.id.expandable_nav_list_view);
        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        final ImageView prof_image = (ImageView) listHeaderView.findViewById(R.id.prof_image);

        final ImageView nav_wallet_more_details = (ImageView) listHeaderView.findViewById(R.id.nav_wallet_more_details);
        final ImageView banner_image = (ImageView) listHeaderView.findViewById(R.id.banner_image);
        final TextView user_name = (TextView) listHeaderView.findViewById(R.id.user_name);

        final TextView nav_mobile_no = (TextView) listHeaderView.findViewById(R.id.nav_mobile_no);
        nav_mobile_no.setText("+91 9988776543");

        user_name.setText("Hi " + ssn.getUserFullName() + "!");
//        wallet_icon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
//        profile_icon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent), PorterDuff.Mode.SRC_IN);


//        View listFooterView = inflater.inflate(R.layout.nav_footer, null, false);

        mExpandableListView.addHeaderView(listHeaderView);
//        mExpandableListView.addFooterView(listFooterView);
        mExpandableListData = ExpandableListDataSource.getData(languagesArr, genresArr);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        addDrawerItems();


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewLead(), "New Lead");
        adapter.addFragment(new MyNewLeads(), "My Leads");

        viewPager.setAdapter(adapter);
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d("selected", "" + mExpandableListTitle.get(groupPosition).toString());
                if (mExpandableListTitle.get(groupPosition).toString().equals("Logout")) {
                    Toast.makeText(MainActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
                    ssn.logoutUser();
                    gotoSignin();
                }
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d("a selected", "" + R.string.selected_item);
                Log.d("b selected", "" + mExpandableListTitle.get(groupPosition).toString());
                if (mExpandableListTitle.get(groupPosition).toString().equals("Logout")) {
                    Toast.makeText(MainActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
                    ssn.logoutUser();
                    gotoSignin();
                }
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                Log.d("c selected", "" + mExpandableListTitle.get(groupPosition).toString() + " -> " + selectedItem);
                Log.d("d selected", "" + childPosition + " -> " + selectedItem);


                return false;
            }
        });
    }

    private void gotoSignin() {
        SessionHandler.logoutUser();
        Intent mIntent = new Intent(MainActivity.this, ActivityLogin.class);
        startActivity(mIntent);
        finish();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle actionbar item clicks here. The action bar will
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id==android.R.id.home){
////            mDrawerLayout.openDrawer(GravityCompat.START);
////            return true;
////        }
//
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.signout:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                gotoSignin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
