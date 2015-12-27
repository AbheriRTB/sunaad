package com.abheri.sunaad.view;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abheri.sunaad.R;
import com.abheri.sunaad.dao.Program;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasanna.ramaswamy on 25/10/15.
 */
public class ProgramListAdapter extends ArrayAdapter<Program> {

    List<Program> Programs;
    private View oldSelection = null;

    public ProgramListAdapter(Context context, int resource,
                         List<Program> Programlist) {
        super(context, resource, Programlist);
        this.Programs = Programlist;
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
            holder.description = (TextView) v.findViewById(R.id.description);
            holder.descripton2 = (TextView) v.findViewById(R.id.description2);
            holder.description3 = (TextView) v.findViewById(R.id.description3);

            holder.iv = (ImageView) v.findViewById(R.id.programImageSmall);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Program currentProgram = this.Programs.get(position);
        String title = currentProgram.getTitle();
        String description = currentProgram.getDetails();
        String description2 = currentProgram.getEventDate().toString();
        String description3 = currentProgram.getLocation_address1();

        String uri = "@drawable/subbulakshmi";  // where myresource.png is the file
        // extension removed from the String

        int imageResource = v.getResources().getIdentifier(uri, null, v.getContext().getPackageName());

        if (position == Program.selectedPosition)
        {
            v.setBackgroundColor(v.getResources().getColor(android.R.color.holo_blue_light));
        }
        else
            v.setBackgroundColor(v.getResources().getColor(android.R.color.white));

        holder.title.setText(title);
        holder.description.setText(description);
        holder.descripton2.setText(description2);
        holder.description3.setText(description3);
        holder.iv.setImageDrawable(v.getResources().getDrawable(R.drawable.vocal));

        return v;
    }

    private static class ViewHolder{
        TextView title, description, descripton2, description3;
        ImageView iv;
    }


}

