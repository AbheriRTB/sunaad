package com.abheri.sunaad.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.DBHelper;
import com.abheri.sunaad.model.Program;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoTools;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    //private NavigationDrawerFragment mNavigationDrawerFragment;
    private NavigationView mNavigationView = null;
    public DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    MenuItem gMenuItem;
    Program noticePrgObj;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main2);
        context = getApplicationContext();

        noticePrgObj = null;
        Bundle args = getIntent().getExtras();
        if(null != args) {

            noticePrgObj = (Program) args.getSerializable("SelectedProgram");
            getIntent().removeExtra("SelectedProgram");
        }

    /*
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    */

        //mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        if(null == mNavigationView) {
            setupToolbar();
            initNavigationDrawer();
        }


        //Force DB OnCreate & OnUpgrade
        DBHelper dbh = new DBHelper(context);
        dbh.getWritableDatabase();

        // Obtain the shared Tracker instance.
        //AnalyticsApplication application = (AnalyticsApplication) getApplication();
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        Util.logToGA(Util.HOME_SCREEN);

        //Navigate to Home screen by default
        //If coming from notification, navigate to ProgramDetails screen
        autoNavigate();
    }

    private void autoNavigate() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction =
                fragmentManager.beginTransaction();

        Bundle args = new Bundle();
        //args.putSerializable(Util.NAVIGATION_FRAGMET, (Serializable) mDrawerLayout);

        //Navigate to Home screen by default
        //If coming from notification, navigate to ProgramDetails screen
        if(null != noticePrgObj){
            args.putSerializable("SelectedProgram", noticePrgObj);
            ProgramFragment pf = new ProgramFragment();
            pf.setArguments(args);
            transaction.replace(R.id.container, pf,"ProgramFragment");
        }else {
            HomeFragment hf = new HomeFragment();
            hf.setArguments(args);
            transaction.replace(R.id.container, hf);
        }
        //Don't add addToBackStack here
        transaction.commit();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_launcher);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigationDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        setupActionBarDrawerToogle();
        if (mNavigationView != null) {
            mNavigationView.setItemIconTintList(null);
            setupDrawerContent(mNavigationView);
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }

    public DrawerLayout getDrawerLayout(){
        return mDrawerLayout;
    }

    private void setupActionBarDrawerToogle() {

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            //public void onDrawerClosed(View view) {
            //    Snackbar.make(view, R.string.drawer_close, Snackbar.LENGTH_SHORT).show();
            //}

            /**
             * Called when a drawer has settled in a completely open state.
             */
            //public void onDrawerOpened(View drawerView) {
            //    Snackbar.make(drawerView, R.string.drawer_open, Snackbar.LENGTH_SHORT).show();
            //}
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void setupDrawerContent(NavigationView navigationView) {

        //setting up selected item listener
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        //menuItem.setChecked(true);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        gMenuItem = menuItem;

        /*Toast.makeText(
                this.getApplicationContext(),
                "Drawer Item" + menuItem.getTitle() + "  Selected...",
                Toast.LENGTH_SHORT).show();*/
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                // this code will be executed after 2 seconds
                FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction =
                        fragmentManager.beginTransaction();

                if (gMenuItem.getItemId() == R.id.nav_home) {
                    Bundle args = new Bundle();
                    //args.putSerializable(Util.NAVIGATION_FRAGMET, (Serializable) mDrawerLayout);

                    HomeFragment hf = new HomeFragment();
                    hf.setArguments(args);
                    transaction.replace(R.id.container, hf);
                    //Don't add addToBackStack here
                    transaction.commit();
                } else if (gMenuItem.getItemId() == R.id.navigation_sub_item_1) {
                    ProgramFragment pf = new ProgramFragment();
                    transaction.replace(R.id.container, pf, "ProgramFragment");
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (gMenuItem.getItemId() == R.id.navigation_sub_item_2) {
                    ArtisteFragment af = new ArtisteFragment();
                    transaction.replace(R.id.container, af);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (gMenuItem.getItemId() == R.id.navigation_sub_item_3) {
                    VenueFragment sf = new VenueFragment();
                    transaction.replace(R.id.container, sf);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (gMenuItem.getItemId() == R.id.navigation_sub_item_4) {
                    OrganizerFragment of = new OrganizerFragment();
                    transaction.replace(R.id.container, of);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (gMenuItem.getItemId() == R.id.navigation_sub_item_5) {
                    EventtypeFragment ef = new EventtypeFragment();
                    transaction.replace(R.id.container, ef);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (gMenuItem.getItemId() == R.id.navigation_dir_sub_item_1) {
                    ArtisteDirectoryFragment adf = new ArtisteDirectoryFragment();
                    transaction.replace(R.id.container, adf);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else {
                    transaction.replace(R.id.container, PlaceholderFragment.newInstance(0 + 1));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        }, 400);


        return true;
    }


    /*
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        *Toast.makeText(
                this.getApplicationContext(),
                "Drawer Item" + position + "  Selected...",
                Toast.LENGTH_SHORT).show(); *


        FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction transaction =
                                    fragmentManager.beginTransaction();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                fragmentManager.findFragmentById(R.id.navigation_drawer);

        if(position == 0){
            Bundle args = new Bundle();
            args.putSerializable(Util.NAVIGATION_FRAGMET, mNavigationDrawerFragment);

            HomeFragment hf =  new HomeFragment();
            hf.setArguments(args);
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
            VenueFragment sf =  new VenueFragment();
            transaction.replace(R.id.container,  sf);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(position == 4){
            OrganizerFragment sf =  new OrganizerFragment();
            transaction.replace(R.id.container,  sf);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(position == 5){
            EventtypeFragment ef =  new EventtypeFragment();
            transaction.replace(R.id.container,  ef);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            transaction.replace(R.id.container, PlaceholderFragment.newInstance(position + 1));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    */

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_menu1);
                break;
            case 2:
                mTitle = getString(R.string.title_submenu1);
                break;
            case 3:
                mTitle = getString(R.string.title_submenu2);
                break;
            case 4:
                mTitle = getString(R.string.title_submenu3);
                break;
            case 5:
                mTitle = getString(R.string.title_submenu4);
                break;
            case 6:
                mTitle = getString(R.string.title_submenu5);
                break;
            case 7:
                mTitle = getString(R.string.title_dir_submenu1);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
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

        FragmentManager fragmentManager;

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
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            // action with ID action_refresh was selected
            case R.id.action_refresh:

                GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
                Util.logToGA(Util.REFRESH_CALLED);

                /* Find which fragment is active when refresh button is pressed
                 * Call corresponding 'getData()' method with force refresh
                 * (second argument as true).
                 *
                 * ProgramDetailsFragment does not have getData method. Hence user
                 * has to go back one screen to ProgramFragment to refresh the data
                 */
                fragmentManager = getSupportFragmentManager();
                Fragment f = fragmentManager.findFragmentById(R.id.container);
                String fragname="";

                //If refresh is called, clear the picasso cache
                Picasso p = Picasso.with(getApplicationContext());
                PicassoTools.clearCache(p);

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
                }else if(f instanceof VenueFragment){
                    fragname="VenueFragment";
                    ((VenueFragment) f).getData((VenueFragment)f, true);
                }else if(f instanceof OrganizerFragment) {
                    fragname = "OrganizerFragment";
                    ((OrganizerFragment) f).getData((OrganizerFragment)f, true);
                }
                    //Toast.makeText(this, "Refresh selected:"+fragname, Toast.LENGTH_SHORT).show();
                break;
            // action with ID action_settings was selected
            case R.id.action_about:
                Toast.makeText(this, "Sunaad: (v"+vn +") - By Team Abheri", Toast.LENGTH_LONG)
                        .show();
                break;
            case R.id.action_feedback:
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
            case R.id.action_settings:
                /*Toast.makeText(this, "Settings", Toast.LENGTH_SHORT)
                        .show();*/

                fragmentManager = getSupportFragmentManager();

                android.support.v4.app.FragmentTransaction transaction =
                        fragmentManager.beginTransaction();

                SettingsFragment sf =  new SettingsFragment();
                transaction.replace(R.id.container,  sf);
                transaction.addToBackStack(null);
                transaction.commit();


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

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
