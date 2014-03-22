package k57ca.pmp.askeverywhere;

/* Copyright (c) 2012 Google Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import k57ca.pmp.askeverywhere.userendpoint.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;



public class MainActivity extends Activity {

 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_main);
   
   User hoang = new User();
   hoang.setEmail("tienhoangna@gmail.com");
   hoang.setName("Hoang");
   hoang.setUserId("H");
   new AddUserToBackEnd(hoang).execute();
   
   User giap = new User();
   giap.setEmail("giap@gmail.com");
   giap.setName("Giap");
   giap.setUserId("G");
   new AddUserToBackEnd(giap).execute();
   

 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
   // Inflate the menu; this adds items to the action bar if it is present.
   getMenuInflater().inflate(R.menu.main, menu);
   return true;
 }

 /**
  * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
  */

}
