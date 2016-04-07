import controller.BaseStationController;
import controller.QueueController;
import entity.*;
import jdk.nashorn.internal.codegen.CompilerConstants;
import library.Constant;

/**
 * Created by user on 6/4/2016.
 */
public class Simulator {

    //Simulation clock
    private double simulationClock;

    //Statistical Counters

    private int numOfCalls;

    private  int numOfDroppedCalls;

    private  int numOfBlockedCalls;

    private int handovercall;

    private boolean reservedMode;
    //Event Controller
    private QueueController queueController;

    //Base station Controller
    private BaseStationController baseStationController;

    public Simulator(boolean reservedMode){
        this.reservedMode = reservedMode;

        //initialization of simulation clock
        this.simulationClock = 0;

        //initialization of statistical counters
        this.numOfBlockedCalls = 0;
        this.numOfDroppedCalls = 0;
        this.numOfCalls = 0;
        this.handovercall =0;
        //start queue contro    ller
        queueController = new QueueController();

        // start base station controller
        baseStationController = new BaseStationController(reservedMode);

    }

    public void start(){
        while(queueController.totalEvent() > 0){

            handleEvent(queueController.dequeue());
        }

        System.out.println("------------------------------------");
        System.out.println("Num of blocked calls: " + numOfBlockedCalls );
        System.out.println("Num of dropped calls: " + numOfDroppedCalls );


        System.out.println("Num of blocked calls: " + ((numOfBlockedCalls * 100) / numOfCalls) + "%");
        System.out.println("Num of dropped calls: " + ((numOfDroppedCalls * 100)/ handovercall) + "%" );
        System.out.println("handover calls: " + handovercall );

    }

    private void handleEvent(Event event){
        //advance the time
        simulationClock = event.getTime();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Simulation Clock: " + simulationClock);
        System.out.println("---------------------------------------------------------------");

        switch(event.getEventType()){

            case Constant.HANDOVER_EVENT:
                handleHandoverEvent((CallHandoverEvent) event);
                break;
            case Constant.INITIATION_EVENT:
                handleInitiationEvent((CallInitiationEvent) event);
                break;
            case Constant.TERMINATION_EVENT:
                handleTerminationEvent( (CallTerminationEvent)event);
                break;
        }
        System.out.println("Print Situation");
        baseStationController.printSituation();

    }


    private void handleInitiationEvent(CallInitiationEvent event){

        //increase statistical counter numof calls
        this.numOfCalls++;

        System.out.println("Call Initiation: time:" +  event.getTime() + ";speed:" + event.getSpeed() + ";duration:" + event.getDuration() );
        System.out.println("                   userId: " +  event.getUserId() + ";station:" + event.getStation()  );
        System.out.println("                   position: " +  event.getPosition()  );


        //check whether it will be handover or terminate in the same cell
        double distanceToNextStation = 2 - event.getPosition(); //km
        double timetoNextStation = distanceToNextStation *3600/ event.getSpeed(); //km / (km / h)

        if(baseStationController.callReserve(event.getStation(), event.getUserId())){

            //it means termination
            if((timetoNextStation ) >= event.getDuration() ){
                System.out.println("Terminate event at: " + (simulationClock + event.getDuration()));
                queueController.enqueue(new CallTerminationEvent(simulationClock + event.getDuration(), event.getStation(),event.getUserId()));
            }

            //it means handover
            else{
                int nextStation = event.getStation() + 1;
                if(nextStation <= BaseStationController.TOTAL_STATION) {

                    System.out.println("Handover it to station: " + (event.getStation() + 1) + "; at: " + (simulationClock + timetoNextStation));

                      queueController.enqueue(new CallHandoverEvent(simulationClock + timetoNextStation,
                            event.getSpeed(),nextStation,
                            event.getDuration() - timetoNextStation,
                            event.getUserId()));
                }else{
                    baseStationController.removeReserve(event.getStation() , event.getUserId());
                    queueController.enqueue(new CallTerminationEvent(simulationClock + timetoNextStation, event.getStation(),event.getUserId()));
                    System.out.println("Bye bye car");
                }
            }
        }
        else{
            System.out.println("CALL BLOCKED");
            this.numOfBlockedCalls++;
        }
    }


    private void handleTerminationEvent(CallTerminationEvent event){
        System.out.println("Call Termination: "  + event.getTime() + ": station: " + event.getStation() + "; id: " + event.getUserId());
        baseStationController.removeReserve(event.getStation(), event.getUserId());
    }

    private void handleHandoverEvent(CallHandoverEvent event){
        this.handovercall++;
        double distanceToNextStation = 2;
        double timeToNextStation = distanceToNextStation * 3600 / event.getSpeed();

//        System.out.println("Call Handover: time: "  + event.getTime());
  //      System.out.println("                   userId: " +  event.getUserId() + ";station:" + event.getStation()  );
//        System.out.println("                   speed: " +  event.getSpeed()  );

        if(baseStationController.handoverReserve(event.getStation(), event.getUserId())){
            //remove preiovus station reserver
            baseStationController.removeReserve(event.getStation() - 1, event.getUserId());
            if((timeToNextStation ) >= event.getDuration()){
                queueController.enqueue(new CallTerminationEvent(simulationClock + event.getDuration(), event.getStation(),event.getUserId()));
            }
            else{
                int nextStation = event.getStation() + 1;
                if(nextStation <= BaseStationController.TOTAL_STATION){
                    System.out.println("Call Handover to station : " + nextStation );
                    queueController.enqueue(new CallHandoverEvent(simulationClock + timeToNextStation,
                            event.getSpeed(), event.getStation() + 1,
                            event.getDuration() - timeToNextStation,
                            event.getUserId()));
                }
                else{
                    queueController.enqueue(new CallTerminationEvent(simulationClock + timeToNextStation, event.getStation(),event.getUserId()));

                    System.out.println("Bye bye car");
                }
            }
        }
        else{
            baseStationController.removeReserve(event.getStation() - 1, event.getUserId());
            System.out.println("CALL DROPPED");
            this.numOfDroppedCalls++;
        }
    }

    private void printCurrentSituation(){
        baseStationController.printSituation();
    }

}
