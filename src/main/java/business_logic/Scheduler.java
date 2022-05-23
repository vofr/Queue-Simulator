package business_logic;
import java.util.ArrayList;
import java.util.List;

import business_logic.SelectionPolicy;
import business_logic.ShortestQueueStrategy;
import business_logic.ConcreteTimeStrategy;
import model.Server;
import model.Task;

public class Scheduler {

	List<Server>servers;
	private int maxNoServers;
	private int maxTaskPerServer;
	private Strategy strategy;
	private List<Thread> threads;
	
	public Scheduler(int maxNoServers,int maxTaskPerServer) {
          servers = new ArrayList<>();
          strategy = new ConcreteTimeStrategy();
          threads = new ArrayList<>();
          for(int i=0;i<maxNoServers;i++) {
        	  Server srv = new Server(0);
        	  servers.add(srv);
        	  this.threads.add(new Thread(srv));
          }
          }
	
    public void changeStrategy(SelectionPolicy selectionPolicy){
        if(selectionPolicy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ShortestQueueStrategy();
        }
        if(selectionPolicy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteTimeStrategy();
        }
    }
    
    public void RunThreads(){
        for(Thread t : threads)
            t.start();
    }
    
    public void dispatchTask(Task task){
        strategy.addTask(servers, task);
    }
    public List<Server> getServers(){
        return servers;
    }
    public List<Thread> getThreads(){
    	return threads;
    }
}
