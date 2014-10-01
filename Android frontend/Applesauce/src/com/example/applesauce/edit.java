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

public class edit extends Activity {
	
	private ProgressDialog pDialog;
	JSONParser jparse = new JSONParser();
	public static String id;
	public static String url;
	public static String message="";
	
	TextView tv;
	Spinner sp,sp1,sp2,sp3;
	Button sub;
	
	EditText contact,cabin;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edi);
		
		Intent i=getIntent();
		id=i.getStringExtra("ID");
		url="http://www.applesauce8412.in/faculty/edit/";
		url=url+id;
		
		tv=(TextView) findViewById(R.id.textView11);
		sp=(Spinner) findViewById(R.id.spinner1);
		sp2=(Spinner) findViewById(R.id.spinner3);
		sp1=(Spinner) findViewById(R.id.spinner2);
		sp3=(Spinner) findViewById(R.id.spinner4);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.planets_array1, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
		        R.array.planets_array2, android.R.layout.simple_spinner_item);
		
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp.setAdapter(adapter);
		sp2.setAdapter(adapter);
		
		sp1.setAdapter(adapter1);
		sp3.setAdapter(adapter1);
		
		contact=(EditText) findViewById(R.id.editText1);
		cabin=(EditText) findViewById(R.id.editText2);
		
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
		
		pDialog = new ProgressDialog(edit.this);
		pDialog.setMessage("Please wait ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
		
		}

		@Override
		protected String doInBackground(String... args) {
			
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			String cab=cabin.getText().toString().toUpperCase();
			String cnt=contact.getText().toString();
			String opod=String.valueOf(sp.getSelectedItem());
			String opot=String.valueOf(sp1.getSelectedItem());
			String optd=String.valueOf(sp2.getSelectedItem());
			String optt=String.valueOf(sp3.getSelectedItem());
			
			String opo=opod+" "+opot;
			String opt=optd+" "+optt;
			
			params.add(new BasicNameValuePair("cabin", cab));
			params.add(new BasicNameValuePair("contact", cnt));
			params.add(new BasicNameValuePair("opo", opo));
			params.add(new BasicNameValuePair("opt", opt));
			
			JSONObject json=jparse.makeHttpRequest(url,"POST", params);
			
			try {
				int success=json.getInt("success");
				if(success==1)
					message="Your Profile has been updated";
				else
					message="Error updating your profile, Try Again !";
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
