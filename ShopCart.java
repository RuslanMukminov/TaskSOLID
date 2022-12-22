import java.util.Map;

public interface ShopCart {
    void addPurchase(String title, int count);

    void printPurchase(Map<String, Integer> assortment);
}
