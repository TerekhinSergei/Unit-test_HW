package hw1.Calc;
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





        // Проверка базового функционала с целыми числами, с использованием утверждений AssertJ:
        assertThat(Calculator.calculation(2, 6, '+')).isEqualTo(8);
        assertThat(Calculator.calculation(7, 2, '-')).isEqualTo(5);
        assertThat(Calculator.calculation(2, 5, '*')).isEqualTo(10);
        assertThat(Calculator.calculation(100, 25, '/')).isEqualTo(4);

        // Проверка ожидаемого исключения, с использованием утверждений AssertJ:
        assertThatThrownBy(() ->
                Calculator.calculation(8, 4, '_')
                ).isInstanceOf(IllegalStateException.class);

            System.out.println(Calculator.calculation(2_147_483_647, 1, '+')); // integer overflow
            System.out.println(Calculator.squareRootExtraction(169));

            System.out.println("All tests passed");
    }
}
