import java.util.Date;

public class Delay {

    public static void delay(int milliSeconds){
        int time;
        Date oldDate, newDate;

        oldDate = new Date();

        while (milliSeconds > 0){
            newDate = new Date();
            milliSeconds += oldDate.getTime() - newDate.getTime();
            oldDate = new Date();
        }
    }
}
