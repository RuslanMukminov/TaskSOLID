import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void printProducts(Map<String, Integer> products) {
        System.out.println("В МАГАЗИНЕ В НАЛИЧИИ");
        for (Map.Entry<String, Integer> productAndPrice : products.entrySet()) {
            System.out.println(productAndPrice.getKey() + " за " + productAndPrice.getValue() + " руб./шт.");
        }
    }

    public static void main(String[] args) {
        Store store = new FoodStore();

        printProducts(store.getAssortment());

        System.out.println("Введите два слова: название товара и количество. Или end");
        Scanner scanner = new Scanner(System.in);

        ShopCart shopCart = new Purchase(store.getSizeAssortment());

        while (true) {
            String line = scanner.nextLine();
            if ("end".equals(line)) break;
            String[] parts = line.split(" ");
            String product = parts[0];
            int count = Integer.parseInt(parts[1]);
            shopCart.addPurchase(product, count);
        }
        shopCart.printPurchase(store.getAssortment());
    }
}
