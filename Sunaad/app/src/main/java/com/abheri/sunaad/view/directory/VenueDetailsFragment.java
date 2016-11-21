package com.abheri.sunaad.view.directory;


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

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.LocalFileReader;
import com.abheri.sunaad.model.Venue;
import com.abheri.sunaad.view.CustomWebViewClient;
import com.abheri.sunaad.view.Util;
import com.squareup.picasso.Picasso;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueDetailsFragment extends Fragment implements View.OnClickListener {

    Context context;
    Venue venueObj;
    View rootView;

    public VenueDetailsFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        rootView = inflater.inflate(R.layout.dir_fragment_venue_details, container, false);

        Intent i = getActivity().getIntent();
        venueObj = (Venue) i.getExtras().getSerializable("VenueDetails");

        //----- Title/Name ----
        WebView titleWV = (WebView) rootView.findViewById(R.id.vdTitleWV);
        String cssStr = LocalFileReader.readRawResourceFile(context, R.raw.webview_css);

        String titleStr = cssStr + "<html><body><center><h3 class=\"underline\"> " +
                venueObj.getVenue_name() + "</h3></center></body></html>";
        titleWV.loadData(titleStr,"text/html", "utf-8");

        //----- Image ----
        String imageUri = venueObj.getImage();
        if (imageUri == null || imageUri.length() <= 0) {
            imageUri = "@drawable/default_artiste.jpeg";
        }
        else {
            imageUri = Util.getImageUrl() + imageUri;
        }

        ImageView iv = (ImageView) rootView.findViewById(R.id.vdVenueImage);
        Picasso.with(context)
                .load(imageUri)
                .placeholder(R.drawable.default_artiste)
                .into(iv);

        //----- Details ----
        WebView detailWV = (WebView) rootView.findViewById(R.id.venueDetailsWV);

        String htmlStr = cssStr + createVenueHTML(venueObj);
        detailWV.loadData(htmlStr, "text/html", "utf-8");

        //Set WebViewClient such that the link opens in a browser
        detailWV.setWebViewClient(new CustomWebViewClient());

        //------- ICONS ------
        ImageView mapIcon = (ImageView) rootView.findViewById(R.id.vdMapImage);
        mapIcon.setOnClickListener(this);

        ImageView parkingIcon = (ImageView) rootView.findViewById(R.id.vdParkingImage);
        ImageView eatariesIcon = (ImageView) rootView.findViewById(R.id.vdEatariesImage);

        String parking = venueObj.getParking();
        if(parking != null && parking.equalsIgnoreCase("yes")){
            parkingIcon.setImageResource(R.drawable.parking_yes);
        }else{
            parkingIcon.setImageResource(R.drawable.parking_no);
        }

        String eataries = venueObj.getEataries();
        if(eataries != null && eataries.equalsIgnoreCase("yes")){
            eatariesIcon.setImageResource(R.drawable.eataries_yes);
        }else{
            eatariesIcon.setImageResource(R.drawable.eataries_no);
        }

        return rootView;
    }


    String createVenueHTML(Venue venueObj) {

        String htmlStr = "";
        UrlValidator urlValidator = new UrlValidator();

        htmlStr += "<html><body class=\"class1\">";
        htmlStr +=  venueObj.getVenue_name() + ", is an great venue for music ";
        htmlStr += "<br><br>";
        htmlStr += "<u><i>Contact Details:</i></u><br>";
        String ad = venueObj.getAddress1();
        if(ad != null & ad.length() > 0) {
            htmlStr += ad + "<br>";
        }
        ad = venueObj.getAddress2();
        if(ad != null & ad.length() > 0) {
            htmlStr += ad + "<br>";
        }
        htmlStr += venueObj.getCity();
        String pc = venueObj.getPincode();
        if(pc != null && pc.length() > 0) {
            htmlStr += " - " + pc + "<br>";
        }
        htmlStr += venueObj.getState() + "<br>";
        htmlStr += venueObj.getCountry() + "<br>";

        String venuePhone = venueObj.getPhone();
        if(venuePhone != null && !venuePhone.toLowerCase().startsWith("ph")) {
            htmlStr += "Ph: <a href=\"tel:" + venuePhone + "\">" + venuePhone + "</a>";
        }
        htmlStr += "<br><br>";

        String venueWebSite = venueObj.getWebsite();
        if(venueWebSite != null && urlValidator.isValid(venueWebSite)) {
            htmlStr += "Visit <a href=\"" + venueWebSite + "\" target=\"_top\">Website</a><br>";
        }

        htmlStr += "</body>";

        return htmlStr;

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.vdMapImage) {
            String uri = "geo:" + venueObj.getMapcoords() + "?q=" + venueObj.getMapcoords() +
                    "(Program Location)";
            //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 13.0104054,77.5488072);
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
            Context ct = rootView.getContext();
            ct.startActivity(mapIntent);
        }
    }
}
