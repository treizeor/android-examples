package com.example.blast;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

public class MyView extends ImageView {

	AnimationDrawable anim;
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// 定义一个方法，用于控制MyView显示的位置
	public void setLocation(int left, int top) {
		this.setFrame(left, top, left+40, top+40);
	}
	
	// 重写该方法，控制播放到最后一帧时，隐藏该View
	@Override
	protected void onDraw(Canvas canvas) {
		try {
			Field field = AnimationDrawable.class.getDeclaredField("mCurFrame");
			field.setAccessible(true);
			// 获取anim动画当前帧
			int curFrame = field.getInt(anim);
			// 如果到了最后一帧,隐藏
			if (curFrame == anim.getNumberOfFrames()-1) {
				setVisibility(View.INVISIBLE);
			}
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
