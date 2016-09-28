package com.dat.android.moveviewontouchdemo.Demo4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.android.moveviewontouchdemo.R;

public class Demo4Activity extends AppCompatActivity {

    @Bind(R.id.recyclerView1)
    protected RecyclerView recyclerView1;
    @Bind(R.id.recyclerView2)
    protected RecyclerView recyclerView2;
    private DraggableItemAdapter draggableItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        draggableItemAdapter = new DraggableItemAdapter();
        recyclerView1.setAdapter(draggableItemAdapter);
        recyclerView2.setAdapter(draggableItemAdapter);
    }
}
