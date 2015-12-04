package com.abheri.sunaad.dao;

import org.json.JSONArray;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class GetDataForHomeFragment {

    String[] GET_DATA_FOR_HOME_FRAGMENT = {"http://"};

    public String[] getData(){

        String[] pages;
        JSONArray ja = new JSONArray();

        pages=dummyPages();

        return pages;
    }

    String[] dummyPages(){

        String[] pages = new String[5];

        String[] colors={"#7777FF","77FF77","FF7777","aa00ee","0022ee"};

        String[] weburls={"http://10.0.3.2/Featured1.htm",
                            "http://10.0.3.2/Featured2.htm",
                            "http://www.google.com",
                            "http://www.amazon.com",
                            "http://www.replicon.com"};

        for(int i=0; i<2; ++i){
            String html = "<div style=\"background-color:#"+colors[i]
                            +"; text-align : center; vertical-align:middle; width : 100% ; height : 100%;\">Concert"
                            + (i+1)
                            + "</div>";

            String base = "<html><body style='margin:0;padding:0;'>"
                            + html
                            + "</body></html>";

            //pages[i]=base;
            pages[i] = weburls[i];
        }

        return pages;
    }
}
