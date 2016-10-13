package ru.innopolis.mputilov;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.stream;

/**
 * Created by mputilov on 13/10/16.
 */
public class Simulation {

    public static final int MAX_NUMBER_OF_ITERATIONS = 100;
    public static final int[] NS = {10_000, 100_000, 1_000_000};
    private double[] results = new double[3];
    private Random random = new Random(0);

    public void run() {
        for (int i = 0; i < NS.length; i++) {
            simulateForN(i);
        }
    }

    /**
     * Запустить симуляцию для какого-то данного N
     *
     * @param indexOfN индекс N в NS массиве
     */
    private void simulateForN(int indexOfN) {
        int[] heights = new int[MAX_NUMBER_OF_ITERATIONS];
        for (int i = 0; i < MAX_NUMBER_OF_ITERATIONS; i++) {
            heights[i] = simulateOneTree(NS[indexOfN]);
        }
        //записываем результат симуляции в соответствующее место в results
        results[indexOfN] = stream(heights).sum() / ((double) heights.length);
    }

    private int simulateOneTree(int currentN) {
        BST<Integer, Integer> bst = new BST<>();
        for (int i = 0; i < currentN; i++) {
            int randomInt = random.nextInt();
            bst.put(randomInt, randomInt);
        }
        return bst.getHeight();
    }

    public void printResult(PrintStream out) {
        out.println("Для следующих N:\n" + Arrays.toString(NS));
        out.println("Высоты деревьев составили соответственно:\n" + Arrays.toString(results));

        double[] theoreticalBound = computeTheoreticalBound();
        out.println("Формула 2.99 IgN соответственно:\n" + Arrays.toString(theoreticalBound));
        double[] spread = computeSpread(theoreticalBound);
        out.println("Разница между теоретической границей и результатами эксперимента соответственно:\n" + Arrays.toString(spread));
    }

    /**
     * Разница между теоретической границей 2.99 lg N и реальным результатом
     *
     * @param theoreticalBound
     * @return
     */
    private double[] computeSpread(double[] theoreticalBound) {
        double[] spread = new double[theoreticalBound.length];
        for (int i = 0; i < theoreticalBound.length; i++) {
            spread[i] = theoreticalBound[i] - results[i];
        }
        return spread;
    }

    /**
     * Теоретическая граница 2.99 lg N
     *
     * @return
     */
    private double[] computeTheoreticalBound() {
        return stream(NS).mapToDouble(n -> 2.99 * Math.log(n) / Math.log(2)).toArray();
    }
}
