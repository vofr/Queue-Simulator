package business_logic;

import java.util.List;

import model.Server;
import model.Task;

public class ShortestQueueStrategy implements Strategy {

	@Override
	public void addTask(List<Server> servers, Task task) {
		Server aux = servers.get(0);
		int minim = servers.get(0).BlockingQueueSize();
		for(Server i : servers) {
			if(i.BlockingQueueSize()<minim) {
				aux=i;
				minim=i.BlockingQueueSize();
			}
		}
		aux.addTask(task);
	}

}
