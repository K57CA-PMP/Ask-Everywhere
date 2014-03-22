package k57ca.pmp.askeverywhere;

import java.io.IOException;

import k57ca.pmp.askeverywhere.userendpoint.Userendpoint;
import k57ca.pmp.askeverywhere.userendpoint.model.User;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

class GetUserFromBackEnd extends AsyncTask<Long, Void, User> {

	   /**
	    * Calls appropriate CloudEndpoint to indicate that user checked into a place.
	    *
	    * @param params the place where the user is checking in.
	    */
	   Long id;
	   
	   public GetUserFromBackEnd(Long id){
		   super();
		   this.id = id;
	   }
	
	   @Override
	   protected User doInBackground(Long... params) {
		   
	     User user = new User();
	     user.setName("Invalid");
	     
	     // Set the ID of the store where the user is. 
	     // This would be replaced by the actual ID in the final version of the code. 

	     Userendpoint.Builder builder = new Userendpoint.Builder(
	         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
	         null);
	         
	     builder = CloudEndpointUtils.updateBuilder(builder);

	     Userendpoint endpoint = builder.build();
	     

	     try {
	       user = endpoint.getUser(id).execute();
	     } catch (Exception e) {
	       // TODO Auto-generated catch block
	       Log.w("User","can be got");
	     }

	     return user;
	   }
}
