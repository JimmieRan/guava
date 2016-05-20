package com.ran.po;

public class ArrayTest {
	
	private int number = 0;
	private int[] array;
	
	public ArrayTest( int maxSize ){
		array = new int[maxSize];
	}
	
	public void insert( int value ){
		array[number] = value;
		number++;
	}
	
	public int find( int value ){
		for( int i = 0; i<number; i++ ){
			if( array[i] == value ){
				return i;
			}
		}
		return -1;
	}
	
	public boolean delete( int value ){
		int index = find(value);
		if( index != -1 ){
			for( int j = find(value); j<number-1; j++ ){
				array[j] = array[j+1];
			}
			array[number-1] = 0;
			number--;
			return true;
		}
		return false;
	}
	
	public void display(){
		for( int a :array ){
			System.out.print(a+"  ");
		}
	}
	
	//Ã°ÅİÅÅĞò
	public void bubbleSort(){
		int temp;
		for(int j = 0; j<number; j++){
			for( int i = 0; i < number-1; i++ ){
				if( array[i] < array[i+1] ){
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
		}
	}
	
	public void fastSort(int star,int end){
		int i = star;
		int j = end;
		int temp;
		while( i != j ){
			for( int m = 0; m < number-1; m++ ){
				if( array[i] > array[i+1] ){
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
			i++;
			for( int n = 0; n < number-1; n++ ){
				if( array[j] < array[j-1] ){
					temp = array[i];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
			j--;
		}
	}
    
	
	public static void main(String[] args) {
		ArrayTest at = new ArrayTest(10);
		at.insert(2);
		at.insert(1);
		at.insert(7);
		at.insert(9);
		at.insert(3);
		at.insert(11);
		at.insert(4);
		at.insert(5);
		at.insert(8);
		at.insert(15);
		at.bubbleSort();
		at.display();
	}

}
