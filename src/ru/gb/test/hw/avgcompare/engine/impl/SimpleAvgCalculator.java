package ru.gb.test.hw.avgcompare.engine.impl;

import java.util.List;
import java.util.function.Function;

public class SimpleAvgCalculator implements Function<List<Double>, Double> {
  @Override
  public Double apply(List<Double> items) {
    if (items.isEmpty()) {
      throw new IllegalArgumentException("Список значений пуст, нет данных для вычислений");
    }

    double sum = 0.0;
    for (Double item : items) {
      sum += item;
    }

    return sum / items.size();
  }
}