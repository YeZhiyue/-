package com.wanli.demo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MyActivity3_0 extends AppCompatActivity {
    List<String> list;
    RatingBar ratingBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity3_0);
        //获取按钮和星选条对象
        Button button = (Button) findViewById(R.id.button);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        button.setOnClickListener(click);

    }

    /**
     * 监听器对象的创建，用于单击事件的监听
     */
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            list = new ArrayList();
//            int accessibilityLiveRegion = ratingBar3.getAccessibilityLiveRegion();
//            int accessibilityTraversalBefore = ratingBar3.getAccessibilityTraversalBefore();
//            int accessibilityTraversalAfter = ratingBar3.getAccessibilityTraversalAfter();
//            Toast.makeText(MyActivity3_0.this,accessibilityLiveRegion+""+accessibilityTraversalBefore+""+accessibilityTraversalAfter,Toast.LENGTH_SHORT).show();
            //获取星选条数据
            //获取总星数
            int numStars = ratingBar3.getNumStars();
            //获取当前星星数
            float rating = ratingBar3.getRating();
            //获取修改星星步长
            float stepSize = ratingBar3.getStepSize();
            //获取是否可以修改星星数的权限
            boolean indicator = ratingBar3.isIndicator();
            Collections.addAll(list, Integer.toString(numStars), Float.toString(rating), Float.toString(stepSize), Boolean.toString(indicator));
            //设置传送目标Activity
            Intent intent = new Intent(MyActivity3_0.this, MyActivity3_1.class);
            //创建Bundle对象存数据
            Bundle bundle = new Bundle();
            System.out.println(list);
            //存入数据
            bundle.putStringArrayList("ratingData", (ArrayList<String>) list);
            //Intent对象中添加Bundle对象
            intent.putExtras(bundle);
            //转到目标Activity
            startActivity(intent);
        }
    };
}
