import java.util.Date;

public class Time implements Runnable{
    int time, waitTime;
    Date oldDate, newDate;
    Time(int time){
        this.time = time;
    }

    public int incrementTime(){
        return time += 10;
    }

    @Override
    public void run() {
        while(true) {
            waitTime = 50;

            oldDate = new Date();

            while (waitTime > 0){
                newDate = new Date();
                waitTime += oldDate.getTime() - newDate.getTime();
                oldDate = new Date();
            }

            incrementTime();
        }
    }
}
