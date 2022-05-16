package com.yan.paging;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {
    // 每页返回的数量
    public int count;
    // 开始位置
    public int start;
    // 数据总数
    public int total;
    // 电影集合
    @SerializedName("subjects")
    public List<Movie> moiveList;

    @Override
    public String toString() {
        return "Moives{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", moiveList=" + moiveList +
                '}';
    }
}
