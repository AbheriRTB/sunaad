package com.abheri.sunaad.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.abheri.sunaad.R;
import com.abheri.sunaad.controller.SettingsController;
import com.abheri.sunaad.model.Settings;
import com.abheri.sunaad.model.SettingsDataHelper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{

    Context context;
    View rootView;
    TextView days_before_textview, alarm_time;
    CheckBox sound_alarm_textview;
    Button settings_save, settings_cancel;
    PopupWindow timeSelectPopup;
    SettingsController settingsController;

    public SettingsFragment() {
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_settings, container,
                false);

        if(null == context){
            context = rootView.getContext();
            settingsController = new SettingsController(context);
        }

        days_before_textview = (TextView)rootView.findViewById(R.id.AlarmDaysBefore);
        alarm_time = (TextView)rootView.findViewById(R.id.AlarmTime);
        sound_alarm_textview = (CheckBox)rootView.findViewById(R.id.SoundAlarm);
        settings_save = (Button)rootView.findViewById(R.id.settings_save);
        settings_cancel = (Button)rootView.findViewById(R.id.settings_cancel);

        settings_save.setOnClickListener(this);
        settings_cancel.setOnClickListener(this);
        alarm_time.setOnClickListener(this);

        Settings stg = settingsController.GetSettings();
        if(stg != null) {
            setDefaults(stg);
        }

        Util.logToGA(Util.SETTINGS_SCREEN);

        return rootView;
    }

    public void setDefaults(Settings default_settings){

        days_before_textview.setText((new Integer(default_settings.getDaysBefore())).toString());
        alarm_time.setText(default_settings.getAtTime());
        sound_alarm_textview.setChecked(default_settings.getSound_alarm()>0?true:false);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.settings_save){
            String days_before = (String)days_before_textview.getText().toString();
            String at_time = (String)alarm_time.getText().toString();
            boolean sound_alarm = (boolean)sound_alarm_textview.isChecked();

            String msg = "Min:" + days_before + " Sound:" + sound_alarm;

            days_before = days_before.trim();
            if(days_before.length() <=0 ){
                days_before = "0";
            }
            Settings stg = new Settings();
            stg.setAtTime(at_time);
            stg.setDaysBefore(Integer.parseInt(days_before));
            stg.setSound_alarm(sound_alarm ? 1 : 0);

            settingsController.SaveSettings(stg);

            Toast.makeText(context, "Settings saved successfully", Toast.LENGTH_LONG).show();
            hideKeyaborad(v);
            getFragmentManager().popBackStack();
        }else if (id == R.id.AlarmTime) {
            showTimePickerPopup(rootView);
        }
        else if (id == R.id.settings_cancel){
            hideKeyaborad(v);
            getFragmentManager().popBackStack();
        }

    }

    void showTimePickerPopup(View rootView){

        // Inflate the popup_layout.xml
        LayoutInflater inflater = LayoutInflater.from(context);

        final View popupView = inflater.inflate(R.layout.layout_timepicker, null);

        timeSelectPopup = new PopupWindow(popupView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        timeSelectPopup.setFocusable(true);
        // Displaying the popup at the specified location, + offsets.
        timeSelectPopup.showAtLocation(rootView, Gravity.CENTER, 0,0);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) popupView.findViewById(R.id.timeSelect);

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePicker tp = (TimePicker)popupView.findViewById(R.id.timePicker);
                int hour = tp.getCurrentHour();
                int min = tp.getCurrentMinute();

                timeSelectPopup.dismiss();
                String selectTime = hour + ":" + min;
                alarm_time.setText(selectTime);

            }
        });
    }

    void hideKeyaborad(View v){
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isAcceptingText()){
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }
}
