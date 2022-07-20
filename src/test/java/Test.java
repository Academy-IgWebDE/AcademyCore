/**
 * @author FlorianLetsPlays
 * @version 1.0
 */
public class Test {

    public static int time;

    public static void main(String[] args) {
        System.out.println(getTimeFormatted(3 * 60 * 60 * 24, () -> System.out.println("test")));
    }


    public static String getTimeFormatted(int time, StopTimer stopTimer) {
        String outPut = "";

        System.out.println(time);

        int days = time / (24 * 3600);
        time -= days * 24 * 3600;
        if (days > 0) outPut = outPut + days + "d ";

        System.out.println(days);

        int hours = time / 3600;
        time -= hours * 3600;
        if (hours > 0) outPut = outPut + hours + "h ";


        int minutes = time / 60;
        time -= minutes * 60;


        if (minutes > 0) outPut = outPut + minutes + "m ";

        outPut = outPut + time + "s";

        stopTimer.handle();

        return outPut;
    }

}
