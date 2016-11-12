package com.abheri.sunaad.view.program;

/**
 * Created by Maha on 27/12/15.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abheri.sunaad.R;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.view.Util;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class VenueExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> sabhaCollections;
    private List<String> sabhas;

    public VenueExpandableListAdapter(Activity context, List<String> sabhaList,
                                      Map<String, List<String>> sabhaCollections) {
        this.context = context;
        this.sabhaCollections = sabhaCollections;
        this.sabhas = sabhaList;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return sabhaCollections.get(sabhas.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Program program = (Program) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.prg_venue_child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.sprogramDetail);
        TextView aDate = (TextView)convertView.findViewById(R.id.aDate);
        ImageView iv = (ImageView) convertView.findViewById(R.id.adArtisteImage);

        String uri = program.getArtiste_image();
        if(uri == null && uri.length()<=0){
            uri = "@drawable/default_artiste";
        }

        uri = Util.getImageUrl() + uri;


        /*
        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<String> child =
                                        artisteCollections.get(artistes.get(groupPosition));
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        */

        item.setText(program.getDetails());
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String eventDate = sdf.format(program.getEventDate()).toString();

        aDate.setText(eventDate);
        Picasso.with(context)
                .load(uri)
                .placeholder(R.drawable.default_artiste)
                .into(iv);

        if (Util.isEventToday(program, false) < 0) {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.oldgray));
        }
        else {
            convertView.setBackgroundColor(convertView.getResources().getColor(android.R.color.white));
        }
        if (Util.isEventToday(program, true) == 1) {
            item.setTextColor(convertView.getResources().getColor(R.color.darkblue));
            aDate.setTextColor(convertView.getResources().getColor(R.color.darkblue));
        }
        else
        {
            item.setTextColor(convertView.getResources().getColor(R.color.black));
            aDate.setTextColor(convertView.getResources().getColor(R.color.black));
        }
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return sabhaCollections.get(sabhas.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return sabhas.get(groupPosition);
    }

    public int getGroupCount() {
        return sabhas.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String sabhaName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.prg_venue_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.sabhaName);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(sabhaName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
