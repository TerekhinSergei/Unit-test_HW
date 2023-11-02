//В классе Calculator создайте метод calculateDiscount,
//который принимает сумму покупки и процент скидки и возвращает сумму с учетом скидки.
// Ваша задача - проверить этот метод с использованием библиотеки AssertJ.
// Если метод calculateDiscount получает недопустимые аргументы,
// он должен выбрасывать исключение ArithmeticException.
// Не забудьте написать тесты для проверки этого поведения.

package Calc;
public class Calculator {
    public static double calculatingDiscount(double purchaseAmount, int discountAmount) {
        if (purchaseAmount < 0) throw new ArithmeticException("Сумма покупки не может быть меньше нуля");

        if (purchaseAmount == 0) throw new ArithmeticException("Сумма покупки не может быть равна нулю");

        if (discountAmount < 0) throw new ArithmeticException("Размер скидки не может быть отрицательной");

        if (discountAmount >= 100) throw new ArithmeticException("Сумма скидки не может быть больше или равна 100%");

        else {
            return purchaseAmount - (purchaseAmount * discountAmount) / 100;
        }
    }
}
