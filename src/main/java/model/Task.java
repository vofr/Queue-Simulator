package model;

public class Task {

	private int ID;
	private int arrivalT;
	private int serviceT;
	
	public Task(int ID, int arrivalT, int serviceT) {
		this.ID=ID;
		this.arrivalT=arrivalT;
		this.serviceT=serviceT;
	}

	@Override
	public String toString() {
		return "(" + ID + ", " + arrivalT + ", " + serviceT+") ";
	}

	public int getID() {
		return ID;
	}
	
	public int getArrivalT() {
		return arrivalT;
	}

	public int getServiceT() {
		return serviceT;
	}

    public int compareTo(Task task){
        return this.arrivalT - task.arrivalT;
    }

	public void setServiceT(int i) {
		this.serviceT=i;
		
	}
}
