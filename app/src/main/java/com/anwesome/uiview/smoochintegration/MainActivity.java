package com.anwesome.uiview.smoochintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.uiview.smoochlikechathead.SmoochLikeChatHead;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmoochLikeChatHead chatHead = new SmoochLikeChatHead(this);
        chatHead.setX(400);
        chatHead.setY(500);
        addContentView(chatHead,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }
}
