package controller;

import entity.CallInitiationEvent;
import entity.Event;
import library.EventPriority;
import library.RandomNumberGenerator;

import java.util.PriorityQueue;

/**
 * Created by user on 6/4/2016.
 */
public class QueueController {
    private final static int TOTAL_USERS = 10000;

    private PriorityQueue<Event> queue =
            new PriorityQueue<Event>(10, new EventPriority());

    /**
     *
     */
    public QueueController(){
        float time = 0;

        double speed;   int station; double position; double duration;
        RandomNumberGenerator rng = new RandomNumberGenerator();

        for(int i = 0; i < TOTAL_USERS; i++){
            speed  = rng.speed[i];
            time += rng.interarrival[i];
            station = rng.base[i];
       duration = rng.duration[i];

           //speed = RandomNumberGenerator.rngSpeed();
           //time += RandomNumberGenerator.rngInterarrivalTime();
           //station = RandomNumberGenerator.rngBaseStation();
           position = RandomNumberGenerator.rngPosition();
           //duration = RandomNumberGenerator.rngCallDuration();
           Event event = new CallInitiationEvent(time, speed, station, position, duration,i);
            queue.add(event);

        }
    }

    public Event dequeue(){
        return queue.remove();
    }

    public void enqueue(Event e){
        queue.add(e);
    }

    public int totalEvent(){
        return queue.size();
    }

}
