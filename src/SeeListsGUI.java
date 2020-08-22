import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SeeListsGUI {
	
	private JTable table; 
	protected JFrame frame; 
	private JScrollPane pane; 
	
	public SeeListsGUI(Vocabulary v, ArrayList<Word> list) {
		frame = new JFrame("Vocabulary List"); 
		frame.setLayout(new BorderLayout());
		String[] column = {"Word", "Definition"}; 
		
		if (v.size() == 0) {
			String[][] data = new String[1][2]; 
			data[0][0] = ""; 
			data[0][1] = ""; 
			table = new JTable(data, column); 
			
		} else {
			String[][] data = new String[list.size()][2]; 
			table = new JTable(data, column); 
		
			for (int i = 0; i < list.size(); i++) {
				data[i][0] = list.get(i).getWord(); 
				data[i][1] = list.get(i).getDefinition(); 
				JPopupMenu popup = new Menu(v, table).createPopupMenu(v, data[i][0].toString()); 
				table.setComponentPopupMenu(popup);
			}	
			table.setColumnSelectionAllowed(true);
		}
		
		pane = new JScrollPane(table);
		frame.add(pane, BorderLayout.CENTER); 
		
		setStyle(); 
		frame.setSize(350, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(new Menu(v, table).createMenuBar(frame));
		frame.setVisible(true);
	}

	private void setStyle() {
		pane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.GRAY);
		table.setShowGrid(true);
	}
}
