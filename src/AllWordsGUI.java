import java.awt.*; 
import javax.swing.*;

public class AllWordsGUI {
	
	private JTable list; 
	private static JFrame frame; 
	private JScrollPane pane; 
	
	public AllWordsGUI(Vocabulary v) { 
		frame = new JFrame("Vocabulary List"); 
		frame.setLayout(new BorderLayout());
		String[] column = {"Word", "Definition"}; 
		
		if (v.size() == 0) {
			String[][] data = new String[1][2]; 
			data[0][0] = ""; 
			data[0][1] = ""; 
			list = new JTable(data, column); 
			
		} else {
			String[][] data = new String[v.size()][2]; 
			list = new JTable(data, column); 
		
			for (int i = 0; i < v.size(); i++) {
				data[i][0] = v.get(i).getWord(); 
				data[i][1] = v.get(i).getDefinition(); 
				JPopupMenu popup = new Menu(v, list).createPopupMenu(v, data[i][0].toString()); 
				list.setComponentPopupMenu(popup);
			}	
			list.setColumnSelectionAllowed(true);
		}
		
		/*
		 * Source: https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
		 */
		pane = new JScrollPane(list);
		frame.add(pane, BorderLayout.CENTER); 
		
		setStyle(); 
		frame.setSize(350, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(new Menu(v, list).createMenuBar(frame));
		frame.setVisible(true);
	}
	
	private void setStyle() {
		pane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		list.setShowHorizontalLines(true);
		list.setShowVerticalLines(true);
		list.setGridColor(Color.GRAY);
		list.setShowGrid(true);
	}
}