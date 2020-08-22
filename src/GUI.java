import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int FRAME_WIDTH=350, FRAME_HEIGHT=700;
	protected static JFrame frame;
	private JButton addNewWord; 
	private JTextField enter; 
	private JTextField definition; 
	private JLabel add1; 
	private JLabel add2; 
	private JButton seeAllWords; 
	private Vocabulary v; 
	private JButton game; 
	
	public GUI() {
		frame = new JFrame("Language Vocabulary Builder"); 
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setLayout(null);
		
		enter = new JTextField(); 
		add1 = new JLabel("Word"); 
		enter.setBounds(40, 200, 110, 30); 
		add1.setBounds(80, 165, 110, 30);
		frame.getContentPane().add(enter); 
		frame.getContentPane().add(add1); 
		
		v = new Vocabulary(); 
		
		definition = new JTextField(); 
		add2 = new JLabel("Definition"); 
		definition.setBounds(200, 200, 110, 30);
		add2.setBounds(225, 165, 110, 30); 
		frame.getContentPane().add(definition); 
		frame.getContentPane().add(add2); 
		
		enter.setHorizontalAlignment(JTextField.CENTER);
		definition.setHorizontalAlignment(JTextField.CENTER);
		
		seeAllWords = new JButton("See All Words"); 
		seeAllWords.setBounds(200, 25, 140, 40);
		
		seeAllWords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new AllWordsGUI(v);
					}
				});
			}
		});
		
		frame.getContentPane().add(seeAllWords); 
		
		addNewWord = new JButton("Add Word"); 
		addNewWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = enter.getText();  
				
				Word w = new Word(str, definition.getText()); 
				if (v.contains(w)) {
					int index = v.indexOf(w); 
					
					if (!(v.get(index).getDefinition().equals(w.getDefinition()))) {
						v.get(index).set(w.getDefinition());
						
					} else {
						JOptionPane.showMessageDialog(GUI.this, "This word is already in your dictionary!"); 
					}
					
				} else {
					v.addWord(w); 
				}
				
				enter.setText("");
				definition.setText("");
			}
		});
		
		addNewWord.setBounds(120, 270, 100, 40);
		frame.add(addNewWord);
		
		game = new JButton("Test Your Vocabulary"); 
		game.setBounds(10, 25, 150, 40);
		game.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new GameGUI(v);
					}
				});
			}
		});
		frame.getContentPane().add(game); 
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
}
