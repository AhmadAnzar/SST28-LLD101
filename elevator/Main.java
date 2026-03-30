import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ElevatorFactory factory = new ElevatorFactory();

        ElevatorSystem system = factory.getSystem(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6)),"shortestPath",2);
        List<Floor> floors = system.getFloors();

        Floor floor5 = findFloor(floors,5);
        Floor floor2 = findFloor(floors,2);

        ElevatorCar car =system.requestElevator(floor5,State.UP);

        System.out.println("Assigned elevator" + car.getCarId());

        car.move();
        car.addDestination(floor2);
        car.move();
    }

    private static Floor findFloor(List<Floor> floors, int number) {

        for (Floor f : floors) {
            if (f.getFloorNumber() == number) {
                return f;
            }
        }

        throw new IllegalArgumentException("Floor not found");
    }
}