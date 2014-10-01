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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class departments extends ListActivity {
	
	private ProgressDialog pDialog;
	JSONArray departments=null;
	JSONParser jparse= new JSONParser();
	ArrayList<HashMap<String,String>> schools;
	
	private static String url="http://www.applesauce8412.in/user/1/-1";
	private static String TAG_SCHOOL="school";
	private static String TAG_MANAGER="manager";
	private static String TAG_SUCCESS="success";
	private static String TAG_MESSAGE="message";
	private static String TAG_ID="ID";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.departments);
		schools=new ArrayList<HashMap<String,String>>();
		new ValidateNow().execute();
		ListView lv = getListView();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();
                
                Intent i = new Intent(getApplicationContext(),
                        faculty.class);
				i.putExtra(TAG_ID,pid);
				startActivity(i);
				
			}
		});
	}
	
	public class ValidateNow extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
		super.onPreExecute();
		
		pDialog = new ProgressDialog(departments.this);
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
					
					String school=obj.getString(TAG_SCHOOL);
					String manager="Program Manager :  "+obj.getString(TAG_MANAGER);
					int id=obj.getInt(TAG_ID);
					
					HashMap<String, String>map=new HashMap<String, String>();
					
					map.put(TAG_ID, ""+id);
					map.put(TAG_SCHOOL,school);
					map.put(TAG_MANAGER, manager);
					
					schools.add(map);
				}
				}
				else
				{
					//we'll do something for this later
					// for right now , we'll just let it re return to that page
					
				/*	 Intent i = new Intent(getApplicationContext(),
	                            departments.class);
	                    // Closing all previous activities
	                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(i);
	                    */
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
				
					ListAdapter adapter=new SimpleAdapter(departments.this,schools,
							R.layout.school_details,new String[]{TAG_ID,TAG_SCHOOL,TAG_MANAGER},
							new int[]{R.id.pid,R.id.school,R.id.manager});
					setListAdapter(adapter);
					
				}
			});
		}
 
	}


}
