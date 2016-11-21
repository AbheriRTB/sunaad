package com.abheri.sunaad.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by prasanna.ramaswamy on 05/11/16.
 */

public class CustomWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.contains("tel")){
            Uri number = Uri.parse(url);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            view.getContext().startActivity(callIntent);
        }
        if(url.contains("message2space.es.vu")) {
            view.loadUrl(url);
        } else  {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(i);
        }
        return true;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }
}



