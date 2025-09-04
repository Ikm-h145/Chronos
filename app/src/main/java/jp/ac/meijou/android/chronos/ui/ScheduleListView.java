package jp.ac.meijou.android.chronos.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
    }

    /** 予定データをセット（他ファイルから読み込んだ List を渡す） */
    public void submit(List<ScheduleItem> items) {
        adapter.submit(items);
    }
}
