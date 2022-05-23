package model;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
	private BlockingQueue <Task> tasks;
	private AtomicInteger waitingPeriod;
	
    public Server(int waitingPeriod){
        this.waitingPeriod = new AtomicInteger(waitingPeriod);
        this.tasks = new LinkedBlockingQueue<>();
    }
	
	public BlockingQueue<Task> getBlockingQueue() {
		return tasks;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public void addTask(Task newTask) {
	        tasks.add(newTask);
	        waitingPeriod = new AtomicInteger(waitingPeriod.intValue() + newTask.getServiceT());
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			if(tasks.isEmpty()==false) {
				tasks.element().setServiceT(tasks.element().getServiceT() - 1);
			    waitingPeriod = new AtomicInteger(waitingPeriod.intValue()-1);   
			 if(tasks.element().getServiceT()==0) {
				 try { tasks.take();
			 }
				 catch(InterruptedException e) {
					 e.printStackTrace();
				 }
			}
		}
		}
	}
	
    @Override
    public String toString() {
        String string = new String();

        for (Task t : tasks)
            string = string + "  " + t.toString();

        return string;
    }
	public int BlockingQueueSize() {
		return tasks.size();
	}
	public Collection<Task> getTask() {
        return tasks;
	}

}
