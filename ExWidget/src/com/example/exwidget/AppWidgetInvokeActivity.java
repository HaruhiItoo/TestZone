package com.example.exwidget;


import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class AppWidgetInvokeActivity extends AppWidgetProvider{

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		// Start UpdateService Service
		context.startService(new Intent(context, UpdateService.class));
		
		int appWidgetId = appWidgetIds[0];
		Intent configIntent = new Intent(context, MainActivity.class);
		PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
		remoteViews.setOnClickPendingIntent(R.id.resultTextView, configPendingIntent);
		appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		
		// 處理 AppWidget onDisabled 事件
		// 當最後一個  AppWidget 被刪除時，會執行 onDisabled
		
		// 停止 UpdateService 服務
		 context.stopService(new Intent(context, UpdateService.class));
		 android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	public static class UpdateService extends Service {

		@Override
		@Deprecated
		public void onStart(Intent intent, int startId) {
			// TODO Auto-generated method stub
			super.onStart(intent, startId);
			
			// 將 MainActivity 設定的 selectedItem 值，更新 AppWidget 內容值
			RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.widgetlayout);
			updateViews.setTextViewText(R.id.resultTextView, 
					"點選這裡選擇設定項目"+"\n"+MainActivity.selectedItem);
			ComponentName thisWidget = new ComponentName(this, AppWidgetInvokeActivity.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			manager.updateAppWidget(thisWidget, updateViews);

		}

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	
}
