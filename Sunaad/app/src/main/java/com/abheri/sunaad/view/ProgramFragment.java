package com.abheri.sunaad.view;


import android.app.Activity;
import android.app.FragmentManager;
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
import android.widget.ViewAnimator;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramDataHandler;
import com.abheri.sunaad.model.ProgramListDataCache;
import com.abheri.sunaad.model.RequestTask;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramFragment extends Fragment implements HandleServiceResponse{

    ViewAnimator viewAnimator;
    Context context;
    String jsonstring;
    View rootView;
    ListView listView;
    ProgressBar progressBar;
    TextView errTextView;
    Activity myActivity;
    List<Program> cachedProgramList;
    int scrollPos=0;
    Program noticePrgObj=null;

    public ProgramFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_program, container,
                false);

        if(null == context){
            context = rootView.getContext();
        }

        Log.i("PRAS", "In ProgramFragment");
        Program.selectedPosition = -1; //Reset the position




        /*Bundle b = getArguments();
        jsonstring = b.getString("jsonstring");
        System.out.println(jsonstring);*/

        // Get ListView object from xml
        listView = (ListView) rootView.findViewById(R.id.programList);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.holo_blue_light);
        listView.setVisibility(View.GONE);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        errTextView = (TextView)rootView.findViewById(R.id.serviceErrorText);
        errTextView.setVisibility(View.GONE);

        final android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        myActivity = getActivity();

        getData(this, false);

        //Automatically open the details if coming from local notification
        Bundle args = getArguments();
        if(null != args) {
            noticePrgObj = (Program) args.getSerializable("SelectedProgram");
            if (null != noticePrgObj) {
                Program nProgObj = noticePrgObj;
                noticePrgObj = null;
                openProgramDetails(nProgObj, fragmentManager);
            }
        }

        //Onclick listener not required for initial implementation
        //Implemented here just for reference
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                // ListView Clicked item value
                Program itemValue = (Program) parent.getItemAtPosition(position);

                /*Toast.makeText(
                        view.getContext(),
                        itemValue.getTitle() + "  Selected...",
                        Toast.LENGTH_SHORT).show(); */

                openProgramDetails(itemValue, fragmentManager);
            }

        });

        ((MainActivity)getActivity()).setActionBarTitle(getString(R.string.title_section2));

        timerDelayRunForScroll(500l);

        // Obtain the shared Tracker instance.
        Util.logToGA(Util.PROGRAM_SCREEN);

        return rootView;
    }

    private void openProgramDetails(Program programToOpen,
                                    android.support.v4.app.FragmentManager fragmentManager){
        Intent prgIntent = new Intent();
        prgIntent.putExtra("ProgramDetails", programToOpen);
        myActivity.setIntent(prgIntent);

        ProgramDetailsFragment pdf = new ProgramDetailsFragment();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, pdf);
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
                    listView.smoothScrollToPositionFromTop(scrollPos, h1 / 2 - h2 / 2, 500);
                } catch (Exception e) {}
            }
        }, time);
    }


    public void getData(ProgramFragment fragmentThis, boolean doRefresh) {

        ProgramListDataCache plc = new ProgramListDataCache(context);
        Util ut = new Util();
        if ((plc.isProgramDataCacheOld() || doRefresh) && ut.isNetworkAvailable(context)) {
            progressBar.setVisibility(View.VISIBLE);
            RequestTask rt = new RequestTask(fragmentThis, SunaadViews.PROGRAM);
            rt.execute(Util.getServiceUrl(SunaadViews.PROGRAM));
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

    void updateProgramList(View rootView, ListView programList, List<Program>values) {

        //ProgramDataHandler prgdata = new ProgramDataHandler();
        //List<Program> values = prgdata.parseProgramListFromJsonResponse(jsonstring);

        scrollPos = Util.getScrollPosition(values);

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ProgramListAdapter adapter = new ProgramListAdapter(
                rootView.getContext(), R.layout.program_list_item, values);

        programList.setAdapter(adapter);
        timerDelayRunForScroll(500l);

    }

    public void onSuccess(Object result) {

        List<Program> values = (List<Program>) result;

        ProgramListDataCache plc = new ProgramListDataCache(context);
        plc.SaveProgramDataInCache((List<Program>) result);

        updateViewFromData(values);
    }

    public void updateViewFromData(List<Program> values){
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        //Filter old programs from the list
        List<Program> fValues = ProgramDataHandler.filterOldPrograms(values, Util.HOW_OLD);

        updateProgramList(rootView, listView, fValues);
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
