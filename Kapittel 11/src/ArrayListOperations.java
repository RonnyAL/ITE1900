import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ArrayListOperations {

	public static void main(String[] args) {
		int[] arr = new int[10];
		
		for (int i=0; i<10; i++) {
			arr[i] = i+1;
		}
		
		ArrayList<Integer> arrList = new ArrayList<>();
		
		for (int i=0; i<arr.length; i++) {
			arrList.add(arr[i]);
		}
		
		
		//Legg til tilfeldig tall
		int rndAdd = (int)(Math.random()*100);
		System.out.println("Legger til " + rndAdd + " i ArrayList.");
		arrList.add(rndAdd);
		
		//Fjern tilfeldig tall
		int rndRemove = (int)(Math.random()*arrList.size());
		int removed = arrList.get(rndRemove);
		System.out.printf("Fjerner tallet %s fra ArrayList.%n%n", removed);
		arrList.remove(arrList.indexOf(removed));
		
		System.out.printf("Does list contain %s: %s%n", rndAdd, arrList.contains(rndAdd)? "Yes" : "No");
		System.out.printf("Does list contain %s: %s%n", removed, arrList.contains(removed)? "Yes" : "No");
		
		//Shuffling
		System.out.printf("%n");
		shuffleList(arrList);
		for (int i=0; i<arrList.size(); i++) {
			System.out.printf("%s ", arrList.get(i) );
		}
		
		//Sortering
		Comparator<Object> cmp = Collections.reverseOrder();
		Collections.sort(arrList, cmp);	
		System.out.printf("%n");
		for (int i=0; i<arrList.size(); i++) {
			System.out.printf("%s ", arrList.get(i) );
		}
		
	}
	
	public static void shuffleList(ArrayList<Integer> l) {
		int n = l.size();
		Random r = new Random();
		r.nextInt();
		for (int i = 0; i<n; i++) {
			int change = i + r.nextInt(n-i);
			swap(l, i, change);
		}
	}
		
	private static void swap(ArrayList<Integer> l, int i, int change) {
		int temp = l.get(i);
		l.set(i, l.get(change));
		l.set(change, temp);
	}
	
}