package com.abheri.sunaad.view.program;


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
import android.widget.ViewAnimator;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramDataHelper;
import com.abheri.sunaad.model.ProgramListDataCache;
import com.abheri.sunaad.view.DataRefreshHandler;
import com.abheri.sunaad.view.MainActivity;
import com.abheri.sunaad.view.SunaadFragmentSuperClass;
import com.abheri.sunaad.view.Util;


import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramFragment extends SunaadFragmentSuperClass {

    Context context;
    View rootView;
    ListView listView;
    ProgressBar progressBar;
    TextView errTextView;
    Activity myActivity;
    List<Program> cachedProgramList;
    int scrollPos=0;
    Program noticePrgObj=null;
    public boolean doScroll=true;
    boolean refreshRunning=false;


    public ProgramFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.prg_fragment_program, container,
                false);

        if(null == context){
            context = rootView.getContext();
        }

        //setHasOptionsMenu(true);

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
                //Program nProgObj = noticePrgObj;
                //noticePrgObj = null;
                args.remove("SelectedProgram");
                openProgramDetails(noticePrgObj, fragmentManager);
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

        if(doScroll) {
            timerDelayRunForScroll(500l);
        }



        // Obtain the shared Tracker instance.
        Util.logToGA(Util.PROGRAM_SCREEN, context);

        return rootView;
    }

    private void openProgramDetails(Program programToOpen,
                                    android.support.v4.app.FragmentManager fragmentManager){
        Intent prgIntent = new Intent();
        prgIntent.putExtra("ProgramDetails", programToOpen);
        myActivity.setIntent(prgIntent);

        ProgramDetailsFragment pdf = new ProgramDetailsFragment();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, pdf,"ProgramDetailsFragment");
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


    public void getData(ProgramFragment fragmentThis, boolean doRefresh) {

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


    void updateProgramList(View rootView, ListView programList, List<Program>values) {

        scrollPos = Util.getScrollPosition(values);

        ProgramListAdapter adapter;
        adapter = (ProgramListAdapter)programList.getAdapter();

        if(adapter == null){
            adapter = new ProgramListAdapter(
                    rootView.getContext(), R.layout.prg_program_list_item, values);
            programList.setAdapter(adapter);
        }else{
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.addAll(values);
            adapter.notifyDataSetChanged();
        }

        if(doScroll) {
            timerDelayRunForScroll(500l);
        }

    }

    public void updateViewFromData(List<Program> values){
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        //Filter old programs from the list
        List<Program> fValues = ProgramDataHelper.filterOldPrograms(values, Util.HOW_OLD);

        updateProgramList(rootView, listView, fValues);
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

}
