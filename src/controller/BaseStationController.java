package controller;

import entity.BaseStation;

import java.util.ArrayList;

/**
 * Created by user on 6/4/2016.
 */
public class BaseStationController {

    /**
     * The data start from 1, all station start from 1
     */
    private ArrayList<BaseStation> baseStationArrayList = new ArrayList<BaseStation>();

    public final static int TOTAL_STATION = 20;

    public BaseStationController(boolean reservedMode){

        for(int i = 1; i <= TOTAL_STATION;i++){
            baseStationArrayList.add(new BaseStation(i, reservedMode ));

        }
    }

    public boolean callReserve(int stationId, int userId){
        for(BaseStation station :baseStationArrayList){
           if(station.getStationId() == stationId){
               return station.callReserve(userId);
           }
        }
        System.out.println("All Channels are reserved in station: " + stationId);
        return false;
    }

    public boolean handoverReserve(int stationId, int userId){
        for(BaseStation station: baseStationArrayList){
            if(station.getStationId() == stationId){
                return station.callHandover(userId);
            }
        }

        return false;
    }

    public void removeReserve(int stationId, int userId){
        for(BaseStation station: baseStationArrayList){
            if(station.getStationId() == stationId){
                station.removeReserve(userId);

            }
        }
    }


    public void printSituation(){
        for(BaseStation station : baseStationArrayList){
            System.out.println("Station " + station.getStationId() +  ": " + station.freeChannel() );
        }
    }
}
