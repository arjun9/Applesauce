package com.example.applesauce;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class loginpage extends Activity {

	private ProgressDialog pDialog;
	
	JSONParser jparse= new JSONParser();
	public static String url="http://www.applesauce8412.in/authenticate/login";
	public static String TAG_SUCCESS="success";
	public static String TAG_MESSAGE="message";
	public static String TAG_ID="ID";
	
	public static String d="";
	EditText username;
	EditText password;
	Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginpage);
		
	
		username=(EditText) findViewById(R.id.editText1);
		password=(EditText) findViewById(R.id.editText2);
		submit=(Button) findViewById(R.id.button1);
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				new ValidateNow().execute();
				
			}
		});
		
	}
	
	public class ValidateNow extends AsyncTask<String, String, String>
	{
  
		@Override
		protected void onPreExecute() {
		super.onPreExecute();

		pDialog = new ProgressDialog(loginpage.this);
		pDialog.setMessage("Please wait ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			String uname=username.getText().toString();
			String pass=password.getText().toString();
			
			params.add(new BasicNameValuePair("username", uname));
			params.add(new BasicNameValuePair("password", pass));
			
			JSONObject json = jparse.makeHttpRequest(url,"POST", params);
			
			try {
				int success=json.getInt(TAG_SUCCESS);
				String message=json.getString("message");
				
				if(success==1)
				{
				String id=json.getString(TAG_ID);
				Intent i= new Intent(loginpage.this,profile.class);
				i.putExtra(TAG_ID,id);
				startActivity(i);
				}
				else
				{
					Intent i= new Intent(loginpage.this,error.class);
					i.putExtra("message", message);
					startActivity(i);
					
				}
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			pDialog.dismiss();
		}
		
	}
}
