package jp.ac.meijou.android.chronos.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.ac.meijou.android.chronos.R;
import java.util.List;

public class ScheduleListView extends ConstraintLayout {
    private RecyclerView rv;
    private ScheduleAdapter adapter;

    public ScheduleListView(Context c, AttributeSet a) {
        super(c, a);
        LayoutInflater.from(c).inflate(R.layout.view_schedule_list, this, true);
        rv = findViewById(R.id.rvSchedule);
        rv.setLayoutManager(new LinearLayoutManager(c));
        adapter = new ScheduleAdapter();
        rv.setAdapter(adapter);
        // （任意）タイムラインをItemDecorationで1本に見せるならここで追加
        // rv.addItemDecoration(new TimelineDecoration(...));

        //余白を設定
        int spacePx = (int) (c.getResources().getDisplayMetrics().density * 16);
        rv.addItemDecoration(new SpacingDecoration(spacePx));
    }

    /** 予定データをセット（他ファイルから読み込んだ List を渡す） */
    public void submit(List<ScheduleItem> items) {
        adapter.submit(items);
    }

    // 各アイテムの下に等間隔の余白を入れるデコレーション
    static class SpacingDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        SpacingDecoration(int space) { this.space = space; }

        @Override
        public void getItemOffsets(
                android.graphics.Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view);
            // 最後以外のアイテムにだけ下余白を付与（最後にも付けたい場合は if を外す）
            if (position < (parent.getAdapter() != null ? parent.getAdapter().getItemCount() - 1 : 0)) {
                outRect.bottom = space;
            }
        }
    }

}
