package jp.ac.meijou.android.chronos.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

import jp.ac.meijou.android.chronos.R;

public class CalendarFragment extends Fragment {

    private TextView txtMonthYear;
    private RecyclerView recycler;
    private DayAdapter adapter;
    // Lint 誤検知を避けるため、フィールドでは初期化しない
    private LocalDate currentMonth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ← フラグメントのレイアウトを膨らませる（activity_calendar ではない）
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        txtMonthYear = v.findViewById(R.id.txtMonthYear);
        recycler = v.findViewById(R.id.recyclerDays);

        // LocalDate はここで初期化（desugaring 有効なら minSdk 24 でOK）
        currentMonth = LocalDate.now().withDayOfMonth(1);

        GridLayoutManager glm = new GridLayoutManager(requireContext(), 7);
        recycler.setLayoutManager(glm);

        adapter = new DayAdapter(cell -> {
            adapter.setSelected(cell.date);
            Toast.makeText(requireContext(), cell.date.toString(), Toast.LENGTH_SHORT).show();
        });
        recycler.setAdapter(adapter);

        v.findViewById(R.id.btnPrev).setOnClickListener(view -> {
            currentMonth = currentMonth.minusMonths(1);
            render();
        });

        v.findViewById(R.id.btnNext).setOnClickListener(view -> {
            currentMonth = currentMonth.plusMonths(1);
            render();
        });

        render();
    }

    private void render() {
        txtMonthYear.setText(CalendarUtil.formatMonthYear(currentMonth));
        List<DayCell> days = CalendarUtil.buildMonth(currentMonth);
        adapter.submit(days);
    }
}
