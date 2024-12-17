package Test;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CustomLinkedList set1 = new CustomLinkedList();
        CustomLinkedList set2 = new CustomLinkedList();

        System.out.print("Set the size of set 1: ");
        int size1 = check("");
        System.out.print("Set the size of set 2: ");
        int size2 = check("");

        set(set1, size1);
        set(set2, size2);

        while (true) {
            menu();
            int choice = check("");
            switch (choice) {
                case 1:
                    System.out.print("Add value for?\n 1. Set 1 \n 2. Set 2 \n Input: ");
                    int input = check("");
                    switch (input) {
                        case 1 -> add("Set 1", set1);
                        case 2 -> add("Set 2", set2);
                        default -> System.out.println("Invalid input, please try again.");
                    }
                    break;

                case 2:
                    System.out.print("Delete value for?\n 1. Set 1 \n 2. Set 2 \n Input: ");
                    input = check("");
                    switch (input) {
                        case 1 -> delete("Set 1", set1);
                        case 2 -> delete("Set 2", set2);
                        default -> System.out.println("Invalid input, please try again.");
                    }
                    break;

                case 3:
                    print("Set 1", set1);
                    print("Set 2", set2);
                    break;

                case 4:
                    CustomLinkedList unionResult = union(set1, set2);
                    System.out.print("Union of Set 1 and Set 2: ");
                    unionResult.print();
                    break;

                case 5:
                	difference(set1, set2);
                    break;

                case 6:
                    System.out.print("Check membership in?\n 1. Set 1 \n 2. Set 2 \n Input: ");
                    int setChoice = check("");
                    System.out.print("Enter the value to check: ");
                    int value = check("");
                    if (setChoice == 1) {
                        boolean isMemberResult = set1.contains(value);
                        System.out.println("Is " + value + " in Set 1? " + isMemberResult);
                    } else if (setChoice == 2) {
                        boolean isMemberResult = set2.contains(value);
                        System.out.println("Is " + value + " in Set 2? " + isMemberResult);
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void set(CustomLinkedList set, int size) {
        System.out.println("Enter " + size + " unique integer values:");
        while (set.size() < size) {
            int value = check("Input: ");
            if (set.contains(value)) {
                System.out.println("Value already exists in the set. Please enter a unique value.");
            } else {
                set.addLast(value);
            }
        }
    }

    public static void add(String name, CustomLinkedList set) {
        System.out.println(name);
        System.out.print("Add value:\n[1] at the beginning\n[2] at the end\n[3] at a certain position\nAction: ");
        int action = check("");

        System.out.print("Enter the number you want to insert: ");
        int data = check("");

        if (set.contains(data)) {
            System.out.println("Value already exists in the set. Duplicates are not allowed.");
            return;
        }

        switch (action) {
            case 1 -> set.addFirst(data);
            case 2 -> set.addLast(data);
            case 3 -> {
                System.out.print("Enter the index you want: ");
                int index = check("");
                set.addAt(index, data);
            }
            default -> System.out.println("Invalid action! Please select a valid option.");
        }
    }

    public static void delete(String name, CustomLinkedList set) {
        System.out.println(name);
        System.out.print("Delete from:\n[1] the beginning\n[2] the end\n[3] a certain position\nAction: ");
        int action = check("");

        switch (action) {
            case 1 -> set.removeFirst();
            case 2 -> set.removeLast();
            case 3 -> {
                System.out.print("Enter the index you want to delete: ");
                int index = check("");
                set.removeAt(index);
            }
            default -> System.out.println("Invalid action! Please select a valid option.");
        }
    }

    public static void print(String name, CustomLinkedList set) {
        System.out.print(name + ": ");
        set.print();
    }

    public static CustomLinkedList union(CustomLinkedList set1, CustomLinkedList set2) {
        CustomLinkedList result = new CustomLinkedList();
        for (int value : set1.toArray()) result.addLast(value);
        for (int value : set2.toArray()) if (!result.contains(value)) result.addLast(value);
        return result;
    }

    public static void difference(CustomLinkedList set1, CustomLinkedList set2) {
    	int sum1 = 0;
    	int sum2 = 0;
    	int difference;
    	for(int x : set1.toArray()) {
    		sum1 += x;
    	}
    	for(int x : set2.toArray()) {
    		sum2 += x;
    	}
    	if(sum1 > sum2) {
    		 difference = sum1 - sum2;
    	} else {
    		 difference = sum2 - sum1;
    	}
    	System.out.println("The Difference of Set 1 and Set 2 is "+difference);
    	
    	
    }

    public static int check(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    }

    public static void menu() {
        System.out.println("=================================");
        System.out.println("       Integer Set Manager       ");
        System.out.println("=================================");
        System.out.println("1. Add Element");
        System.out.println("2. Remove Element");
        System.out.println("3. Display Sets");
        System.out.println("4. Union of Sets");
        System.out.println("5. Difference of Sets");
        System.out.println("6. Check Membership");
        System.out.println("0. Exit");
        System.out.println("=================================");
        System.out.print("Select an option: ");
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class CustomLinkedList {
        private Node head;
        private int size;

        public int size() {
            return size;
        }

        public void addFirst(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
            size++;
        }

        public void addLast(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }

        public void addAt(int index, int data) {
            if (index < 0 || index > size) {
                System.out.println("Invalid index. Please try again.");
                return;
            }
            if (index == 0) {
                addFirst(data);
                return;
            }
            Node newNode = new Node(data);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }

        public void removeFirst() {
            if (head == null) {
                System.out.println("The list is empty. Nothing to delete.");
                return;
            }
            head = head.next;
            size--;
        }

        public void removeLast() {
            if (head == null) {
                System.out.println("The list is empty. Nothing to delete.");
                return;
            }
            if (head.next == null) {
                head = null;
            } else {
                Node current = head;
                while (current.next.next != null) {
                    current = current.next;
                }
                current.next = null;
            }
            size--;
        }

        public void removeAt(int index) {
            if (index < 0 || index >= size) {
                System.out.println("Invalid index. Please try again.");
                return;
            }
            if (index == 0) {
                removeFirst();
                return;
            }
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }

        public boolean contains(int data) {
            Node current = head;
            while (current != null) {
                if (current.data == data) return true;
                current = current.next;
            }
            return false;
        }

        public int[] toArray() {
            int[] arr = new int[size];
            Node current = head;
            int i = 0;
            while (current != null) {
                arr[i++] = current.data;
                current = current.next;
            }
            return arr;
        }

        public void print() {
            Node current = head;
            System.out.print("[");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(", ");
                current = current.next;
            }
            System.out.println("]");
        }
    }
}
