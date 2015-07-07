package com.example.exhandlermsg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//http://idlesun.blogspot.tw/2012/12/android-handler-and-message-tutorial.html
public class MainActivity extends Activity {

	static public enum WhatAbout { ART, LIFE, MONEY, WORLD };
	static public WhatAbout[] wa = WhatAbout.values();
	static TextView textOut;
	
	static Handler updater1 = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what < wa.length){
				String header = "[H1,TID:" + Thread.currentThread().getId() + "] ";
				switch(wa[msg.what]){
				case ART:
        			textOut.append(header + "ART is Art.\n");
        			break;
        		case LIFE:
        			textOut.append(header + "LIFE is Life.\n");
        			break;
        		case MONEY:
        			textOut.append(header + "MONEY is Money.\n");
        			break;
        		case WORLD:
        			textOut.append(header + "WORLD is World.\n");
        			break;					
				}//END switch
				if(msg.what + 1 < wa.length){
					// Send next message, and delay 2s.
					sendMessageDelayed(obtainMessage(msg.what+1,  0, 0), 2000);
				}
			}
		}
		
	};
	
	static Handler updater2 = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what < wa.length) {
    			String header = "[H2,TID:" + Thread.currentThread().getId() + "] ";
        		switch (wa[msg.what]) {
        		case ART:
        			textOut.append(header + "ART is good.\n");
        			break;
        		case LIFE:
        			textOut.append(header + "LIFE is better.\n");
        			break;
        		case MONEY:
        			textOut.append(header + "MONEY is even better.\n");
        			break;
        		case WORLD:
        			textOut.append(header + "WORLD is chaos.\n");
        			break;
        		}
        		if (msg.what+1 < wa.length) 
        			sendMessageDelayed(obtainMessage(msg.what+1, 0, 0), 2000);
    		}
		}
		
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textOut = (TextView) this.findViewById(R.id.text_out);
        textOut.append("\n\n");
        
        ((Button) findViewById(R.id.btn_start1)).setOnClickListener( new View.OnClickListener() {
			public void onClick(View view) {
		        updater1.sendMessage(updater1.obtainMessage(WhatAbout.ART.ordinal(), 0, 0));
			}
        });
        ((Button) findViewById(R.id.btn_start2)).setOnClickListener( new View.OnClickListener() {
			public void onClick(View view) {
		        updater2.sendMessage(updater2.obtainMessage(WhatAbout.ART.ordinal(), 0, 0));
			}
        });
        ((Button) findViewById(R.id.btn_add)).setOnClickListener( new View.OnClickListener() {
			public void onClick(View view) {
    			textOut.append("[H:-,TID:" + Thread.currentThread().getId() + "] -- You clicked. --\n");
			}
        });
        ((Button) findViewById(R.id.btn_exit)).setOnClickListener( new View.OnClickListener() {
			public void onClick(View view) {
    			finish();
			}
        });
        
    }
}
