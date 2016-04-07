package library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by user on 6/4/2016.
 */
public class RandomNumberGenerator {
    public double[] interarrival = new double[10000];
    public double[] duration = new double[10000];
    public double[] speed = new double[10000];
    public int[] base = new int[10000];

    public RandomNumberGenerator(){
        try (BufferedReader br = new BufferedReader(new FileReader("c:/users/user/documents/github/simulation/src/library/data.txt")))
        {
            String sCurrentLine;
            double previous =0;
            int i = 0;
            for(i = 0 ; i < 10000;i++) {
                sCurrentLine = br.readLine();
                sCurrentLine = sCurrentLine.trim().replaceAll("\\\\s+", " ");
              //  System.out.println (sCurrentLine + " " + i);

                String[] splitted = sCurrentLine.trim().split("\\s+");

                interarrival[Integer.parseInt(splitted[0].trim()) - 1]= Double.parseDouble(splitted[1].trim()) - previous;
                previous = Double.parseDouble(splitted[1].trim());
                base[Integer.parseInt(splitted[0].trim()) - 1] = Integer.parseInt(splitted[2].trim());
                duration[Integer.parseInt(splitted[0].trim()) - 1] = Double.parseDouble(splitted[3].trim());
                speed[Integer.parseInt(splitted[0].trim()) - 1] = Double.parseDouble(splitted[4].trim());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double rngInterarrivalTime(){
        double beta = 1/1.43
                ;
        //exponentially distributed
        return Math.log(1- Math.random())/ (-beta);

        //   return rnd;
    }

    public static double rngCallDuration(){
        double beta = 1/99.836;
        return (Math.log(1- Math.random())/ (-beta)) + 10.004
                ;

//        return rngNormal(avg, std);
    }

    public static int rngBaseStation(){
        return 1 + (int) ( ( Math.random() * 19) + 0.5);
    }

    public static double rngSpeed(){
        double avg = 120.072098;
        double std = 9.019;

        return rngNormal(avg, std);
    //    return avg + Math.random() * std;
    }

    public static double rngPosition(){
        return 0 + Math.random() * 2;
    }

    private static double rngNormal(double mean, double std){
        int N = 50;

        // u is some randomness
        double u = 0;
        for(int i = 0; i < N;i++){
            u += Math.random();
        }

        return  mean + std * (u - (N/2)) / Math.sqrt(N/12);
    }
}
