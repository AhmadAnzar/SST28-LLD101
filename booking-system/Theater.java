import java.util.ArrayList;
import java.util.List;

class Theater {
    int id;
    String name;

    List<Screen> screens = new ArrayList<>();
    PricingStrategy pricingStrategy;

    Theater(int id, String name, PricingStrategy pricingStrategy) {
        this.id = id;
        this.name = name;
        this.pricingStrategy = pricingStrategy;
    }

    void addScreen(Screen screen) {
        screens.add(screen);
    }
}