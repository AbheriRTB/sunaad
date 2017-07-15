package com.abheri.sunaad.view.program;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.ProgramDataHelper;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramListDataCache;
import com.abheri.sunaad.view.DataRefreshHandler;
import com.abheri.sunaad.view.MainActivity;
import com.abheri.sunaad.view.SunaadFragmentSuperClass;
import com.abheri.sunaad.view.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtisteFragment extends SunaadFragmentSuperClass {

    ViewAnimator viewAnimator;
    Context context;
    String jsonstring;
    View rootView;
    List<String> aList = new ArrayList<String>();
    LinkedHashMap<String, List<Program>> artisteProgramCollection = new LinkedHashMap<String, List<Program>>();

    ExpandableListView expListView;
    ProgressBar progressBar;
    TextView errTextView;
    Activity myActivity;
    List<Program> cachedProgramList;

    public ArtisteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.prg_fragment_artiste, container,
                false);

        if(null == context){
            context = rootView.getContext();
        }

        Log.i("PRAS", "In ArtisteFragment");
        Program.selectedPosition = -1; //Reset the position

        /*Bundle b = getArguments();
        jsonstring = b.getString("jsonstring");
        System.out.println(jsonstring);*/

        // Get ListView object from xml
        expListView = (ExpandableListView) rootView.findViewById(R.id.artisteExpList);


        expListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        expListView.setSelector(android.R.color.holo_blue_light);
        expListView.setVisibility(View.GONE);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        errTextView = (TextView)rootView.findViewById(R.id.serviceErrorText);
        errTextView.setVisibility(View.GONE);

        final android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        myActivity = getActivity();

        getData(this, false);



        //Onclick listener not required for initial implementation
        //Implemented here just for reference
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // ListView Clicked item value
                Program itemValue = (Program) expListView.getExpandableListAdapter().getChild(groupPosition, childPosition);

                /*Toast.makeText(
                        v.getContext(),
                        itemValue.getTitle() + "  Selected...",
                        Toast.LENGTH_SHORT).show();*/

                Intent prgIntent = new Intent();
                prgIntent.putExtra("ProgramDetails", itemValue);
                myActivity.setIntent(prgIntent);

                ProgramDetailsFragment pdf = new ProgramDetailsFragment();

                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, pdf);
                ft.addToBackStack(null);
                ft.commit();

                return true;
            }

        });

        ((MainActivity)getActivity()).setActionBarTitle(getString(R.string.title_section3));
        // Obtain the shared Tracker instance.
        Util.logToGA(Util.ARTISTE_SCREEN);

        return rootView;
    }

    public void getData(ArtisteFragment fragmentThis, boolean doRefresh){

        ProgramListDataCache plc = new ProgramListDataCache(context);
        cachedProgramList = plc.RetrieveProgramDataFromCache();
        Util ut = new Util();
        DataRefreshHandler drh = new DataRefreshHandler(fragmentThis, doRefresh, context);

        if(cachedProgramList != null) {
            updateViewFromData(cachedProgramList);
        }

        if (ut.isNetworkAvailable(context)) {
            drh.updateData();
        } else {
            if(cachedProgramList == null) {
                errTextView.setText("Connect to network to get Sunaad Data");
                errTextView.setVisibility(View.VISIBLE);
            }
        }

    }

    void updateArtisteList(View rootView, ListView artisteList, List<String> aList, LinkedHashMap<String, List<Program>> artisteProgramCollection) {


        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        final ArtisteExpandableListAdapter expListAdapter = new ArtisteExpandableListAdapter(
                                    myActivity, aList, (Map)artisteProgramCollection);
        expListView.setAdapter(expListAdapter);
    }


    public void updateViewFromData(List<Program> values){

        //Filter old programs from the list
        List<Program> fValues = ProgramDataHelper.filterOldPrograms(values, Util.HOW_OLD);

        ProgramDataHelper gdp = new ProgramDataHelper();
        aList = gdp.getArtisteListFromPrograms(fValues);
        artisteProgramCollection = gdp.createArtisteProgramCollection(fValues, aList);

        progressBar.setVisibility(View.GONE);
        expListView.setVisibility(View.VISIBLE);
        updateArtisteList(rootView, expListView, aList, artisteProgramCollection);

    }

    @Override
    public void updateOnError(Object result){

        Exception e = (Exception)result;
        String st = "";
        if(null != e)
            st = e.toString();

        progressBar.setVisibility(View.GONE);
        errTextView.setText("Service Error Occurred:" + st);
        errTextView.setTextColor(Color.parseColor("#FF0000"));
        errTextView.setVisibility(View.VISIBLE);

    }



}
