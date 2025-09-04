package jp.ac.meijou.android.chronos.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class CalendarUtil {

    public static List<DayCell> buildMonth(LocalDate baseMonth) {
        LocalDate first = baseMonth.withDayOfMonth(1);
        int firstDow = first.getDayOfWeek().getValue(); // 1=Mon … 7=Sun
        int offset = (firstDow % 7); // Sun を0にしたい
        LocalDate start = first.minusDays(offset);

        List<DayCell> list = new ArrayList<>();
        LocalDate cursor = start;
        for (int i = 0; i < 42; i++) {
            boolean inMonth = cursor.getMonth() == baseMonth.getMonth();
            boolean hasEvent = false; // ここは後で差し替え
            list.add(new DayCell(cursor, inMonth, hasEvent));
            cursor = cursor.plusDays(1);
        }
        return list;
    }

    public static String formatMonthYear(LocalDate month) {
        return month.getYear() + "年" + month.getMonthValue() + "月";
    }
}
