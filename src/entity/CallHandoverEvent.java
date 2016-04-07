package entity;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import library.Constant;

/**
 * Created by user on 6/4/2016.
 */
public class CallHandoverEvent extends Event {

    private double speed;

    private double duration;

    public CallHandoverEvent(double time, double speed, int station, double duration, int userId){
        super(time, station, Constant.HANDOVER_EVENT, userId);
        this.speed = speed;
        this.duration = duration;

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
