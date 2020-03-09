package com.wanli.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity2_0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2_0);
        //第一步：获取4个按钮组件对象
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        //第二步：为四个组件对象注册监听器
        button.setOnClickListener(click);
        textView.setOnClickListener(click);
        imageButton.setOnClickListener(click);
        imageView.setOnClickListener(click);
    }

    /**
     * 监听器对象的创建，用于单击事件的监听，我们已经为四个按钮注册了这个单击事件监听器。
     */
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                Toast.makeText(MyActivity2_0.this, "普通按钮被点击了！", Toast.LENGTH_SHORT).show();
            }
            if (v.getId() == R.id.imageButton) {
                Toast.makeText(MyActivity2_0.this, "图片按钮被点击了！", Toast.LENGTH_SHORT).show();
            }
            if (v.getId() == R.id.imageView) {
                Toast.makeText(MyActivity2_0.this, "图片查看器按钮被点击了！", Toast.LENGTH_SHORT).show();
            }
            if (v.getId() == R.id.textView) {
                Toast.makeText(MyActivity2_0.this, "文本框按钮被点击了！", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
