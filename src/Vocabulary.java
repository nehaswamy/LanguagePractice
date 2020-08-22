import java.util.ArrayList;

public class Vocabulary {
	
	private ArrayList<Word> totalList; 
	private ArrayList<Word> comfortableList; 
	private ArrayList<Word> studyList; 
	
	public Vocabulary() {
		totalList = new ArrayList<Word>(); 
		comfortableList = new ArrayList<Word>(); 
		studyList = new ArrayList<Word>(); 
	}
	
	public void addWord(Word vocabWord) {
		totalList.add(vocabWord); 
	}
	
	public void addToComfortableList(Word vocabWord) {
		comfortableList.add(vocabWord); 
	}
	
	public void addToStudyList(Word vocabWord) {
		studyList.add(vocabWord); 
	}
	
	public ArrayList<Word> getComfortableList() {
		ArrayList<Word> copy = new ArrayList<Word>(); 
		for (Word w : comfortableList) {
			copy.add(w); 
		}
		
		return copy;
	}
	
	public ArrayList<Word> getStudyList() {
		ArrayList<Word> copy = new ArrayList<Word>(); 
		for (Word w : studyList) {
			copy.add(w); 
		}
		return copy; 
	}
	
	public ArrayList<Word> getTotalList() {
		ArrayList<Word> copy = new ArrayList<Word>(); 
		for (Word w : totalList) {
			copy.add(w); 
		}
		return copy; 
	}
	
	public int indexOf(Word w) {
		return totalList.indexOf(w); 
	}
	
	public Word get(int index) {
		return totalList.get(index); 
	}
	
	public boolean contains(Word w) {
		return totalList.contains(w); 
	}
	
	public int size() {
		return totalList.size(); 
	}
	
	public boolean isEmpty() {
		return totalList.size() == 0; 
	}
	
	public int sizeOfStudyList() {
		return studyList.size(); 
	}
	
	public Word findWordInStudyList(String word) {
		Word toFind = null; 
		
		for (Word w : studyList) {
			if (w.getWord().equals(word)) {
				toFind = w; 
				break; 
			}
		}
		return toFind; 
	}
}
