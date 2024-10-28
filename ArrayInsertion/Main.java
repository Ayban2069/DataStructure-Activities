package ArrayInsertion;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        
        while (true) {
            // Display the array
            for (int i : array) {
                System.out.print("[" + i + "]");
            }
            
            System.out.print("\n[1] Insert\n[2] Delete\n[0] Exit\nAction: ");
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    System.out.print("Enter the index you want: ");
                    int index = sc.nextInt();
                    
                    if (index <= array.length) {
                        System.out.print("Enter the number you want to insert: ");
                        int data = sc.nextInt();
                        array = insert(index, data, array);
                    } else {
                        System.out.println("Invalid Index. Please try again!");
                    }
                    break;
                
                case 2:
                    System.out.print("Enter the index of the value you want to delete: ");
                    int slot = sc.nextInt();
                    
                    if (slot < array.length) {
                        array = delete(slot, array);
                    } else {
                        System.out.println("Invalid Index. Please try again!");
                    }
                    break;
                
                case 0:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;
                
                default:
                    System.out.println("Invalid action. Please try again!");
                    break;
            }
        }
    }

    static int[] insert(int index, int data, int[] array) {
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
    }

    static int[] delete(int index, int[] array) {
        
        int[] temp = new int[array.length - 1];
        
        for (int i = 0; i < index; i++) {
            temp[i] = array[i];
        }
        
        for (int i = index + 1; i < array.length; i++) {
            temp[i - 1] = array[i];
        }
        
        System.out.println("Element deleted successfully.");
        return temp;
    }
}
