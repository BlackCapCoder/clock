public class Clock {
  private long time;

  public Clock () {
    time = 0;
  }

  private static long daysInYear (long k) { return k%4==0? 366: 365; }
  private static long daysInMonth (int k) {
    long[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    return arr[k];
  }
  private static String monthName (int k) {
    String[] arr = { "Jan", "Feb", "Mar", "Apr", "May"
                   , "Jun", "Jul", "Aug", "Sep", "Oct"
                   , "Nov", "Dec" };
    return arr[k];
  }
  private static String dayName (int k) {
    String[] arr = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
    return arr[k];
  }

  void addMs   (long x) { time += x; }
  void addSec  (long x) { addMs(x*1000); }
  void addMin  (long x) { addSec(x*60); }
  void addHour (long x) { addMin(x*60); }
  void addDay  (long x) { addHour(x*24); }

  long getTotalMs   () { return time; }
  long getTotalSec  () { return getTotalMs()/1000; }
  long getTotalMin  () { return getTotalSec()/60; }
  long getTotalHour () { return getTotalMin()/60; }
  long getTotalDay  () { return getTotalHour()/24; }
  long getTotalYear () {
    long rest = getTotalDay();
    long cur  = 0;

    while (rest >= daysInYear(++cur))
      rest -= daysInYear(cur);

    return cur-1;
  }

  long getMs   () { return getTotalMs()  % 1000; }
  long getSec  () { return getTotalSec() % 60; }
  long getMin  () { return getTotalMin() % 60; }
  long getHour () { return getTotalHour() % 24; }
  long getDay  () {
    long rest = getTotalDay();
    long cnt  = 0;

    while (rest >= (daysInYear(++cnt)))
      rest -= daysInYear(cnt);

    return rest;
  }

  long getMonth () {
    long rest = getDay();
    long m    = 0;

    while (rest >= daysInMonth((int) m)) {
      rest -= daysInMonth((int) m);
      m = (m+1)%12;
    }

    return m;
  }

  long getDayInMonth () {
    long rest = getDay();
    long m    = 0;

    while (rest >= daysInMonth((int) m)) {
      rest -= daysInMonth((int) m);
      m = (m+1)%12;
    }

    return rest;
  }

  long getDayIndexInMonth () {
    return (getDay()+5) % 7;
  }

  String getTimeString () {
    String out = "";
    out += Long.toString(getHour()) + ":";
    out += Long.toString(getMin()) + ":";
    out += Long.toString(getSec());
    return out;
  }

  String getTimeStringPM () {
    String out = "";

    long hour = 1+getHour();
    long time = hour%12;
    if (time == 0) time++;
    out += String.format("%02d", time) + ":";
    out += String.format("%02d", getMin()) + ":";
    out += String.format("%02d", getSec());
    out += hour >= 12? " PM": " AM";
    return out;
  }

  String getMonthString () {
    return monthName((int) getMonth());
  }
  String getDayString () {
    return dayName((int) getDayIndexInMonth());
  }

  String getDateString () {
    return getDayString() + " "
         + getMonthString() + " "
         + String.format("%02d", getDayInMonth()) + " "
         + getTimeStringPM() + " "
         + (1970+getTotalYear())
         ;
  }
}
