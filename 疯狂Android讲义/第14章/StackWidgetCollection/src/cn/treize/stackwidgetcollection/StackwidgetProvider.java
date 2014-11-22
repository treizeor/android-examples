package cn.treize.stackwidgetcollection;

import cn.treize.stackwidgetcollection.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class StackwidgetProvider extends AppWidgetProvider {
	public static final String TOAST_ACTION = "cn.treize.desktop.TOAST_ACTION";
	public static final String EXTRA_ITEM = "cn.treize.desktop.EXTRA_ITEM";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		RemoteViews rv = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		Intent intent = new Intent(context, StackWidgetService.class);
		// 使用intent更新rv中的stack_view组件(StackView)
		rv.setRemoteAdapter(R.id.stack_view, intent);
		// 设置当StackWidgetService提供的列表项为空时，直接显示empty_view
		rv.setEmptyView(R.id.stack_view, R.id.empty_view);
		// 创建启动StackWidgetProvider组件（作为BroadcastReceiver）的Intent
		Intent toastIntent = new Intent(context, StackwidgetProvider.class);
		toastIntent.setAction(StackwidgetProvider.TOAST_ACTION);
		// 将Intent包装成PendingIntent
		PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context,
				0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 将PendingIntent于stack_view进行关联
		rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);
		// 使用AppWidgetManager通过RemoteViews更新AppWidgetProvider
		appWidgetManager.updateAppWidget(new ComponentName(context,
				StackwidgetProvider.class), rv);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	// 重写该方法，将该组件当成BroadcastReceiver使用
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(StackwidgetProvider.TOAST_ACTION)) {
			int viewIndex = intent.getIntExtra(StackwidgetProvider.EXTRA_ITEM,
					0);
			Toast.makeText(context, "点击第【" + viewIndex + "】个列表项",
					Toast.LENGTH_LONG).show();
		}
		super.onReceive(context, intent);
	}
}
