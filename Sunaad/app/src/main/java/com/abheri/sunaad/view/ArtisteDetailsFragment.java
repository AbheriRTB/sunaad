package com.abheri.sunaad.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.Program;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtisteDetailsFragment extends Fragment {

    Context context;

    public ArtisteDetailsFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program_details, container, false);

        Intent i = getActivity().getIntent();
        Program prgObj = (Program)i.getExtras().getSerializable("ProgramDetails");

        DateFormat df = new SimpleDateFormat("dd/mmm/yyyy");


        TextView title = (TextView)rootView.findViewById(R.id.title);

        //TextView name = (TextView)rootView.findViewById(R.id.programDetails);
        TextView date = (TextView)rootView.findViewById(R.id.date);
        //TextView time = (TextView)rootView.findViewById(R.id.time);
        TextView place = (TextView)rootView.findViewById(R.id.place);
        TextView phNo = (TextView)rootView.findViewById(R.id.Phone);
        TextView eateriesNY = (TextView)rootView.findViewById(R.id.eateriesYN);
        TextView parkingYN = (TextView)rootView.findViewById(R.id.parkingYN);


        title.setText(prgObj.getTitle());
        //name.setText(prgObj.getDetails());
        date.setText(df.format(prgObj.getEventDate()));
        //time.setText(prgObj.getStartTime());
        place.setText(prgObj.getPlace());
        phNo.setText(prgObj.getPhone());
        eateriesNY.setText(prgObj.getLocation_eataries());
        parkingYN.setText(prgObj.getParking());

        return rootView;
    }

}
