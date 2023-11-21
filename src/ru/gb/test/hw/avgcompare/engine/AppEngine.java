package ru.gb.test.hw.avgcompare.engine;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AppEngine {

  private final Supplier<List<Double>> valuesProvider;

  private final Function<List<Double>, Double> avgCalculator;

  private final Consumer<String> resultPrinter;

  public AppEngine(
          Supplier<List<Double>> valuesProvider,
          Function<List<Double>, Double> avgCalculator,
          Consumer<String> resultPrinter
  ) {
    this.valuesProvider = valuesProvider;
    this.avgCalculator = avgCalculator;
    this.resultPrinter = resultPrinter;
  }

  public void run() {
    double firstCalculated = avgCalculator.apply(valuesProvider.get());
    double secondCalculated = avgCalculator.apply(valuesProvider.get());

    if (firstCalculated < secondCalculated) {
      resultPrinter.accept("Второй список имеет большее среднее значение");
      return;
    }

    if (firstCalculated > secondCalculated) {
      resultPrinter.accept("Первый список имеет большее среднее значение");
      return;
    }

    resultPrinter.accept("Средние значения равны");
  }
}
