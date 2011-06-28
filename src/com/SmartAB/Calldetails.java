package com.SmartAB;

import java.util.List;
import java.util.Vector;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Calldetails extends ListActivity {
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;
	static final String[] title = new String[] {
		"*New*Apple iPad Wi-Fi (16GB)", "7 Touch Tablet -2GB Google Android",	
		"Apple iPad Wi-Fi (16GB) Rarely Used ","Apple iPad Wi-Fi (16GB) AppleCase"    	};
	static final String[] detail = new String[] {"1h 37m Shipping: $10.00","1h 39m Shipping: Free","58m 6s Shipping: $10.00","59m 30s Shipping: $10.95"	};
	private Integer[] imgid = {R.drawable.icon};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		Bundle bundle = getIntent().getExtras(); 
		String name1=bundle.getString("ans");
		System.out.println("name.."+name1);
		mInflater = (LayoutInflater) getSystemService(
				Activity.LAYOUT_INFLATER_SERVICE);
		data = new Vector<RowData>();
		for(int i=0;i<title.length;i++){
			try {
				rd = new RowData(i,title[i],detail[i]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			data.add(rd);
		}
		CustomAdapter adapter = new CustomAdapter(this, R.layout.list,R.id.title, data);
		setListAdapter(adapter);
		getListView().setTextFilterEnabled(true);

	}
	public void onListItemClick(ListView parent, View v, int position,
			long id) {        	
		Toast.makeText(getApplicationContext(), "You have selected "
				+(position+1)+"th item",  Toast.LENGTH_SHORT).show();
	}
	private class RowData {
		protected int mId;
		protected String mTitle;
		protected String mDetail;
		RowData(int id,String title,String detail){
			mId=id;
			mTitle = title;
			mDetail=detail;
		}
		@Override
		public String toString() {
			return mId+" "+mTitle+" "+mDetail;
		}
	}
	private class CustomAdapter extends ArrayAdapter<RowData> {
		public CustomAdapter(Context context, int resource,
				int textViewResourceId, List<RowData> objects) {               
			super(context, resource, textViewResourceId, objects);
		}
		public View getView(int position, View convertView, ViewGroup parent) {   
			ViewHolder holder = null;
			TextView title = null;
			TextView detail = null;
			ImageView i11=null;

			RowData rowData= getItem(position);
			if(null == convertView){
				convertView = mInflater.inflate(R.layout.list, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			title = holder.gettitle();
			title.setText(rowData.mTitle);
			detail = holder.getdetail();
			detail.setText(rowData.mDetail);                                                     
			i11=holder.getImage();
			i11.setImageResource(imgid[rowData.mId]);
			return convertView;
		}
		private class ViewHolder {
			private View mRow;
			private TextView title = null;
			private TextView detail = null;
			private ImageView i11=null; 
			public ViewHolder(View row) {
				mRow = row;
			}
			public TextView gettitle() {
				if(null == title){
					title = (TextView) mRow.findViewById(R.id.title);
				}
				return title;
			}     
			public TextView getdetail() {
				if(null == detail){
					detail = (TextView) mRow.findViewById(R.id.detail);
				}
				return detail;
			}
			public ImageView getImage() {
				if(null == i11){
					i11 = (ImageView) mRow.findViewById(R.id.img);
					i11.setOnClickListener(new View.OnClickListener() {

						public void onClick(View view) 
						{

							Toast.makeText(Calldetails.this, "Task Added Successfully",Toast.LENGTH_SHORT).show();
						}

					});

				}
				return i11;
			}
		}

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