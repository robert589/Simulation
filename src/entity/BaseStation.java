package entity;

import java.util.ArrayList;

/**
 * Created by user on 6/4/2016.
 */
public class BaseStation {

    private final static int totalSize = 10;

    private int stationId;

    private ArrayList<Channel> channelList = new ArrayList<Channel>();

    public BaseStation(int stationId,boolean reservedMode){

        this.stationId = stationId;

        for(int i = 0; i < totalSize; i++){
            if(reservedMode && i == (totalSize - 1)){
                channelList.add(new Channel(i, true));
            }
            else{
                channelList.add(new Channel(i, false));
            }
        }
    }


    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public boolean callReserve(int userId){
        for(Channel channel : channelList){
            if(!channel.isOccupied() && !channel.isReservedForHandover()){
                channel.setOccupied(true);
                channel.setUserId(userId);
                System.out.println("Channel is found: " + channel.getChannelId());
                return true;
            }
        }
        System.out.println("Channels empty");



        return false;
    }

    public boolean callHandover(int userId){
        //check channel for reserved whether it is filled
        for(Channel channel : channelList){
            if(!channel.isOccupied() && channel.isReservedForHandover()){
                channel.setOccupied(true);
                channel.setUserId(userId);
                System.out.println("Reserve channel is occupied at channel: " + channel.getChannelId());
                return true;
            }
        }

        for(Channel channel2 : channelList){
            if(!channel2.isOccupied()){
                channel2.setOccupied(true);
                channel2.setUserId(userId);
                System.out.println("Free channel is occupied at channel: " + channel2.getChannelId());

                return true;
            }
        }
        System.out.println("Dropped");

        return false;
    }

    public void removeReserve(int userId){
        for(Channel channel: channelList){
            if(channel.getUserId() == userId){
                channel.setOccupied(false);
                channel.setUserId(0);
                System.out.println("Remove reserve of station: " + stationId + ": userId: " + userId + "channelId:" + channel.getChannelId());

            }
        }
    }

    public int freeChannel(){
        int free = 0;
        for(Channel channel : channelList){
            if(!channel.isOccupied()){
               System.out.print(channel.getChannelId() + " ");
                free++;
            }
        }

        return free;
    }
}
