package Project2;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LinkedList<Integer> set1 = new LinkedList<>();
        LinkedList<Integer> set2 = new LinkedList<>();

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
                    LinkedList<Integer> unionResult = union(set1, set2);
                    System.out.println("Union of Set 1 and Set 2: " + unionResult);
                    break;

                case 5:
                    System.out.print("Find difference for?\n 1. Set1 - Set2 \n 2. Set2 - Set1 \n Input: ");
                    int diffChoice = check("");
                    if (diffChoice == 1) {
                        LinkedList<Integer> diffResult = difference(set1, set2);
                        System.out.println("Difference (Set 1 - Set 2): " + diffResult);
                    } else if (diffChoice == 2) {
                        LinkedList<Integer> diffResult = difference(set2, set1);
                        System.out.println("Difference (Set 2 - Set 1): " + diffResult);
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;

                case 6:
                    System.out.print("Check membership in?\n 1. Set 1 \n 2. Set 2 \n Input: ");
                    int setChoice = check("");
                    System.out.print("Enter the value to check: ");
                    int value = check("");
                    if (setChoice == 1) {
                        boolean isMemberResult = isMember(set1, value);
                        System.out.println("Is " + value + " in Set 1? " + isMemberResult);
                    } else if (setChoice == 2) {
                        boolean isMemberResult = isMember(set2, value);
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

    public static void set(LinkedList<Integer> set, int size) {
        System.out.println("Enter " + size + " unique integer values:");
        while (set.size() < size) {
            int value = check("Input: ");
            if (set.contains(value)) {
                System.out.println("Value already exists in the set. Please enter a unique value.");
            } else {
                set.add(value);
            }
        }
    }

    public static void add(String name, LinkedList<Integer> set) {
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
            case 1 -> {
                set.addFirst(data);
                System.out.println("Element inserted successfully at the beginning.");
            }
            case 2 -> {
                set.addLast(data);
                System.out.println("Element inserted successfully at the end.");
            }
            case 3 -> {
                System.out.print("Enter the index you want: ");
                int index = check("");
                if (index >= 0 && index <= set.size()) {
                    set.add(index, data);
                    System.out.println("Element inserted successfully at position " + index + ".");
                } else {
                    System.out.println("Invalid index. Please try again.");
                }
            }
            default -> System.out.println("Invalid action! Please select a valid option.");
        }
    }

    public static void delete(String name, LinkedList<Integer> set) {
        System.out.println(name);
        System.out.print("Delete from the name:\n[1] from the beginning\n[2] from the end\n[3] from a certain position\nAction: ");
        int action = check("");

        switch (action) {
            case 1 -> {
                if (!set.isEmpty()) {
                    int removed = set.removeFirst();
                    System.out.println("Element " + removed + " removed successfully from the beginning.");
                } else {
                    System.out.println("The list is empty. Nothing to delete.");
                }
            }
            case 2 -> {
                if (!set.isEmpty()) {
                    int removed = set.removeLast();
                    System.out.println("Element " + removed + " removed successfully from the end.");
                } else {
                    System.out.println("The list is empty. Nothing to delete.");
                }
            }
            case 3 -> {
                System.out.print("Enter the index you want to delete: ");
                int index = check("");
                if (index >= 0 && index < set.size()) {
                    int removed = set.remove(index);
                    System.out.println("Element " + removed + " removed successfully from position " + index + ".");
                } else {
                    System.out.println("Invalid index. Please try again.");
                }
            }
            default -> System.out.println("Invalid action! Please select a valid option.");
        }
    }

    public static void print(String name, LinkedList<Integer> set) {
        System.out.println(name + ": " + set);
    }
    public static LinkedList<Integer> union(LinkedList<Integer> set1, LinkedList<Integer> set2) {
        LinkedList<Integer> result = new LinkedList<>(set1);
        for (int value : set2) {
            if (!result.contains(value)) { 
                result.add(value);
            }
        }
        return result;
    }
    public static LinkedList<Integer> difference(LinkedList<Integer> set1, LinkedList<Integer> set2) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int value : set1) {
            if (!set2.contains(value)) { 
                result.add(value);
            }
        }
        return result;
    }
    public static boolean isMember(LinkedList<Integer> set, int value) {
        return set.contains(value); 
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
        System.out.println("6. Check Memeber");
        System.out.println("0. Exit");
        System.out.println("=================================");
        System.out.print("Select an option: ");
    }
}
