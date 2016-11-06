package com.abheri.sunaad.model;

import com.abheri.sunaad.BuildConfig;
import com.abheri.sunaad.view.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class ArtisteDataHelper {

    JSONArray ja;

    public List<Artiste> parseArtisteListFromJsonResponse(String jsonstring) {


        List<Artiste> artistes = new ArrayList<Artiste>();
        List<Artiste> cachedArtistes = new ArrayList<Artiste>();

        Artiste tmpArtiste;

        String dummy_json =  "[{\"artiste_id\":1,\"artiste_name\":\"Ananthram B K\",\"artiste_phone\":\"123456789\",\"artiste_website\":\"null\",\"art_type\":\"Carnatic\",\"audio_clip\":\"aud    io_bka.wav\",\"artiste_instrument\":\"null\",\"artiste_address1\":\"9th Main\",\"artiste_address2\":\"Subramanyanagar\",\"artiste_city\":\"Bangalore\",\"artiste_state\":\"Karnataka\",\"artiste_country\":\"India\",\"artiste_pincode\":\"560079\",\"artiste_coords\":\"undefined\",\"artiste_image\":\"b_k_ananthram.jpeg\",\"artiste_is_published\":\"null\"}]";

        try {
            ja = new JSONArray(jsonstring);
            if (ja != null) {
                for (int i = 0; i < ja.length(); ++i) {
                    tmpArtiste = new Artiste();
                    JSONObject jo = ja.getJSONObject(i);

                    tmpArtiste.setId(jo.getLong("artiste_id"));
                    tmpArtiste.setArtisteName(jo.getString("artiste_name"));
                    tmpArtiste.setArtistePhone(jo.getString("artiste_phone"));
                    tmpArtiste.setArtisteAddress1(jo.getString("artiste_address1"));
                    tmpArtiste.setArtisteAddress2(jo.getString("artiste_address2"));
                    tmpArtiste.setArtisteCity(jo.getString("artiste_city"));
                    tmpArtiste.setArtisteState(jo.getString("artiste_state"));
                    tmpArtiste.setArtisteCountry(jo.getString("artiste_country"));
                    tmpArtiste.setArtistePincode(jo.getString("artiste_pincode"));
                    tmpArtiste.setArtisteCoords(jo.getString("artiste_coords"));
                    tmpArtiste.setArtisteWebsite(jo.getString("artiste_website"));
                    tmpArtiste.setIs_published(jo.getString("artiste_is_published"));
                    tmpArtiste.setArtisteImage(jo.getString("artiste_image"));
                    tmpArtiste.setArtisteInstrument(jo.getString("artiste_instrument"));
                    tmpArtiste.setArtisteAudioClip(jo.getString("audio_clip"));
                    tmpArtiste.setArtisteArtType(jo.getString("art_type"));


                    //Add unpublished data only when the build type is NOT Release
                    if(BuildConfig.DEBUG) {
                        artistes.add(tmpArtiste);
                    }else if(Util.isYes(tmpArtiste.getIs_published())){
                        artistes.add(tmpArtiste);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return artistes;
    }


    /* -----------------------------------------
    public List<String> getArtisteListFromPrograms(List<Program> prgList) {

        List<String> artisteList = new ArrayList<String>();
        TreeSet<String> artisteSet = new TreeSet<String>();

        for (int i = 0; i < prgList.size(); ++i) {

            Program pr = prgList.get(i);
            String artiste = pr.getArtisteName();

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
                art = pr.getArtisteName();

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
            String sabha = pr.getVenueName();

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
                sab = pr.getVenueName();

                if (sab.trim().equalsIgnoreCase(selSab)) {
                    sabhaProgramList.add(pr);
                }

            }

            sabhaProgramCollection.put(selSab, sabhaProgramList);
        }

        return sabhaProgramCollection;
    }

    public List<String> getOrganizerListFromPrograms(List<Program> prgList) {

        List<String> organizerList = new ArrayList<String>();
        TreeSet<String> organizerSet = new TreeSet<String>();

        for (int i = 0; i < prgList.size(); ++i) {

            Program pr = prgList.get(i);
            String org = pr.getOrganizerName();

            organizerSet.add(org);
        }

        organizerList = new ArrayList<String>(organizerSet);

        return organizerList;
    }

    public LinkedHashMap<String, List<Program>> createOrganizerProgramCollection(List<Program> prgList, List<String> organizerList) {

        LinkedHashMap<String, List<Program>> organizerProgramCollection = new LinkedHashMap<String, List<Program>>();
        String org = "";


        for(int i=0; i< organizerList.size(); ++i){
            String selOrg = organizerList.get(i);
            List<Program> organizerProgramList = new ArrayList<Program>();

            for (int j = 0; j < prgList.size(); ++j) {

                Program pr = prgList.get(j);
                org = pr.getOrganizerName();

                if (org.trim().equalsIgnoreCase(selOrg)) {
                    organizerProgramList.add(pr);
                }

            }

            organizerProgramCollection.put(selOrg, organizerProgramList);
        }

        return organizerProgramCollection;
    }

    public List<String> getEventtypeListFromPrograms(List<Program> prgList) {

        List<String> eventtypeList = new ArrayList<String>();
        TreeSet<String> eventtypeSet = new TreeSet<String>();

        for (int i = 0; i < prgList.size(); ++i) {

            Program pr = prgList.get(i);
            String eventType = pr.getArtType();

            eventtypeSet.add(eventType);
        }

        eventtypeList = new ArrayList<String>(eventtypeSet);

        return eventtypeList;
    }

    public LinkedHashMap<String, List<Program>> createEventtypeProgramCollection(List<Program> prgList, List<String> organizerList) {

        LinkedHashMap<String, List<Program>> eventtypeProgramCollection = new LinkedHashMap<String, List<Program>>();
        String eventtype = "";


        for(int i=0; i< organizerList.size(); ++i){
            String selEventtype = organizerList.get(i);
            List<Program> organizerProgramList = new ArrayList<Program>();

            for (int j = 0; j < prgList.size(); ++j) {

                Program pr = prgList.get(j);
                eventtype = pr.getArtType();

                if (eventtype.trim().equalsIgnoreCase(selEventtype)) {
                    organizerProgramList.add(pr);
                }

            }

            eventtypeProgramCollection.put(selEventtype, organizerProgramList);
        }

        return eventtypeProgramCollection;
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

    --------------------------------------- */
}
