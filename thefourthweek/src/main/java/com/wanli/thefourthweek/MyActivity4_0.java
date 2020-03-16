package com.wanli.thefourthweek;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.app.ActionBar;


public class MyActivity4_0 extends AppCompatActivity implements MyFragment4_0.FOneBtnClickListener, MyFragment4_1.FOneBtnClickListener, MyFragment4_2.FOneBtnClickListener, MyFragment4_3.FOneBtnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity4_0);
        ActionBar actionBar = getSupportActionBar(); //获取ActionBar

        //设置ActionBar为选项卡模式
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);  //隐藏标题栏
        actionBar.addTab(actionBar.newTab().setText("按钮对话"). //将标签页添加ActionBar中
                setTabListener(new MyTabListener4_0(this, MyFragment4_0.class)));
        actionBar.addTab(actionBar.newTab().setText("列表对话"). //将标签页添加ActionBar中
                setTabListener(new MyTabListener4_0(this, MyFragment4_1.class)));
        actionBar.addTab(actionBar.newTab().setText("单选对话").////将标签页添加ActionBar中
                setTabListener(new MyTabListener4_0(this, MyFragment4_2.class)));
        actionBar.addTab(actionBar.newTab().setText("多选对话").////将标签页添加ActionBar中
                setTabListener(new MyTabListener4_0(this, MyFragment4_3.class)));
    }

    /**
     * 带确定取消按钮的对话框
     */
    @Override
    public void onFOneBtnClick() {
        //创建对话框对象
        //设置对话框的图标
        //设置对话框的标题
        //设置要显示的内容
        AlertDialog alertDialog = new AlertDialog.Builder(MyActivity4_0.this).create();
        alertDialog.setIcon(R.drawable.button);
        alertDialog.setTitle("对话框1:");
        alertDialog.setMessage("显示带取消确定按钮的对话框");
        //添加取消按钮
        //添加确定按钮
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyActivity4_0.this, "您单击了否按钮", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyActivity4_0.this, "您单击了是按钮 ", Toast.LENGTH_SHORT).show();
            }
        });
        //显示对话框
        alertDialog.show();
    }

    /**
     * 带列表项的对话框
     */
    @Override
    public void onFTwoBtnClick() {
        //设置列表项
        final String[] items = getResources().getStringArray(R.array.string_array_resource);
        //创建对话框对象
        //设置对话框的图标
        //设置对话框的标题
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity4_0.this);
        builder.setIcon(R.drawable.button);                    //设置对话框的图标
        builder.setTitle("列表对话框：");            //设置对话框的标题
        //添加列表项
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyActivity4_0.this,
                        "您选择了" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();  // 创建对话框并显示
    }

    /**
     * 带单选按钮的对话框
     */
    @Override
    public void onFThreeBtnClick() {
        //创建名字字符串数组
        final String[] items = getResources().getStringArray(R.array.string_array_resource);
        // 显示带单选列表项的对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity4_0.this);
        //设置对话框的图标
        builder.setIcon(R.drawable.button);
        //设置对话框的标题
        builder.setTitle("如果让你选择，你最想做哪一个：");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //显示选择结果
                Toast.makeText(MyActivity4_0.this,
                        "您选择了" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        //添加确定按钮
        builder.setPositiveButton("确定", null);
        // 创建对话框并显示
        builder.create().show();
    }

    /**
     * 带多选按钮的对话框
     */
    //多选按钮菜单项
    boolean[] checkedItems;
    //多选按钮状态列表
    String[] items;
    @Override
    public void onFForeBtnClick() {

        Toast.makeText(MyActivity4_0.this, "ff", Toast.LENGTH_SHORT).show();

        //初始化各个选项的内容
        items = getResources().getStringArray(R.array.string_array_resource);
        //设置列表初始状态都为false
        checkedItems = new boolean[items.length];
        for (int len = 0; len < items.length; len++) {
            checkedItems[len] = false;
        }
        // 显示带单选列表项的对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity4_0.this);
        //设置对话框的图标
        builder.setIcon(R.drawable.button);
        //设置对话框标题
        builder.setTitle("可以选择一项或者多项：");
        //设置列表选项，设置列表监听
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //改变被操作列表项的状态
                checkedItems[which] = isChecked;
            }
        });
        //为对话框添加“确定”按钮,并且对输出结果的字符串进行格式化
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for (int i = 0; i < checkedItems.length; i++) {
                    //当选项被选择时
                    if (checkedItems[i]) {
                        //将选项的内容添加到result中
                        result += items[i] + "、";
                    }
                }
                //当result不为空时，通过消息提示框显示选择的结果
                if (!"".equals(result)) {
                    //去掉最后面添加的“、”号
                    result = result.substring(0, result.length() - 1);
                    Toast.makeText(MyActivity4_0.this,
                            "您选择了[ " + result + " ]", Toast.LENGTH_LONG).show();
                }
            }
        });
        // 创建对话框并显示
        builder.create().show();
    }
}

