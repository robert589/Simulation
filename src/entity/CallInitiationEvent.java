package entity;

import library.Constant;
import library.StatisticalCounter;

/**
 * Created by user on 6/4/2016.
 */
public class CallInitiationEvent extends Event {

    private double speed;

    private double position;

    private double duration;

    public CallInitiationEvent(double time, double speed, int station, double position, double duration, int userId){
        super(time, station, Constant.INITIATION_EVENT, userId);
        this.speed = speed;
        this.position = position;
        this.duration = duration;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
