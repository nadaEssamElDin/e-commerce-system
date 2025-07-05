import java.util.*;

public class ShippingService {
    static void send(List<Shippable> shippingItems) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Shippable s : shippingItems) {
            System.out.println(s.getItemName());
            System.out.println((s.getItemWeight() * 1000) + "g");
            totalWeight += s.getItemWeight();
        }
        System.out.println("Total package weight " + totalWeight + "kg");
    }
}
