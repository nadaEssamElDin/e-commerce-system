import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Product(
                "Cheese", 100, 10,
                true, LocalDate.of(2025, 12, 31),
                true, 0.4
        );

        Product biscuits = new Product(
                "Biscuits", 150, 5,
                true, LocalDate.of(2025, 12, 31),
                true, 0.7
        );

        Product tv = new Product(
                "TV", 3000, 2,
                false, null,
                true, 5.0
        );

        Product scratchCard = new Product(
                "ScratchCard", 50, 20,
                false, null,
                false, 0
        );

        Customer customer = new Customer("Nada", 7000);

        Cart shoppingCart = new Cart();
        shoppingCart.add(cheese, 2);
        shoppingCart.add(biscuits, 1);
        shoppingCart.add(tv, 1);
        shoppingCart.add(scratchCard, 1);

        doCheckout(customer, shoppingCart);
    }

    static void doCheckout(Customer customer, Cart shoppingCart) {
        if (shoppingCart.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return;
        }

        double subtotal = 0;
        double shippingFeePerUnit = 10;
        double totalShippingCost = 0;

        List<Shippable> itemsToShip = new ArrayList<>();

        for (CartItem cartItem : shoppingCart.getItems()) {
            Product product = cartItem.product;

            if (product.isExpired()) {
                System.out.println("Error: " + product.title + " is expired");
                return;
            }

            if (cartItem.count > product.availableQuantity) {
                System.out.println("Error: Not enough stock for " + product.title);
                return;
            }

            subtotal += product.unitPrice * cartItem.count;

            if (product.requiresShipping) {
                totalShippingCost += shippingFeePerUnit * cartItem.count;
                itemsToShip.add(new Shippable() {
                    public String getItemName() {
                        return cartItem.count + "x " + product.title;
                    }

                    public double getItemWeight() {
                        return product.weightPerUnit * cartItem.count;
                    }
                });
            }
        }

        double totalToPay = subtotal + totalShippingCost;

        if (customer.wallet < totalToPay) {
            System.out.println("Error: Insufficient balance");
            return;
        }

        customer.wallet -= totalToPay;

        if (!itemsToShip.isEmpty()) {
            ShippingService.send(itemsToShip);
        }

        System.out.println("** Checkout receipt **");
        for (CartItem cartItem : shoppingCart.getItems()) {
            double lineTotal = cartItem.product.unitPrice * cartItem.count;
            System.out.println(cartItem.count + "x " + cartItem.product.title + "\t" + lineTotal);
        }
        System.out.println("---------------------");
        System.out.println("Subtotal\t" + subtotal);
        System.out.println("Shipping\t" + totalShippingCost);
        System.out.println("Amount\t" + totalToPay);

    }
}
