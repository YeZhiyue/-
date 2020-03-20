package com.example.week5.activity;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.week5.R;
import com.example.week5.model.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivityShar extends AppCompatActivity implements OnClickListener {


  public static final String TAG = "849";
  //一、公用资源准备
  //1.数据库创建
  //2.获取数据库操作对象
  //3.获取容器
  //4.数据表字段字符串准备
  //5.遍历游标准备
  private DatebaseHeleper datebaseHeleper;
  private SQLiteDatabase sqLiteDatabase;
  private ContentValues contentValues;
  private final String[] COLUMN_STRING_ARRAYS = {DatebaseHeleper.STUDENT_ID, DatebaseHeleper.NAME,
      DatebaseHeleper.PASSWORD, DatebaseHeleper.EMAIL};
  private Cursor cursor;

  List<Student> stu_data_list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_week5);

    //设置按钮监听
    ((Button) findViewById(R.id.button)).setOnClickListener(this);
    //获取文本框组件
    editText = (EditText) findViewById(R.id.editText);
    textView = (TextView) findViewById(R.id.textView);
    //获取列表视图
    listView = (ListView) findViewById(R.id.listView);

    //初始化需要使用的字段
    datebaseHeleper = new DatebaseHeleper(this, "wanli.db", null, 2);
    sqLiteDatabase = datebaseHeleper.getWritableDatabase();
    contentValues = new ContentValues();

    //新建表
//    sqLiteDatabase.execSQL("create table "
//        + DatebaseHeleper.STU_TABLE + "( "
//        + DatebaseHeleper.STUDENT_ID + " text primary key,"
//        + DatebaseHeleper.NAME + " text not null,"
//        + DatebaseHeleper.PASSWORD + " text not null,"
//        + DatebaseHeleper.EMAIL + " text)");

    insert();
    Log.i(TAG, "二、插入操作---------------------------");
    query();
    Log.i(TAG, "三、删除操作---------------------------");
    delete();
    query();
    Log.i(TAG, "四、更新操作---------------------------");
    update();
    query();
    Log.i(TAG, "五、阶段结束---------------------------");

    //更新文本框数据
    textView.setText("当前查询到数据条数：" + row_count + " 条");
    //刷新列表视图
    flushListView();


  }

  EditText editText;
  TextView textView;
  ListView listView;
  int row_count;

  public void flushListView() {

    List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
//通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
    String[] adpter_from_string = {"id_adp", "name_adp", "pass_adp", "email_adp"};
    int[] adpter_to_string = {R.id.id_adp, R.id.name_adp, R.id.pass_adp, R.id.email_adp};
    for (int i = 0; i < stu_data_list.size(); i++) {
      // 实例化Map对象，每次创建一个map对象并且这里仅仅存入两个数据

      Map<String, Object> map = new HashMap<String, Object>();
      map.put(adpter_from_string[0], "学号：" + stu_data_list.get(i).getStudentID());
      map.put(adpter_from_string[1], "姓名：" + stu_data_list.get(i).getName());
      map.put(adpter_from_string[2], "密码：" + stu_data_list.get(i).getPassword());
      map.put(adpter_from_string[3], "邮箱：" + stu_data_list.get(i).getEmail());
      // 将map对象添加到List集合中
      listItems.add(map);
    }
// 创建SimpleAdapter
//参数： 数组名、res/layout下的资源文件名、cell中两个组件id、res/layout下的资源文件中两个组件的id
    SimpleAdapter adapter = new SimpleAdapter(this, listItems,
        R.layout.adapter_layout, adpter_from_string, adpter_to_string);
    listView.setAdapter(adapter);
  }

  @Override
  public void onClick(View v) {
    String sql_str = editText.getText().toString();
    try {

      sqLiteDatabase.execSQL(sql_str);
      Toast.makeText(MyActivityShar.this, "正在执行SQL语句", Toast.LENGTH_SHORT).show();

    } catch (Exception e) {

      Toast.makeText(MyActivityShar.this, "Sql语句语法错误", Toast.LENGTH_SHORT).show();
    }

    query();
    textView.setText("当前查询到数据条数：" + row_count + " 条");
    flushListView();
  }

  public void insert() {
    //循化遍历标志位
    int len;
    //字符串资源的获取
    Resources res = getResources();
    String[] stu_data_array = res.getStringArray(R.array.sql_data);
    Log.i(TAG, "一、原始数据读取---------------------------");
    for (String stu_data : stu_data_array) {
      Log.w(TAG, stu_data);
      String[] column_data_array = stu_data.split(",");
      //1.数据库insert操作
      //把键值对放入
      for (len = 0; len < COLUMN_STRING_ARRAYS.length; len++) {
        contentValues.put(COLUMN_STRING_ARRAYS[len], column_data_array[len]);
      }
      //在这里遇到一个bug，现在已经解决，是疏忽了，难以发现
      sqLiteDatabase.insert(DatebaseHeleper.STU_TABLE, null, contentValues);
    }
  }

  private void delete() {
    //Delete：
    String whereCLauseString = "name=?";
    String[] whereArgsString = {"王五"};
    sqLiteDatabase.delete(DatebaseHeleper.STU_TABLE, whereCLauseString, whereArgsString);
  }


  public void query() {
    //2.数据库query操作
    cursor = sqLiteDatabase.query(DatebaseHeleper.STU_TABLE, null, null, null, null, null, null);
    //根据游标属性来读取查询数据
//    cursor.moveToFirst();
    stu_data_list = new ArrayList<Student>();
    Student stu;
    row_count = cursor.getCount();
    while (cursor.moveToNext()) {
      //获取查询到的行数，进行遍历打印
      String studentID = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STRING_ARRAYS[0]));
      String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STRING_ARRAYS[1]));
      String passWord = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STRING_ARRAYS[2]));
      String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STRING_ARRAYS[3]));
      Log.w(TAG,
          " 学号：" + studentID
              + " 姓名；" + name
              + " 密码：" + passWord
              + " 邮箱：" + email
      );
      stu = new Student(studentID, name, passWord, email);
      stu_data_list.add(stu);
    }
    //调试数据模型
    Log.w(TAG, stu_data_list.toString());

  }

  private void update() {
    //Update:
    contentValues = new ContentValues();
    //更新的内容
    contentValues.put(DatebaseHeleper.PASSWORD, "123456");
    //更新的条件判断
    String whereClauseString = DatebaseHeleper.NAME + "=?";
    String[] whereArgsString = {"李四"};
    sqLiteDatabase
        .update(DatebaseHeleper.STU_TABLE, contentValues, whereClauseString, whereArgsString);
    //删除表
//    sqLiteDatabase.execSQL("DROP TABLE stu");
  }


}
