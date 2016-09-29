package com.dat.android.moveviewontouchdemo.Demo4;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.android.moveviewontouchdemo.Demo4.DragListView.BoardView;
import com.dat.android.moveviewontouchdemo.Demo4.DragListView.DragItemRecyclerView;
import com.dat.android.moveviewontouchdemo.R;
import java.util.ArrayList;

public class Demo4Activity extends AppCompatActivity {

    private static int sCreatedItems = 0;
    @Bind(R.id.boardView)
    protected BoardView boardView;

    private int mColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4);
        ButterKnife.bind(this);
        boardView.setSnapToColumnsWhenScrolling(false);
        boardView.setSnapToColumnWhenDragging(false);
        boardView.setSnapDragItemToTouch(false);
        boardView.setColumnWidth(300);
        boardView.setBoardListener(new BoardView.BoardListener() {
            @Override
            public void onItemDragStarted(int column, int row) {
                /*Toast.makeText(boardView.getContext(), "Start - column: " + column + " row: " + row,
                    Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onItemChangedColumn(int oldColumn, int newColumn) {
                TextView itemCount1 =
                    (TextView) boardView.getHeaderView(oldColumn).findViewById(R.id.item_count);
                itemCount1.setText(
                    Integer.toString(boardView.getAdapter(oldColumn).getItemCount()));
                TextView itemCount2 =
                    (TextView) boardView.getHeaderView(newColumn).findViewById(R.id.item_count);
                itemCount2.setText(
                    Integer.toString(boardView.getAdapter(newColumn).getItemCount()));
            }

            @Override
            public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {
                /*if (fromColumn != toColumn || fromRow != toRow) {
                    Toast.makeText(boardView.getContext(),
                        "End - column: " + toColumn + " row: " + toRow, Toast.LENGTH_SHORT).show();
                }*/
                MyDragItemAdapter adapterToColumn =
                    (MyDragItemAdapter) boardView.getAdapter(toColumn);
                MyDragItemAdapter adapterFromColumn =
                    (MyDragItemAdapter) boardView.getAdapter(fromColumn);

                DragItemRecyclerView fromRecyclerView =
                    (DragItemRecyclerView) boardView.getRecyclerView(fromColumn);
                fromRecyclerView.setDraggablePosition(adapterFromColumn.getDraggablePosition());

                DragItemRecyclerView toRecyclerView =
                    (DragItemRecyclerView) boardView.getRecyclerView(toColumn);
                toRecyclerView.setDraggablePosition(adapterToColumn.getDraggablePosition());
            }
        });
        addColumnList(5);
        addColumnList(0);
        addColumnList(0);
        initDraggablePositions();
    }

    private void initDraggablePositions() {
        for (int i = 0; i < mColumns; i++) {
            ((DragItemRecyclerView) boardView.getRecyclerView(i)).setDraggablePosition(
                ((MyDragItemAdapter) boardView.getAdapter(i)).getDraggablePosition());
        }
    }

    private void addColumnList(int addItems) {
        final ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
        for (int i = 0; i < addItems; i++) {
            long id = sCreatedItems++;
            mItemArray.add(new Pair<>(id, "Item " + id));
        }
        final MyDragItemAdapter listAdapter =
            new MyDragItemAdapter(mItemArray, R.layout.column_item, R.id.item_layout, false);
        final View header = View.inflate(this, R.layout.column_header, null);
        ((TextView) header.findViewById(R.id.text)).setText("Column " + (mColumns + 1));
        ((TextView) header.findViewById(R.id.item_count)).setText("" + addItems);

        boardView.addColumnList(listAdapter, header, false);

        mColumns++;
    }
}
