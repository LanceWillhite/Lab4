import java.util.*;

public class UrgencyManager {
    private static List<UrgencyObserver> observers = new ArrayList<>();

    public static void register(UrgencyObserver observer) {
        observers.add(observer);
    }

    public static void notifyObservers() {
        for (UrgencyObserver observer : observers) {
            observer.updateUrgency();
        }
    }
}