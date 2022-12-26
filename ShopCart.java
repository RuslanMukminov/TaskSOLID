import java.util.Map;

public interface ShopCart {
    void add(Purchase purchase);

    void printAll(Map<String, Integer> assortment);
}
