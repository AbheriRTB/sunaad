package com.abheri.sunaad.view;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.util.Log;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.GetDataForHomeFragment;
import com.abheri.sunaad.dao.Program;
import com.abheri.sunaad.dao.ProgramListDataCache;
import com.abheri.sunaad.dao.RequestTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HandleServiceResponse, View.OnTouchListener {

    ViewAnimator viewAnimator;
    Context context;
    CycleView rc;
    String[] pages;
    View rootView;
    ProgressBar progressBar;
    TextView errTextView;
    List<Program> cachedProgramList;
    NavigationDrawerFragment mDrawerFragmet;

    public HomeFragment() {
        // Required empty public constructor
        context = getContext();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        if (null == context) {
            context = rootView.getContext();
        }

        Bundle args;

        args = getArguments();
        mDrawerFragmet =(NavigationDrawerFragment)args.getSerializable(Util.NAVIGATION_FRAGMET);

        progressBar = (ProgressBar) rootView.findViewById(R.id.homeProgressBar);
        progressBar.setVisibility(View.VISIBLE);

        errTextView = (TextView) rootView.findViewById(R.id.homeServiceErrorText);
        errTextView.setVisibility(View.GONE);


        ImageView logoImg = (ImageView)rootView.findViewById(R.id.homeLogo);
        viewAnimator = (ViewAnimator) rootView.findViewById(R.id.viewAnimator);

        logoImg.setOnTouchListener((View.OnTouchListener) this);

        //final Animation inAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        //final Animation outAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);

        //getData(this, false);
        updateWebViews();
        //viewAnimator.setInAnimation(inAnim);
        //viewAnimator.setOutAnimation(outAnim);

        return rootView;
    }

    public void getData(HomeFragment fragmentThis, boolean doRefresh) {

        ProgramListDataCache plc = new ProgramListDataCache(context.getApplicationContext());
        Util ut = new Util();
        if (ut.isNetworkAvailable(context) && (plc.isProgramDataCacheOld() || doRefresh))  {
            RequestTask rt = new RequestTask(fragmentThis, SunaadViews.HOME);
            rt.execute(Util.getServiceUrl(SunaadViews.HOME));
        } else {
            cachedProgramList = plc.RetrieveProgramDataFromCache();
            //If network is available the cached list will be non-null
            //Else it will be null. If null, show error text
            if(cachedProgramList != null) {
                updateViewFromData(cachedProgramList);
            }
            else {
                errTextView.setText("Connect to network to get Sunaad Data");
                errTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //LOGGER.debug("Stopping thread: " + thread);
        Log.v("sunaad", "Cancelling Timer...");

        if (rc != null) {
            rc.timer.cancel();
            rc.timer.purge();
            //LOGGER.debug("Thread successfully stopped.");
            Log.v("sunaad", "Timer Cancelled");
        }
    }

    @Override
    public void onSuccess(Object result) {
        List<Program> values = (List<Program>) result;

        ProgramListDataCache plc = new ProgramListDataCache(context.getApplicationContext());
        plc.SaveProgramDataInCache((List<Program>) result);

        updateViewFromData(values);
    }

    public void updateViewFromData(List<Program> values) {
        ArrayList<String> pagesList = new ArrayList<>();

        progressBar.setVisibility(View.GONE);
        for (int i = 0; i < values.size(); ++i) {
            Program tmp = values.get(i);
            String surl = tmp.getSplash_url();
            if (null != surl && surl.trim().length() > 0) {
                pagesList.add(surl);
                surl = "";
            }
        }

        if (pagesList.size() > 0) {
            pages = new String[pagesList.size()];
            pages = pagesList.toArray(pages);

            updateWebViews();
        }

    }

    @Override
    public void onError(Object result) {
        Exception e = (Exception) result;
        String st = "";
        if (null != e)
            st = e.toString();

        progressBar.setVisibility(View.GONE);
        errTextView.setText("Service Error Occurred:" + st);
        errTextView.setTextColor(Color.parseColor("#FF0000"));
        errTextView.setVisibility(View.VISIBLE);
    }

    void updateWebViews() {

        Animation inAnim, outAnim;
        progressBar.setVisibility(View.GONE);


        String urlBase = Util.getPageUrl(SunaadViews.HOME);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        Util ut = new Util();

        String newpages[] = {Util.FEATURED_CONCERT_TICKER} ;
        pages = newpages;

        if (null != pages && ut.isNetworkAvailable(context) ) {

            for (int i = 0; i < pages.length; ++i) {
                WebView wv = new WebView(rootView.getContext());
                wv.setLayoutParams(lp);
                wv.setWebViewClient(new WebViewClient());
                wv.setOnTouchListener(new WebViewOnTouchListener());
                wv.loadUrl(urlBase + pages[i]);

                viewAnimator.addView(wv);
            }
        }


    }

    void updateWebViews_old() {

        Animation inAnim, outAnim;
        progressBar.setVisibility(View.GONE);


        String urlBase = Util.getPageUrl(SunaadViews.HOME);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);


        //Add the Sunaad static flyer page at the beginning
        /*
        String sunaadFlyerStr = readSunaadFlyer(context);
        WebView swv = new WebView(rootView.getContext());
        swv.setLayoutParams(lp);
        swv.setWebViewClient(new WebViewClient());
        swv.loadData(sunaadFlyerStr, "text/html; charset=utf-8", "utf-8");
        swv.setOnTouchListener(new WebViewOnTouchListener());
        viewAnimator.addView(swv);
        */

        float cycletime = (float) 2; //Initialize to 15 sec delay
        Util ut = new Util();

        String newpages[] = {Util.FEATURED_CONCERT_TICKER} ;
        pages = newpages;

        if (null != pages && ut.isNetworkAvailable(context) ) {

            for (int i = 0; i < pages.length; ++i) {
                WebView wv = new WebView(rootView.getContext());
                wv.setLayoutParams(lp);
                wv.setWebViewClient(new WebViewClient());
                wv.setOnTouchListener(new WebViewOnTouchListener());
                wv.loadUrl(urlBase + pages[i]);
                //wv.loadUrl(pages[i]);

                viewAnimator.addView(wv);
            }
            if (pages.length >= 1){
                //If there are web urls available,
                //reduce the cycle time to 5 sec
                cycletime = (float)2;
            }
        }

        inAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        outAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);

        viewAnimator.startLayoutAnimation();

        if(pages.length >=1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewAnimator.showNext();
        }
        rc = new CycleView(cycletime);

    }

    //Reads the default flyer HTML from Assets folder
    String readSunaadFlyer(Context c) {

        String retStr = "";
        try {
            AssetManager am = c.getAssets();
            InputStream is = am.open("sunaad_flyer.html");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line;
            do {
                line = br.readLine();
                if(line != null)
                    retStr += line;
            } while (line != null);
        } catch (IOException e) {
            System.out.println("Error in readSunaadFlyer");
            e.printStackTrace();
        }

        return retStr;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mDrawerFragmet.openDrawer();
        return true;
    }


    class CycleView {
        Timer timer;

        public CycleView(float seconds) {
            timer = new Timer();
            timer.schedule(new CycleTask(), (int)(seconds * 1000), (int)(seconds * 1000));
        }

        class CycleTask extends TimerTask {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Random rand = new Random();
                        //int index = rand.nextInt(image_rundow.length);
                        //mapimg.setBackgroundResource(image_rundow[index]);
                        viewAnimator.showNext();
                    }
                });
            }
        }
    }

    class WebViewOnTouchListener implements View.OnTouchListener{
        //@Override
        public boolean onTouch1(View v, MotionEvent event) {
            // Do what you want
            mDrawerFragmet.openDrawer();
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event){
             final  int FINGER_RELEASED = 0;
             final  int FINGER_TOUCHED = 1;
             final  int FINGER_DRAGGING = 2;
             final  int FINGER_UNDEFINED = 3;

             int fingerState = FINGER_RELEASED;

            Log.i("SUNAAD", "FingerState:"+fingerState);

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        if (fingerState == FINGER_RELEASED)
                            fingerState = FINGER_TOUCHED;
                        else
                            fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_UP:
                        if(fingerState != FINGER_DRAGGING && fingerState != FINGER_TOUCHED
                                    && fingerState != FINGER_UNDEFINED) {
                            fingerState = FINGER_RELEASED;

                            mDrawerFragmet.openDrawer();

                        }
                        else if (fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_RELEASED;
                        else
                            fingerState = FINGER_UNDEFINED;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else
                            fingerState = FINGER_UNDEFINED;
                        break;

                    default:
                        fingerState = FINGER_UNDEFINED;
                        break;

                }

                return false;
            }
    }

}
