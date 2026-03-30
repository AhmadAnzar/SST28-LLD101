import java.util.ArrayList;
import java.util.List;
public class ElevatorFactory {
    public ElevatorSystem getSystem(ArrayList<Integer> floorNumbers,String strategyName,int numberOfCars) {
        List<Floor> floors = new ArrayList<>();
        List<ElevatorCar> cars = new ArrayList<>();

        for (int number : floorNumbers) {
            floors.add(new Floor( number,new OutButtons()));
        }

        floors.sort((a, b) -> a.getFloorNumber() - b.getFloorNumber());

        for (int i = 1; i <= numberOfCars; i++) {
            cars.add( new ElevatorCar( i, new InButtons(floors), floors.get(0)));
        }
        CallStrategy strategy;

        if ("fcfs".equals(strategyName)) {
            strategy = new FcfsCallStrategy(cars);

        }
        else if ("shortestPath".equals(strategyName)) {
            strategy = new ShortestPathCallStrategy(cars);

        }
        else {
            throw new IllegalArgumentException("Unsupported strategy");
        }

        return new ElevatorSystem(floors, strategy, cars);
    }
}