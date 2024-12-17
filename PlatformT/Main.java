package PlatformT;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Process> processes = new LinkedList<>();
        System.out.print("Enter how many processes: ");
        int size = sc.nextInt();

        for (int x = 1; x <= size; x++) {
            String name = "P" + x;
            System.out.print("Enter Arrival Time for " + name + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter Burst Time for " + name + ": ");
            int burstTime = sc.nextInt();
            processes.add(new Process(name, arrivalTime, burstTime));
            System.out.println();
        }

        LinkedList<Process> fcfsProcesses = new LinkedList<>();
        LinkedList<Process> sjnProcesses = new LinkedList<>();
        for (Process p : processes) {
            fcfsProcesses.add(new Process(p.name, p.arrivalTime, p.burstTime));
            sjnProcesses.add(new Process(p.name, p.arrivalTime, p.burstTime));
        }

        System.out.println("\n=== FCFS Scheduling ===");
        FCFS(fcfsProcesses);

        System.out.println("\n=== SJN (Preemptive) Scheduling ===");
        LinkedList<Process> scheduledSJN = SJN(sjnProcesses);
        calculateTimes(scheduledSJN);
        displayProcesses(scheduledSJN);
    }

    public static void FCFS(LinkedList<Process> processes) {
    	
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;

        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.completionTime = currentTime + p.burstTime;
            currentTime = p.completionTime;
        }

        calculateTimes(processes);
        displayProcesses(processes);
    }

    public static LinkedList<Process> SJN(LinkedList<Process> processes) {
        LinkedList<Process> scheduled = new LinkedList<>();
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingTime));
        int currentTime = 0;

        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            while (!processes.isEmpty() && processes.peek().arrivalTime <= currentTime) {
                Process p = processes.poll();
                p.remainingTime = p.burstTime;
                readyQueue.add(p);
            }

            if (!readyQueue.isEmpty()) {
                Process currentProcess = readyQueue.poll();

                currentProcess.remainingTime--;
                currentTime++;

                if (currentProcess.remainingTime == 0) {
                    currentProcess.completionTime = currentTime;
                    scheduled.add(currentProcess);
                } else {
                    readyQueue.add(currentProcess);
                }
            } else {
                currentTime++;
            }
        }
        return scheduled;
    }

    public static void calculateTimes(LinkedList<Process> processes) {
        for (Process p : processes) {
            p.turnAroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnAroundTime - p.burstTime;
        }
    }

    public static void displayProcesses(LinkedList<Process> processes) {
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : processes) {
            System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\n",
                    p.name, p.arrivalTime, p.burstTime, p.completionTime, p.turnAroundTime, p.waitingTime);
        }

        int totalTAT = processes.stream().mapToInt(p -> p.turnAroundTime).sum();
        int totalWT = processes.stream().mapToInt(p -> p.waitingTime).sum();
        double avgTAT = (double) totalTAT / processes.size();
        double avgWT = (double) totalWT / processes.size();

        System.out.printf("\nTotal Turnaround Time: %d\n", totalTAT);
        System.out.printf("Average Turnaround Time: %.1f\n", avgTAT);
        System.out.printf("\nTotal Waiting Time: %d\n", totalWT);
        System.out.printf("Average Waiting Time: %.1f\n", avgWT);
    }

}
