package ArrayInsertion;
import java.util.Scanner;
public class Main {
	static int size = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			System.out.print("Enter the length of your array:");
				 int capacity = sc.nextInt();
			
				 int[] array = new int[capacity];
		
				while(true) {
					for (int i : array) {
						System.out.print("["+i+"]");
					}
					System.out.print("\n[1]Insert\n[2]Delete\n[0]Exit\nAction:");
						int action = sc.nextInt();
				
						switch(action) {
						case 1:
						
						System.out.print("Enter the index you want:");
						int index = sc.nextInt();
							if(index < capacity) {
								System.out.print("Eneter the number you want to insert:");
								int data = sc.nextInt();
									insert(index, data, array);
							}else
							System.out.println("Invalid Index Try Again!");
						break;
						
						case 2:
							System.out.print("Enter the index of the value you want to delete:");
								int slot = sc.nextInt();
									delete(slot, array);
						break;
						}
					
				}
				
				
	/*Methods
	 * 	add();
	 * insert();
	 * delete();
	 * search();
	 * isEmpty();
	 */
				
	}
	static void delete(int data, int[] array) {
		for(int x = 0; x < array.length; x++) {
			if( x == data) {
				array[data] = 0;
				break;
			}
		}
	}
	static void insert(int index, int data, int[] array) {
		if(array[index] == 0 ) {
			array[index] = data;
		}else{
			System.out.println("Slot is occupied!");
		}
	}

}
