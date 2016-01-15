package com.github.lzyzsd.leakmemoryexample;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeakActivity extends AppCompatActivity {

    @Bind(R.id.iv_image)
    ImageView mImageView;
    @Bind(R.id.btn_allocate)
    Button mAllocationButton;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        ButterKnife.bind(this);
        Listener.add(this);

        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    List<Rect> rects = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        Rect rect = new Rect();
                        rects.add(rect);
                    }
                }
            }
        });
    }

    @OnClick(R.id.btn_load)
    public void onLoadClick(View view) {
        Glide.with(this)
                .load("http://i2.hunantv.com/p1/20130109/1134505932.jpg")
                .asBitmap()
                .into(mImageView);
    }

    @OnClick(R.id.btn_allocate)
    public void onAllocateClick(View view) {
        if (!mThread.isAlive()) {
            mThread.start();
        }
    }

    @OnClick(R.id.btn_stop_allocation)
    public void onStopAllcationClick(View view) {
        if (mThread.isAlive()) {
            mThread.interrupt();
        }
    }
}
