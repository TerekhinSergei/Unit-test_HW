package hw1.Shop;

import java.util.*;
import static org.assertj.core.api.Assertions.*;

public class ShopTest {

    public static void main(String[] args) {

        Shop testShop = new Shop();
        testShop.setProducts(getShuffleListProducts(getTestListProducts()));

        // проверка верного количества продуктов
        assertThat(testShop.getProducts()).hasSize(4);

        // проверка верного самого дорогого продукта
        assertThat(testShop.getMostExpensiveProduct().getTitle()).isEqualTo(getTestListProducts().get(3).getTitle());

        // проверка отсортированного по цене списка продуктов
        assertThat(testShop.getSortedListProducts().get(0).getCost()).isEqualTo(56);
        assertThat(testShop.getSortedListProducts().get(1).getCost()).isEqualTo(65);
        assertThat(testShop.getSortedListProducts().get(2).getCost()).isEqualTo(90);
        assertThat(testShop.getSortedListProducts().get(3).getCost()).isEqualTo(250);    }

    static List<Product> getTestListProducts() {

        Product product1 = new Product();
        product1.setTitle("Молоко");
        product1.setCost(90);

        Product product2 = new Product();
        product2.setTitle("Хлеб");
        product2.setCost(56);

        Product product3 = new Product();
        product3.setTitle("Cахар");
        product3.setCost(65);

        Product product4 = new Product();
        product4.setTitle("Сосиски");
        product4.setCost(250);

        List<Product> productList = new ArrayList<>();

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        return productList;
    }

    static List<Product> getShuffleListProducts(List<Product> productList) {
        Collections.shuffle(productList);
        return productList;
    }

}
