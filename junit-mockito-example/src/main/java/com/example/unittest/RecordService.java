package com.example.unittest;

public class RecordService
{
   private DatabaseDAO database;
   private NetworkDAO network;
   private SensorsDAO sensors;

   public RecordService(DatabaseDAO database,NetworkDAO network,SensorsDAO sendors){
        this.database=database;
        this.network=network;
        this.sensors=sendors;
   }
    //setters and getters
 
    public boolean save(String fileName) 
    {
       boolean db= database.save(fileName);
        System.out.println("Saved in database in Main class"+db);
         if(db==true) {
             network.save(fileName);
             System.out.println("Saved in network in Main class");
             return true;
         }
         
        return false;
    }
    public boolean save2(String fileName)
    {
        boolean db= database.save2(fileName);
        System.out.println("Saved in database in Main class");
        if(db==true) {
            network.save(fileName);
            System.out.println("Saved in network in Main class");
            return true;
        }

        return false;
    }

    //good practice
    public String dataTransform(String input1,String input2){
       return Util.dataTransformation1(input1,input2);
    }
    public void readInfo(String input1,String input2){ ;
        this.sensors.send_response(dataTransform(input1,input2));
    }
    /*

    public void readInfo(String input1,String input2){ ;
       this.sensors.send_response(Util.dataTransformation1(input1,input2));
    }  */
    public void readSensors(){

    }

}