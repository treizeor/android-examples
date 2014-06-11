package com.example.customview;
/*
 * 自定义布局:跟随手指移动的小球
 */

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 若不在xml布局文件中配置，则使用代码布局如下
//        LinearLayout root = (LinearLayout)findViewById(R.id.root);
//        final DrawView draw = new DrawView(this);
//        draw.setMinimumHeight(500);
//        draw.setMinimumWidth(300);
//        root.addView(draw);
    }
}
