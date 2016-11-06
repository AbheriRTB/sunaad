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
        View rootView = inflater.inflate(R.layout.fragment_artiste_details, container, false);

        Intent i = getActivity().getIntent();
        Artiste artObj = (Artiste) i.getExtras().getSerializable("ArtisteDetails");

        //----- Title/Name ----
        WebView titleWV = (WebView) rootView.findViewById(R.id.adTitleWV);
        String cssStr = LocalFileReader.readRawResourceFile(context, R.raw.webview_css);

        String titleStr = cssStr + "<html><body><center><h3 class=\"underline\"> " +
                artObj.getgetArtisteName() + "</h3></center></body></html>";
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
        detailWV.setScrollbarFadingEnabled(true); // Explicitly, however it's a default, I think.
        detailWV.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);

        //Set WebViewClient such that the link opens in a browser

        String htmlStr = cssStr + createArtisteHTML(artObj);
        detailWV.loadData(htmlStr, "text/html", "utf-8");

        detailWV.setWebViewClient(new CustomWebViewClient());


        return rootView;
    }


    String createArtisteHTML(Artiste artObj) {

        String htmlStr = "";
        UrlValidator urlValidator = new UrlValidator();

        htmlStr += "<html><body class=\"class1\">";
        htmlStr += "Vid. " + artObj.getgetArtisteName() + ", is an accomplished ";
        htmlStr += getSpeciality(artObj.getArtisteInstrument());
        htmlStr += "<br><br>";
        htmlStr += "<u><i>Contact Details:</i></u><br>";
        htmlStr += artObj.getArtisteAddress1() + "<br>";
        htmlStr += artObj.getArtisteAddress2() + "<br>";
        htmlStr += artObj.getArtisteCity() + " - " + artObj.getArtistePincode() + "<br>";
        htmlStr += artObj.getArtisteState() + "<br>";
        htmlStr += artObj.getArtisteCountry() + "<br>";

        String artPhone = artObj.getArtistePhone();
        if(artPhone != null) {
            htmlStr += "Ph: <a href=\"tel:" + artPhone + "\">" + artPhone + "</a>";
        }
        htmlStr += "<br><br>";

        String artWebSite = artObj.getArtisteWebsite();
        if(artWebSite != null && urlValidator.isValid(artWebSite)) {
            htmlStr += "Visit <a href=\"" + artObj.getArtisteWebsite() + "\" target=\"_top\">Website</a><br>";
        }

        String artAudio = artObj.getArtisteAudioClip();
        if(artAudio != null) {
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
            default:
                spl = "Musician";
                break;
        }

        return spl;
    }

}
