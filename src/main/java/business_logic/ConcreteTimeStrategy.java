package business_logic;

import java.util.List;

import model.Server;
import model.Task;

public class ConcreteTimeStrategy implements Strategy {

	@Override
	public void addTask(List<Server> servers, Task task) {
		Server aux = servers.get(0);
		int minim =servers.get(0).getWaitingPeriod().intValue();
		for(Server i : servers) {
			if(i.getWaitingPeriod().intValue()<minim) {
				aux=i;
				minim=i.getWaitingPeriod().intValue();
			}
		}
		aux.addTask(task);
	}

}
