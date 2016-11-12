package com.abheri.sunaad.view.program;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abheri.sunaad.R;
import com.abheri.sunaad.controller.SettingsController;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.view.ProportionalImageView;
import com.abheri.sunaad.view.Util;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramDetailsFragment extends Fragment implements View.OnClickListener {

    Context context;
    Program prgObj;
    View rootView;

    String eatariesMsg = "";
    String parkingMsg = "";
    String alarmMg = "";

    public ProgramDetailsFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.prg_fragment_program_details, container, false);
        context = getContext();

        Intent i = getActivity().getIntent();
        prgObj = (Program)i.getExtras().getSerializable("ProgramDetails");

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String eventDate = sdf.format(prgObj.getEventDate()).toString();


        ProportionalImageView iv = (ProportionalImageView)rootView.findViewById(R.id.programImage);
        WebView titleWV = (WebView)rootView.findViewById(R.id.titleWV);

        WebView prgDetailWV = (WebView)rootView.findViewById(R.id.programDetailsWV);

        TextView date = (TextView)rootView.findViewById(R.id.date);
        TextView onText = (TextView)rootView.findViewById(R.id.onText);
        TextView atText = (TextView)rootView.findViewById(R.id.atText);
        TextView place = (TextView)rootView.findViewById(R.id.place);
        TextView line1 = (TextView)rootView.findViewById(R.id.addressLine1);
        TextView line2 = (TextView)rootView.findViewById(R.id.addressLine2);
        TextView city = (TextView)rootView.findViewById(R.id.city);
        TextView state = (TextView)rootView.findViewById(R.id.state);
        TextView phNo = (TextView)rootView.findViewById(R.id.Phone);
        TextView time = (TextView)rootView.findViewById(R.id.time);

        ImageView mapimg = (ImageView)rootView.findViewById(R.id.mapImage);
        ImageView alarmimg = (ImageView)rootView.findViewById(R.id.alarmImage);

        ImageView eatariesImg = (ImageView)rootView.findViewById(R.id.eatariesImage);
        ImageView parkingImg = (ImageView)rootView.findViewById(R.id.parkingImage);

        String imageUri = prgObj.getArtiste_image();
        if(imageUri == null && imageUri.length()<=0){
            imageUri = "@drawable/default_artiste.jpeg";
        }

        imageUri = Util.getImageUrl() + imageUri;

        Picasso.with(context)
                .load(imageUri)
                .placeholder(R.drawable.default_artiste)
                .into(iv);

        Date eDate = prgObj.getEventDate();
        Date tDate = new Date();
        if(Util.isEventToday(prgObj, false) > 0) {
            rootView.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
            prgDetailWV.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
            titleWV.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
            parkingImg.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
            eatariesImg.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));

        }
        else {
            rootView.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));
            prgDetailWV.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));
            titleWV.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));
            parkingImg.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));
            eatariesImg.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));

        }

        if(!Util.isYes(prgObj.getIs_published())){
            rootView.setBackgroundColor(rootView.getResources().getColor(R.color.orange));
        }

        if(Util.isEventToday(prgObj, true) == 1) {


            date.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            onText.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            atText.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            place.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            line1.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            line2.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            city.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            state.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            phNo.setTextColor(rootView.getResources().getColor(R.color.darkblue));
            time.setTextColor(rootView.getResources().getColor(R.color.darkblue));

            //String colorStr="<font color='" +
            //                    Color.parseColor("#"+Integer.toHexString(context.getResources().getColor(R.color.darkblue))) +
             //                   "'>";

            String colorStr="<font color='" +
                                "#0099FF" +
                               "'>";

            prgDetailWV.loadData("<html><body><center><h4>" + colorStr + prgObj.getDetails() + "</h4></center></body></html>",
                    "text/html", "utf-8");

            titleWV.loadData("<html><body><center><h3><u>" + colorStr + prgObj.getTitle() + "</u></h3></center></body></html>",
                    "text/html", "utf-8");
        }
        else {
            prgDetailWV.loadData("<html><body><center><h4><font color='#000000'>" + prgObj.getDetails() + "</h4></center></body></html>",
                    "text/html", "utf-8");

            titleWV.loadData("<html><body><center><h3><u><font color='#000000'> " + prgObj.getTitle() + "</u></h3></center></body></html>",
                    "text/html", "utf-8");

        }


        /*
        //Set the fontsize for the webview_css component
        final WebSettings webSettings = prgDetailWV.getSettings();
        Resources res = getResources();
        float fontSize = res.getDimension(R.dimen.prg_detail_text);
        webSettings.setDefaultFontSize((int)fontSize); */


        date.setText(eventDate);
        place.setText(prgObj.getVenueName());
        line1.setText(prgObj.getVenueAddress1());

        String line2str = prgObj.getVenueAddress2();
        if(line2str != null && !line2str.trim().equals("") &&
                    !line2str.trim().equalsIgnoreCase("null")) {
            line2.setText(prgObj.getVenueAddress2());
        }

        city.setText(prgObj.getVenueCity());
        state.setText(prgObj.getVenueState());
        phNo.setText(prgObj.getOrganizerPhone());
        try {
            time.setText(SettingsController.getFormattedTime(prgObj.getStartTime()));
        }
        catch (Exception e) {
            System.out.print("Time Error");
            e.printStackTrace();
        }

        mapimg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.mapicon));
        mapimg.setOnClickListener(this);

        alarmimg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.alarm));
        alarmimg.setTag(prgObj);
        alarmimg.setOnClickListener(this);



        String isEataries = prgObj.getVenueEataries();
        if(null != isEataries && isEataries.equalsIgnoreCase("yes")){
            eatariesImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.eataries_yes));
            eatariesMsg = "Eataries Are Available at Location";
            eatariesImg.setTag(eatariesMsg);

        }
        else{
            eatariesImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.eataries_no));
            eatariesMsg = "Eataries Not Available at Location";
        }

        String isParking = prgObj.getVenueParking();
        if(null != isParking && isParking.equalsIgnoreCase("yes")){
            parkingImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.parking_yes));
            parkingMsg = "Parking Is Available at Location";
        }
        else{
            parkingImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.parking_no));
            parkingMsg = "Parking Not Available at Location";
        }

        eatariesImg.setOnClickListener(this);
        eatariesImg.setTag(eatariesMsg);

        parkingImg.setOnClickListener(this);
        parkingImg.setTag(parkingMsg);

        /*
        String uri = "geo:"+ prgObj.getVenueCoords();
        //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 13.0104054,77.5488072);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        Context ct = rootView.getContext();
        ct.startActivity(mapIntent);
        */

        return rootView;
    }
    @Override
    public void onClick(View v) {
        System.out.println("map clicked");
        int id = v.getId();
        if(id == R.id.alarmImage){
            String msg="";
            Program pObj = (Program) v.getTag();

            if(pObj.alarm_millis < 0){
                msg = "Alarm Not Set";
            }if(pObj.alarm_millis > 0){

                Date d = new Date();
                d.setTime(pObj.alarm_millis);
                SimpleDateFormat ft = new SimpleDateFormat("dd-MMM hh:mm a");
                msg = "Alarm is set at: " + ft.format(d);
            }

            Toast.makeText(v.getContext(), msg, Toast.LENGTH_LONG).show();
        }else if(id == R.id.mapImage) {
            String uri = "geo:" + prgObj.getVenueCoords() + "?q=" + prgObj.getVenueCoords() +
                    "(Program Location)";
            //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 13.0104054,77.5488072);
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
            Context ct = rootView.getContext();
            ct.startActivity(mapIntent);
        }
        else if(id == R.id.eatariesImage) {
            Toast.makeText(v.getContext(), (String)v.getTag(), Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.parkingImage) {
            Toast.makeText(v.getContext(), (String)v.getTag(), Toast.LENGTH_SHORT).show();
        }
    }

    public void showAlarmStatus(Program pObj, Context ctxt) {


    }

    @Override
    public void onDestroy(){
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("ProgramFragment");
        if(fragment instanceof ProgramFragment) {
            ((ProgramFragment) fragment).doScroll=false;
        }

        super.onDestroy();
    }

}

