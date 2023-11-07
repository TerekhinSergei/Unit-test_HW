package simple_shopping_cart;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import second.model.Cart;
import second.model.Product;
import second.model.Shop;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Shop shop;
    Cart cart;
    /**
     * Создаем набор продуктов для магазина:
     *
     * @return список продуктов
     */
    public static List<Product> getStoreItems() {
        List<Product> products = new ArrayList<>();

        // Три массива Названия, Цены, Кол-во
        String[] productNames = {"bacon", "beef", "ham", "salmon", "carrot", "potato", "onion", "apple", "melon", "rice", "eggs", "yogurt"};
        Double[] productPrice = {170.00d, 250.00d, 200.00d, 150.00d, 15.00d, 30.00d, 20.00d, 59.00d, 88.00d, 100.00d, 80.00d, 55.00d};
        Integer[] stock = {10, 10, 10, 10, 10, 10, 10, 70, 13, 30, 40, 60};

        // Последовательно наполняем список продуктами
        for (int i = 0; i < productNames.length; i++) {
            products.add(new Product(i + 1, productNames[i], productPrice[i], stock[i]));
        }
        return products;
    }

    private ByteArrayOutputStream output = new ByteArrayOutputStream();


/*
            ID | Название  | Цена, р. | Кол-во в магазине, шт.
            1  | bacon     | 170.0    | 10
            2  | beef      | 250.0    | 10
            3  | ham       | 200.0    | 10
            4  | salmon    | 150.0    | 10
            5  | carrot    | 15.0     | 10
            6  | potato    | 30.0     | 10
            7  | onion     | 20.0     | 10
            8  | apple     | 59.0     | 70
            9  | melon     | 88.0     | 13
            10 | rice      | 100.0    | 30
            11 | eggs      | 80.0     | 40
            12 | yogurt    | 55.0     | 60
*/
    @BeforeEach
    void setUp() {
        shop = new Shop(getStoreItems());
        cart = new Cart(shop);
    }
    /**
     * 2.1. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь положил в корзину несколько продуктов разной стоимости
     * <br><b>Ожидаемый результат:</b>
     * Стоимость корзины посчиталась корректно
     */
    @Test
    void priceCartIsCorrectCalculated() {
        // Arrange (Подготовка)

        // Act (Выполнение)
        cart.addProductToCartByID(1); // 170
        cart.addProductToCartByID(1); // 170
        cart.addProductToCartByID(10); // 100
        // Assert (Проверка утверждения)
        assertThat(cart.getTotalPrice()).isEqualTo(440);
    }

    /**
     * 2.2. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь положил в корзину несколько продуктов разной стоимости (несколько продуктов одного вида)
     * <br><b>Ожидаемый результат:</b>
     * Стоимость корзины посчиталась корректно
     */
    @Test
    void priceCartProductsSameTypeIsCorrectCalculated() {
        // Arrange (Подготовка)

        // Act (Выполнение)
        cart.addProductToCartByID(1); // 170
        cart.addProductToCartByID(1); // 170
        cart.addProductToCartByID(2); // 250
        cart.addProductToCartByID(2); // 250

        // Assert (Проверка утверждения)
        assertThat(cart.getTotalPrice()).isEqualTo(840);
    }

    /**
     * 2.3. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь удаляет товар из корзины
     * <br><b>Ожидаемый результат:</b>
     * Вызывается метод пересчета стоимости корзины, стоимость корзины меняется
     */
    @Test
    void whenChangingCartCostRecalculationIsCalled() {
        // Arrange (Подготовка)
        // Act (Выполнение)
        cart.addProductToCartByID(1); //170
        cart.addProductToCartByID(2); //250
        cart.addProductToCartByID(3); //200
        cart.removeProductByID(1);
        // Assert (Проверка утверждения)
        assertThat(cart.getTotalPrice()).isEqualTo(170+250+200-170);
    }

    /**
     * 2.4. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь кладет в корзину продукт в некотором количестве (не весь оставшийся)
     * <br><b>Ожидаемый результат:</b>
     * Количество товара в магазине уменьшается на число продуктов в корзине пользователя
     */
    @Test
    void quantityProductsStoreChanging() {
        // Arrange (Подготовка)
        // Act (Выполнение)
        int startQuan = shop.getProductsShop().get(0).getQuantity();
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(1);
        int finQuan = shop.getProductsShop().get(0).getQuantity();
        // Assert (Проверка утверждения)
        assertThat(finQuan).isEqualTo(startQuan - 4);

    }

    /**
     * 2.5. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь забрал последние оставшиеся продукты из магазина
     * <br><b>Ожидаемый результат:</b>
     * Больше такой продукт заказать нельзя, он не появляется на полке
     */
    @Test
    void lastProductsDisappearFromStore() {
        // Arrange (Подготовка)
        // Act (Выполнение)
        for (int i = 0; i < 11; i++) {
            cart.addProductToCartByID(1);

        }
        // Assert (Проверка утверждения)
        assertTrue(shop.getProductsShop().get(0).getQuantity()>= 0);
        assertEquals(10, cart.cartItems.get(0).getQuantity());

    }

    /**
     * 2.6. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь удаляет продукт из корзины
     * <br><b>Ожидаемый результат:</b>
     * Количество продуктов этого типа на складе увеличивается на число удаленных из корзины продуктов
     */
    @Test
    void deletedProductIsReturnedToShop() {
        // Arrange (Подготовка)
        // Act (Выполнение)
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(1);
        cart.addProductToCartByID(1);
        int finQuan = shop.getProductsShop().get(0).getQuantity();
        cart.removeProductByID(1);
        int endQuan = shop.getProductsShop().get(0).getQuantity();
        // Assert (Проверка утверждения)

        assertEquals(finQuan + 1, endQuan);

    }

