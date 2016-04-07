package entity;

import library.Constant;

/**
 * Created by user on 6/4/2016.
 */
public class CallTerminationEvent extends  Event {

    public CallTerminationEvent(double time, int station, int userId){

        super(time, station, Constant.TERMINATION_EVENT, userId);
    }





}
