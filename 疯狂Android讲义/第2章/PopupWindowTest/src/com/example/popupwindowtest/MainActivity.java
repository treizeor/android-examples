package com.example.popupwindowtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View root = this.getLayoutInflater().inflate(R.layout.popup, null);
        final PopupWindow popup = new PopupWindow(root, 480, 600);
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 以下拉方式显示
				//popup.showAsDropDown(v);
				// 将PopupWindow显示在指定地方
				popup.showAtLocation(findViewById(R.id.button1), Gravity.CENTER, 20, 20);
			}
		});
        
        root.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 关闭PopupWindow
				popup.dismiss();
			}
		});
    }
}