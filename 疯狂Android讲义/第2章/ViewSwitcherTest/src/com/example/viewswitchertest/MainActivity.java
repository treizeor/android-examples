package com.example.viewswitchertest;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;


/*

public class MainActivity extends Activity {

	// 定义一个常量，用于显示每屏的app数量
	public static final int NUMBER_PER_SCREEN = 12;

	// 代表app的内部类
	public static class AppItem {
		public String appName;
		public Drawable drawable;
	}

	// 保存系统所有app的List集合
	private ArrayList<AppItem> items = new ArrayList<AppItem>();
	// 记录正在显示第几屏的apps
	private int screenNo = -1;
	// 保存apps所占的总屏数
	private int screenCount;

	ViewSwitcher viewSwitcher;
	// 创建LayoutInflater对象
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = LayoutInflater.from(MainActivity.this);

		// 创建一个包含40个元素的List集合，模拟40个app
		for (int i = 0; i < 40; i++) {
			String label = "" + i;
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_launcher);
			AppItem item = new AppItem();
			item.appName = label;
			item.drawable = drawable;
			items.add(item);
		}
		screenCount = items.size() % NUMBER_PER_SCREEN == 0 ? items.size()
				/ NUMBER_PER_SCREEN : items.size() / NUMBER_PER_SCREEN + 1;

		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
		viewSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				return inflater.inflate(R.layout.slidelistview, null);
			}
		});
		// 页面加载时先显示第一屏
		next(null);
	}

	public void prev(View view) {
		if (screenNo > 0) {
			screenNo--;
			viewSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
			viewSwitcher.setInAnimation(this, android.R.anim.slide_out_right);
			((GridView) viewSwitcher.getNextView()).setAdapter(adapter);
			viewSwitcher.showPrevious();
		}
	}

	public void next(View view) {
		if (screenNo < screenCount - 1) {
			screenNo++;
			viewSwitcher.setInAnimation(this, R.anim.slide_in_right);
			viewSwitcher.setInAnimation(this, R.anim.slide_out_left);
			((GridView) viewSwitcher.getNextView()).setAdapter(adapter);
			viewSwitcher.showNext();
		}
	}

	private BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (convertView == null) {
				view = inflater.inflate(R.layout.labelicon, null);
			}
			ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
			imageView.setImageDrawable(getItem(position).drawable);
			TextView textView = (TextView)view.findViewById(R.id.textview);
			textView.setText(getItem(position).appName);

			return view;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public AppItem getItem(int position) {
			// TODO Auto-generated method stub
			return items.get(screenNo * NUMBER_PER_SCREEN + position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (screenNo == screenCount - 1
					&& items.size() % NUMBER_PER_SCREEN != 0) {
				return items.size() % NUMBER_PER_SCREEN;
			}
			return NUMBER_PER_SCREEN;
		}
	};
}

*/


//===================================================
public class MainActivity extends Activity  
{  
    // 定义一个常量，用于显示每屏显示的应用程序数  
    public static final int NUMBER_PER_SCREEN = 12;  
  
    // 代表应用程序的内部类，  
    public static class DataItem  
    {  
        // 应用程序名称  
        public String dataName;  
        // 应用程序图标  
        public Drawable drawable;  
    }  
  
    // 保存系统所有应用程序的List集合  
    private ArrayList<DataItem> items = new ArrayList<DataItem>();  
    // 记录当前正在显示第几屏的程序  
    private int screenNo = -1;  
    // 保存程序所占的总屏数  
    private int screenCount;  
    ViewSwitcher switcher;  
    // 创建LayoutInflater对象  
    LayoutInflater inflater;  
  
