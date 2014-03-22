package k57ca.pmp.askeverywhere;

import java.io.IOException;

import k57ca.pmp.askeverywhere.userendpoint.Userendpoint;
import k57ca.pmp.askeverywhere.userendpoint.model.User;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

class AddUserToBackEnd extends AsyncTask<User, Void, Void> {

	   /**
	    * Calls appropriate CloudEndpoint to indicate that user checked into a place.
	    *
	    * @param params the place where the user is checking in.
	    */
	   User user;
	   
	   public AddUserToBackEnd(User user){
		   super();
		   this.user = user;
	   }
	
	   @Override
	   protected Void doInBackground(User... params) {
	     User userToAdd = user;
	     
	     // Set the ID of the store where the user is. 
	     // This would be replaced by the actual ID in the final version of the code. 

	     Userendpoint.Builder builder = new Userendpoint.Builder(
	         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
	         null);
	         
	     builder = CloudEndpointUtils.updateBuilder(builder);

	     Userendpoint endpoint = builder.build();
	     

	     try {
	       endpoint.insertUser(userToAdd).execute();
	     } catch (IOException e) {
	       // TODO Auto-generated catch block
	       e.printStackTrace();
	     }

	     return null;
	   }
}
