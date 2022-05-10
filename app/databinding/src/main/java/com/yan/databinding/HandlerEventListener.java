package com.yan.databinding;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class HandlerEventListener {
    private Context context;

    public HandlerEventListener(Context context) {
        this.context = context;
    }

    public void buttonOnClick(View view){
        Toast.makeText(context,"喜欢",Toast.LENGTH_LONG).show();
    }
}

