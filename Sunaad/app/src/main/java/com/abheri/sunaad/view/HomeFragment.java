package com.abheri.sunaad.view;


import android.content.Context;
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
import android.widget.ViewAnimator;
import android.util.Log;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.GetDataForHomeFragment;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewAnimator viewAnimator;
    Context context;
    CycleView rc;

    public HomeFragment() {
        // Required empty public constructor
        context = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        viewAnimator = (ViewAnimator)rootView.findViewById(R.id.viewAnimator);

        final Animation inAnim = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_in_left);
        final Animation outAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);

        GetDataForHomeFragment gd = new GetDataForHomeFragment();
        String[] pages = gd.getData();
        String urlBase = Util.getServiceUrl(SunaadViews.HOME);
        for(int i=0; i<pages.length; ++i){
            WebView wv = new WebView(rootView.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            wv.setLayoutParams(lp);
            //wv.loadData(pages[i], "text/html", "UTF_8");
            wv.setWebViewClient(new WebViewClient());
            wv.loadUrl(urlBase+pages[i]);
            viewAnimator.addView(wv);
        }

        viewAnimator.startLayoutAnimation();

        rc = new CycleView(5);

        Button btn1 = (Button) rootView.findViewById(R.id.buttonPrev);
        btn1.setVisibility(View.GONE);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewAnimator.showPrevious();
            }
        });

        Button btn2 = (Button) rootView.findViewById(R.id.buttonNext);
        btn2.setVisibility(View.GONE);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewAnimator.showNext();
            }
        });

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
