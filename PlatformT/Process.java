package PlatformT;

class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int turnAroundTime;
    int waitingTime;
    int completionTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}
