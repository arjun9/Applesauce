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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class available extends Activity {
	
	private ProgressDialog pDialog;

	JSONParser jparse=new JSONParser();
	
	public static String url;
	public static String id;
	public static String selected;
	public static String message="";
	
	Spinner sp;
	Button up;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.available);
		
		Intent i = getIntent();
		id=i.getStringExtra("ID");
		url="http://www.applesauce8412.in/faculty/setavailability/";
		url=url+id;
		
		sp = (Spinner) findViewById(R.id.spinner1);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.planets_array, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp.setAdapter(adapter);
		
		up=(Button) findViewById(R.id.button1);
		
		tv=(TextView) findViewById(R.id.textView2);
		
		up.setOnClickListener(new OnClickListener() {
			
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
		
		pDialog = new ProgressDialog(available.this);
		pDialog.setMessage("Please wait ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
		
		
		}

		@Override
		protected String doInBackground(String... args) {
			
			List<NameValuePair> params= new ArrayList<NameValuePair>();
			
			selected=String.valueOf(sp.getSelectedItem());
			params.add(new BasicNameValuePair("available", selected));
			
			JSONObject json=jparse.makeHttpRequest(url,"POST", params);
			
			try {
				int success=json.getInt("success");
				if(success==1)
					message="AVAILABILITY  STATUS  HAS  BEEN  SET  TO :\n\n"+selected;
				else
					message="ERROR! UPDATING STATUS";
			} catch (JSONException e) {
				
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
