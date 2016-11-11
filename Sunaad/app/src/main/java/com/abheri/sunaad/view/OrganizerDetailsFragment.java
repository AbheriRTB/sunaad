package com.abheri.sunaad.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Artiste;
import com.abheri.sunaad.model.LocalFileReader;
import com.abheri.sunaad.model.Organizer;
import com.squareup.picasso.Picasso;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizerDetailsFragment extends Fragment {

    Context context;

    public OrganizerDetailsFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        View rootView = inflater.inflate(R.layout.fragment_organizer_details, container, false);

        Intent i = getActivity().getIntent();
        Organizer orgObj = (Organizer) i.getExtras().getSerializable("OrganizerDetails");

        //----- Title/Name ----
        WebView titleWV = (WebView) rootView.findViewById(R.id.odTitleWV);
        String cssStr = LocalFileReader.readRawResourceFile(context, R.raw.webview_css);

        String titleStr = cssStr + "<html><body><center><h3 class=\"underline\"> " +
                orgObj.getOrganizerName() + "</h3></center></body></html>";
        titleWV.loadData(titleStr,"text/html", "utf-8");

        //----- Image ----
        String imageUri = orgObj.getOrganizerLogo();
        if (imageUri == null || imageUri.length() <= 0) {
            imageUri = "@drawable/default_artiste.jpeg";
        }
        else {
            imageUri = Util.getImageUrl() + imageUri;
        }

        ImageView iv = (ImageView) rootView.findViewById(R.id.odOrganizerLogo);
        Picasso.with(context)
                .load(imageUri)
                .placeholder(R.drawable.default_artiste)
                .into(iv);

        //----- Details ----
        WebView detailWV = (WebView) rootView.findViewById(R.id.organizereDetailsWV);

        String htmlStr = cssStr + createArtisteHTML(orgObj);
        detailWV.loadData(htmlStr, "text/html", "utf-8");

        //Set WebViewClient such that the link opens in a browser
        detailWV.setWebViewClient(new CustomWebViewClient());

        return rootView;
    }


    String createArtisteHTML(Organizer orgObj) {

        String htmlStr = "";
        UrlValidator urlValidator = new UrlValidator();

        htmlStr += "<html><body class=\"class1\">";
        htmlStr +=  orgObj.getOrganizerName() + ", is an great organizer ";
        htmlStr += "<br><br>";
        htmlStr += "<u><i>Contact Details:</i></u><br>";
        htmlStr += orgObj.getOrganizerAddress1() + "<br>";
        htmlStr += orgObj.getOrganizerAddress2() + "<br>";
        htmlStr += orgObj.getOrganizerCity() + " - " + orgObj.getOrganizerPincode() + "<br>";
        htmlStr += orgObj.getOrganizerState() + "<br>";
        htmlStr += orgObj.getOrganizerCountry() + "<br>";

        String artPhone = orgObj.getOrganizerPhone();
        if(artPhone != null) {
            htmlStr += "Ph: <a href=\"tel:" + artPhone + "\">" + artPhone + "</a>";
        }
        htmlStr += "<br><br>";

        /*String artWebSite = orgObj.getOrganizerWebsite();
        if(artWebSite != null && urlValidator.isValid(artWebSite)) {
            htmlStr += "Visit <a href=\"" + orgObj.getArtisteWebsite() + "\" target=\"_top\">Website</a><br>";
        }*/

        htmlStr += "</body>";

        return htmlStr;

    }


}
