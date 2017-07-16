package com.abheri.sunaad.view.directory;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Venue;
import com.abheri.sunaad.model.VenueListDataCache;
import com.abheri.sunaad.view.DataRefreshHandler;
import com.abheri.sunaad.view.MainActivity;
import com.abheri.sunaad.view.SunaadFragmentSuperClass;
import com.abheri.sunaad.view.Util;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueDirectoryFragment extends SunaadFragmentSuperClass {

    Context context;
    View rootView;
    ListView listView;
    ProgressBar progressBar;
    TextView errTextView;
    Activity myActivity;
    List<Venue> cachedVenueList;
    int scrollPos=0;
    public boolean doScroll=true;
    boolean refreshRunning=false;

    public VenueDirectoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dir_fragment_venue_directory, container,
                false);

        if(null == context){
            context = rootView.getContext();
        }

        //setHasOptionsMenu(true);

        Log.i("PRAS", "In Venue Directory Fragment");

        // Get ListView object from xml
        listView = (ListView) rootView.findViewById(R.id.venueDirList);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.holo_blue_light);
        listView.setVisibility(View.GONE);

        progressBar = (ProgressBar)rootView.findViewById(R.id.venueDirProgressBar);

        errTextView = (TextView)rootView.findViewById(R.id.serviceErrorText);
        errTextView.setVisibility(View.GONE);

        final android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        myActivity = getActivity();

        getData(this, false);


        //Onclick listener not required for initial implementation
        //Implemented here just for reference
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                // ListView Clicked item value
                Venue itemValue = (Venue) parent.getItemAtPosition(position);

                /*Toast.makeText(
                        view.getContext(),
                        itemValue.getTitle() + "  Selected...",
                        Toast.LENGTH_SHORT).show(); */

                openVenueDetails(itemValue, fragmentManager);
            }

        });

        ((MainActivity)getActivity()).setActionBarTitle(getString(R.string.title_section2));

        if(doScroll) {
            timerDelayRunForScroll(500l);
        }

        ((MainActivity)getActivity()).setActionBarTitle(getString(R.string.title_dir_submenu3));
        // Obtain the shared Tracker instance.
        Util.logToGA(Util.VENUE_DIR_SCREEN, context);

        return rootView;
    }

    private void openVenueDetails(Venue venueToOpen,
                                    android.support.v4.app.FragmentManager fragmentManager){
        Intent artIntent = new Intent();
        artIntent.putExtra("VenueDetails", venueToOpen);
        myActivity.setIntent(artIntent);

        VenueDetailsFragment odf = new VenueDetailsFragment();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, odf,"VenueDetailsFragment");
        ft.addToBackStack(null);
        ft.commit();

    }

    public void timerDelayRunForScroll(final long time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    int h1 = listView.getHeight();
                    int h2 = rootView.getHeight();
                    //scrollPos = Util.getScrollPosition(values);
                    listView.smoothScrollToPositionFromTop(scrollPos, h1 / 2 - h2 / 2, 500);
                } catch (Exception e) {
                }
            }
        }, time);
    }


    public void getData(VenueDirectoryFragment fragmentThis, boolean doRefresh) {

        VenueListDataCache vlc = new VenueListDataCache(context);
        Util ut = new Util();
        DataRefreshHandler drh = new DataRefreshHandler(fragmentThis, doRefresh, context);

        cachedVenueList = vlc.RetrieveVenueDataFromCache();

        //First render the screen with cached data
        if(cachedVenueList != null) {
            progressBar.setVisibility(View.GONE);
            updateVenueViewFromData(cachedVenueList);
        }

        //If network is available refresh the cache and the view
        if (ut.isNetworkAvailable(context)) {
            progressBar.setVisibility(View.VISIBLE);
            drh.updateData();
        }else{
            //If cache is null & network is not available, show error
            if(cachedVenueList == null) {
                errTextView.setText("Connect to network to get Sunaad Data");
                errTextView.setVisibility(View.VISIBLE);
            }
        }
    }


    void updateVenueList(View rootView, ListView venueList, List<Venue> values) {

        //scrollPos = Util.getScrollPosition(values);

        VenueListAdapter adapter;
        adapter = (VenueListAdapter)venueList.getAdapter();

        if(adapter == null){
            adapter = new VenueListAdapter(
                    rootView.getContext(), R.layout.dir_venue_dir_child_item, values);
            venueList.setAdapter(adapter);
        }else{
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.addAll(values);
            adapter.notifyDataSetChanged();
        }

        progressBar.setVisibility(View.GONE);

        if(doScroll) {
            timerDelayRunForScroll(500l);
        }

    }

    @Override
    public void updateVenueViewFromData(List<Venue> values){
        listView.setVisibility(View.VISIBLE);

        //Filter old programs from the list
        //List<Program> fValues = ArtisteDataHelper.filterOldPrograms(values, Util.HOW_OLD);

        updateVenueList(rootView, listView, values);
    }

    @Override
    public void updateOnError(Object result){
        refreshRunning=false;

        Exception e = (Exception)result;
        String st = "";
        if(null != e)
            st = e.toString();

        progressBar.setVisibility(View.GONE);
        errTextView.setText("Service Error Occurred:" + st);
        errTextView.setTextColor(Color.parseColor("#FF0000"));
        errTextView.setVisibility(View.VISIBLE);

    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

}
