package com.example.simple3d;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLSurfaceView glView = new GLSurfaceView(this);
		MyRenderer myRenderer = new MyRenderer();
		glView.setRenderer(myRenderer);
		setContentView(glView);
	}
}