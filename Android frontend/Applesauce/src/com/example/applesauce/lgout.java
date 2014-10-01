package com.example.applesauce;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
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
import android.widget.TextView;

public class lgout extends Activity {
	
	private ProgressDialog pDialog;
	JSONParser jpase=new JSONParser();
	public static String id;
	public static String url;
	public static String message;
	TextView tv,mes;
	Button mp;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(lgout.this,MainActivity.class);
		i.putExtra("E","yes");
		finish();
		startActivity(i);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.out);
		
		Intent i =getIntent();
		id=i.getStringExtra("ID");
		
		url="http://www.applesauce8412.in/authenticate/logout/";
		url=url+id;
		tv=(TextView) findViewById(R.id.textView1);
		
		tv.setText(id);
		mes=(TextView) findViewById(R.id.textView2);
		mp=(Button) findViewById(R.id.button1);
		
		mp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(lgout.this,MainActivity.class);
				i.putExtra("E","yes");
				finish();
				startActivity(i);
				
			}
		});
		
		
		new VaidateNow().execute();
		
	     
		
	}
	
	public class VaidateNow extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pDialog = new ProgressDialog(lgout.this);
			pDialog.setMessage("Please wait ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
			
		}
		@Override
		protected String doInBackground(String... args) {
		
			List<NameValuePair> params =new ArrayList<NameValuePair>();
			
			JSONObject json = jpase.makeHttpRequest(url,"GET", params);
			
			try {
				int success=json.getInt("success");
				if(success==1)
					message="You have successfully logged out .";
				else
					message="Error! logging out .";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			mes.setText(message);
		}
		
	}

}
