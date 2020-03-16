package com.wanli.thefourthweek;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
public class MyTabListener4_0 implements ActionBar.TabListener {
    //定义Fragment
    //定义Activity
    //定义Class
    private Fragment fragment;
    private final Activity activity;
    private final Class aClass;

    public MyTabListener4_0(Activity activity, Class aClass) {
        //添加构造函数
        this.activity = activity;
        this.aClass = aClass;
    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //判断碎片是否初始化
        //如果没有初始化，将其初始化
        if (fragment == null) {
            fragment = Fragment.instantiate(activity, aClass.getName());
            ft.add(android.R.id.content/*安卓给我们提供的*/, fragment/*我们需要放入的fragment对象*/, null);
        }
        //显示新画面
        ft.attach(fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment != null) {
            //删除旧画面
            ft.detach(fragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}