package com.dat.android.moveviewontouchdemo.DraggableRecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.android.moveviewontouchdemo.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DraggableRecyclerViewDemoActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String a = i + "";
            data.add(a);
        }
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN
                | ItemTouchHelper.UP
                | ItemTouchHelper.START
                | ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
            RecyclerView.ViewHolder target) {
            Collections.swap(adapter.data, viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
            // and notify the adapter that its dataset has changed
            adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //ignore
        }
    };
}
