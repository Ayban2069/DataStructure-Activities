package Project1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
        int[] array = {};
        System.out.print("Do you want to use the pre-made array?\n[y/n]:");
        char input = sc.nextLine().charAt(0);

        switch (input) {
            case 'y':
            case 'Y':
                array = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90};
                break;
            case 'n':
            case 'N':
                System.out.print("Enter the size of the array: ");
                int size = sc.nextInt();
                array = new int[size];
                for (int x = 0; x < size; x++) {
                    System.out.print("Enter a value for index [" + x + "]: ");
                    array[x] = sc.nextInt();
                }
                break;
            default:
                System.out.println("Invalid input");
                return;
        }

        while (true) {
            System.out.println("==Menu==");
            System.out.print("[1] Insert\n[2] Delete\n[3] Update\n[4] Sort\n[5] Print\n[6] Check\n[7] Search\n[0] Exit\nAction: ");
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    array = insert(sc, array);
                case 2:
                    array = delete(sc, array);
                case 3:
                    update(sc, array);
                case 4:
                    array = sort(array);
                case 5:
                    print(array);
                case 6:
                    check(array);
                case 7:
                    search(sc, array);
                case 0:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
            }
        }
    }

    static int[] insert(Scanner sc, int[] array) {
        System.out.print("Enter the index you want: ");
        int index = sc.nextInt();

        if (index <= array.length) {
            System.out.print("Enter the number you want to insert: ");
            int data = sc.nextInt();
            int[] temp = new int[array.length + 1];

            for (int i = 0; i < index; i++) {
                temp[i] = array[i];
            }
            temp[index] = data;
            for (int i = index; i < array.length; i++) {
                temp[i + 1] = array[i];
            }

            System.out.println("Element inserted successfully.");
            return temp;
        } else {
            System.out.println("Invalid Index. Please try again!");
        }
        return array;
    }

    static int[] delete(Scanner sc, int[] array) {
        System.out.print("Enter the index of the value you want to delete: ");
        int index = sc.nextInt();

        if (index < array.length) {
            int[] temp = new int[array.length - 1];
            for (int i = 0; i < index; i++) {
                temp[i] = array[i];
            }
            for (int i = index + 1; i < array.length; i++) {
                temp[i - 1] = array[i];
            }
            System.out.println("Element deleted successfully.");
            return temp;
        } else {
            System.out.println("Invalid Index. Please try again!");
        }
        return array;
    }

    static void update(Scanner sc, int[] array) {
        System.out.print("Enter the index you want to update: ");
        int index = sc.nextInt();
        if (index < array.length) {
            System.out.print("Enter a new value: ");
            array[index] = sc.nextInt();
            System.out.println("Element updated successfully!");
        } else {
            System.out.println("Invalid index");
        }
    }

    static int[] sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("Array sorted successfully!");
        return array;
    }

    static void print(int[] array) {
        for (int x : array) {
            System.out.print("[" + x + "]");
        }
        System.out.println();
    }

    static void check(int[] array) {
        boolean isEmpty = true;
        for (int num : array) {
            if (num != 0) {
                isEmpty = false;
                break;
            }
        }
        System.out.println("Array is empty: " + isEmpty);
    }

    static void search(Scanner sc, int[] array) {
        System.out.print("Enter a value you want to search: ");
        int data = sc.nextInt();
        boolean found = false;

        for (int x : array) {
            if (x == data) {
                System.out.println("Element found!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Element not found!");
        }
    }
}
