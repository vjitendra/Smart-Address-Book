package com.SmartAB;



import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
public class DisplayCallList extends Activity implements OnItemClickListener
{


	private ArrayList<String> results = new ArrayList<String>();
	String name,img;
	int callid,id1,id2;
	String ans1;
	String name1,n,n1,oname;
	DataBaseHelper data;
	Button bsearch,bback,badd,bdel;
	ListView lv1;
	EditText es;
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
     	super.onCreate(icicle);
		setContentView(R.layout.calllistdetails);
		Bundle bundle = getIntent().getExtras(); 
		name1=bundle.getString("name");
		data=new DataBaseHelper(this);
		 lv1=(ListView)findViewById(R.id.List);
		  bback=(Button)findViewById(R.id.back);
		   showtask();
	   	  displayResultList();  
       bback.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				call();

			}
		});
        badd=(Button)findViewById(R.id.add);
		badd.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				call1();

			}
		});
		 badd=(Button)findViewById(R.id.delete);
			badd.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) 
				{
					call2();

				}
			});
}
	private void displayResultList() {
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);
		
	}
	private void showtask()
	{
		Cursor c1=data.getDataCallid(name1);
		while (c1.moveToNext())
		{	
			id1=c1.getInt(0);
			System.out.println("IDs "+id1);
		}
		Cursor c=data.getDataCallList(id1);
		System.out.println("ID1 "+id1);
		while (c.moveToNext())
		{	

			n=c.getString(0);
			n1=c.getString(1);
			results.add(n+"  "+n1);
			System.out.println("FName"+n);
			System.out.println("LName"+n1);
			//oname=n+n1;
		}
		Cursor c2=data.getCallListid1(n,n1);
		while(c2.moveToNext())
		{
			id2=c2.getInt(0);
			System.out.println("ContactID "+id2);
		}
		Cursor c3=data.getimage(id2);
		while(c3.moveToNext())
		{
			img=c3.getString(0);
			System.out.println("My Path "+img);
		}
	}
	public void call()
	{
		Intent i=new Intent(DisplayCallList.this,CallList.class);
		startActivity(i);
	}
	public void call1()
	{
		Intent i1=new Intent(DisplayCallList.this,EditList.class);
		startActivity(i1);
	}
	public void call2()
	{
		Intent i2= new Intent(this,DeleteList.class);
		Bundle bun=new Bundle();
		bun.putString("name1",ans1);
		System.out.println("Name "+name1);
		i2.putExtras(bun);
		startActivity(i2);
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		setContentView(R.layout.calllistdetails1);


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




