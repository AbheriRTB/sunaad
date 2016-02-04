package com.abheri.sunaad.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.util.Log;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.GetDataForHomeFragment;
import com.abheri.sunaad.dao.Program;
import com.abheri.sunaad.dao.RequestTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HandleServiceResponse{

    ViewAnimator viewAnimator;
    Context context;
    CycleView rc;
    String[] pages;
    View rootView;
    ProgressBar progressBar;
    TextView errTextView;

    public HomeFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.homeProgressBar);
        progressBar.setVisibility(View.VISIBLE);

        errTextView = (TextView)rootView.findViewById(R.id.homeServiceErrorText);
        errTextView.setVisibility(View.GONE);


        viewAnimator = (ViewAnimator)rootView.findViewById(R.id.viewAnimator);

        final Animation inAnim = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_in_left);
        final Animation outAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);

        RequestTask rt = new RequestTask(this, SunaadViews.HOME);
        rt.execute(Util.getServiceUrl(SunaadViews.HOME));

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);

        return rootView;
    }

    @Override
    public void onPause(){
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
        List<Program> values = (List<Program>)result;
        ArrayList<String> pagesList = new ArrayList<>();

        progressBar.setVisibility(View.GONE);
        for(int i=0; i< values.size(); ++i){
            Program tmp = values.get(i);
            String surl = tmp.getSplash_url();
            if(null!=surl && surl.trim().length() > 0){
                pagesList.add(surl);
                surl = "";
            }
        }

        if(pagesList.size() > 0){
            pages = new String[pagesList.size()];
            pages = pagesList.toArray(pages);

            updateWebViews();
        }

    }

    @Override
    public void onError(Object result) {
        Exception e = (Exception)result;
        String st = "";
        if(null != e)
            st = e.toString();

        progressBar.setVisibility(View.GONE);
        errTextView.setText("Service Error Occurred:" + st);
        errTextView.setTextColor(Color.parseColor("#FF0000"));
        errTextView.setVisibility(View.VISIBLE);
    }

    void updateWebViews(){

        String urlBase = Util.getPageUrl(SunaadViews.HOME);
        if(null == pages || pages.length <= 0)
            return;

        for(int i=0; i<pages.length; ++i){
            WebView wv = new WebView(rootView.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
            wv.setLayoutParams(lp);
            //wv.loadData(pages[i], "text/html", "UTF_8");
            wv.setWebViewClient(new WebViewClient());
            wv.loadUrl(urlBase+pages[i]);
            viewAnimator.addView(wv);
        }

        viewAnimator.startLayoutAnimation();

        rc = new CycleView(5);

    }


    class CycleView {
        Timer timer;
        public CycleView(int seconds) {
            timer = new Timer();
            timer.schedule(new CycleTask(), seconds * 1000, seconds*1000);
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


}
