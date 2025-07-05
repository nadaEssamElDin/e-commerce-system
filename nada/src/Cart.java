import java.util.*;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    void add(Product product, int count) {
        if (count > product.availableQuantity) {
            System.out.println("Error: Not enough stock for " + product.title);
            return;
        }
        items.add(new CartItem(product, count));
    }

    boolean isEmpty() {
        return items.isEmpty();
    }

    List<CartItem> getItems() {
        return items;
    }
}
