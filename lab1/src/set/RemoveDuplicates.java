package set;

public class RemoveDuplicates {


	public static int[] uniqueElements(int[] ints){
		if(ints==null){
			return null;
		} else {
			MaxSet<Integer> elements = new MaxSet<>();
			for (int i=0; i < ints.length; i++){
				elements.add(ints[i]);
			}
			
			int[] sortedElements = new int[elements.size()];
			
			int index = sortedElements.length-1;
			while(!elements.isEmpty()){
				int tempMax = elements.getMax();
				sortedElements[index] = tempMax;
				index --;
				
				elements.remove(tempMax); //autoboxing här
			}
			return sortedElements;
		
		}
		
		
		
	}
}
