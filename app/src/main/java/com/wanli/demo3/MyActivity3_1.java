package com.wanli.demo3;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyActivity3_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity3_1);
        //获取布局管理器对象
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        //设置权重
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f);
        //获取Intent对象
        Intent intent = getIntent();
        //intent对象获取传递的Bundle信息 intent.getExtras()
        Bundle bundle = intent.getExtras();
        List<String> ratingData1 = (List<String>) bundle.get("ratingData");
        String[] ratingMSG = {"总的星星数： ", "当前星星数： ", "每次可以改变的星星数： ", "是否可以改变星星数： ", "返回星选条界面"};
        for (int len = 0; len <= ratingData1.size(); len++) {
            TextView textView = new TextView(MyActivity3_1.this);
            //设置对齐方式
            //设置最后添加一个按钮用于返回星选界面
            if (len == ratingData1.size()) {
                textView.setText(ratingMSG[len]);
                //设置对齐方式
                textView.setGravity(Gravity.CENTER);
                //设置字体颜色
                textView.setTextColor(ColorStateList.valueOf(Color.RED));
                //添加监听事件
                textView.setOnClickListener(click);
            } else {
                textView.setText(ratingMSG[len] + ratingData1.get(len));
                textView.setGravity(Gravity.CENTER_VERTICAL);
            }
            //设置文本尺寸
            textView.setTextSize(20);
            //设置权重是1
            textView.setLayoutParams(lp);
            //添加到布局管理器中
            linearLayout3.addView(textView);
        }
    }

    /**
     * 监听器对象的创建，用于单击事件的监听
     */
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            //添加Activity类
            ComponentName componentName = new ComponentName("com.wanli.demo3", "com.wanli.demo3.MyActivity3_0");
            intent.setComponent(componentName);
            //切换页面
            startActivity(intent);
        }
    };
}
