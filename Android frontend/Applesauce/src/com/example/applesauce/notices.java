package com.example.applesauce;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import android.widget.TextView;

public class notices extends Activity {
	
	private ProgressDialog pDialog;
	JSONParser jparse=new JSONParser();
	public static String id;
	public static String url;
	public static String sn;
	public static String message="";
	
	TextView tv;
	EditText et;
	Button sub;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notices);
		
		Intent i = getIntent();
		id=i.getStringExtra("ID");
		url="http://www.applesauce8412.in/faculty/setnotice/";
		
		url=url+id;
		
		tv=(TextView) findViewById(R.id.textView2);
		
		et=(EditText) findViewById(R.id.editText1);
		
		sub=(Button) findViewById(R.id.button1);
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new ValidateNow().execute();
			}
		});
		
		
		
	}
	
	
	public class ValidateNow extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			pDialog = new ProgressDialog(notices.this);
			pDialog.setMessage("Please wait ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {

           List<NameValuePair> params=new ArrayList<NameValuePair>();
           sn=et.getText().toString();
           params.add(new BasicNameValuePair("notice", sn));
           
           JSONObject json=jparse.makeHttpRequest(url,"POST", params);
           
           try {
			int success = json.getInt("success");
			
			if(success==1)
				message="Your notice has been updated .";
				else
				message="ERROR ! Unable to update .";
					
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
			tv.setText(message);
			
		}
		
	}
	
	

}
