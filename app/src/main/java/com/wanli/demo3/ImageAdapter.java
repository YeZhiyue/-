package com.wanli.demo3;
/************************BaseAdapter:ImageAdapter********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：构建适配器对象
 *
 *流程：
 *    1.设置图片数组
 *    2.getView方法中设置视图中需要存放的组件
 *      配置这些组件的属性
 *    3.返回适配器对象
 *    4.
 *注意：可以为这些组件添加额外的属性
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

//创建ImageAdapter
public class ImageAdapter extends BaseAdapter {
    private Context mContext;  //获取上下文
    private Integer[] $pictureArray$ = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
            R.drawable.a4,};

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return $pictureArray$.length;//图片数组的长度
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {        //判断传过来的值是否为空
            imageView = new ImageView(mContext);  //创建ImageView组件
            imageView.setLayoutParams(new GridView.LayoutParams(100, 90));   //为组件设置宽高
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);        //选择图片铺设方式

        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource($pictureArray$[position]);    //将获取图片放到ImageView组件中
        return imageView; //返回ImageView
    }
}
/**----------------------------END：Activity:ImageAdapter------------------------------------**/
