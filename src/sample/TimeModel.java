package sample;

public class TimeModel {
    private TimeModel() {
    }

    static TimeModel inst;

    static TimeModel getIns() {
        if (inst == null) inst = new TimeModel();
        return inst;
    }

    String[] getTime() {
        String[] s = {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};
        return s;
    }

    String findRoute(String from, String to, String time) {
        return "route from " + from + "\n to " + to + " at " + time;
    }
}


