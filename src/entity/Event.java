package entity;

/**
 * Created by user on 6/4/2016.
 */
public class Event {

    protected double time;

    protected int station;

    protected int userId;

    protected  int eventType;

    public Event(double timeArrival, int station, int eventType, int userId){
        this.time = timeArrival;
        this.station = station;
        this.eventType = eventType;
        this.userId = userId;
    }

    public double getTime() {
        return time;
    }

    public void setTime(float timeArrival) {
        this.time = timeArrival;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
