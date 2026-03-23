public class CapMechanism implements StartingMechanism {

    @Override
    public void start(Pen pen) {
        pen.setStarted(true);
        System.out.println("Cap opened.");
    }

    @Override
    public void close(Pen pen) {
        pen.setStarted(false);
        System.out.println("Cap closed.");
    }
}