package DaysOfWeek;

public enum DayOfWeek {

    MONDAY(40), TUESDAY(32), WEDNESDAY(24), THURSDAY(16), FRIDAY(8),
    SATURDAY(0), SUNDAY(0);

    private int workingHours;

    DayOfWeek(int workingHours){
        this.workingHours = workingHours;
    }

    public static void getWorkingHours(DayOfWeek dayOfWeek) {
        if (dayOfWeek.workingHours>0) System.out.println("До конца рабочей недели осталось " + dayOfWeek.workingHours + " часов.");
        else System.out.println("Сейчас выходные!");
    }

//    public static String getWorkingHours(DaysOfWeek.DayOfWeek dayOfWeek) {
//        String s = Integer.toString(dayOfWeek.workingHours);
//        if (s.equals("0")) s = "Сейчас выходные!";
//        return s;
//    }
}
