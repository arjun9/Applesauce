package com.example.applesauce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class facultydetails extends ListActivity {
	private ProgressDialog pDialog;
	JSONArray departments=null;
	JSONParser jparse= new JSONParser();
	ArrayList<HashMap<String,String>> schools;
	
	public static String url;
	private static String TAG_NAME="name";
	private static String TAG_CABIN="cabin";
	private static String TAG_CONTACT="contact";
	private static String TAG_OPO="opo";
    private static String TAG_OPT="opt";
	private static String TAG_OPEN="openhrs";
	private static String TAG_NOTICE="notice";
	private static String TAG_AVAILABLE="available";
	private static String TAG_SUCCESS="success";
	private static String TAG_MESSAGE="message";
	private static String TAG_ID="ID";
	
	public static String id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		url="http://www.applesauce8412.in/user/3/";
		Intent i= getIntent();
		id=i.getStringExtra("ID");
		
		url=url+id;
		
		schools=new ArrayList<HashMap<String,String>>();
		new ValidateNow().execute();
		ListView lv = getListView();
		
		Button b= (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent i = getIntent();
				finish();
				i.putExtra("ID", id);
				startActivity(i);
				
			}
		});
		
		/*lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();
				Intent i = new Intent(getApplicationContext(),facultydetails.class);
				i.putExtra(TAG_ID,pid);
				startActivity(i);
				
			}
		});*/
	}
	
	public class ValidateNow extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
		super.onPreExecute();
		
		pDialog = new ProgressDialog(facultydetails.this);
		pDialog.setMessage("Please wait ...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
			
		}

		@Override
		protected String doInBackground(String... args)
		{
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			
			JSONObject json=jparse.makeHttpRequest(url,"GET", params);
			
			Log.d("HERE",json.toString());
			try {
				int success=json.getInt(TAG_SUCCESS);
				String message=json.getString(TAG_MESSAGE);
				departments=json.getJSONArray("data");
				
				if(success==1)
				{
				for(int i=0;i<departments.length();i++)
				{
					JSONObject obj =departments.getJSONObject(i);
					
					String name="NAME        :  "+obj.getString(TAG_NAME);
				   String cabin="CABIN NO.   :  "+obj.getString(TAG_CABIN);
				 String contact="CONTACT NO. :  "+obj.getString(TAG_CONTACT); 
				   String opo=obj.getString(TAG_OPO);
					 String opt=obj.getString(TAG_OPT);
				  String notice="NOTICE     : \n\n"+obj.getString(TAG_NOTICE);
			   String available="AVAILABILITY  :\n\nFaculty is "+obj.getString(TAG_AVAILABLE)+" in cabin right now .";
					
					
					int id=obj.getInt(TAG_ID);
					String openhrs="OPEN HOURS :\n\n"+"   "+opo+"\n\n   "+opt;
					HashMap<String, String>map=new HashMap<String, String>();
					
					map.put(TAG_ID, ""+id);
					map.put(TAG_NAME,name);
					map.put(TAG_CABIN,cabin);
					map.put(TAG_CONTACT,contact);
					map.put(TAG_OPEN,openhrs);
					map.put(TAG_NOTICE,notice);
					map.put(TAG_AVAILABLE,available);
					
					
					
					schools.add(map);
				}
				}
				else
				{
					//we'll do something for this later
					// for right now , we'll just let it re return to that page
					
					/* Intent i = new Intent(getApplicationContext(),
	                            departments.class);
	                    // Closing all previous activities
	                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(i);
	                    */

					Intent i = new Intent(facultydetails.this,err.class);
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
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				
				@Override
				public final void run() {
				
					ListAdapter adapter=new SimpleAdapter(facultydetails.this,schools,
							R.layout.details_details,new String[]{TAG_ID,TAG_NAME,TAG_CABIN,TAG_CONTACT,TAG_OPEN,TAG_NOTICE,TAG_AVAILABLE},
							new int[]{R.id.pid,R.id.name,R.id.cabin,R.id.contact,R.id.openhrs,R.id.notice,R.id.available});
					setListAdapter(adapter);
					
				}
			});
		}
 
	}


}
