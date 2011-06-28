package com.Smart_task1;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;

public class Contacts extends ExpandableListActivity implements
OnChildClickListener {
	private int mGroupIdColumnIndex;

	private String mPhoneNumberProjection[] = new String[] {
			People.Phones._ID, People.NUMBER // CHANGE HERE
	};

	private ExpandableListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// Query for people
		Cursor groupCursor = managedQuery(People.CONTENT_URI,
				new String[] {People._ID, People.NAME}, null, null,
				null);

		// Cache the ID column index
		mGroupIdColumnIndex =
			groupCursor.getColumnIndexOrThrow(People._ID);

		// Set up our adapter
		mAdapter = new MyExpandableListAdapter(groupCursor,
				this,
				android.R.layout.simple_expandable_list_item_1,
				android.R.layout.simple_list_item_multiple_choice,
				new String[] {People.NAME}, // Name for group layouts
				new int[] {android.R.id.text1},
				new String[] {People.NUMBER}, // AND CHANGE HERE
				new int[] {android.R.id.text1});
		setListAdapter(mAdapter);
		
	}

	public class MyExpandableListAdapter extends
	SimpleCursorTreeAdapter {

		public MyExpandableListAdapter(Cursor cursor, Context context,
				int groupLayout,
				int childLayout, String[] groupFrom, int[] groupTo,
				String[] childrenFrom,
				int[] childrenTo) {
			super(context, cursor, groupLayout, groupFrom, groupTo,
					childLayout, childrenFrom,
					childrenTo);
		}

		@Override
		protected Cursor getChildrenCursor(Cursor groupCursor) {
			// Given the group, we return a cursor for all the children within that group

			// Return a cursor that points to this contact's phone numbers
			Uri.Builder builder = People.CONTENT_URI.buildUpon();

			ContentUris.appendId(builder,
					groupCursor.getLong(mGroupIdColumnIndex));

			builder.appendEncodedPath(People.Phones.CONTENT_DIRECTORY);
			Uri phoneNumbersUri = builder.build();

			return managedQuery(phoneNumbersUri,
					mPhoneNumberProjection, null, null, null);
		}
	}
	 
	@Override
	public boolean onChildClick(android.widget.ExpandableListView
			parent,
			View v, int groupPosition, int childPosition, long id)
			{
			System.out.println("Parent.."+parent.getParent());
		
		AlertDialog dialog = new
		AlertDialog.Builder(Contacts.this)
		.setMessage(((TextView) v).getText().toString())
		.setPositiveButton("OK", null).create();
		dialog.show();
		return true;
	} 
	
}