package com.abheri.sunaad.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.GetDataForHomeFragment;
import com.abheri.sunaad.dao.Program;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramDetailsFragment extends Fragment {

    Context context;

    public ProgramDetailsFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program_details, container, false);

        Intent i = getActivity().getIntent();
        Program prgObj = (Program)i.getExtras().getSerializable("ProgramDetails");

        TextView name = (TextView)rootView.findViewById(R.id.artistName);


        name.setText(prgObj.getTitle());



        return rootView;
    }

}
