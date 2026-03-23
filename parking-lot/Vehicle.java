public class Vehicle {
    String id;
    String type;

    public Vehicle(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{id='" + id + "',type='" + type + "'}";
    }
}