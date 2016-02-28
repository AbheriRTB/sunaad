package com.abheri.sunaad.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abheri.sunaad.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Context context;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        context = getApplicationContext();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // Obtain the shared Tracker instance.
        //AnalyticsApplication application = (AnalyticsApplication) getApplication();
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        AnalyticsApplication application = (AnalyticsApplication) new AnalyticsApplication();
        mTracker = application.getDefaultTracker();
        Log.i("Sunaad", "Setting screen name: " + Util.HOME_SCREEN);
        mTracker.setScreenName("Image~" + Util.HOME_SCREEN);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        /*Toast.makeText(
                this.getApplicationContext(),
                "Drawer Item" + position + "  Selected...",
                Toast.LENGTH_SHORT).show(); */


        FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction =
                                    fragmentManager.beginTransaction();

        if(position == 0){
            HomeFragment hf =  new HomeFragment();
            transaction.replace(R.id.container, hf);
            //Don't add addToBackStack here
            transaction.commit();
        } else if(position == 1){
            ProgramFragment pf =  new ProgramFragment();
            transaction.replace(R.id.container,  pf);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(position == 2){
            ArtisteFragment af =  new ArtisteFragment();
            transaction.replace(R.id.container,  af);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(position == 3){
            SabhaFragment sf =  new SabhaFragment();
            transaction.replace(R.id.container,  sf);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            transaction.replace(R.id.container, PlaceholderFragment.newInstance(position + 1));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        } */

        int v = 0;
        String vn = "";
        try {
            String pkgname = context.getPackageName();
            PackageManager pm = context.getPackageManager();
            v = pm.getPackageInfo(pkgname, 0).versionCode;
            vn = pm.getPackageInfo(pkgname, 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (id) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                /* Find which fragment is active when refresh button is pressed
                 * Call corresponding 'getData()' method with force refresh
                 * (second argument as true).
                 *
                 * ProgramDetailsFragment does not have getData method. Hence user
                 * has to go back one screen to ProgramFragment to refresh the data
                 */
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment f = fragmentManager.findFragmentById(R.id.container);
                String fragname="";

                if (f instanceof HomeFragment){
                    fragname="HomeFragment";
                    ((HomeFragment) f).getData((HomeFragment)f, true);
                }else if(f instanceof ProgramFragment){
                    fragname="ProgramFragment";
                    ((ProgramFragment) f).getData((ProgramFragment)f, true);
                }else if(f instanceof ProgramDetailsFragment){
                    fragname="ProgramDetailsFragment";
                    Toast.makeText(this, "Please use back button before refresh",
                            Toast.LENGTH_SHORT).show();
                }else if(f instanceof ArtisteFragment){
                    fragname="ArtisteFragment";
                    ((ArtisteFragment) f).getData((ArtisteFragment)f, true);
                }else if(f instanceof SabhaFragment){
                    fragname="SabhaFragment";
                    ((SabhaFragment) f).getData((SabhaFragment)f, true);
                }

                //Toast.makeText(this, "Refresh selected:"+fragname, Toast.LENGTH_SHORT).show();
                break;
            // action with ID action_settings was selected
            case R.id.action_about:

                Toast.makeText(this, "Sunaad: v"+vn, Toast.LENGTH_LONG)
                        .show();
                break;
            case R.id.action_settings:
                /*Toast.makeText(this, "Settings", Toast.LENGTH_SHORT)
                        .show();*/

                String subject = "Feedback on Sunaad v" + vn;
                String body ="Hi Team Abheri, \n\nHere is my feedback on Sunaad!";
                String to = "prasmax@gmail.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + subject + "&body=" + body + "&to=" + to);
                intent.setData(data);
                startActivity(intent);
                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static int sn = 1;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            sn = sectionNumber;
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            TextView tv = (TextView)rootView.findViewById(R.id.textView);
            //tv.setText(sn+"");
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
