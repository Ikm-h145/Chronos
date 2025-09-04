package jp.ac.meijou.android.chronos.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.ac.meijou.android.chronos.R;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.VH> {
    private final List<ScheduleItem> data = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTime, tvCard;
        View vAccent;
        VH(@NonNull View v) {
            super(v);
            tvTime = v.findViewById(R.id.tvTime);
            tvCard = v.findViewById(R.id.tvCard);
            vAccent = v.findViewById(R.id.vAccent);
        }
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int vType) {
        View v = LayoutInflater.from(p.getContext()).inflate(R.layout.item_schedule, p, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        ScheduleItem it = data.get(pos);
        h.tvTime.setText(it.time);
        h.tvCard.setText(it.text);
        try { h.vAccent.setBackgroundColor(Color.parseColor(it.accentColor)); } catch (Exception ignore) {}

        // 背景切替
        int bg = R.drawable.bg_card_mint;
        if ("peach".equals(it.cardBg)) bg = R.drawable.bg_card_peach;
        else if ("pink".equals(it.cardBg)) bg = R.drawable.bg_card_pink;
        h.tvCard.setBackgroundResource(bg);
    }

    @Override public int getItemCount() { return data.size(); }

    public void submit(List<ScheduleItem> items) {
        data.clear();
        if (items != null) data.addAll(items);
        notifyDataSetChanged();
    }
}
