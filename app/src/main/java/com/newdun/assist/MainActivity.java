package com.newdun.assist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newdun.assist.config.Config;
import com.newdun.assist.dummy.DummyContent;
import com.newdun.assist.fragments.ClassifyFragment;
import com.newdun.assist.fragments.ContactFragment;

import java.util.HashMap;
import java.util.Map;
import com.grjf365.gongrongpoints.R;
import com.newdun.assist.fragments.HomePageFragment;

public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        onSetTab();
        mSectionsPagerAdapter.notifyDataSetChanged();
        for (int pos = 0; pos < mSectionsPagerAdapter.getCount(); pos ++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(pos);
            if (tab != null) {
                tab.setCustomView(mSectionsPagerAdapter.getTabView(pos));
            }
        }

    }

    public void onSetTab () {
        addTab(R.string.tab_dynamic, R.drawable.tab_home, HomePageFragment.class);
        addTab(R.string.tab_message, R.drawable.tab_message, ClassifyFragment.class);
        addTab(R.string.tab_contact, R.drawable.tab_contact, ContactFragment.class);
        addTab(R.string.tab_discover, R.drawable.tab_discover, ContactFragment.class);
        addTab(R.string.tab_personal, R.drawable.tab_person, ContactFragment.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        int mTabCounts = 0;

        class Holder {
            Class<?> mFragment;
            View mTabView;
            Integer mTitle;
            Integer mIcon;
        }

        Map<Integer, Holder> mHolders = new HashMap<Integer, Holder>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            try {
                return (Fragment) mHolders.get(position).mFragment.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int getCount() {
            return mTabCounts;
        }

        public View getTabView(Integer position) {
            Holder holder = mHolders.get(position);
            if (holder.mTabView != null) {
                return holder.mTabView;
            } else {
                View tabIndicator = MainActivity.this.getLayoutInflater().inflate(R.layout.custom_tab, null);

                TextView title = (TextView) tabIndicator.findViewById(R.id.title);
                ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icons);
                if (0 != holder.mTitle) {
                    title.setText(holder.mTitle);
                } else {
                    title.setVisibility(View.GONE);
                }

                icon.setImageResource(holder.mIcon);
                mHolders.get(position).mTabView = tabIndicator;
                return tabIndicator;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Holder hodler = mHolders.get(position);
            return getString(hodler.mTitle);
        }

        public void addTab(int labelId, int drawableId, Class<?> c) {
            int position = mTabCounts;
            Holder holder = new Holder();
            holder.mTitle = labelId;
            holder.mIcon = drawableId;
            holder.mFragment = c;
            mHolders.put(mTabCounts ++, holder);
        }
    }

    /******************************************
     * 注意
     **************************************************/
    private void setRedPoint(int type) {
        if (type == Config.ORDER_WORK) {
            int count = Config.getInstance().getOrderCount(Config.ORDER_WORK);
            changeTabImage(3, count);

        } else {
            int count = Config.getInstance().getOrderCount(
                    Config.ORDER_SEND_ORDER);
            changeTabImage(3, count);
            count = 0;
            count += Config.getInstance().getOrderCount(
                    Config.ORDER_RECEIVE_ORDER);
            count += Config.getInstance().getOrderCount(Config.ORDER_SYSTEM);
            count += Config.getInstance().getOrderCount(Config.ORDER_MESSAGE);
            changeTabImage(1, count);
        }
    }

    public void addTab(int labelId, int drawableId, Class<?> c) {
        mSectionsPagerAdapter.addTab(labelId, drawableId, c);
    }

    void changeTabImage(int tabNo, int number) {
//        View tabView = tabWidget.getChildAt(tabNo);
//        TextView textView = (TextView) tabView.findViewById(R.id.unread_number);
//        if (number > 0) {
//            textView.setVisibility(View.VISIBLE);
//            textView.setText(String.valueOf(number));
//        } else {
//            textView.setVisibility(View.INVISIBLE);
//        }
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

}