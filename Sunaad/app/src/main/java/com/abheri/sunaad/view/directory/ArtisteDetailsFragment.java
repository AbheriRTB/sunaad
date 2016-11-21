package com.abheri.sunaad.view.directory;


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
import com.abheri.sunaad.view.CustomWebViewClient;
import com.abheri.sunaad.view.Util;
import com.squareup.picasso.Picasso;
import org.apache.commons.validator.routines.UrlValidator;

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

        context = getContext();
        View rootView = inflater.inflate(R.layout.dir_fragment_artiste_details, container, false);

        Intent i = getActivity().getIntent();
        Artiste artObj = (Artiste) i.getExtras().getSerializable("ArtisteDetails");

        //----- Title/Name ----
        WebView titleWV = (WebView) rootView.findViewById(R.id.adTitleWV);
        String cssStr = LocalFileReader.readRawResourceFile(context, R.raw.webview_css);

        String titleStr = cssStr + "<html><body><center><h3 class=\"underline\"> " +
                artObj.getArtisteName() + "</h3></center></body></html>";
        titleWV.loadData(titleStr,"text/html", "utf-8");

        //----- Image ----
        String imageUri = artObj.getArtisteImage();
        if (imageUri == null && imageUri.length() <= 0) {
            imageUri = "@drawable/default_artiste.jpeg";
        }
        imageUri = Util.getImageUrl() + imageUri;

        ImageView iv = (ImageView) rootView.findViewById(R.id.adArtisteImage);
        Picasso.with(context)
                .load(imageUri)
                .placeholder(R.drawable.default_artiste)
                .into(iv);

        //----- Details ----
        WebView detailWV = (WebView) rootView.findViewById(R.id.artisteDetailsWV);

        String htmlStr = cssStr + createArtisteHTML(artObj);
        detailWV.loadData(htmlStr, "text/html", "utf-8");

        //Set WebViewClient such that the link opens in a browser
        detailWV.setWebViewClient(new CustomWebViewClient());

        return rootView;
    }


    String createArtisteHTML(Artiste artObj) {

        String htmlStr = "";
        UrlValidator urlValidator = new UrlValidator();

        htmlStr += "<html><body class=\"class1\">";
        htmlStr += "Vid. " + artObj.getArtisteName() + ", is an accomplished ";
        htmlStr += getSpeciality(artObj.getArtisteInstrument());
        htmlStr += "<br><br>";
        htmlStr += "<u><i>Contact Details:</i></u><br>";
        String ad = artObj.getArtisteAddress1();
        if(ad != null & ad.length() > 0) {
            htmlStr += ad + "<br>";
        }
        ad = artObj.getArtisteAddress2();
        if(ad != null & ad.length() > 0) {
            htmlStr += ad + "<br>";
        }
        htmlStr += artObj.getArtisteCity();

        String pc = artObj.getArtistePincode();
        if(pc != null && pc.length() > 0) {
            htmlStr += " - " + artObj.getArtistePincode() + "<br>";
        }
        htmlStr += artObj.getArtisteState() + "<br>";
        htmlStr += artObj.getArtisteCountry() + "<br>";

        String artPhone = artObj.getArtistePhone();
        if(artPhone != null && !artPhone.toLowerCase().startsWith("ph")) {
            htmlStr += "Ph: <a href=\"tel:" + artPhone + "\">" + artPhone + "</a>";
        }
        htmlStr += "<br><br>";

        String artWebSite = artObj.getArtisteWebsite();
        if(artWebSite != null && urlValidator.isValid(artWebSite)) {
            htmlStr += "Visit <a href=\"" + artObj.getArtisteWebsite() + "\" target=\"_top\">Website</a><br>";
        }

        String artAudio = artObj.getArtisteAudioClip();
        if(artAudio != null && false) {
            htmlStr += "Listen to <a href=\"" + artObj.getArtisteAudioClip() + "\" target=\"_top\">Audio Clip</a>";
        }
        htmlStr += "</body>";

        return htmlStr;

    }

    String getSpeciality(String instrument) {

        String spl = "";
        String nInstrument = instrument.toLowerCase();

        switch (nInstrument) {
            case "":
                spl = "Vocalist";
                break;
            case "flute":
                spl = "Flautist";
                break;
            case "violin":
                spl = "Violinist";
                break;
            case "bharatanatyam":
                spl = "Bharatanatyam Dancer";
                break;
            case "kuchipudi":
                spl = "Kuchipudi Dancer";
                break;
            case "kathak":
                spl = "Kathak Dancer";
                break;
            case "dance":
                spl = "Dancer";
                break;
            default:
                spl = "Musician";
                break;
        }

        return spl;
    }

}
