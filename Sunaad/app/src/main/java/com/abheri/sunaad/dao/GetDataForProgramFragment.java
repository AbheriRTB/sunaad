package com.abheri.sunaad.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class GetDataForProgramFragment {

    String[] GET_DATA_FOR_PROGRAM_FRAGMENT = {"http://192.168.1.115:80/api/programs/"};
    JSONArray ja;

    public List<Program> getData(String jsonstring) {

        List<Program> programs = new ArrayList<Program>();
        Program tmpPrg;

        try {
            ja = new JSONArray(jsonstring);
            if (ja != null) {
                for (int i = 0; i < ja.length(); ++i) {
                    tmpPrg = new Program();
                    JSONObject jo = ja.getJSONObject(i);

                    String dt = jo.getString("event_date");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateObj = formatter.parse(dt);

                    tmpPrg.setTitle(jo.getString("title"));
                    tmpPrg.setEventType(jo.getString("event_type"));
                    tmpPrg.setDetails(jo.getString("description"));
                    tmpPrg.setEntryFee(jo.getString("entry_fee"));
                    tmpPrg.setWebsite(jo.getString("website"));
                    tmpPrg.setEventDate(dateObj);


                    tmpPrg.setPlace(jo.getString("place"));
                    tmpPrg.setArtiste(jo.getString("artiste"));
                    tmpPrg.setPhone(jo.getString("phone"));
                    tmpPrg.setStartTime(jo.getString("event_start"));
                    tmpPrg.setEndTime(jo.getString("event_end"));
                    tmpPrg.setDuration(jo.getDouble("duration"));
                    tmpPrg.setLocation_address1(jo.getString("location_address1"));
                    tmpPrg.setLocation_address2(jo.getString("location_address2"));
                    tmpPrg.setLocation_city(jo.getString("location_city"));
                    tmpPrg.setLocation_state(jo.getString("location_state"));
                    tmpPrg.setLocation_country(jo.getString("location_country"));
                    tmpPrg.setLoacation_pincode(jo.getString("location_pincode"));
                    tmpPrg.setLocation_coords(jo.getString("location_mapcoords"));
                    tmpPrg.setParking(jo.getString("location_parking"));
                    tmpPrg.setLocation_eataries(jo.getString("location_eataries"));


                    programs.add(tmpPrg);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //programs=dummyPrograms();

        return programs;
    }


    public List<String> getArtisteListFromPrograms(List<Program> prgList) {

        List<String> artisteList = new ArrayList<String>();
        Set<String> artisteSet = new HashSet<String>();

        for (int i = 0; i < prgList.size(); ++i) {

            Program pr = prgList.get(i);
            String artiste = pr.getArtiste();

            artisteSet.add(artiste);
        }

        artisteList = new ArrayList<String>(artisteSet);

        return artisteList;
    }

    public LinkedHashMap<String, List<Program>> createArtisteProgramCollection(List<Program> prgList, List<String> artList) {

        LinkedHashMap<String, List<Program>> artisteProgramCollection = new LinkedHashMap<String, List<Program>>();
        String art = "";

        for (int i = 0; i < artList.size(); ++i) {
            String selArt = artList.get(i);
            List<Program> artisteProgramList = new ArrayList<Program>();

            for (int j = 0; j < prgList.size(); ++j) {

                Program pr = prgList.get(j);
                art = pr.getArtiste();

                if (art.trim().equalsIgnoreCase(selArt)) {
                    artisteProgramList.add(pr);
                }

            }

            artisteProgramCollection.put(selArt, artisteProgramList);
        }

        return artisteProgramCollection;
    }

    public List<Program> dummyPrograms() {

        List<Program> programs = new ArrayList<Program>();
        Program tmpPrg;

        String[] titles = {"Program1", "Program2", "Program3", "Program4", "Program5",
                "Program6", "Program7", "Program8", "Program9", "Program10",
                "Program11", "Program12"};
        String[] details = {"Detail1", "Detail2", "Detail3", "Detail4", "Detail5",
                "Detail6", "Detail7", "Detail8", "Detail9", "Detail10",
                "Detail11", "Detail12"};


        for (int i = 0; i < 12; ++i) {

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
