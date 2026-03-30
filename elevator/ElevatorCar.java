import java.util.*;

public class ElevatorCar {
    private final int carId;
    private final InButtons inButtons;
    private final PriorityQueue<Floor> targets;
    private Floor atFloor;
    private int weightCapacity;
    private State state;

    public ElevatorCar(int carId, InButtons inButtons, Floor initialFloor) {
        this.carId = carId;
        this.inButtons = inButtons;
        this.atFloor = initialFloor;
        this.weightCapacity = 700;
        this.state = State.IDLE;

        this.targets = new PriorityQueue<>(Comparator.comparingInt(floor -> Math.abs(floor.getFloorNumber()- atFloor.getFloorNumber())));
        this.inButtons.attachTo(this);
    }

    void setState(State nextState) {
        this.state = nextState;
    }

    int getCarId() {
        return carId;
    }

    Floor getAtFloor() {
        return atFloor;
    }

    State getState() {
        return state;
    }

    boolean isAvailable() {
        return state != State.MAINTENANCE && state != State.EMERGENCYSTOP;
    }

    void addDestination(Floor destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination cannot be null");
        }
        targets.offer(destination);

        updateState();
    }

    void move() throws Exception {
        if (state == State.MAINTENANCE) {
            throw new Exception("Lift under maintenance");
        }
        if (state == State.EMERGENCYSTOP) {
            throw new Exception("Lift in emergency stop");
        }
        if (targets.isEmpty()) {
            state = State.IDLE;
            return;
        }
        Floor nextFloor = targets.poll();

        state = getDirection(nextFloor);
        atFloor = nextFloor;

        stop();
        openDoor();
        closeDoor();
        updateState();
    }

    void stop() {
        System.out.println("Elevator"+ carId + "stopped at floor" + atFloor.getFloorNumber());
    }

    void openDoor() {
        System.out.println("Opening door for elevator "+ carId);
    }

    void closeDoor() throws Exception {
        if (DoorSensor.check()) {
            throw new Exception("Door blocked");
        }
        if (WeightSensor.check(weightCapacity)) {
            throw new Exception("Overloaded");
        }
        System.out.println("Closing door for elevator "+ carId);
    }

    private void updateState() {
        Floor nextFloor = targets.peek();
        if (nextFloor == null) {
            state = State.IDLE;
            return;
        }

        state = getDirection(nextFloor);
    }

    private State getDirection(Floor destination) {
        int current = atFloor.getFloorNumber();
        int target = destination.getFloorNumber();

        if (target > current) {
            return State.UP;
        }
        if (target < current) {
            return State.DOWN;
        }

        return State.IDLE;
    }

}