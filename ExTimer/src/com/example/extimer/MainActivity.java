package com.example.extimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//http://examples.javacodegeeks.com/android/core/os/handler/android-timer-example/
public class MainActivity extends Activity {

	// Point!! how to update the value of the timer => handler
	//  Handler places the runnable process as a job in an execution queue 
	//  to be run after a specified amount of time.
	//  The runnable will be run on the thread to which this handler is attached.
	
	
	private Button startButton;
	private Button pauseButton;
	private TextView timerValue;
	private long startTime = 0L;
	private Handler customHandler  = new Handler();
	/** Time passed during runnable **/
	long timeInMilliseconds = 0L;	
	/** Time stored when pausing. **/
	long timeSwapBuff = 0L;
	/** Time passed. **/
	long updatedTime = 0L;
	
	private Runnable updateTimerThread = new Runnable() {		
		@Override
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			
			int secs = (int)(updatedTime/1000);
			int mins = secs/60;
			secs = secs%60;
			int milliseconds = (int)(updatedTime%1000);
			timerValue.setText("" + mins + ":"
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			
			// Post updateTimerThread again.
			//customHandler.postDelayed(this, 0);
			customHandler.post(this);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		timerValue = (TextView) findViewById(R.id.timerValue);
		
		startButton = (Button) findViewById(R.id.startButton);		
		startButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Returns milliseconds since boot, not counting time spent in deep sleep.
				startTime = SystemClock.uptimeMillis();
				// Post runnable without delay(0). This runnable will run  
				//  in the thread of handler.
				//customHandler.postDelayed(updateTimerThread, 0);				
				customHandler.post(updateTimerThread);
			}
		});
		
		pauseButton = (Button) findViewById(R.id.pauseButton);
		pauseButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				timeSwapBuff += timeInMilliseconds;
				customHandler.removeCallbacks(updateTimerThread);
				
			}
		});
		
		
	}
}
