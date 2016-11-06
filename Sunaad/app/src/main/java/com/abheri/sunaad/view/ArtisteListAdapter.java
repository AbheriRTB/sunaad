package com.abheri.sunaad.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Artiste;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class ArtisteListAdapter extends ArrayAdapter<Artiste> {

    List<Artiste> Artistes;
    private View oldSelection = null;
    Context myContext;

    public ArtisteListAdapter(Context context, int resource,
                              List<Artiste> ArtisteList) {
        super(context, resource, ArtisteList);
        this.Artistes = ArtisteList;
        myContext = context;
        // TODO Auto-generated constructor stub
    }

    ArrayList<Artiste> ArtisteList;
    Activity currentActivity;
    final ArtisteListAdapter self = this;

    public void update() {
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                self.notifyDataSetChanged();
            }
        });
    }

    public void clearSelection() {
        if (oldSelection != null) {
            oldSelection.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_blue_light));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ProgramViewHolder holder = null;

        if (v == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.artiste_dir_child_item, null);

            holder = new ProgramViewHolder();
            holder.name = (TextView) v.findViewById(R.id.adName);
            holder.detail = (TextView) v.findViewById(R.id.adDetail);

            holder.iv = (ImageView) v.findViewById(R.id.adArtisteImage);

            v.setTag(holder);
        } else {
            holder = (ProgramViewHolder) v.getTag();
        }


        Artiste currentArtiste = this.Artistes.get(position);

        //Pass ProgramFragment to ProgramController so that it can update the
        //Data to persist the alarmSwitch state
        final Context context = parent.getContext();
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        Fragment f = fragmentManager.findFragmentById(R.id.container);

        SimpleDateFormat ft = new SimpleDateFormat("E, dd-MMM-yyyy");

        String name = currentArtiste.getgetArtisteName();
        String specialization = currentArtiste.getArtisteInstrument();
        if(specialization == null || specialization == ""){
            specialization = "Vocal";
        }
        String uri = currentArtiste.getArtisteImage();
        if (uri == null || uri.length() <= 0) {
            uri = "@drawable/default_artiste.jpeg";
        }

        uri = Util.getImageUrl() + uri;

        int imageResource = v.getResources().getIdentifier(uri, null, v.getContext().getPackageName());


        if (position == Artiste.selectedPosition) {
            v.setBackgroundColor(v.getResources().getColor(android.R.color.holo_blue_light));
        } else
            v.setBackgroundColor(v.getResources().getColor(android.R.color.white));

        //TODO put correct event time and current time for compariosn
        if (!Util.isYes(currentArtiste.getIs_published())) {
            v.setBackgroundColor(v.getResources().getColor(R.color.orange));
        }


        holder.name.setText(name);
        holder.detail.setText("Specialization:" + specialization);

        //holder.iv.setImageDrawable(v.getResources().getDrawable(R.drawable.vocal));
        Picasso.with(myContext)
                .load(uri)
                .placeholder(R.drawable.default_artiste)
                .into(holder.iv);

        return v;
    }

    private static class ProgramViewHolder {
        TextView name, detail;
        ImageView iv;
    }

    public static void expandTouchArea(final View bigView, final View smallView, final int extraPadding) {


        bigView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                smallView.getHitRect(rect);
                rect.top -= extraPadding;
                rect.left -= extraPadding;
                rect.right += extraPadding;
                rect.bottom += extraPadding;
                bigView.setTouchDelegate(new TouchDelegate(rect, smallView));
            }
        });


    }


}

