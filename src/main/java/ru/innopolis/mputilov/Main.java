package ru.innopolis.mputilov;

/**
 * Created by mputilov on 13/10/16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Simulation started, please be patient...");
        Simulation simulation = new Simulation();
        simulation.run();
        simulation.printResult(System.out);
    }
}
