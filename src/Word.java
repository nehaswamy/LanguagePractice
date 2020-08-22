
public class Word implements Comparable<Word> {
	private String word;
	private String definition; 
	private int comfortLevel; 
	
	public Word(String word, String definition) {
		this.word = word; 
		this.definition = definition; 
		comfortLevel = 0; 
	}
	
	@Override
	public int compareTo(Word other) {
		return this.comfortLevel - other.comfortLevel; 
	} 	
	
	public String getDefinition() {
		return this.definition; 
	}
	
	public String getWord() {
		return this.word; 
	}
	
	public int getComfortLevel() {
		return comfortLevel; 
	}
	
	public void set(String definition) {
		this.definition = definition; 
	}
	
	public boolean equals(Object o) {
		if (this == o) {
			return true; 
		}
		
		if (!(o instanceof Word)) {
			return false; 
		}
		
		Word other = (Word) o; 
		String thisWord = this.word.toLowerCase(); 
		String otherWord = other.word.toLowerCase(); 
		return thisWord.equals(otherWord); 
	}
	
	public void increaseComfortLevel() {
		this.comfortLevel++; 
	}
	
	public void decreaseComfortLevel() {
		this.comfortLevel--; 
	}
	
	public String toString() {
		return this.word; 
	}
}