package com.example.exwidget;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

// http://wuteyu.blogspot.tw/2012/05/androidappwidget-activity-activity.html
// 1.  AppWidgetInvokeActivity
// 2. @drawable/widget
// 3. widgetlayout.xml
// 4. Add static members of MainActivity
// 5. class MainActivity
// 6. appwidget.xml
// 7. Manifest
public class MainActivity extends Activity {

	public static String selectedItem = "";
	
	 ListView listviewSourceFrom;
	 ListAdapter laSingleChoice;
	 static final String[] mSourceFrom = new String[] {  "選擇項目-1",
	                                                     "選擇項目-2",
	                                                     "選擇項目-3" };
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViews();
        setListviewControls();
        setListviewListener();
    }

	private void setListviewListener() {
		listviewSourceFrom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectedItem = "選擇了第 "+String.valueOf(id+1)+" 個項目";
				final Context context = MainActivity.this;
				context.startService(new Intent(context, AppWidgetInvokeActivity.UpdateService.class));
				Intent resultValue = new Intent();
	            setResult(RESULT_OK, resultValue);
	            finish();				
			}
		});
	}

	private void setListviewControls() {
		laSingleChoice = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_single_choice, mSourceFrom);
		listviewSourceFrom.setAdapter(laSingleChoice);
		listviewSourceFrom.setChoiceMode(ListView.CHOICE_MODE_SINGLE);		
	}

	private void findViews() {
		listviewSourceFrom = (ListView) findViewById(R.id.sourcefromListview);		
	}
	

}
