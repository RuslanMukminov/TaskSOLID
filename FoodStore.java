import java.util.HashMap;
import java.util.Map;

public class FoodStore implements Store {
    protected HashMap<String, Integer> products = new HashMap<>(Map.of(
            "Хлеб", 56,
            "Масло", 153,
            "Колбаса", 211,
            "Пирожок", 45
    ));

    public FoodStore() {
    }

    @Override
    public HashMap<String, Integer> getAssortment() {
        return products;
    }

    @Override
    public int getSizeAssortment() {
        return products.size();
    }
}
