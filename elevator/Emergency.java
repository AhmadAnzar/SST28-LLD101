import java.util.*;
public class Emergency {
    private static List<ElevatorCar> registeredCars = Collections.emptyList();

    static void setCars(List<ElevatorCar> cars) {
        registeredCars = cars;
    }

    static void emergency() {
        System.out.println("All elevators stopping because of emergency");

        for (ElevatorCar car : registeredCars) {
            car.stop();
            car.setState(State.EMERGENCYSTOP);
        }
    }
}