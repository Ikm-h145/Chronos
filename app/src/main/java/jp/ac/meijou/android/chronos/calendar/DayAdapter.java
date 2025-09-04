package jp.ac.meijou.android.chronos.calendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import androidx.core.content.ContextCompat;

import jp.ac.meijou.android.chronos.R;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.VH> {

    public interface OnDayClickListener {
        void onDayClick(DayCell cell);
    }

    private final List<DayCell> items = new ArrayList<>();
    private final OnDayClickListener listener;
    private LocalDate selected;

    public DayAdapter(OnDayClickListener listener) {
        this.listener = listener;
    }

    public void submit(List<DayCell> days) {
        items.clear();
        items.addAll(days);
        notifyDataSetChanged();
    }

    public void setSelected(LocalDate date) {
        selected = date;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        // 幅を7等分
        int width = parent.getMeasuredWidth() / 7;
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        DayCell cell = items.get(position);
        h.txt.setText(String.valueOf(cell.date.getDayOfMonth()));

        // 当月外は薄く
        int color = ContextCompat.getColor(
                h.itemView.getContext(),
                cell.isInCurrentMonth ? R.color.cal_text : R.color.cal_muted
        );
        h.txt.setTextColor(color);


        // 今日の丸印
        if (cell.date.equals(LocalDate.now())) {
            h.txt.setBackgroundResource(R.drawable.bg_day_today);
            h.txt.setPadding(8, 4, 8, 4);
            h.txt.setTextColor(Color.WHITE);
        } else {
            h.txt.setBackground(null);
        }

        // 選択日のハイライト
        if (selected != null && selected.equals(cell.date)) {
            h.container.setBackgroundResource(R.drawable.bg_day_selected);
        } else {
            h.container.setBackground(null);
        }

        // イベント点
        h.dot.setVisibility(cell.hasEvent ? View.VISIBLE : View.GONE);

        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onDayClick(cell);
        });
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView txt;
        View dot;
        ViewGroup container;
        VH(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtDayNumber);
            dot = itemView.findViewById(R.id.dotEvent);
            container = itemView.findViewById(R.id.dayContainer);
        }
    }
}
