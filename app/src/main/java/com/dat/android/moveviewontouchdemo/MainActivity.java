package com.dat.android.moveviewontouchdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnTouchListener {

    @Bind(R.id.root)
    FrameLayout layout;
    @Bind(R.id.textView)
    TextView textView;

    private int _xDelta;
    private int _yDelta;

    private int initX, initY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                FrameLayout.LayoutParams lParams =
                    (FrameLayout.LayoutParams) textView.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                FrameLayout.LayoutParams params =
                    (FrameLayout.LayoutParams) textView.getLayoutParams();
                params.topMargin = initY;
                params.leftMargin = initX;
                v.setLayoutParams(params);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                FrameLayout.LayoutParams layoutParams =
                    (FrameLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                textView.setLayoutParams(layoutParams);
                break;
        }
        layout.invalidate();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setOnTouchListener(this);
        FrameLayout.LayoutParams layoutParams =
            (FrameLayout.LayoutParams) textView.getLayoutParams();
        initX = layoutParams.leftMargin;
        initY = layoutParams.topMargin;
    }
}
