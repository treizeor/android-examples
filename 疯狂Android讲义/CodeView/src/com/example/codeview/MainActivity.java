package com.example.codeview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        super.setContentView(layout);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(this);
        textView.setText("CodeTextView");
        layout.addView(textView);
        

    }


}
