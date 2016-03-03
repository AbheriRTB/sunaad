package com.abheri.sunaad.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.Program;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class ProgramListAdapter extends ArrayAdapter<Program> {

    List<Program> Programs;
    private View oldSelection = null;
    Context myContext;

    public ProgramListAdapter(Context context, int resource,
                         List<Program> Programlist) {
        super(context, resource, Programlist);
        this.Programs = Programlist;
        myContext = context;
        // TODO Auto-generated constructor stub
    }

    ArrayList<Program> Programlist;
    Activity currentActivity;
    final ProgramListAdapter self = this;

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
        ViewHolder holder = null;

        if (v == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.program_list_item, null);

            holder = new ViewHolder();
            holder.title = (TextView) v.findViewById(R.id.title);
            holder.description = (TextView) v.findViewById(R.id.details);
            holder.descripton2 = (TextView) v.findViewById(R.id.eventDate);
            holder.description3 = (TextView) v.findViewById(R.id.locationAddress1);

            holder.iv = (ImageView) v.findViewById(R.id.programImageSmall);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        SimpleDateFormat ft = new SimpleDateFormat ("E, dd-MMM-yyyy");

        Program currentProgram = this.Programs.get(position);
        String title = currentProgram.getTitle();
        String details = currentProgram.getDetails();
        String eventDate = ft.format(currentProgram.getEventDate());
        String locationAddress1 = currentProgram.getLocation_address1();
        String uri = currentProgram.getArtiste_image();
        if(uri == null && uri.length()<=0){
            uri = "@drawable/default_artiste.jpeg";
        }

        uri = Util.getImageUrl() + uri;

        int imageResource = v.getResources().getIdentifier(uri, null, v.getContext().getPackageName());


        if (position == Program.selectedPosition)
        {
            v.setBackgroundColor(v.getResources().getColor(android.R.color.holo_blue_light));
        }
        else
            v.setBackgroundColor(v.getResources().getColor(android.R.color.white));

        //TODO put correct event time and current time for compariosn
        Date eDate = currentProgram.getEventDate();
        Calendar cae = Calendar.getInstance();
        cae.setTime(eDate);
        cae.set(cae.get(Calendar.YEAR), cae.get(Calendar.MONTH), cae.get(Calendar.DATE),0,1);
        eDate = cae.getTime();

        Calendar cat = Calendar.getInstance();
        cat.set(cat.get(Calendar.YEAR), cat.get(Calendar.MONTH), cat.get(Calendar.DATE),0,0);
        Date tDate = cat.getTime();

        if(null != eDate){
            if(eDate.compareTo(tDate) < 0){
                v.setBackgroundColor(v.getResources().getColor(R.color.oldgray));
            }

            //If the event is for today, set the text color to blue
            String dateStr1 = ft.format(eDate);
            String dateStr2 = ft.format(tDate);
            if(dateStr1.equalsIgnoreCase(dateStr2)){
                holder.title.setTextColor(v.getResources().getColor(R.color.darkblue));
                holder.description.setTextColor(v.getResources().getColor(R.color.darkblue));
                holder.descripton2.setTextColor(v.getResources().getColor(R.color.darkblue));
                holder.description3.setTextColor(v.getResources().getColor(R.color.darkblue));
            }
            else{
                holder.title.setTextColor(v.getResources().getColor(R.color.black));
                holder.description.setTextColor(v.getResources().getColor(R.color.black));
                holder.descripton2.setTextColor(v.getResources().getColor(R.color.black));
                holder.description3.setTextColor(v.getResources().getColor(R.color.black));
            }

        }


        holder.title.setText(title);
        holder.description.setText(details);
        holder.descripton2.setText(eventDate);
        holder.description3.setText(locationAddress1);
        //holder.iv.setImageDrawable(v.getResources().getDrawable(R.drawable.vocal));
        Picasso.with(myContext)
                .load(uri)
                .placeholder(R.drawable.default_artiste)
                .into(holder.iv);

        return v;
    }

    private static class ViewHolder{
        TextView title, description, descripton2, description3;
        ImageView iv;
    }


}

