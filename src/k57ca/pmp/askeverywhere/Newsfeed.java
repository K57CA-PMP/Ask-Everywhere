package k57ca.pmp.askeverywhere;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Newsfeed extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        
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
	                String body = "<p>In a scatter plot matrix, I would like to draw a region in every subplot and print the points that are included in the region. I found the <code>LassoSelector</code> widget, which does exactly that. I am trying to extend its functionality for more than one subplots. I am getting the following error: <code>self.xys = collection.get_offsets()</code>, <code>AttributeError: 'numpy.flatiter' object has no attribute 'get_offsets'</code>. when the line <code>selector = SelectFromCollection(axes, ax.flat)</code> is in the for loop, and I am getting the error: <code>self.canvas = ax.figure.canvas</code>,<code>AttributeError: 'numpy.ndarray' object has no attribute 'figure'</code> when the line <code>selector = SelectFromCollection(ax, ax.flat)</code> is outside of the loop. Why does this happen?</p> <p>Here is my code:</p> <pre><code>from __future__ import print_function import numpy as np from matplotlib.widgets import LassoSelector from matplotlib.path import Path class SelectFromCollection(object): \"\"\"Select indices from a matplotlib collection using `LassoSelector`. Selected indices are saved in the `ind` attribute. This tool highlights selected points by fading them out (i.e., reducing their alpha values). If your collection has alpha < 1, this tool will permanently alter them. Note that this tool selects collection objects based on their *origins* (i.e., `offsets`). Parameters ---------- ax : :class:`~matplotlib.axes.Axes` Axes to interact with. collection : :class:`matplotlib.collections.Collection` subclass Collection you want to select from. alpha_other : 0 <= float <= 1 To highlight a selection, this tool sets all selected points to an alpha value of 1 and non-selected points to `alpha_other`. \"\"\" def __init__(self, ax, collection, alpha_other=0.3): self.canvas = ax.figure.canvas self.collection = collection self.alpha_other = alpha_other self.xys = collection.get_offsets() self.Npts = len(self.xys) # Ensure that we have separate colors for each object self.fc = collection.get_facecolors() if len(self.fc) == 0: raise ValueError('Collection must have a facecolor') elif len(self.fc) == 1: self.fc = np.tile(self.fc, self.Npts).reshape(self.Npts, -1) self.lasso = LassoSelector(ax, onselect=self.onselect) self.ind = [] def onselect(self, verts): path = Path(verts) self.ind = np.nonzero([path.contains_point(xy) for xy in self.xys])[0] self.fc[:, -1] = self.alpha_other self.fc[self.ind, -1] = 1 self.collection.set_facecolors(self.fc) self.canvas.draw_idle() print(selector.xys[selector.ind]) #selector.disconnect() def disconnect(self): self.lasso.disconnect_events() self.fc[:, -1] = 1 self.collection.set_facecolors(self.fc) self.canvas.draw_idle() if __name__ == '__main__': import matplotlib.pyplot as plt plt.ion() data=np.loadtxt(r\"data.txt\") x = data[:, 3] x1 = data[:, 4] y = data[:,5] y1 = data[:,6] fig, ax = plt.subplots(nrows=2, ncols=2, squeeze=True) for axes, marker in zip(ax.flat, ['o', 'o']): ax.flat[0].plot(x, y, 'r', ls='', marker=marker) ax.flat[1].plot(x, x1,'r', ls='', marker=marker) ax.flat[2].plot(x, y1,'r', ls='', marker=marker) ax.flat[3].plot(y, x1,'r', ls='', marker=marker) selector = SelectFromCollection(ax, ax.flat) plt.show(block=True) plt.draw() </code></pre> ";
	                IntentsUtils.startAnswerActivity(Newsfeed.this, item, body);
	              }
	          });
	      }

	    });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
		int base = Menu.FIRST;
		MenuItem search = menu.add(base, 1, 1, "Search");
		MenuItem addQuestion = menu.add(base, 2, 2, "Add question");
		MenuItem help = menu.add(base, 3, 3, "Help");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
        case 1:
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Newsfeed) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
