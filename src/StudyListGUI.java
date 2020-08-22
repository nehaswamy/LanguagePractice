import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import javax.swing.*;

public class StudyListGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton enterButton; 
	protected static JFrame frame; 
	private JButton next; 
	private JTextField enterWord; 
	private JLabel label; 
	private Game game; 
	private ArrayList<Word> playedWords; 
	private Vocabulary v; 
	private Random r; 
	
	public StudyListGUI(Vocabulary v) {
		this.v = v; 
		frame = new JFrame("Test Study List"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(350, 700));
		frame.setLayout(null);
		
		enterButton = new JButton("Enter"); 
		enterButton.setBounds(100, 250, 120, 30);
		
		next = new JButton("Next Word"); 
		next.setBounds(170, 300, 110, 30);
		
		enterWord = new JTextField("Enter Definition"); 
		enterWord.setBounds(100, 250, 120, 30);
		enterWord.setHorizontalAlignment(JTextField.CENTER);
		
		label = new JLabel(); 
		
		playedWords = new ArrayList<>(); 
		
		play(); 
		
		frame.getContentPane().add(enterButton); 
		frame.getContentPane().add(next);
		frame.getContentPane().add(enterWord); 
		frame.getContentPane().add(label); 
		
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isCorrect = false; 
				
				if (enterWord.getText().isEmpty() || 
					enterWord.getText().equals("Enter Definition")) {
				
				} else {
					enterButton.setVisible(false);
					Word randomWord = null; 
					
					for (Word w : v.getStudyList()) {
						if (w.getWord().equals(label.getText())) {
							randomWord = w; 
						}
					}
					
					isCorrect = game.checkDefinition(enterWord.getText(), randomWord);
					if (isCorrect) {
						Color green = new Color(190, 255, 149); 
						enterWord.setBackground(green);
							
						Word w = v.findWordInStudyList(label.getText()); 
							
						if (w != null) {
							if (w.getComfortLevel() > 0) {
								v.getStudyList().remove(w);  
							}
						}
						
						} else {
							Color red = new Color(255, 98, 98); 
							enterWord.setBackground(red);
						}
					}
				}
			});
			
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterWord.setBackground(Color.WHITE);
				play(); 
			}
		}); 
	}

	private void play() {
		if (v.isEmpty()) {
			JOptionPane.showMessageDialog(this, "There are no words in your list!"); 
			
		} else {
			if (playedWords.size() != v.sizeOfStudyList()) {
				int randomIndex = r.nextInt(v.sizeOfStudyList()); 
				Word randomWord = v.getStudyList().get(randomIndex);  
		
				while (playedWords.contains(randomWord)) {
					randomIndex = r.nextInt(v.sizeOfStudyList()); 
					randomWord = v.getStudyList().get(randomIndex);  
				}
			
				playedWords.add(randomWord);  
				label = new JLabel(randomWord.toString()); 
				label.setBounds(40, 150, 110, 50);
				enterWord.setVisible(true);
				enterWord.setText("");
				
			} else {
				JOptionPane.showMessageDialog(this, "You have tested all the words.");
			}
		}
	}
}