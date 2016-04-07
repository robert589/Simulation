package entity;

/**
 * Created by user on 6/4/2016.
 */
public class Channel {
    private int channelId;

    private boolean occupied;

    private boolean reservedForHandover ;

    private int userId;

    public Channel(int channelId, boolean reservedForHandover){
        this.channelId = channelId;
        this.reservedForHandover = reservedForHandover;
        this.occupied = false;
    }

    public int getChannelId() {

        return channelId;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isReservedForHandover() {
        return reservedForHandover;
    }

    public void setReservedForHandover(boolean reservedForHandover) {
        this.reservedForHandover = reservedForHandover;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
