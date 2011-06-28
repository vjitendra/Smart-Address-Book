package com.SmartAB;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class Contacts extends Activity
{
	Button b1,b2,b3,b4,b5;
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.contacts);
		b1 = (Button)findViewById(R.id.baddc);
		b1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent newActivity = new Intent(Contacts.this,AddContact.class);     
				startActivity(newActivity);
			}
		});
		b2 = (Button)findViewById(R.id.beditc);
		b2.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent newActivity = new Intent(Contacts.this,EditContact.class);     
				startActivity(newActivity);
			}
		});
		b3 = (Button)findViewById(R.id.bdelc);
		b3.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent newActivity = new Intent(Contacts.this,DeleteContact.class);     
				startActivity(newActivity);
			}
		});

	}
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.msearch:Intent i = new Intent(this, Search.class);
		startActivity(i);
		break;
		case R.id.mallconct:Intent i6 = new Intent(this, AllContacts.class);
		startActivity(i6);
		break;
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		case R.id.medit: Intent i2 = new Intent(this, EditContact.class);
		startActivity(i2);
		break;
		/*case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
		break; */
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;

		}
		return true;
	}    

}