//    @Test (вторая реализация)
//    void deletedProductIsReturnedToShop() {
//        cart.addProductToCartByID(2);
//        int count = shop.getProductsShop().get(1).getQuantity();
//        cart.removeProductByID(2);
//        int countProduct = shop.getProductsShop().get(1).getQuantity();
//
//        assertThat(countProduct).isEqualTo(count + 1);
//    }

    /**
     * 2.7. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь вводит неверный номер продукта
     * <br><b>Ожидаемый результат:</b>
     * Исключение типа RuntimeException и сообщение Не найден продукт с id
     * *Сделать тест параметризованным
     */
    @ParameterizedTest
    @ValueSource(ints = {0,13})
    void incorrectProductSelectionCausesException(int param) {
        // Arrange (Подготовка)
        // Act (Выполнение)
        // Assert (Проверка утверждения)
        assertThatThrownBy(() -> cart.addProductToCartByID(param)).isInstanceOf(RuntimeException.class).hasMessage("Не найден продукт с id: " + param);

    }

    /**
     * 2.8. Нужно написать юнит-тест для проверки следующей <b>ситуации</b>:
     * Пользователь удаляет из корзины больше продуктов чем у него есть в корзине (удаляет продукты до того, как их добавить)
     * <br><b>Ожидаемый результат:</b> Исключение типа NoSuchFieldError и сообщение "В корзине не найден продукт с id"
     */
    @Test
    void incorrectProductRemoveCausesException() {
        // Arrange (Подготовка)
        // Act (Выполнение)
        // Assert (Проверка утверждения)
        RuntimeException except = assertThrows(RuntimeException.class,()->cart.removeProductByID(1));
        assertThat(except.getMessage()).isEqualTo("В корзине не найден продукт с id: "+1);

        assertThatThrownBy(() -> cart.removeProductByID(1)).isInstanceOf(RuntimeException.class)
                .hasMessage("В корзине не найден продукт с id: 1");
    }

    /**
     * 2.9. Нужно восстановить тест
     */
    // boolean Сломанный-Тест() {
    //          // Assert (Проверка утверждения)
    //          assertThat(cart.getTotalPrice()).isEqualTo(cart.getTotalPrice());
    //          // Act (Выполнение)
    //          cart.addProductToCartByID(2); // 250
    //          cart.addProductToCartByID(2); // 250
    //          // Arrange (Подготовка)
    //          Shop shop = new Shop(getStoreItems());
    //          Cart cart = new Cart(shop);
    //      }
    @Test
    void testSUM() {
        // Arrange (Подготовка)

        // Act (Выполнение)
        cart.addProductToCartByID(2); // 250
        cart.addProductToCartByID(2); // 250
        // Assert (Проверка утверждения)
        assertThat(cart.getTotalPrice()).isEqualTo(500);
    }
    /**
     * 2.10. Нужно изменить тест по следующим критериям:
     * <br> 1. Отображаемое имя - "Advanced test for calculating TotalPrice"
     * <br> 2. Тест повторяется 10 раз
     * <br> 3. Установлен таймаут на выполнение теста 70 Миллисекунд (unit = TimeUnit.MILLISECONDS)
     * <br> 4. После проверки работоспособности теста, его нужно выключить
     */
    @Test
    @DisplayName("Advanced test for calculating TotalPrice")
    @RepeatedTest(10)
    @Timeout(value = 70, unit = TimeUnit.MILLISECONDS)
    void priceCartIsCorrectCalculatedExt() {
        // Arrange (Подготовка)
        // Act (Выполнение)
        cart.addProductToCartByID(1);
        cart.removeProductByID(1);
        // Assert (Проверка утверждения)
        assertThat(cart.getTotalPrice()).isEqualTo(0);
    }
}