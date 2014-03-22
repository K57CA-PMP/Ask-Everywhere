package k57ca.pmp.askeverywhere;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

	private AuthPreferences authPreferences;
	private AccountManager accountManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);			
		if (authPreferences.getUser() != null
				&& authPreferences.getToken() != null){			
		} else
			{
			startActivity(new Intent(this, LogInActivity.class));
		}
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
=======
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
 
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
 
    // Hashmap for ListView
    public ArrayList<HashMap<String, String>> itemList;
    
    public static String[] titles = new String[30];
    public static String[] bodies = new String[30];
    public static int[] icons = new int[30];
    int index = 0;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
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
//		MenuItem addQuestion = menu.add(base, 2, 2, "Add question");
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
        	break;
        case 3:
        	break;
        default:
        	return super.onOptionsItemSelected(item);
        }
        return true;
    }
    
}

