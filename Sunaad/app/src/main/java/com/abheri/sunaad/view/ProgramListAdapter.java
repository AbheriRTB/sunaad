package com.abheri.sunaad.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.abheri.sunaad.controller.ProgramController;
import com.squareup.picasso.Picasso;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Program;

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
        ProgramViewHolder holder = null;

        if (v == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.program_list_item, null);

            holder = new ProgramViewHolder();
            holder.title = (TextView) v.findViewById(R.id.title);
            holder.details = (TextView) v.findViewById(R.id.details);
            holder.eventDate = (TextView) v.findViewById(R.id.eventDate);
            holder.locationAddress1 = (TextView) v.findViewById(R.id.locationAddress1);

            holder.iv = (ImageView) v.findViewById(R.id.programImageSmall);
            //holder.alarmSwitch = (Switch) v.findViewById(R.id.alarm_switch);
            holder.alarmToggle = (ImageView) v.findViewById(R.id.alarmToggle);

            v.setTag(holder);
        } else {
            holder = (ProgramViewHolder) v.getTag();
        }


        Program currentProgram = this.Programs.get(position);

        //Set alarm setting callback
        //holder.alarmSwitch.setTag(R.string.alarm_program, currentProgram);
        holder.alarmToggle.setTag(R.string.alarm_program, currentProgram);

        //Pass ProgramFragment to ProgramController so that it can update the
        //Data to persist the alarmSwitch state
        final Context context = parent.getContext();
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        Fragment f = fragmentManager.findFragmentById(R.id.container);
        /*
        if(f instanceof ProgramFragment){
            holder.alarmSwitch.setOnCheckedChangeListener(new ProgramController(myContext, f));
        }else{
            holder.alarmSwitch.setOnCheckedChangeListener(new ProgramController(myContext));
        } */

        if(f instanceof ProgramFragment){
            holder.alarmToggle.setOnClickListener(new ProgramController(myContext, f));
        }else{
            holder.alarmToggle.setOnClickListener(new ProgramController(myContext));
        }


        //expandTouchArea((View)holder.alarmSwitch.getParent(), holder.alarmSwitch, 20);

        SimpleDateFormat ft = new SimpleDateFormat("E, dd-MMM-yyyy");

        String title = currentProgram.getTitle();
        String details = currentProgram.getDetails();
        String eventDate = ft.format(currentProgram.getEventDate());
        String locationAddress1 = currentProgram.getVenueAddress1();
        String uri = currentProgram.getArtiste_image();
        if (uri == null && uri.length() <= 0) {
            uri = "@drawable/default_artiste.jpeg";
        }


        if(currentProgram.alarm_millis < 0){
            //holder.alarmSwitch.setChecked(false);
            holder.alarmToggle.setBackground(null);
            holder.alarmToggle.setColorFilter(Color.argb(150, 60, 60, 60));
        }else{
            //holder.alarmSwitch.setChecked(true);
            holder.alarmToggle.clearColorFilter();
        }

        uri = Util.getImageUrl() + uri;

        int imageResource = v.getResources().getIdentifier(uri, null, v.getContext().getPackageName());


        if (position == Program.selectedPosition) {
            v.setBackgroundColor(v.getResources().getColor(android.R.color.holo_blue_light));
        } else
            v.setBackgroundColor(v.getResources().getColor(android.R.color.white));

        //TODO put correct event time and current time for compariosn
        if (Util.isEventToday(currentProgram, false) < 0) {
            v.setBackgroundColor(v.getResources().getColor(R.color.oldgray));
            //holder.alarmSwitch.setClickable(false);//don't let user set alarm for past events
            holder.alarmToggle.setBackground(null);
            holder.alarmToggle.setColorFilter(Color.argb(150, 60, 60, 60));
            holder.alarmToggle.setClickable(false);//don't let user set alarm for past events
        } else if (!Util.isYes(currentProgram.getIs_published())) {
            v.setBackgroundColor(v.getResources().getColor(R.color.orange));
        }

        Date eDate = currentProgram.getEventDate();
        Calendar cae = Calendar.getInstance();
        cae.setTime(eDate);
        cae.set(cae.get(Calendar.YEAR), cae.get(Calendar.MONTH), cae.get(Calendar.DATE), 0, 1);
        eDate = cae.getTime();


        if (Util.isEventToday(currentProgram, true) == 1) {
            holder.title.setTextColor(v.getResources().getColor(R.color.darkblue));
            holder.details.setTextColor(v.getResources().getColor(R.color.darkblue));
            holder.eventDate.setTextColor(v.getResources().getColor(R.color.darkblue));
            holder.locationAddress1.setTextColor(v.getResources().getColor(R.color.darkblue));
        } else {
            holder.title.setTextColor(v.getResources().getColor(R.color.black));
            holder.details.setTextColor(v.getResources().getColor(R.color.black));
            holder.eventDate.setTextColor(v.getResources().getColor(R.color.black));
            holder.locationAddress1.setTextColor(v.getResources().getColor(R.color.black));
        }

        holder.title.setText(title);
        holder.details.setText(details);
        holder.eventDate.setText(eventDate);
        holder.locationAddress1.setText(locationAddress1);
        //holder.iv.setImageDrawable(v.getResources().getDrawable(R.drawable.vocal));
        Picasso.with(myContext)
                .load(uri)
                .placeholder(R.drawable.default_artiste)
                .into(holder.iv);

        return v;
    }

    private static class ProgramViewHolder {
        TextView title, details, eventDate, locationAddress1;
        ImageView iv, alarmToggle;
        Switch alarmSwitch;
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

