package com.abheri.sunaad.model;

import android.content.Context;

import com.abheri.sunaad.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Maha on 06/03/16.
 */
public class DummyData {

    public static String getJSONFromRawFile(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        String outStr = "";

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                outStr += (char) ctr;
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outStr;
    }
}

