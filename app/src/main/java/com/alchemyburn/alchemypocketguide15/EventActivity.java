package com.alchemyburn.alchemypocketguide15;

import android.app.Activity;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventActivity extends AppCompatActivity {

    AlchemyEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);

        Intent intent = getIntent();

        String jsonString = intent.getStringExtra(DayViewActivity.EVENT_DATA);

        try {
            event = new AlchemyEvent(new JSONObject(jsonString));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setTitle(event.getEventName());

        TextView eventNameView = (TextView) findViewById(R.id.eventNameView);
        eventNameView.setText(event.getEventName());

        TextView startView = (TextView) findViewById(R.id.startDateView);
        startView.setText(event.getStartDate());

        TextView endView = (TextView) findViewById(R.id.endDateView);
        endView.setText(event.getEndDate());

        TextView locationView = (TextView) findViewById(R.id.locationView);
        locationView.setText(event.getLocation());

        TextView allDayView = (TextView) findViewById(R.id.allDayView);
        allDayView.setText(event.getAllDayEvent());

        TextView daysView = (TextView) findViewById(R.id.daysView);
        daysView.setText(event.getDaysText());

        TextView descriptionView = (TextView) findViewById(R.id.descriptionView);
        descriptionView.setText(event.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_calendar_event) {
            createCalendarEventForAlchemyEvent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createCalendarEventForAlchemyEvent() {
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(getDate(event.getStartDate()));
        Calendar endTime = Calendar.getInstance();
//        endTime.set(getDate(event.getEndDate()));
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, event.getEventName())
                .putExtra(CalendarContract.Events.DESCRIPTION, event.getDescription())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLocation())
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;BYDAY=" + event.getDaysAbrv() + " ;UNTIL=20151004T000000Z");
        startActivity(intent);
    }

    private Date getDate(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a dd/mm/yy");
        Date date = null;

//        try {
//            date = sdf.parse(stringDate + );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return date;
    }
}
