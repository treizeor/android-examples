package com.example.ch003button;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.os.Build;

public class MainActivity extends Activity {

	private TextView textView;
	private Button button;
	private ImageButton imageButton;
	private ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.display);
        button = (Button)findViewById(R.id.btn);
        imageButton = (ImageButton)findViewById(R.id.imgbtn);
        toggleButton = (ToggleButton)findViewById(R.id.toggle_btn);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.setText("单击了按钮1");
				Toast.makeText(MainActivity.this, "单击了按钮1", 1).show();
			}
		});
        
        imageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.setText("单击了按钮2");
				Toast.makeText(MainActivity.this, "单击了按钮2", 1).show();
				
			}
		});
        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				textView.setText("长按了按钮2");
				Toast.makeText(MainActivity.this, "长按按钮2", 1).show();
				return false;
			}
		});
        
        
        toggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (toggleButton.isChecked()) {
					toggleButton.setChecked(true);
				}else {
					toggleButton.setChecked(false);
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
