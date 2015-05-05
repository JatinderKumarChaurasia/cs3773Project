package com.marsdayjam.eventplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static android.view.View.OnClickListener;


public class AddCalendarEventFragment extends Fragment{

    private static Boolean eventEntered = false;
    private static Boolean timeEntered = false;
    private static Button add, cancel;
    private static String date;
    private static String startTime;
    private static String endTime;
    private static int startH;
    private static int startM;
    private static int endH;
    private static int endM;
    private static String event = "";
    private static EditText text;
    private static Boolean correctTime = Boolean.TRUE;
    private CalendarFragment calendarFragment;

    //Constructor, pass in necessary arguments such as date
    public AddCalendarEventFragment(CalendarFragment calendarFragment){
        this.calendarFragment = calendarFragment;
    }

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_calendar_event, container, false);
        add = (Button) view.findViewById (R.id.addCalendarEvent);
        add.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                text = (EditText) v.findViewById(R.id.addCalendarEvent);
                event = text.toString();
                if(event.isEmpty())
                    eventEntered = Boolean.FALSE;

                //take care of an invalid Time insertions
                text = (EditText) v.findViewById(R.id.start);
                startTime = text.toString();
                text = (EditText) v.findViewById(R.id.end);
                endTime = text.toString();
                if(startTime.isEmpty() && endTime.isEmpty()){
                    timeEntered = Boolean.FALSE;
                }
                else if(startTime.isEmpty() ^ endTime.isEmpty()){
                    timeEntered = Boolean.TRUE;
                    correctTime = Boolean.FALSE;
                }
                else if(startTime.contains(":") == false || endTime.contains(":") == false) {
                    timeEntered = Boolean.TRUE;
                    correctTime = Boolean.FALSE;
                }
                else{
                    String sTimes[] = startTime.split(":");
                    String eTimes[] = endTime.split(":");
                    timeEntered = Boolean.TRUE;
                    if(sTimes.length > 2 || eTimes.length > 2) {
                        correctTime = Boolean.FALSE;
                    }
                    else {
                        startH = Integer.parseInt(sTimes[0]);
                        startM = Integer.parseInt(sTimes[1]);
                        endH = Integer.parseInt(eTimes[0]);
                        endM = Integer.parseInt(eTimes[1]);
                        if(24 < startH || 0 > startH || 0 > startM || 59 < startM ||
                                24 < endH || 0 > endH || 0 > endM || 59 < endM){
                            correctTime = Boolean.FALSE;
                        }
                    }
                }

                if(eventEntered == true){
                    if(timeEntered == Boolean.TRUE && correctTime == Boolean.TRUE){
                        //do something with the correct time values and the event message
                    }
                    else if( correctTime == Boolean.TRUE ){
                        startH = 0;
                        startM = 0;
                        endH = 23;
                        endM = 59;
                        //do something with these values representing all day event and the event message
                    }

                    //is incorrect input
                    else{

                    }
                }
            }
        });


        cancel = (Button) view.findViewById (R.id.cancelCalendarEdit);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });


        return view;
    }

    public void remove(){
        eventEntered = Boolean.FALSE;
        timeEntered = Boolean.FALSE;
        //arbitrary method to go back to calendarOptions fragment
        calendarFragment.recreate(this);
    }
}
