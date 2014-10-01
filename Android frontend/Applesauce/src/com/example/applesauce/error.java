package com.example.applesauce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class error extends Activity {
	
	TextView tv;
	Button b;
	public static String message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.error);
		
		Intent i=getIntent();
		message=i.getStringExtra("message");
		
		tv=(TextView) findViewById(R.id.textView2);
		tv.setText(message);
		
		b=(Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(error.this,loginpage.class);
				finish();
				startActivity(i);
				
			}
		});
		
		
		
	}

}
