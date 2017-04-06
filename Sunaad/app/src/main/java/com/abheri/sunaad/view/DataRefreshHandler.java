package com.abheri.sunaad.view;

import android.content.Context;

import com.abheri.sunaad.model.Artiste;
import com.abheri.sunaad.model.ArtisteListDataCache;
import com.abheri.sunaad.model.CloudDataFetcherAsyncTask;
import com.abheri.sunaad.model.ModifiedFlagFetcherAsyncTask;
import com.abheri.sunaad.model.Organizer;
import com.abheri.sunaad.model.OrganizerListDataCache;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramListDataCache;
import com.abheri.sunaad.model.SQLStrings;
import com.abheri.sunaad.model.Venue;
import com.abheri.sunaad.model.VenueListDataCache;
import com.abheri.sunaad.view.directory.ArtisteDirectoryFragment;
import com.abheri.sunaad.view.directory.OrganizerDirectoryFragment;
import com.abheri.sunaad.view.directory.VenueDirectoryFragment;
import com.abheri.sunaad.view.program.ArtisteFragment;
import com.abheri.sunaad.view.program.EventtypeFragment;
import com.abheri.sunaad.view.program.OrganizerFragment;
import com.abheri.sunaad.view.program.ProgramFragment;
import com.abheri.sunaad.view.program.VenueFragment;

import java.util.List;

/**
 * Created by prasanna.ramaswamy on 04/04/17.
 */

public class DataRefreshHandler implements HandleServiceResponse, HandleModifiedFlagServiceResponse {

    SunaadViews whichView = SunaadViews.ARTISTE;
    SunaadFragmentSuperClass viewFragment;
    Context context;
    boolean refresh;
    String whichField;

    public DataRefreshHandler(SunaadFragmentSuperClass fragment, boolean doRefresh, Context ctx){
        viewFragment = fragment;
        context = ctx;
        refresh = doRefresh;
    }

    public void updateData(){

        if(viewFragment instanceof ProgramFragment){
            whichField = SQLStrings.COLUMN_PROGRAM_LAST_REFRESH;
        }else if(viewFragment instanceof ArtisteFragment){
            whichField = SQLStrings.COLUMN_PROGRAM_LAST_REFRESH;
        }else if(viewFragment instanceof VenueFragment){
            whichField = SQLStrings.COLUMN_PROGRAM_LAST_REFRESH;
        }else if(viewFragment instanceof OrganizerFragment) {
            whichField = SQLStrings.COLUMN_PROGRAM_LAST_REFRESH;
        }else if(viewFragment instanceof EventtypeFragment) {
            whichField = SQLStrings.COLUMN_PROGRAM_LAST_REFRESH;
        }else if(viewFragment instanceof ArtisteDirectoryFragment) {
            whichField = SQLStrings.COLUMN_ARTISTE_LAST_REFRESH;
        }else if(viewFragment instanceof OrganizerDirectoryFragment) {
            whichField = SQLStrings.COLUMN_ORGANIZER_LAST_REFRESH;
        }else if(viewFragment instanceof VenueDirectoryFragment) {
            whichField = SQLStrings.COLUMN_VENUE_LAST_REFRESH;
        }

        Util ut = new Util();
        //If network is available refresh the cache and the view

        if (ut.isNetworkAvailable(context)) {
            if(refresh)
            {
                //If user tapped Refresh, force a refresh.
                getListData();
            }
            else {
                viewFragment.getActivity().setProgressBarIndeterminateVisibility(true);
                viewFragment.getActivity().setProgressBarVisibility(true);

                ModifiedFlagFetcherAsyncTask ft = new ModifiedFlagFetcherAsyncTask(this, whichField, context);
                ft.execute(Util.getServiceUrl(SunaadViews.SETTINGS));
            }
        }


    }

    //Succes & Failure handlers of the isModifiedFlagFetch Async Task
    @Override
    public void onModifiedFlagFetchSuccess(Object result) {

        if(((String)result).contains("true")){
            getListData();
        }else{
            viewFragment.hideProgressBar();
        }
    }

    @Override
    public void onModifiedFlagFetchError(Object result) {
        //If couldn't get the flag for some reason, assume it is true
        getListData();
    }

    void getListData(){
        if(viewFragment instanceof ProgramFragment){
            whichView = SunaadViews.PROGRAM;
        }else if(viewFragment instanceof ArtisteFragment){
            whichView = SunaadViews.ARTISTE;
        }else if(viewFragment instanceof VenueFragment){
            whichView = SunaadViews.LOCATION;
        }else if(viewFragment instanceof OrganizerFragment) {
            whichView = SunaadViews.SABHA;
        }else if(viewFragment instanceof EventtypeFragment) {
            whichView = SunaadViews.EVENT_TYPE;
        }else if(viewFragment instanceof ArtisteDirectoryFragment) {
            whichView = SunaadViews.ARTISTE_DIR;
        }else if(viewFragment instanceof OrganizerDirectoryFragment) {
            whichView = SunaadViews.ORGANIZER_DIR;
        }else if(viewFragment instanceof VenueDirectoryFragment) {
            whichView = SunaadViews.VENUE_DIR;
        }

        //progressBar.setVisibility(View.VISIBLE);
        CloudDataFetcherAsyncTask rt = new CloudDataFetcherAsyncTask(this, whichView, context);
        rt.execute(Util.getServiceUrl(whichView));
    }


    @Override
    public void onSuccess(Object result) {

        switch(whichView){
            case PROGRAM:
            case ARTISTE:
            case LOCATION:
            case SABHA:
            case EVENT_TYPE:
                List<Program> values = (List<Program>) result;

                ProgramListDataCache plc = new ProgramListDataCache(context);
                plc.SaveProgramDataInCache((List<Program>) result);

               viewFragment.updateViewFromData(values);
                break;
            case ARTISTE_DIR:
                List<Artiste> aValues = (List<Artiste>) result;

                ArtisteListDataCache alc = new ArtisteListDataCache(context);
                alc.SaveArtisteDataInCache((List<Artiste>) result);

                viewFragment.updateArtisteViewFromData(aValues);
                break;
            case ORGANIZER_DIR:
                List<Organizer> oValues = (List<Organizer>) result;

                OrganizerListDataCache olc = new OrganizerListDataCache(context);
                olc.SaveOrganizerInCache((List<Organizer>) result);

                viewFragment.updateOrganizerViewFromData(oValues);
                break;
            case VENUE_DIR:
                List<Venue> vValues = (List<Venue>) result;

                VenueListDataCache vlc = new VenueListDataCache(context);
                vlc.SaveVenueDataInCache((List<Venue>) result);

                viewFragment.updateVenueViewFromData(vValues);
                break;

        }


    }

    @Override
    public void onError(Object result) {
        viewFragment.updateOnError(result);
    }
}
