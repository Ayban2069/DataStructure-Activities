package LinkedList;
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
                case 1 -> {
                    System.out.print("Add value for?\n 1. Set 1 \n 2. Set 2 \n Input: ");
                    int input = check("");
                    switch (input) {
                        case 1 -> add("Set 1", set1);
                        case 2 -> add("Set 2", set2);
                        default -> System.out.println("Invalid input, please try again.");
                    }
                }
                case 2 -> {
                    System.out.print("Delete value for?\n 1. Set 1 \n 2. Set 2 \n Input: ");
                    int input = check("");
                    switch (input) {
                        case 1 -> delete("Set 1", set1);
                        case 2 -> delete("Set 2", set2);
                        default -> System.out.println("Invalid input, please try again.");
                    }
                }
                case 3 -> {
                    System.out.print("Set 1: ");
                    set1.printList();
                    System.out.print("Set 2: ");
                    set2.printList();
                }
                case 0 -> {
                    System.out.println("Exiting program.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
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
                try {
                    set.addAt(index, data);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            default -> System.out.println("Invalid action! Please select a valid option.");
        }
    }

    public static void delete(String name, CustomLinkedList set) {
        System.out.println(name);
        System.out.print("Delete value: \n [1] from the beginning \n [2] from the end[3] at a certain position \n Action: ");
        int action = check("");

        switch (action) {
            case 1 -> {
                try {
                    int removed = set.removeFirst();
                    System.out.println("Element " + removed + " removed successfully from the beginning.");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                try {
                    int removed = set.removeLast();
                    System.out.println("Element " + removed + " removed successfully from the end.");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                System.out.print("Enter the index you want to delete: ");
                int index = check("");
                try {
                    int removed = set.removeAt(index);
                    System.out.println("Element " + removed + " removed successfully from position " + index + ".");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            default -> System.out.println("Invalid action! Please select a valid option.");
        }
    }

    public static void print(String name, CustomLinkedList set) {
        System.out.print(name + " contains: ");
        set.printList();
    }

    public static boolean isMember(CustomLinkedList set, int value) {
        return set.contains(value);
    }

    public static CustomLinkedList union(CustomLinkedList set1, CustomLinkedList set2) {
        CustomLinkedList result = new CustomLinkedList();
        Node temp = set1.head;
        while (temp != null) {
            result.addLast(temp.data);
            temp = temp.next;
        }
        temp = set2.head;
        while (temp != null) {
            if (!result.contains(temp.data)) {
                result.addLast(temp.data);
            }
            temp = temp.next;
        }
        return result;
    }

    public static CustomLinkedList difference(CustomLinkedList set1, CustomLinkedList set2) {
        CustomLinkedList result = new CustomLinkedList();
        Node temp = set1.head;
        while (temp != null) {
            if (!set2.contains(temp.data)) {
                result.addLast(temp.data);
            }
            temp = temp.next;
        }
        return result;
    }

    public static int check(String message) {
        int value;
        while (true) {
            try {
                System.out.print(message);
                value = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return value;
    }
