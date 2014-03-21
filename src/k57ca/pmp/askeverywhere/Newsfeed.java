package k57ca.pmp.askeverywhere;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

@SuppressLint("NewApi")
public class Newsfeed extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsfeed);
		
		final ListView listview = (ListView) findViewById(R.id.list_view);
	    String[] values = new String[] { "Stack Overflow", "Quora", "Stack Overflow", 
	    				"ABC", "XYZ", "Stack Overflow", "Stack Overflow", "Question1", 
	    				"Stack Overflow", "Stack Overflow", "Stack Overflow",
	    				"Question2", "Android/Eclipse: how can I add an image in the res/drawable folder?",
	    				"Can The Android drawable directory contain subdirectories?",
	    				"In the Android SDK documentation, all of the examples used with the " +
	    				"@drawable/my_image xml syntax directly address images that are stored in" +
	    				" the res/drawable directory in my project.",
	    				"Stack Overflow" };

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	    	list.add(values[i]);
	    }
	    final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	    	  final String item = (String) parent.getItemAtPosition(position);
	    	  view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                list.remove(item);
	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	          });
	      }

	    });
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.newsfeed, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_newsfeed,
					container, false);
			return rootView;
		}
	}

}
