import java.awt.*; 
import java.awt.event.*; 
import java.util.ArrayList;
import javax.swing.*; 

public class GameGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Word> playedWords; 
	private Game game; 
	private static JFrame frame; 
	private JTextField enterWord; 
	private JButton start; 
	private JButton enterButton; 
	private JButton next;
	private Vocabulary v; 
	private JLabel label; 
	
	public GameGUI(Vocabulary v) {
		this.v = v; 
		playedWords = new ArrayList<Word>(); 
		frame = new JFrame("Vocabulary Building Game"); 
		frame.setSize(350, 700); 
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game = new Game(v); 
		
		label = new JLabel(); 
		label.setBounds(150, 200, 110, 50); 
		
		enterWord = new JTextField(); 
		enterWord.setBounds(100, 250, 120, 30);
		enterWord.setHorizontalAlignment(JTextField.CENTER);
		enterWord.setText("Enter Definition");
		
		start = new JButton("Start"); 
		start.setBounds(120, 300, 110, 30);
		enterButton = new JButton("Enter"); 
		enterButton.setBounds(50, 300, 110, 30);
		next = new JButton("Next Word"); 
		next.setBounds(170, 300, 110, 30);
		start.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			start.setVisible(false); 
			frame.getContentPane().add(label); 
			frame.getContentPane().add(enterWord); 
			frame.getContentPane().add(enterButton); 
			frame.getContentPane().add(next);
			play();  
			}
		});
		
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isCorrect = false; 
				
				if (enterWord.getText().isEmpty() || 
					enterWord.getText().equals("Enter Definition")) {
					JOptionPane.showMessageDialog(frame, "You must enter a word"); 
					
				} else {
					enterButton.setVisible(false);
					String word = label.getText(); 
					Word randomWord = null; 
					for (Word w : v.getTotalList()) {
						if (w.getWord().equals(word)) {
							randomWord = w; 
						}
					}
					isCorrect = game.checkDefinition(enterWord.getText(), randomWord);
					if (isCorrect) {
						Color green = new Color(190, 255, 149); 
						enterWord.setBackground(green);
						
					} else {
						Color red = new Color(255, 98, 98); 
						enterWord.setBackground(red);
					}
					playedWords.add(randomWord); 
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
		
		frame.getContentPane().add(start); 
			
		frame.setVisible(true);
	}
	
	private void play() {
		if (v.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "There are no words in your list!"); 
			
		} else {
			if (playedWords.size() != v.size()) {
				Word randomWord = game.getRandom(); 
		
				while (playedWords.contains(randomWord)) {
					randomWord = game.getRandom(); 
				}
			
				label.setText(randomWord.toString()); 
				enterWord.setVisible(true);
				enterWord.setText("");
				enterButton.setVisible(true);
			
			} else {
				addAutomatically(); 
				
				if (v.sizeOfStudyList() > 0) {
					JLabel statement = new JLabel("There are " + v.sizeOfStudyList() 
				                             + " words in your study list. "); 
					JLabel question = new JLabel("Do you want to retest these words?"); 
				
					enterButton.setVisible(false);
					label.setVisible(false);
					next.setVisible(false);
					enterWord.setVisible(false);
				
					statement.setBounds(50, 100, 250, 40);
					question.setBounds(50, 120, 250, 40);
					frame.getContentPane().add(statement); 
					frame.getContentPane().add(question); 
				
					JButton yes = new JButton("Yes"); 
					JButton no = new JButton("No"); 
					yes.setBounds(50, 200, 100, 30);
					no.setBounds(170, 200, 100, 30);
					frame.getContentPane().add(yes); 
					frame.getContentPane().add(no); 
				
					yes.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									StudyListGUI.frame.setVisible(true);
								}
							});
						
						}
					});
				
					no.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
							javax.swing.SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									GUI.frame.setVisible(true);
								}
							});
						}
					}); 
					
				} else {
					JOptionPane.showMessageDialog(frame, "You successfully played all the words!"); 
					frame.setVisible(false);
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GUI.frame.setVisible(true);
						}
					});
				}
			}
		}
	}
	
	private void addAutomatically() {
		for (int i = 0; i < playedWords.size(); i++) {
			if (playedWords.get(i).getComfortLevel() < 1) {
				v.addToStudyList(playedWords.get(i));
				
			} else {
				v.addToComfortableList(playedWords.get(i));
			}
		}
	}
}