package com.devbliss.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.net.URI;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "OnCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "OnStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LOG_TAG, "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "OnResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(LOG_TAG, "OnStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "OnDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent settingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(settingsActivity);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_show_location) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//            Uri.Builder builder = new Uri.Builder();
//            builder.encodedPath("geo:0,0");
//            builder.appendQueryParameter("q",prefs.getString(getString(R.string.pref_location_key),getString(R.string.pref_location_default)));
//            showMap(builder.build());
            String location = Utility.getPreferredLocation(this);
            showMap(Uri.parse("geo:0,0?q="+location));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
