import java.util.LinkedList;
import java.util.Queue;

public class TestAereiMilitari {
    public static void main(String[] args) {
        final String[] nameOfFactories = {"ALFA", "BRAVO", "CHARLIE", "DELTA", "ECHO"};
        final String[] nameConsumers = {"Marines", "US Navy", "Air Force"};

        Queue<String> warehouse = new LinkedList<>();
        int maxSizeWarehouse = 10;

        Time time = new Time(0);

        new Thread(time).start();

        for (int i = 0; i < nameOfFactories.length; i++) {
            new Thread(new Factories(nameOfFactories[i], warehouse, maxSizeWarehouse, time)).start();
        }
        for (int i = 0; i < nameConsumers.length; i++) {
            new Thread(new Consumers(nameConsumers[i], warehouse, maxSizeWarehouse, time)).start();
        }
    }
}
