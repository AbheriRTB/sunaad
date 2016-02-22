package com.abheri.sunaad.view;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.Program;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramDetailsFragment extends Fragment implements View.OnClickListener {

    Context context;
    Program prgObj;
    View rootView;

    public ProgramDetailsFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_program_details, container, false);

        Intent i = getActivity().getIntent();
        prgObj = (Program)i.getExtras().getSerializable("ProgramDetails");

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String eventDate = sdf.format(prgObj.getEventDate()).toString();


        ProportionalImageView iv = (ProportionalImageView)rootView.findViewById(R.id.programImage);
        WebView titleWV = (WebView)rootView.findViewById(R.id.titleWV);

        WebView prgDetailWV = (WebView)rootView.findViewById(R.id.programDetailsWV);

        TextView date = (TextView)rootView.findViewById(R.id.date);
        TextView place = (TextView)rootView.findViewById(R.id.place);
        TextView line1 = (TextView)rootView.findViewById(R.id.addressLine1);
        TextView line2 = (TextView)rootView.findViewById(R.id.addressLine2);
        TextView city = (TextView)rootView.findViewById(R.id.city);
        TextView state = (TextView)rootView.findViewById(R.id.state);
        TextView phNo = (TextView)rootView.findViewById(R.id.Phone);

        ImageView mapimg = (ImageView)rootView.findViewById(R.id.mapImage);

        ImageView eatariesImg = (ImageView)rootView.findViewById(R.id.eateriesImage);
        ImageView parkingImg = (ImageView)rootView.findViewById(R.id.parkingImage);

        String imageUri = prgObj.getArtiste_image();
        if(imageUri == null && imageUri.length()<=0){
            imageUri = "@drawable/music_image";
        }

        imageUri = Util.getImageUrl() + imageUri;

        Picasso.with(context)
                .load(imageUri)
                .into(iv);

        Date eDate = prgObj.getEventDate();
        Date tDate = new Date();
        if(null != eDate && eDate.compareTo(tDate) < 0){
            rootView.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));
            prgDetailWV.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));
            titleWV.setBackgroundColor(rootView.getResources().getColor(R.color.oldgray));

        }
        else {
            rootView.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
            prgDetailWV.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
            titleWV.setBackgroundColor(rootView.getResources().getColor(android.R.color.white));
        }

        /*
        //Set the fontsize for the webview component
        final WebSettings webSettings = prgDetailWV.getSettings();
        Resources res = getResources();
        float fontSize = res.getDimension(R.dimen.prg_detail_text);
        webSettings.setDefaultFontSize((int)fontSize); */
        prgDetailWV.loadData("<html><body><center><h4>" + prgObj.getDetails() + "</h4></center></body></html>",
                "text/html", "utf-8");

        titleWV.loadData("<html><body><center><h3><u> " + prgObj.getTitle() + "</u></h3></center></body></html>",
                "text/html", "utf-8");

        date.setText(eventDate);
        place.setText(prgObj.getPlace());
        line1.setText(prgObj.getLocation_address1());

        String line2str = prgObj.getLocation_address2();
        if(line2str != null && !line2str.trim().equals("") &&
                    !line2str.trim().equalsIgnoreCase("null")) {
            line2.setText(prgObj.getLocation_address2());
        }

        city.setText(prgObj.getLocation_city());
        state.setText(prgObj.getLocation_state());
        phNo.setText(prgObj.getPhone());

        mapimg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.map_icon));
        mapimg.setOnClickListener(this);


        String isEataries = prgObj.getLocation_eataries();
        if(null != isEataries && isEataries.equalsIgnoreCase("yes")){
            eatariesImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.yes_icon));
        }
        else{
            eatariesImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.no_icon));

        }

        String isParking = prgObj.getParking();
        if(null != isParking && isParking.equalsIgnoreCase("yes")){
            parkingImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.yes_icon));
        }
        else{
            parkingImg.setImageDrawable(rootView.getResources().getDrawable(R.drawable.no_icon));

        }


        /*
        String uri = "geo:"+ prgObj.getLocation_coords();
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
        String uri = "geo:"+ prgObj.getLocation_coords() + "?q=" + prgObj.getLocation_coords() +
                  "(Program Location)";
        //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 13.0104054,77.5488072);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        Context ct = rootView.getContext();
        ct.startActivity(mapIntent);
    }

}

