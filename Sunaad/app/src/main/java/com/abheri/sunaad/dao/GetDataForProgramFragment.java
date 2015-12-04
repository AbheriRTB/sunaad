package com.abheri.sunaad.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class GetDataForProgramFragment {

    String[] GET_DATA_FOR_PROGRAM_FRAGMENT = {"http://192.168.1.115:80/api/programs/"};
    JSONArray ja;

    public List<Program> getData(String jsonstring){

        List<Program> programs = new ArrayList<Program>();
        Program tmpPrg;

        try {
            ja = new JSONArray(jsonstring);
            if(ja != null) {
                for (int i = 0; i < ja.length(); ++i){
                    tmpPrg = new Program();
                    JSONObject jo = ja.getJSONObject(i);

                    String dt = jo.getString("event_date");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateObj = formatter.parse(dt);

                    tmpPrg.setEventDate(dateObj);
                    tmpPrg.setTitle(jo.getString("title"));
                    tmpPrg.setDetails(jo.getString("description"));

                    programs.add(tmpPrg);
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }

        //programs=dummyPrograms();

        return programs;
    }

    List<Program> dummyPrograms(){

        List<Program> programs = new ArrayList<Program>();
        Program tmpPrg;

        String[] titles={"Program1","Program2","Program3","Program4","Program5",
                            "Program6", "Program7","Program8","Program9", "Program10",
                            "Program11", "Program12"};
        String[] details={"Detail1","Detail2","Detail3","Detail4","Detail5",
                            "Detail6", "Detail7", "Detail8","Detail9","Detail10",
                            "Detail11", "Detail12"};


        for(int i=0; i<12; ++i){

            tmpPrg = new Program();

            tmpPrg.setTitle(titles[i]);
            tmpPrg.setDetails(details[i]);
            Date nd = new Date();
            nd.setDate(i);

            tmpPrg.setEventDate(nd);

            programs.add(tmpPrg);
        }

        return programs;
    }
}
