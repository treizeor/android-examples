package com.example.ch003tabhost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("标签一").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("标签二").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("标签三").setContent(R.id.tab3));
        
        tab2 = (LinearLayout)findViewById(R.id.tab2);
        button = new Button(MainActivity.this);
        button.setText("测试");
        tab2.addView(button);
        
        
    }
    private TabHost tabHost;
    private LinearLayout tab2;
    private Button button;

}
