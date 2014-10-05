package z.z;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import z.z.diagnose.SelfDiagnoseTab;
import z.z.outbreak.CurrentOutbreakTab;
import z.z.report.DistrictsDataSource;
import z.z.report.ReportZombieTab;

public class ZombieActivity extends Activity implements ActionBar.TabListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private DistrictsDataSource districtsDataSource;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            ActionBar.Tab tab = actionBar.newTab()
                    .setCustomView(R.layout.tab_view)
                    .setTabListener(this);
            ((TextView) tab.getCustomView().findViewById(R.id.title)).setText(getTabTitle(i));
            tab.getCustomView().findViewById(R.id.icon).setBackgroundResource(getTabIcon(i));
            actionBar.addTab(tab);
        }
        districtsDataSource = new DistrictsDataSource();
    }

    private String getTabTitle(int position) {
        switch (position) {
            case 0:
                return "REPORT\nZOMBIES";
            case 1:
                return "CURRENT\nOUTBREAK";
            case 2:
                return "SELF\nDIAGNOSE";
        }
        return null;
    }

    private int getTabIcon(int position) {
        switch (position) {
            case 0:
                return R.drawable.icon_flag;
            case 1:
                return R.drawable.icon_location;
            case 2:
            default:
                return R.drawable.icon_pill;
        }
    }

    public DistrictsDataSource getDistrictsDataSource() {
        return districtsDataSource;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        tab.getCustomView().setBackgroundColor(getResources().getColor(R.color.selected_tab));
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        tab.getCustomView().setBackgroundColor(getResources().getColor(R.color.unselected_tab));
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ReportZombieTab();
            } else if (position == 1) {
                return new CurrentOutbreakTab();
            } else if (position == 2) {
                return new SelfDiagnoseTab();
            } else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
