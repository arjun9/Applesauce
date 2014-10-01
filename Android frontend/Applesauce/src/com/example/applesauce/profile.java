package com.example.applesauce;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
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
import android.widget.TextView;

public class profile extends Activity {
	
    private ProgressDialog pDialog;
    JSONParser jparse= new JSONParser();
    JSONArray data=null;
    
    public static String url;
    
	public static String id;
	public static String fname="";
	public static String fcabin="";
	public static String fcontact="";
	public static String fopen="";
	public static String favailable="";
	public static String fnotice="";
	
	public static String message;
	public static String scess;
	
	Button avail;
	Button logout,refresh,notic,edi;
	TextView iden,name,opo,cabin,contact,available,notice;
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = getIntent();
		finish();
		startActivity(i);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		Intent i = getIntent();
		id=i.getStringExtra("ID");
		
		url="http://www.applesauce8412.in/faculty/profile/";
		url=url+id;
		
		iden=(TextView) findViewById(R.id.textView1);
		iden.setText(id);
        name=(TextView) findViewById(R.id.textView2);
        cabin=(TextView) findViewById(R.id.textView3);
        contact=(TextView) findViewById(R.id.textView4);
        opo=(TextView) findViewById(R.id.textView5);
        available=(TextView) findViewById(R.id.textView6);
        notice=(TextView) findViewById(R.id.textView7);
        
        new ValidateNow().execute();
        
        avail=(Button) findViewById(R.id.button2);
        
        refresh=(Button) findViewById(R.id.button4);
        logout=(Button) findViewById(R.id.button5);
        notic=(Button) findViewById(R.id.button1);
        edi=(Button) findViewById(R.id.button3);
        edi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
  
				Intent i =new Intent(profile.this,edit.class);
				i.putExtra("ID", id);
				startActivity(i);
			}
		});
        
        
        notic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent i =new Intent(profile.this,notices.class);
				i.putExtra("ID", id);
				startActivity(i);
				
			}
		});
        
        logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i =new Intent(profile.this,lgout.class);
				i.putExtra("ID", id);
				startActivity(i);
				
			}
		});
        
        refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=getIntent();
				finish();
				i.putExtra("ID", id);
				startActivity(i);
				
			}
		});
        
        avail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(profile.this,available.class);
				i.putExtra("ID", id);
				startActivity(i);
				
			}
		});
		}
	
	public class ValidateNow extends AsyncTask<String, String, String>
	{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pDialog = new ProgressDialog(profile.this);
			pDialog.setMessage("Please wait ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... args) {
			
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			
			JSONObject json=jparse.makeHttpRequest(url,"GET", params);
			
			try {
				int success=json.getInt("success");
				message=json.getString("message");
				if(success==1)
				{
					
					data=json.getJSONArray("data");
					JSONObject ob=data.getJSONObject(0);
					fname="NAME  :  "+ob.getString("name");
					fcabin="CABIN NO. :  "+ob.getString("cabin");
					fcontact="CONTACT NO. :  "+ob.getString("contact"); 
					fopen="OPEN HOURS  :\n\n"+ob.getString("opo")+"\n\n"+ob.getString("opt");
					favailable="AVAILABILITY  : "+ob.getString("available");
					fnotice="NOTICE  :\n\n"+ob.getString("notice");
					
				}
				else
				{
					//we'll do this later
                 Intent i = new Intent(profile.this,err.class);
					i.putExtra("ID", id);
					finish();
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
			name.setText(fname);
			cabin.setText(fcabin);
			contact.setText(fcontact);
			opo.setText(fopen);
			available.setText(favailable);
			notice.setText(fnotice);
			
		}
	}

}
