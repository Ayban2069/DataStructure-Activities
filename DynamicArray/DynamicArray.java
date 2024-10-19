package DynamicArray;

public class DynamicArray {

	int size;
	int capacity = 10;
	Object[] array;
	
	public DynamicArray() {
		this.array = new Object[capacity];
	}
	
	public DynamicArray(int capacity) {
		this.capacity = capacity;
		this.array = new Object[capacity];
	}
	
	public Object get(int index) {
		return array[index];
	}
	
	public void add(Object data) {
		if(size >= capacity) {
			grow();
		}
		array[size] = data;
		size++;
	}
	
	public void insert(int index, Object data) {
		if(size >= capacity) {
			grow();
		}
		for(int x = size; x > index; x--) {
			array[x] = array[x - 1];
		}
		array[index] = data;
		size++;
	}
	
	public void delete(Object data) {
		for(int x = 0; x < size; x++) {
			if(array[x] == data) {
				for(int i = 0; i < (size - x - 1); i++) {
					array[x + i] = array[x + i + 1];
				}
				array[size -1] = null;
				size--;
				if(size <= (int) (capacity/3)) {
					shrink();
				}
				break;
			}
		}
	}
	
	public int search(Object data) {
		for(int x = 0; x < size; x++) {
			if(array[x] == data) {
				return x;
			}
		}
		return -1;
	}
	
	private void grow() {
		int newCapacity = (int) (capacity * 2);
		Object[] newArray = new Object[newCapacity];
		
		for(int x = 0; x < size; x++) {
			newArray[x] = array[x];
		}
		capacity = newCapacity;
		array = newArray;
	}
	
	private void shrink() {
		int newCapacity = (int) (capacity / 2);
		Object[] newArray = new Object[newCapacity];
		
		for(int x = 0; x < size; x++) {
			newArray[x] = array[x];
		}
		capacity = newCapacity;
		array = newArray;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public String toString() {
		String string = "";
		
		for(int x = 0; x < capacity; x++) {
			string += array[x] + ",";
		}
		if(string != "" ) {
			string = "[" + string.substring(0, string.length() - 2) + "]";
		}
		else {
			string = "[]";
		}
		return string;
	}
		
}
