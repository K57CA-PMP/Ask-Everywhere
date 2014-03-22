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

	public static void userProfile(Activity activity) {
		Intent intent = new Intent(activity, UserProfileActivity.class);
		activity.startActivity(intent); 
	}
	
	public static void LogIn(Activity activity) {
		Intent intent = new Intent(activity, LogInActivity.class);
		activity.startActivity(intent);
	}
	
	public static void LogInGoogle(Activity activity) {
		Intent intent = new Intent(activity, AuthActivity.class);
		activity.startActivity(intent);
	}
	
	public static void AddConnections(Activity activity) {
		Intent intent = new Intent(activity, AddConnections.class);
		activity.startActivity(intent);
	}
}
