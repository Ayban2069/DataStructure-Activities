package ArrayInsertion;

public class Array {
	
		int size = 10;
		int[] array = {10, 20, 30, 40, 50, 60, 70,80, 90, 100};
		
		
	public void delete(int index) {
		
		int[] temp = new int[array.length-1];
	
		for(int x = 0, i = 0; x < temp.length; x++) {
			if(x != index) {
				temp[i] = array[x];
				i++;
			}
		}
		array = temp;
		for(int x : array) {
			System.out.print("["+x+"]");
		}
	}
}

