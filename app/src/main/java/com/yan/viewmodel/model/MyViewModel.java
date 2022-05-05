package com.yan.viewmodel.model;

import androidx.lifecycle.ViewModel;

/**
 * 使用ViewModel保存得数据在页面重新绘制时不丢失
 */
public class MyViewModel extends ViewModel {
    public int number;
}
