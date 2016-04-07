package library;

/**
 * Created by user on 6/4/2016.
 */
public class StatisticalCounter {

    //Simulation clock
    private static double simulationClock;

    //Statistical Counters

    private static int numOfCalls;

    private static int numOfDroppedCalls;

    private static int numOfBlockedCalls;

    private static int numOfHandover;

    public static double getSimulationClock() {
        return simulationClock;
    }

    public static void setSimulationClock(double simulationClock) {
        StatisticalCounter.simulationClock = simulationClock;
    }

    public static void incrementNumCalls(){
        numOfCalls += 1;
    }

    public static void incrementDroppedCalls(){
        numOfDroppedCalls += 1;
    }

    public static void incrementBlockedCalls(){
        numOfBlockedCalls += 1;
    }

    public static int getNumOfBlockedCalls() {
        return numOfBlockedCalls;
    }

    public static void setNumOfBlockedCalls(int numOfBlockedCalls) {
        StatisticalCounter.numOfBlockedCalls = numOfBlockedCalls;
    }

    public static int getNumOfDroppedCalls() {
        return numOfDroppedCalls;
    }

    public static void setNumOfDroppedCalls(int numOfDroppedCalls) {
        StatisticalCounter.numOfDroppedCalls = numOfDroppedCalls;
    }

    public static int getNumOfCalls() {
        return numOfCalls;
    }

    public static void setNumOfCalls(int numOfCalls) {
        StatisticalCounter.numOfCalls = numOfCalls;
    }
}
