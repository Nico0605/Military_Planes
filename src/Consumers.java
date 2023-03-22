import java.util.Queue;

public class Consumers implements Runnable {
    String[] nameConsumers = {"Marines", "US Navy", "Air Force"};
    int[] dayReq = {2000, 2300, 1500};
    int[] quantityReq = {3, 5, 7};

    String factoriesName;
    Queue<String> warehouse;
    int maxSizeWarehouse;
    Time timeOBJ;

    Consumers(String factoriesName, Queue<String> warehouse, int maxSizeWarehouse, Time time){
        this.factoriesName = factoriesName;
        this.warehouse = warehouse;
        this.maxSizeWarehouse = maxSizeWarehouse;
        this.timeOBJ = time;
    }

    @Override
    public void run() {
        int point = 0;
        for (int i = 0; i < nameConsumers.length; i++) {
            if(nameConsumers[i].equals(factoriesName))
                point = i;
        }
        while(true){
            try {
                Delay.delay(50);

                if(warehouse.size() >= quantityReq[point] && timeOBJ.time % dayReq[point] == 0 && timeOBJ.time != 0) {
                    consumer(point);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void consumer(int point) throws InterruptedException {
        synchronized (warehouse){
            while (warehouse.size() == 0){
                System.out.println("Warehouse is empty");
                warehouse.wait();
            }

            for (int i = 0; i < quantityReq[point]; i++) {
                warehouse.poll();
            }

            System.out.println(factoriesName + " taken " + quantityReq[point] + " planes");
        }
    }
}
