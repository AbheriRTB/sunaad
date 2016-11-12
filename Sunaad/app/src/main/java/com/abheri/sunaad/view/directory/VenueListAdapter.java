package com.abheri.sunaad.view.directory;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Artiste;
import com.abheri.sunaad.model.Venue;
import com.abheri.sunaad.view.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class VenueListAdapter extends ArrayAdapter<Venue> {

    List<Venue> Venues;
    private View oldSelection = null;
    Context myContext;

    public VenueListAdapter(Context context, int resource,
                            List<Venue> VenueList) {
        super(context, resource, VenueList);
        this.Venues = VenueList;
        myContext = context;
        // TODO Auto-generated constructor stub
    }

    Activity currentActivity;
    final VenueListAdapter self = this;

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
        VenueViewHolder holder = null;

        if (v == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.dir_venue_dir_child_item, null);

            holder = new VenueViewHolder();
            holder.name = (TextView) v.findViewById(R.id.vdName);
            holder.detail = (TextView) v.findViewById(R.id.vdDetail);

            holder.iv = (ImageView) v.findViewById(R.id.vdVenueImage);

            v.setTag(holder);
        } else {
            holder = (VenueViewHolder) v.getTag();
        }


        Venue currentVenue = this.Venues.get(position);

        //Pass ProgramFragment to ProgramController so that it can update the
        //Data to persist the alarmSwitch state
        final Context context = parent.getContext();
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        Fragment f = fragmentManager.findFragmentById(R.id.container);

        String name = currentVenue.getVenue_name();
        String description = currentVenue.getVenue_description();
        if(description == null || description == ""){
            description = "Vocal";
        }

        String uri = currentVenue.getImage();
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
        if (!Util.isYes(currentVenue.getIs_published())) {
            v.setBackgroundColor(v.getResources().getColor(R.color.orange));
        }


        holder.name.setText(name);
        holder.detail.setText("Desc:" + description);

        //holder.iv.setImageDrawable(v.getResources().getDrawable(R.drawable.vocal));
        Picasso.with(myContext)
                .load(uri)
                .placeholder(R.drawable.default_artiste)
                .into(holder.iv);

        return v;
    }

    private static class VenueViewHolder {
        TextView name, detail;
        ImageView iv;
    }

}

