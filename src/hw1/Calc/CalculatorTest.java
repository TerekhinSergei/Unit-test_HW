package Calc;
import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {
    public static void main(String[] args) {
        assertThat(Calculator.calculatingDiscount(100, 20)).isEqualTo(80);

        assertThat(Calculator.calculatingDiscount(100, 0)).isEqualTo(100);

        assertThat(Calculator.calculatingDiscount(120.2, 50)).isEqualTo(60.1);

        assertThatThrownBy(() -> Calculator.calculatingDiscount(-100, 20))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Сумма покупки не может быть меньше нуля");

        assertThatThrownBy(() -> Calculator.calculatingDiscount(0, 10))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Сумма покупки не может быть равна нулю");

        assertThatThrownBy(() -> Calculator.calculatingDiscount(100, -20))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Размер скидки не может быть отрицательной");

        assertThatThrownBy(() -> Calculator.calculatingDiscount(100, 110))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Сумма скидки не может быть больше или равна 100%");

        System.out.println("All tests passed");
    }
}
