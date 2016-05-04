import java.util.*;

public class Anagrams { //Anagram class.
	private Set<String> words;
	
	/*
	 * Anagram that takes dictionary into the set.
	 */
	public Anagrams(Set<String> dictionary) {
		if(dictionary==null){
			throww();
		}
		words = dictionary;
	}
	
	
	/*
	 * Method that stores all the words in the tree set(for the alphabetic order)
	 * By using the given LetterInventory, put the possible words if the inventory contains the word. 
	 */
	public Set<String> getWords(String phrase) {
		Set<String> wordsPossible = new TreeSet<String>();
		if(phrase==null){
			throww();
		}
		LetterInventory Inventory = new LetterInventory(phrase);
		for (String word : words) {
			if (Inventory.contains(word)) {
				wordsPossible.add(word);
			}
		}
		return wordsPossible;
	}
	
	/*
	 * print method that starts the recursion with other printing method.
	 */
	public void print(String phrase) {
		if(phrase==null){
			throww();
		}
		print(phrase, 0);
	}
	
	/*
	 * print method that takes two parameters. It creates the array, new LetterInventory, and Stack.
	 * Lastly, It follows the new recursion with various parameters.
	 */

	public void print(String phrase, int max) {
		if (phrase == null) {
			System.out.println("Empty String generates no output.");
			throww();
		} else if(max <0){
			throww();
		}
		/*
		 * Like the toArray() method, this method acts as bridge between array-based and collection-based APIs. Further, this method allows precise control over the runtime type of the output array, and may, under certain circumstances, be used to save allocation costs.
Suppose x is a set known to contain only strings. The following code can be used to dump the set into a newly allocated array of String: 
     String[] y = x.toArray(new String[0]);
		 */
		String[] possibles = getWords(phrase).toArray(new String[0]);
		LetterInventory characters = new LetterInventory(phrase); //New LetterInventory that stores the characters which can be used.
		Stack<String> done = new Stack<String>(); //the stack which contains the finished words for the anagram solver.
		
		print(characters, done, possibles, max); //recursive method.

	}
	
	
	private void print(LetterInventory characters, Stack<String> done, String[] possibles, int max) {
		if (characters.isEmpty() && done.size() <= max ) { //if the inventory is empty and size is less than the input max,
			System.out.println(done.toString());           //print the chosen stack..
		}else if(characters.isEmpty() && max == 0){        //if the inverntory is empty and the input max is 0, print the chosen stacks.
			System.out.println(done.toString());
		}else {
			for (String word : possibles) {
				if (characters.contains(word)) {	//if the inventory cotains the word,
					done.push(word);                //stack gets the new node.
					characters.subtract(word);      //after that, the word should be now subtracted from the inventory.
					print(characters, done, possibles, max); //do the recursive print again for other possibilities.
					characters.add(word);          //add a word again to the inventory. (Not choosing the word)
					done.pop();                    //pull out the recent string from the stack and then remove it.
				}
			}
		}
	}
		
	private void throww(){
		throw new IllegalArgumentException();
	}
	
	
	
} // class gwalho