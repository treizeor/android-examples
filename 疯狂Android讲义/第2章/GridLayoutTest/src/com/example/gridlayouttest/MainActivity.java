package com.example.gridlayouttest;
/*
 * GridLayoutの计算器界面
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity {

	private GridLayout root;
	// 定义16个按钮的文本
	String[] chars = new String[]{	
			"7", "8", "9", "÷",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			".", "0", "=", "x"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		root = (GridLayout)findViewById(R.id.root);
		for (int i = 0; i < chars.length; i++) {
			Button btn = new Button(this);
			btn.setText(chars[i]);
			btn.setTextSize(60);
			// 指定组件所在行
			GridLayout.Spec rowSpec = GridLayout.spec(i/4+2);
			// 指定组件所在列
			GridLayout.Spec columnSpec = GridLayout.spec(i%4);
			GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
			// 指定该组件占满容器
			params.setGravity(Gravity.FILL);
			root.addView(btn, params);
		}
		
	}
}