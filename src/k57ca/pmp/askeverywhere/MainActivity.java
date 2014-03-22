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
import android.view.View;
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
 
    // JSON Node names
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";
    
    private static final String TAG_ITEMS = "items";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINK = "link";
    private static final String TAG_BODY = "body";
 
    // contacts JSONArray
    JSONArray items = null;
 
    // Hashmap for ListView
    public ArrayList<HashMap<String, String>> itemList;
    
    public String[] titles = new String[1000];
    public String[] bodys = new String[1000];
    int index = 0;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        itemList = new ArrayList<HashMap<String, String>>();
 
        ListView lv = getListView();
 
        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                    int position, long id) {
                // getting values from selected ListItem
                String title = ((TextView) view.findViewById(R.id.title))
                        .getText().toString();
                String description = ((TextView) view.findViewById(R.id.label))
                        .getText().toString();
  	    	  view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
	              @Override
	              public void run() {
//	                list.remove(item);
//	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	          });
                Log.d(title, description);
            }
        });
 
        // Calling async task to get json
        new GetContacts().execute();
    }
 
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {
 
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
                         
                        String title = q.getString(TAG_TITLE);
                        String link = q.getString(TAG_LINK);
                        String body = q.getString(TAG_BODY);
                        titles[index] = title;
                        bodys[index] = body;
                        
                        Log.d(title, body);
                        
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
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, itemList,
                    R.layout.question, new String[] { TAG_TITLE, TAG_LINK },
                    			new int[] { R.id.title, R.id.label});
 
            setListAdapter(adapter);
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