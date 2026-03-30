import java.util.List;
public class ElevatorSystem {
    private final List<Floor> floors;
    private final CallStrategy dispatcher;
    private final List<ElevatorCar> fleet;

    public ElevatorSystem(List<Floor> floors, CallStrategy dispatcher, List<ElevatorCar> fleet) {
        this.floors = floors;
        this.dispatcher = dispatcher;
        this.fleet = fleet;
        Emergency.setCars(fleet);
    }

    ElevatorCar requestElevator(Floor floor,State direction) {
        ElevatorCar chosen =dispatcher.assignCar(floor,direction);
        chosen.addDestination(floor);
        return chosen;
    }

    List<Floor> getFloors() {
        return floors;
    }

    List<ElevatorCar> getCars() {
        return fleet;
    }

}