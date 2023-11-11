package hw3;

public class Utils {
    public static boolean evenOddNumber(int n) {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean numberInInterval(int n) {
        final int fromExcluded = 25;
        final int toExcluded = 100;

        if (n <= fromExcluded) {
            return false;
        }

        if (n >= toExcluded) {
            return false;
        }

        return true;
    }
}
