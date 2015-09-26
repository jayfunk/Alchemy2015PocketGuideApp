package com.alchemyburn.alchemypocketguide15;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectDayActivity extends AppCompatActivity {

    public static final String SELECTED_DAY = "com.alchemyburn.alchemypocketguide15.SELECTED_DAY";

    List<Map<String, String>> days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        days = initDays();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day);

        ListView daysView = (ListView) findViewById(R.id.selectDayView);

        daysView.setOnItemClickListener(new onEventItemClickListener());

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, days, android.R.layout.simple_list_item_1, new String[] {"day"}, new int[] {android.R.id.text1});

        daysView.setAdapter(simpleAdapter);
    }

    private List<Map<String, String>> initDays() {
        List<Map<String, String>> daysList =  new ArrayList<Map<String, String>>();

        String[] dayNames = new String[] {"Thursday", "Friday", "Saturday", "Sunday"};

        for(int i = 0; i < dayNames.length; i++) {
            Map<String, String> day = new HashMap<String, String>();

            String dayName = dayNames[i];

            day.put("day", dayName);

            daysList.add(day);
        }

        return daysList;
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

    private class onEventItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SelectDayActivity.this, DayViewActivity.class);
            intent.putExtra(SELECTED_DAY, days.get(position).get("day"));
            startActivity(intent);
        }
    }
}
