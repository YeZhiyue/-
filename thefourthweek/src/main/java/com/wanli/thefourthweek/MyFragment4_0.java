package com.wanli.thefourthweek;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MyFragment4_0 extends Fragment implements OnClickListener {
    private Button mBtn;
    public interface FOneBtnClickListener {
        void onFOneBtnClick();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_fragment4_0, container, false);
        mBtn = (Button) view.findViewById(R.id.button1);
        mBtn.setOnClickListener(this);
        return view;
    }
    /**
     * 交给宿主Activity处理，如果它希望处理
     */
    @Override
    public void onClick(View v) {
        //如果宿主Activity实现了这个内部接口，那么就表示我们的宿主重写了处理这个单击事件
        //宿主重写了onFOneBtnClick表示对单击事件的处理
        if (getActivity() instanceof FOneBtnClickListener) {
            ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
        }
    }
}