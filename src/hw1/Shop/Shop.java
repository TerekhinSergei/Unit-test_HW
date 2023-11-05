package hw1.Shop;


import java.util.Collections;
import java.util.List;

public class Shop {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    //сортирует список продуктов по стоимости
    public List<Product> getSortedListProducts() {
        Collections.sort(products);
        return products;
    }

    // возвращает самый дорогой продукт
    public Product getMostExpensiveProduct() {
        return getSortedListProducts().get(products.size() - 1);
    }
}