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
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramDataHelper;
import com.abheri.sunaad.model.ProgramListDataCache;
import com.abheri.sunaad.model.CloudDataFetcherAsyncTask;
import com.abheri.sunaad.view.HandleServiceResponse;
import com.abheri.sunaad.view.MainActivity;
import com.abheri.sunaad.view.SunaadViews;
import com.abheri.sunaad.view.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventtypeFragment extends Fragment implements HandleServiceResponse {

    ViewAnimator viewAnimator;
    Context context;
    View rootView;
    List<String> eList = new ArrayList<String>();
    LinkedHashMap<String, List<Program>> eventtypeProgramCollection = new LinkedHashMap<String, List<Program>>();

    ExpandableListView expListView;
    ProgressBar progressBar;
    TextView errTextView;
    Activity myActivity;
    List<Program> cachedProgramList;

    public EventtypeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.prg_fragment_eventtype, container,
                false);

        if(null == context){
            context = rootView.getContext();
        }

        Log.i("PRAS", "In VenueFragment");
        Program.selectedPosition = -1; //Reset the position

        /*Bundle b = getArguments();
        jsonstring = b.getString("jsonstring");
        System.out.println(jsonstring);*/

        // Get ListView object from xml
        expListView = (ExpandableListView) rootView.findViewById(R.id.eventtypeExpList);


        expListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        expListView.setSelector(android.R.color.holo_blue_light);
        expListView.setVisibility(View.GONE);

        progressBar = (ProgressBar)rootView.findViewById(R.id.eventtypeProgressBar);
        progressBar.setVisibility(View.VISIBLE);

        errTextView = (TextView)rootView.findViewById(R.id.eventtypeServiceErrorText);
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
                Program itemValue = (Program)expListView.getExpandableListAdapter().getChild( groupPosition, childPosition);

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

        ((MainActivity)getActivity()).setActionBarTitle(getString(R.string.title_section6));
        Util.logToGA(Util.SABHA_SCREEN);

        return rootView;
    }

    public void getData(EventtypeFragment fragmentThis, boolean doRefresh) {

        ProgramListDataCache plc = new ProgramListDataCache(context);
        Util ut = new Util();
        if ((plc.isProgramDataCacheOld() || doRefresh) && ut.isNetworkAvailable(context)) {
            CloudDataFetcherAsyncTask rt = new CloudDataFetcherAsyncTask(fragmentThis, SunaadViews.SABHA, context);
            rt.execute(Util.getServiceUrl(SunaadViews.SABHA));
        } else {
            cachedProgramList = plc.RetrieveProgramDataFromCache();
            //If network is available the cached list will be non-null
            //Else it will be null. If null, show error text
            if(cachedProgramList != null) {
                updateViewFromData(cachedProgramList);
            }
            else {
                errTextView.setText("Connect to network to get Sunaad Data");
                errTextView.setVisibility(View.VISIBLE);
            }
        }

    }


    void updateEventtypeList(View rootView, ListView sabhaList, List<String> aList, LinkedHashMap<String, List<Program>> sabhaProgramCollection) {
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        final EventtypeExpandableListAdapter expListAdapter = new EventtypeExpandableListAdapter(
                                    myActivity, aList, (Map)eventtypeProgramCollection);
        expListView.setAdapter(expListAdapter);
    }

    public void onSuccess(Object result) {

        List<Program> values = (List<Program>) result;

        ProgramListDataCache plc = new ProgramListDataCache(context);
        plc.SaveProgramDataInCache((List<Program>) result);

        updateViewFromData(values);
    }

    public void updateViewFromData(List<Program> values){

        //Filter old programs from the list
        List<Program> fValues = ProgramDataHelper.filterOldPrograms(values, Util.HOW_OLD);

        ProgramDataHelper pdh = new ProgramDataHelper();
        eList = pdh.getEventtypeListFromPrograms(fValues);
        eventtypeProgramCollection = pdh.createEventtypeProgramCollection(fValues, eList);

        progressBar.setVisibility(View.GONE);
        expListView.setVisibility(View.VISIBLE);
        updateEventtypeList(rootView, expListView, eList, eventtypeProgramCollection);

    }

    public void onError(Object result){

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
