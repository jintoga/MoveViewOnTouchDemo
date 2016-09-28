package com.dat.android.moveviewontouchdemo.Demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.android.moveviewontouchdemo.R;

public class Demo3Activity extends AppCompatActivity implements View.OnTouchListener {

    @Bind(R.id.root)
    LinearLayout root;
    @Bind(R.id.layout1)
    LinearLayout layout1;
    @Bind(R.id.layout2)
    LinearLayout layout2;
    @Bind(R.id.myViewItem)
    View myViewItem;

    private int _xDelta;
    private int _yDelta;

    private int initX, initY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                LinearLayout.LayoutParams lParams =
                    (LinearLayout.LayoutParams) myViewItem.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                LinearLayout.LayoutParams params =
                    (LinearLayout.LayoutParams) myViewItem.getLayoutParams();
                params.topMargin = initY;
                params.leftMargin = initX;
                v.setLayoutParams(params);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) myViewItem.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                myViewItem.setLayoutParams(layoutParams);
                break;
        }
        root.invalidate();
        layout1.invalidate();
        layout2.invalidate();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view_demo2);
        ButterKnife.bind(this);
        myViewItem.setOnTouchListener(this);
        LinearLayout.LayoutParams layoutParams =
            (LinearLayout.LayoutParams) myViewItem.getLayoutParams();
        initX = layoutParams.leftMargin;
        initY = layoutParams.topMargin;
    }
}
