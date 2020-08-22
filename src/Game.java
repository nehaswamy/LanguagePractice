import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Game {
	
	private Vocabulary v; 
	
	public Game(Vocabulary v) {
		this.v = v; 
	}
	
	public Word getRandom() {
		Random rand = new Random(); 
		int index = rand.nextInt(v.size()); 
		return v.get(index); 
	}
	
	public boolean checkWord(String input, Word w) {
		String str; 
		boolean b = false; 
		
		try {
			str = new String(input.getBytes(), "UTF-8"); 
			b = str.toLowerCase().equals(w.getWord().toLowerCase()); 
			
			if (b == true) {
				w.increaseComfortLevel(); 
				
			} else {
				w.decreaseComfortLevel(); 
			}
			
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getStackTrace());
		}
		
		return b; 
	}
	
	public boolean checkDefinition(String input, Word w) {
		String str; 
		boolean b = false;
		
		try {
			str = new String(input.getBytes(), "UTF-8"); 
			b = str.toLowerCase().equals(w.getDefinition().toLowerCase()); 
			
			if (b == true) {
				w.increaseComfortLevel(); 
				
			} else {
				w.decreaseComfortLevel(); 
			}
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getStackTrace());
		}
		return b; 
	}
}