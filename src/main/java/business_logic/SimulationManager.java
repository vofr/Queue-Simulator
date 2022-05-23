package business_logic;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import business_logic.SelectionPolicy;
import gui_pack.GUI;
import gui_pack.SimulationResults;
import model.Server;
import model.Task;

public class SimulationManager implements Runnable{

	private int timeLimit;
	private int maxProcessingTime;
	private int minProcessingTime;
	private int numberOfServers;
	private int numberOfClients;
	private int maxArrivalTime;
	private int minArrivalTime;
	
    private float avgWaitingTime;
    private float avgServiceTime;
    
	private List<Task> client = new ArrayList();
	private Scheduler scheduler;
    String textAfis;
    private String textOneTime;
    private int peakHour;
	
	
	public SimulationManager (int timeLimit,int maxProcessingTime,int minProcessingTime, int numberOfServers, int numberOfClients,int minArrivalTime,int maxArrivalTime){
		this.timeLimit=timeLimit;
		this.maxProcessingTime=maxProcessingTime;
		this.minProcessingTime=minProcessingTime;
		this.numberOfServers= numberOfServers;
		this.numberOfClients=numberOfClients;
		this.minArrivalTime=minArrivalTime;
		this.maxArrivalTime=maxArrivalTime;
	    this.scheduler=new Scheduler(this.numberOfServers,this.numberOfClients);
		generateClients();
		displayList();
	}


	
	@Override
	public String toString() {
		return "SimulationManager [timeLimit=" + timeLimit + ", maxProcessingTime=" + maxProcessingTime
				+ ", minProcessingTime=" + minProcessingTime + ", numberOfServers=" + numberOfServers
				+ ", numberOfClients=" + numberOfClients + ", maxArrivalTime=" + maxArrivalTime + ", minArrivalTime="
				+ minArrivalTime + "]";
	}



@Override
public void run() {
    textAfis = new String();
    scheduler.RunThreads();
    int actualTime = 0;
    int maxTaskPerHour = 0;
    SimulationResults print = new SimulationResults();
    while (actualTime < timeLimit && emptyQs()){
        actualTime++;
        String string = new String("\n\nCRONOMETRU: " + actualTime + "\n");
        List<Task> aux = new ArrayList<>();
        for(Task t : client){
            if(t.getArrivalT() == actualTime) {
                scheduler.dispatchTask(t);
            }
            else aux.add(t);
        }
        client = aux;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int numberOfTasks = 0;
        for(Server s : scheduler.servers){
            numberOfTasks = numberOfTasks + s.BlockingQueueSize();
        }
        if(maxTaskPerHour < numberOfTasks){
            maxTaskPerHour = numberOfTasks;
            peakHour=actualTime;
        }

        textOneTime = string + writeText();
        textAfis = textAfis + textOneTime;
        System.out.println(textOneTime);
        print.Display(textAfis);
    }
    
    System.out.println("\n\n\n\nSIMULAREA A LUAT SFARSIT!");
   DisplayFile(textAfis);
}


public String gettextOneTime() {
    return textOneTime;
}
    
  public boolean emptyQs(){
	  boolean ret=false;
        for(Server q : scheduler.servers)
            if(q.BlockingQueueSize() != 0){
                ret=true;
            }
        if(!client.isEmpty())
            ret=true;
        return ret;
    }
    
    public void DisplayFile(String aux){
        try {
            FileWriter fila = new FileWriter("SIMULARE.txt");
            aux = aux + "Ora de varf:"+peakHour+"\n"+ "Timp mediu asteptare: " + avgWaitingTime + "\ntimp mediu servire: " + avgServiceTime +"\n";
            fila.write(aux);
            fila.close();
        } catch (IOException e) {
        	System.out.println("eroare scriere fila.");
            e.printStackTrace();
        }
    }
    
	public void generateClients() {
		Random rand = new Random();
		int serviceTotal=0;
		int waitingTotal=0;
		for(int i=1;i<=numberOfClients;i++) {
			int rand_arrival = rand.nextInt(maxArrivalTime-minArrivalTime+1);
			rand_arrival+=minArrivalTime;
			int rand_processing = rand.nextInt(maxProcessingTime-minProcessingTime+1);
			rand_processing+=minProcessingTime;
			Task aux = new Task(i,rand_arrival,rand_processing);
			serviceTotal = serviceTotal + aux.getServiceT();
			waitingTotal = waitingTotal + aux.getArrivalT();
			this.client.add(aux);
		}
        client.sort(new Comparator<Task>() {
			public int compare(Task o1, Task o2) {
				if(Integer.compare(o1.getArrivalT(),o2.getArrivalT())!=0)
				    return Integer.compare(o1.getArrivalT(),o2.getArrivalT());
				else
					return Integer.compare(o1.getServiceT(),o2.getServiceT());
			}
		});
        avgWaitingTime = (float)serviceTotal / numberOfClients;
        avgServiceTime = (float)waitingTotal / numberOfClients;
	}
	public void displayList() {
		
		for(int i=0;i<client.size();i++) {
			System.out.println(client.get(i));
		}
		System.out.println();
	}
	
    public String writeText(){
        String sir = "";
        sir= sir+ "Clientii in asteptare: \n";
        
        for(Task t : client)
            sir = sir + " " + t.toString();
        sir = sir + "\n";
        
        int contor=1;
        for(Server s : scheduler.servers) {
            if(!s.toString().isEmpty())	
            	sir = sir + "Coada " + contor + ": " + s.toString() + "\n";
            else
            	sir = sir + "Coada " + contor + ":   Libera\n";
            contor++;
        }
        sir = sir + "\n\n";
        return sir;
    }
}
