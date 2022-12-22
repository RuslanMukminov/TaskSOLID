# Домашнее задание по теме «Свойства хорошего кода и принцип SOLID»

### Описание задания:

Требуется понять код без подробных комментариев, найти нарушения базовых принципов и исправить.

В репозиторий находятся классы в исправленном варианте.

Также добавлены интерфейсы по принципу "Dependency Inversion Principle":
- Store: его раелизует класс FoodStore, в котором хранится ассортимент продуктов;
- ShopCart: его раелизует класс Purchase, который обрабатывает список покупок;

### Далее код, который нужно исправить, с комментариями нарушений и исправлений:

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    // хранение ассортимента товаров вынес в отдельный класс FoodStore,
    // принцип "Single Responsibility Principle" чтобы разделить функции хранения
    // и взаимодействия с клиентом
        HashMap<String, Integer> products = new HashMap<>(); 
        products.put("Хлеб", 56);	
        products.put("Масло", 153);	
        products.put("Колбаса", 211);	
        products.put("Пирожок", 45);	
        
        // печать ассортимента вынес в отдельный метод printProducts()
        // по принципу "Single Responsibility Principle"
        // для логического разделения функций внутри Main
        System.out.println("В МАГАЗИНЕ В НАЛИЧИИ");
        for (Map.Entry<String, Integer> productAndPrice : products.entrySet()) {
            System.out.println(productAndPrice.getKey() + " за " + productAndPrice.getValue() + " руб./шт.");
        }

        System.out.println("Введите два слова: название товара и количество. Или end");
        Scanner scanner = new Scanner(System.in);
        Purchase purchase = new Purchase();
        while (true) {
            String line = scanner.nextLine();
            if ("end".equals(line)) break;
            String[] parts = line.split(" ");
            String product = parts[0];
            int count = Integer.parseInt(parts[1]);
            purchase.addPurchase(product, count);
        }
        
        // вывод списка покупок и итоговой суммы переместил в метод printPurchase()
        // данный функционал логичнее использовать в классе Purchase
        long sum = purchase.sum(products);
        System.out.println("ИТОГО: " + sum);
    }
}
```

```java
import java.util.Map;

public class Purchase {
    protected String title;
    protected int count;
    
    // создание массива переместил в конструктор где передается параметр size - размер массива
    // принцип "магических чисел"
    protected Purchase[] purchases = new Purchase[4];

    public Purchase(String title, int count) {
        this.title = title;
        this.count = count;
    }
  
    // в данном конструкторе передается параметр size - размер массива и создается массив purchases
    public Purchase() {
    }

    public void addPurchase(String title, int count) {
        for (int i = 0; i < purchases.length; i++) {
            if (purchases[i] == null) {
                purchases[i] = new Purchase(title, count);
                return;
            }
            if (purchases[i].title.equals(title)) {
                purchases[i].count += count;
                return;
            }
        }
    }

    // в этом методе происходит печать списка покупок и посчет итоговой суммы, которая возвращается
    // т.е. логику можно объединить в печать покупок с потраченной суммой
    // метод преобразован в printPurchase() который ничего не возвращает
    public long sum(Map<String, Integer> prices) {
        long sum = 0;
        System.out.println("КОРЗИНА:");
        for (int i = 0; i < purchases.length; i++) {
            Purchase purchase = purchases[i];
            if (purchase == null) continue;
            System.out.println("\t" + purchase.title + " " + purchase.count + " шт. в сумме " + (purchase.count * prices.get(purchase.title)) + " руб.");
            sum += purchase.count * prices.get(purchase.title);
        }
        return sum;
    }
}
```
