import java.util.Map;

public class ShopCartImpl implements ShopCart {
    protected Purchase[] shopCart;

    public ShopCartImpl(int size) {
        shopCart = new PurchaseImpl[size];
    }

    @Override
    public void add(Purchase purchase) {
        String title = purchase.getTitle();
        int count = purchase.getCount();
        for (int i = 0; i < shopCart.length; i++) {
            if (shopCart[i] == null) {
                shopCart[i] = purchase;
                return;
            }
            if (shopCart[i].getTitle().equals(title)) {
                shopCart[i].setCount(count);
                return;
            }
        }
    }

    @Override
    public void printAll(Map<String, Integer> assortment) {
        long sum = 0;
        System.out.println("КОРЗИНА:");
        for (Purchase purchase : shopCart) {
            if (purchase == null) continue;

            String title = purchase.getTitle();
            int count = purchase.getCount();
            System.out.println("\t" + title + " " + count + " шт. в сумме "
                    + (count * assortment.get(title)) + " руб.");
            sum += count * assortment.get(title);
        }
        System.out.println("ИТОГО: " + sum);
    }
}
