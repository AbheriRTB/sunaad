package com.abheri.sunaad.dao;

import com.abheri.sunaad.BuildConfig;
import com.abheri.sunaad.R;
import com.abheri.sunaad.view.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class ProgramDataHandler {

    String[] GET_DATA_FOR_PROGRAM_FRAGMENT = {"http://192.168.1.115:80/api/programs/"};
    JSONArray ja;

    public List<Program> parseProgramListFromJsonResponse(String jsonstring) {


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
                    tmpPrg.setArtiste_image(jo.getString("artiste_image"));
                    tmpPrg.setIs_featured(jo.getString("is_featured"));
                    tmpPrg.setSplash_url(jo.getString("splash_url"));
                    tmpPrg.setIs_published(jo.getString("is_published"));

                    //Add unpublished data only when the build type is NOT Release
                    if(BuildConfig.DEBUG) {
                        programs.add(tmpPrg);
                    }else if(Util.isYes(tmpPrg.getIs_published())){
                        programs.add(tmpPrg);
                    }
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
        TreeSet<String> artisteSet = new TreeSet<String>();

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

    public List<String> getSabhaListFromPrograms(List<Program> prgList) {

        List<String> sabhaList = new ArrayList<String>();
        TreeSet<String> sabhaSet = new TreeSet<String>();

        for (int i = 0; i < prgList.size(); ++i) {

            Program pr = prgList.get(i);
            String sabha = pr.getPlace();

            sabhaSet.add(sabha);
        }

        sabhaList = new ArrayList<String>(sabhaSet);

        return sabhaList;
    }

    public LinkedHashMap<String, List<Program>> createSabhaProgramCollection(List<Program> prgList, List<String> sabhaList) {

        LinkedHashMap<String, List<Program>> sabhaProgramCollection = new LinkedHashMap<String, List<Program>>();
        String sab = "";


        for(int i=0; i< sabhaList.size(); ++i){
            String selSab = sabhaList.get(i);
            List<Program> sabhaProgramList = new ArrayList<Program>();

            for (int j = 0; j < prgList.size(); ++j) {

                Program pr = prgList.get(j);
                sab = pr.getPlace();

                if (sab.trim().equalsIgnoreCase(selSab)) {
                    sabhaProgramList.add(pr);
                }

            }

            sabhaProgramCollection.put(selSab, sabhaProgramList);
        }

        return sabhaProgramCollection;
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

    public static List<Program> filterOldPrograms(List<Program> plist, int howOld){

        List<Program> filteredPrograms = new ArrayList<Program>();
        for(int i=0; i<plist.size(); ++i) {

            Date eDate = plist.get(i).getEventDate();
            Date tDate = new Date();

            long diff = eDate.getTime() - tDate.getTime();
            long diffdays = diff/(1000 * 60 * 60 * 24);

            if (diffdays >= 0) {
                filteredPrograms.add(plist.get(i));
            }
            else if(Math.abs(diffdays) <= howOld){
                filteredPrograms.add(plist.get(i));
            }
        }

        return filteredPrograms;
    }
    class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program p1, Program p2) {

            if (null == p1 || null == p2){
                return 0;
            }

            Date eDate1 = p1.getEventDate();
            Date eDate2 = p2.getEventDate();

            if(null == eDate1 || null == eDate2){
                return 0;
            }
            int result = eDate1.compareTo(eDate2);

            return result;
        }
    }
}
