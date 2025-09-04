package jp.ac.meijou.android.chronos.calendar;

import java.time.LocalDate;

public class DayCell {
    public final LocalDate date;
    public final boolean isInCurrentMonth;
    public final boolean hasEvent;

    public DayCell(LocalDate date, boolean isInCurrentMonth, boolean hasEvent) {
        this.date = date;
        this.isInCurrentMonth = isInCurrentMonth;
        this.hasEvent = hasEvent;
    }
}

