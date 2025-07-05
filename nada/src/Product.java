import java.time.LocalDate;

public class Product {
    String title;
    double unitPrice;
    int availableQuantity;
    boolean hasExpiryDate;
    LocalDate expiryDate;
    boolean requiresShipping;
    double weightPerUnit;

    public Product(String title, double unitPrice, int availableQuantity,
                   boolean hasExpiryDate, LocalDate expiryDate,
                   boolean requiresShipping, double weightPerUnit) {
        this.title = title;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
        this.hasExpiryDate = hasExpiryDate;
        this.expiryDate = expiryDate;
        this.requiresShipping = requiresShipping;
        this.weightPerUnit = weightPerUnit;
    }

    boolean isExpired() {
        return hasExpiryDate && expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}
