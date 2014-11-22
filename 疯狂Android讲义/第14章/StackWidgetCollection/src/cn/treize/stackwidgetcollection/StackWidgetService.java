package cn.treize.stackwidgetcollection;

import cn.treize.stackwidgetcollection.R;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService{

	// 重写该方法，该方法返回一个RemoteViewsFactory对象，该对象作用类似于Adapter
	// 负责为RemoteView中的指定组件提供多个列表项
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	class StackRemoteViewsFactory implements RemoteViewsFactory {

		// 定义一个数组来保存该组件生产的多个列表项
		private int[] items = null;
		private Context mContext;
		
		public StackRemoteViewsFactory(Context context, Intent intent) {
			// TODO Auto-generated constructor stub
			this.mContext = context;
		}
		
		@Override
		public void onCreate() {
			// 初始化items数组
			items = new int[]{
					R.drawable.ic_launcher,
					R.drawable.clock,
					R.drawable.heart,
					R.drawable.img00,
			};
			
		}

		@Override
		public void onDataSetChanged() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			items =null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.length;
		}

		@Override
		public RemoteViews getViewAt(int position) {
			// 创建RemoteViews对象 加载layout
			RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
			rv.setImageViewResource(R.id.widget_item, items[position]);
			Intent fillInIntent = new Intent();
			fillInIntent.putExtra(StackwidgetProvider.EXTRA_ITEM, position);
			// 设置当单击该RemoteViews传递fillIntent包含的数据
			rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
			// 此处让线暂停0.5秒来模拟加载该组件
			try {
				System.out.println("---加载"+position+"位置的组件---");
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return rv;
		}

		@Override
		public RemoteViews getLoadingView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
}
