package com.wanli.mywidget;

    import android.app.PendingIntent;
    import android.appwidget.AppWidgetManager;
    import android.appwidget.AppWidgetProvider;
    import android.content.ComponentName;
    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Color;
    import android.text.TextUtils;
    import android.util.Log;
    import android.widget.RemoteViews;

/************************Brodcast:WidgetDemo********************************/
/************************制定时间：2020/3/13 0013********************************/
/**
 *功能：窗口小部件
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：
 */


/**----------------------------END：Brodcast:WidgetDemo------------------------------------**/

    public class WidgetDemo extends AppWidgetProvider {

        public static final String WIDGET_BUTTON_ACTION = "widget_button_action";

        @Override//设置接收信息后处理的动作，实质就是广播
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
/*            if(intent != null && TextUtils.equals(intent.getAction(),WIDGET_BUTTON_ACTION )){
                Log.i(WIDGET_BUTTON_ACTION, "be clicked");
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_my_widget);
                remoteViews.setTextViewText(R.id.textView, "be clicked");
                remoteViews.setTextColor(R.id.textView, Color.RED);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName componentName = new ComponentName(context, WidgetDemo.class);
                appWidgetManager.updateAppWidget(componentName, remoteViews);
            }*/
        }

/*        @Override//更新的时候
        public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            super.onUpdate(context, appWidgetManager, appWidgetIds);
            //更新布局文件
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_my_widget);
            //Intent是最后需要通过广播的方式发送出去
            Intent intent = new Intent();
            intent.setClass(context, WidgetDemo.class);
            intent.setAction(WIDGET_BUTTON_ACTION);
            //未来要执行的动作
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
            remoteViews.setOnClickPendingIntent(R.id.button,pendingIntent);
            //
            appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
        }*/
    }

