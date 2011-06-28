package com.SmartAB;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
public class CallLog extends Activity
{
	DataBaseHelper data;
	@Override
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


		super.onCreate(icicle);
		//setContentView(R.layout.main);
		data=new DataBaseHelper(this);
		/*Cursor c1=data.getDataStatus();
  while(c1.moveToNext())
  {
	   int no1=c1.getInt(0);
	   String d1=c1.getString(1);
	  String n1=c1.getString(2);
	 String m1=c1.getString(3);
	String s1=c1.getString(4);
	String t1=c1.getString(5);
	 System.out.println("No1: "+no1);
	 System.out.println("Date1: "+d1);
	 System.out.println("Name 1: "+n1);
	System.out.println("Mob1: "+m1);
	System.out.println("Status: "+s1);
	System.out.println("Time: "+t1);
  }*/

	}
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
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
