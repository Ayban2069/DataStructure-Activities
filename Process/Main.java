import java.util.*;

public class Process {
    int id, arrivalTime, burstTime, remainingTime, completionTime, turnaroundTime, waitingTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SchedulingAlgorithms {

    public static void fcfsScheduling(Process[] processes) {
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime)); // Sort by arrival time
        int currentTime = 0;
        
        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            currentTime = p.completionTime;
        }

        System.out.println("FCFS Scheduling:");
        System.out.println("Process ID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (Process p : processes) {
            System.out.println(p.id + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" +
                    p.completionTime + "\t\t" + p.turnaroundTime + "\t\t" + p.waitingTime);
        }
        System.out.println();
    }

    public static void sjnPreemptiveScheduling(Process[] processes) {
        int currentTime = 0;
        int completedProcesses = 0;
        int n = processes.length;
        int[] remainingBurstTime = new int[n];
        
        // Initialize remaining burst time
        for (int i = 0; i < n; i++) {
            remainingBurstTime[i] = processes[i].burstTime;
        }

        // Process completion time, turnaround time, and waiting time
        while (completedProcesses < n) {
            int minBurstTime = Integer.MAX_VALUE;
            int idx = -1;
            
            // Find the process with the shortest remaining burst time at the current time
            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= currentTime && remainingBurstTime[i] > 0 && remainingBurstTime[i] < minBurstTime) {
                    minBurstTime = remainingBurstTime[i];
                    idx = i;
                }
            }

            // Execute the process
            if (idx != -1) {
                remainingBurstTime[idx]--;
                if (remainingBurstTime[idx] == 0) {
                    completedProcesses++;
                    processes[idx].completionTime = currentTime + 1;
                    processes[idx].turnaroundTime = processes[idx].completionTime - processes[idx].arrivalTime;
                    processes[idx].waitingTime = processes[idx].turnaroundTime - processes[idx].burstTime;
                }
            }

            currentTime++;
        }

        System.out.println("SJN Preemptive Scheduling:");
        System.out.println("Process ID\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (Process p : processes) {
            System.out.println(p.id + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime + "\t\t" +
                    p.completionTime + "\t\t" + p.turnaroundTime + "\t\t" + p.waitingTime);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Predefined values for processes: {ID, Arrival Time, Burst Time}
        Process[] fcfsProcesses = {
            new Process(1, 0, 8),
            new Process(2, 1, 4),
            new Process(3, 2, 9),
            new Process(4, 3, 5)
        };

        Process[] sjnProcesses = {
            new Process(1, 0, 8),
            new Process(2, 1, 4),
            new Process(3, 2, 9),
            new Process(4, 3, 5)
        };

        fcfsScheduling(fcfsProcesses);
        sjnPreemptiveScheduling(sjnProcesses);
    }
}
