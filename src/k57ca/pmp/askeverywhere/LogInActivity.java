package k57ca.pmp.askeverywhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogInActivity extends Activity {
		
	Button Log_in_google; 
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.login);
		
		Log_in_google = (Button) findViewById(R.id.log_in_with_google);
		
		Log_in_google.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				// TODO Auto-generated method stub	
				Intent intent = new Intent(view.getContext(), AuthActivity.class);
				startActivity(intent);
			}
		});
	}
}