    @Override  
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        inflater = LayoutInflater.from(MainActivity.this);  
        // 创建一个包含40个元素的List集合，用于模拟包含40个应用程序  
        for (int i = 0; i < 40; i++)  
        {  
            String label = "" + i;  
            Drawable drawable = getResources().getDrawable(  
                    R.drawable.ic_launcher);  
            DataItem item = new DataItem();  
            item.dataName = label;  
            item.drawable = drawable;  
            items.add(item);  
        }  
        // 计算应用程序所占的总屏数。  
        // 如果应用程序的数量能整除NUMBER_PER_SCREEN，除法的结果就是总屏数。  
        // 如果不能整除，总屏数应该是除法的结果再加1。  
        screenCount = items.size() % NUMBER_PER_SCREEN == 0 ?   
                items.size()/ NUMBER_PER_SCREEN :  
                items.size() / NUMBER_PER_SCREEN    + 1;  
        switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);  
        switcher.setFactory(new ViewFactory()  
        {  
            // 实际上就是返回一个GridView组件  
            @Override  
            public View makeView()  
            {  
                // 加载R.layout.slidelistview组件，实际上就是一个GridView组件。  
                return inflater.inflate(R.layout.slidelistview, null);  
            }  
        });  
        // 页面加载时先显示第一屏。  
        next(null);  
    }  
  
    public void next(View v)  
    {  
        if (screenNo < screenCount - 1)  
        {  
            screenNo++;  
            // 为ViewSwitcher的组件显示过程设置动画  
            switcher.setInAnimation(this, R.anim.slide_in_right);  
            // 为ViewSwitcher的组件隐藏过程设置动画  
            switcher.setOutAnimation(this, R.anim.slide_out_left);  
            // 控制下一屏将要显示的GridView对应的 Adapter  
            ((GridView) switcher.getNextView()).setAdapter(adapter);  
            // 点击右边按钮，显示下一屏，也可通过手势检测实现显示下一屏.  
            switcher.showNext();   
        }  
    }  
  
    public void prev(View v)  
    {  
        if (screenNo > 0)  
        {  
            screenNo--;  
            // 为ViewSwitcher的组件显示过程设置动画  
            switcher.setInAnimation(this, android.R.anim.slide_in_left);  
            // 为ViewSwitcher的组件隐藏过程设置动画  
            switcher.setOutAnimation(this, android.R.anim.slide_out_right);  
            // 控制下一屏将要显示的GridView对应的 Adapter  
            ((GridView) switcher.getNextView()).setAdapter(adapter);  
            // 点击左边按钮，显示上一屏，也可通过手势检测实现显示上一屏.  
            switcher.showPrevious();   
        }  
    }  
  
    // 该BaseAdapter负责为每屏显示的GridView提供列表项  
    private BaseAdapter adapter = new BaseAdapter()  
    {  
        @Override  
        public int getCount()  
        {  
            // 如果已经到了最后一屏，且应用程序的数量不能整除NUMBER_PER_SCREEN  
            if (screenNo == screenCount - 1  
                    && items.size() % NUMBER_PER_SCREEN != 0)  
            {  
                // 最后一屏显示的程序数为应用程序的数量对NUMBER_PER_SCREEN求余  
                return items.size() % NUMBER_PER_SCREEN;  
            }  
            // 否则每屏显示的程序数量为NUMBER_PER_SCREEN  
            return NUMBER_PER_SCREEN;  
        }  
  
        @Override  
        public DataItem getItem(int position)  
        {  
            // 根据screenNo计算第position个列表项的数据  
            return items.get(screenNo * NUMBER_PER_SCREEN + position);  
        }  
  
        @Override  
        public long getItemId(int position)  
        {  
            return position;  
        }  
  
        @Override  
        public View getView(int position  
                , View convertView, ViewGroup parent)  
        {  
            View view = convertView;  
            if (convertView == null)  
            {  
                // 加载R.layout.labelicon布局文件  
                view = inflater.inflate(R.layout.labelicon, null);  
            }  
            // 获取R.layout.labelicon布局文件中的ImageView组件，并为之设置图标  
            ImageView imageView = (ImageView)  
                    view.findViewById(R.id.imageview);  
            imageView.setImageDrawable(getItem(position).drawable);  
            // 获取R.layout.labelicon布局文件中的TextView组件，并为之设置文本  
            TextView textView = (TextView)   
                    view.findViewById(R.id.textview);  
            textView.setText(getItem(position).dataName);  
            return view;  
        }  
    };  
}  

