package com.alchemyburn.alchemypocketguide15;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class EventActivity extends Activity {

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
