package com.abheri.sunaad.view;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.GetDataForProgramFragment;
import com.abheri.sunaad.dao.Program;
import com.abheri.sunaad.dao.RequestTask;

import java.lang.annotation.Target;
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

    public ProgramFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_program, container,
                false);

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

        RequestTask rt = new RequestTask(this, SunaadViews.PROGRAM);
        rt.execute(Util.getServiceUrl(SunaadViews.PROGRAM));

        final android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        myActivity = getActivity();

        //Onclick listener not required for initial implementation
        //Implemented here just for reference
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                // ListView Clicked item value
                Program itemValue = (Program) parent.getItemAtPosition(position);

                Toast.makeText(
                        view.getContext(),
                        itemValue.getTitle() + "  Selected...",
                        Toast.LENGTH_SHORT).show();

                Intent prgIntent = new Intent();
                prgIntent.putExtra("ProgramDetails", itemValue);
                myActivity.setIntent(prgIntent);

                ProgramDetailsFragment pdf = new ProgramDetailsFragment();

                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, pdf);
                ft.addToBackStack(null);
                ft.commit();
            }

        });

        return rootView;
    }

    void updateProgramList(View rootView, ListView programList, List<Program>values) {

        //GetDataForProgramFragment prgdata = new GetDataForProgramFragment();
        //List<Program> values = prgdata.getData(jsonstring);

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ProgramListAdapter adapter = new ProgramListAdapter(
                rootView.getContext(), R.layout.program_list_item, values);

        programList.setAdapter(adapter);
    }

    public void onSuccess(Object result){

        List<Program> values = (List<Program>)result;

        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        updateProgramList(rootView, listView, values);

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
