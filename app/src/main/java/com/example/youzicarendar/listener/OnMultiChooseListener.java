package com.example.youzicarendar.listener;

import android.view.View;

import com.example.youzicarendar.bean.DateBean;

/**
 * 多选接口
 */
public interface OnMultiChooseListener {
    /**
     * @param view
     * @param date
     * @param flag 多选时flag=true代表选中数据，flag=false代表取消选中
     */
    void onMultiChoose(View view, DateBean date, boolean flag);
}
