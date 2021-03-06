package k57ca.pmp.askeverywhere;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class MainActivity extends ListActivity {
 
    private ProgressDialog pDialog;
 
    // URL to get contacts JSON
    private static String url = "http://api.stackexchange.com/2.2/questions?order=desc&sort=activity&site=stackoverflow&filter=withBody";
    
    private static final String TAG_ITEMS = "items";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINK = "link";
    private static final String TAG_BODY = "body";
 
    // contacts JSONArray
    JSONArray items = null;
    
    public static String[] titles = new String[30];
    public static String[] bodies = new String[30];
    public static int[] icons = new int[30];
    int index = 0;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        IntentsUtils.LogIn(this);
 
        // Calling async task to get json
        new GetQuestions().execute();
    }
 
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetQuestions extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
 
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
 
            Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                     
                    // Getting JSON Array node
                    items = jsonObj.getJSONArray(TAG_ITEMS);
 
                    // looping through All Contacts
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject q = items.getJSONObject(i);
                       
                        titles[index] = q.getString(TAG_TITLE);
                        bodies[index] = q.getString(TAG_BODY);
                        icons[index] = (int)(Math.random() * 2) + 1;
                        
                        Log.d(titles[index], bodies[index]);
                        
                        index++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
 
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
        }
 
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
		int base = Menu.FIRST;
		MenuItem search = menu.add(base, 1, 1, "Newsfeed");
		MenuItem login = menu.add(base, 2, 2, "Login");
//		MenuItem help = menu.add(base, 3, 3, "Help");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
        case 1:
        	IntentsUtils.newsfeed(this);
        	break;
        case 2:
        	IntentsUtils.LogIn(this);
        	break;
        case 3:
        	break;
        default:
        	return super.onOptionsItemSelected(item);
        }
        return true;
    }
    
}

