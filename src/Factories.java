import java.util.Queue;

public class Factories implements Runnable {

    private final String[] nameOfFactories = {"ALFA", "BRAVO", "CHARLIE", "DELTA", "ECHO"};
    private final int[] dayProduction = {500, 450, 600, 350, 720};
    private final int[] kmTransporting = {800, 400, 300, 600, 200};
    String factoriesName;
    Queue<String> warehouse;
    int maxSizeWarehouse;
    Time timeOBJ;
    int countProduction = 0;

    Factories(String factoriesName, Queue<String> warehouse, int maxSizeWarehouse, Time time){
        this.factoriesName = factoriesName;
        this.warehouse = warehouse;
        this.maxSizeWarehouse = maxSizeWarehouse;
        this.timeOBJ = time;
    }


    @Override
    public void run() {
        int counterName = 1, point = 0;
        for (int i = 0; i < nameOfFactories.length; i++) {
            if(nameOfFactories[i] == factoriesName)
                point = i;
        }
        while(true){
            try {
                Delay.delay(50);

                if(timeOBJ.time % dayProduction[point] == 0 && timeOBJ.time != 0 && Math.round(Math.floor(timeOBJ.time / dayProduction[point])) > countProduction) {
                    produce(counterName, point);
                    counterName++;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void produce(int counterName, int point) throws InterruptedException {
        synchronized (warehouse){
            while (warehouse.size() == maxSizeWarehouse){
                System.out.println("Warehouse is full");
                warehouse.wait();
            }

            System.out.println(factoriesName + " produced a plane (" + factoriesName + counterName + ")");

            while(timeOBJ.time % (dayProduction[point] + (kmTransporting[point] / 10)) != 0){
                Delay.delay(60);
            }

            warehouse.offer(String.valueOf(factoriesName + counterName));
            System.out.println(warehouse.toString() + " " + timeOBJ.time);
            countProduction++;
        }
    }
}
