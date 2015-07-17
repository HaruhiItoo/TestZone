package com.example.exwidget;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

// http://blog.changyy.org/2010/12/android-widget.html
// 最小尺寸(dip) = (格子數 * 74) - 2
public class MyWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		 RemoteViews updateViews = new RemoteViews( context.getPackageName(), R.layout.main);		
		 updateViews.setTextViewText(R.id.now,  
				 new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" ).format(new Date()));		
		 appWidgetManager.updateAppWidget(appWidgetIds, updateViews);
	}

}
