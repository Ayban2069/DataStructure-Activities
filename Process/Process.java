package Process;

public class Process {
	String name;
	int arrivalTime;
	int burstTime;
	int count;
	
	Process() {
		
	}
	
	Process (String name, int arrivalTime, int burstTime) {
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		count++;
	}
}
