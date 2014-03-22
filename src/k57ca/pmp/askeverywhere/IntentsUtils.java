package k57ca.pmp.askeverywhere;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class IntentsUtils {
	public static void newsfeed(Activity activity) {
		Intent intent = new Intent(activity, Newsfeed.class);
		activity.startActivity(intent);
	}
	
	public static void invokeWebBrowser(Activity activity){ 
		Intent intent = new Intent(Intent.ACTION_VIEW); 
		intent.setData(Uri.parse("http://www.google.com.vn")); 
		activity.startActivity(intent); 
	}
	
	public static void startAnswerActivity(Activity activity, int position){ 
		Intent intent = new Intent(activity, AnswerActivity.class);  
		intent.putExtra("position", position);
		activity.startActivity(intent); 
	}
}
