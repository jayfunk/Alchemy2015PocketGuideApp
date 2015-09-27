package com.alchemyburn.alchemypocketguide15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayViewActivity extends AppCompatActivity {

    List<Map<String, String>> eventNames;

    List<AlchemyEvent> events = new ArrayList<AlchemyEvent>();

    public final static String EVENT_DATA = "com.alchemyburn.alchemypocketguide15.EVENT_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_day_view);

        Intent intent = getIntent();

        String daySelected = intent.getStringExtra(SelectDayActivity.SELECTED_DAY);

        setTitle(getTitle().toString() + " " + daySelected);

        eventNames = this.getDaysEventData(daySelected);

        eventNames = sortEventsAlphabetically(eventNames);

        ListView dayView = (ListView) findViewById(R.id.dayView);

        dayView.setOnItemClickListener(new onEventItemClickListener());

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, eventNames, android.R.layout.simple_list_item_1, new String[] {"eventName"}, new int[] {android.R.id.text1});

        dayView.setAdapter(simpleAdapter);

    }

    private List<Map<String,String>> sortEventsAlphabetically(List<Map<String, String>> eventNames) {
        Collections.sort(eventNames, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> lhs, Map<String, String> rhs) {
                return lhs.get("eventName").compareToIgnoreCase(rhs.get("eventName"));
            }
        });

        return eventNames;
    }

    private class onEventItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(DayViewActivity.this, EventActivity.class);
            intent.putExtra(EVENT_DATA, events.get(position).getJSONEvent().toString());
            startActivity(intent);
        }
    }

    private List<Map<String, String>> getDaysEventData(String daySelected){

        JSONArray jsonArray = getJSONArray();

        List<Map<String, String>> eventData = new ArrayList<Map<String,String>>();

        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject eventJSON = jsonArray.getJSONObject(i);
                AlchemyEvent event = new AlchemyEvent(eventJSON);

                if(!isOnSelectedDay(daySelected, event)){
                    continue;
                }

                HashMap<String, String> eventNameMap = new HashMap<String, String>();
                eventNameMap.put("eventName", event.getEventName());
                eventData.add(eventNameMap);
                this.events.add(event);

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        return eventData;
    }

    private boolean isOnSelectedDay(String daySelected, AlchemyEvent event) {
        boolean isFromSelectedDay = false;
        String[] days = event.getDays();
        for(int i = 0; i < days.length; i++){
            String day = days[i];
            if(day.equalsIgnoreCase(daySelected)){
                isFromSelectedDay = true;
            }
        }
        return isFromSelectedDay;
    }


    private JSONArray getJSONArray(){
        String jsonString = parseEventJSONData();

        try {
            return new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseEventJSONData() {
        InputStream is = getResources().openRawResource(R.raw.data);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        return writer.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
