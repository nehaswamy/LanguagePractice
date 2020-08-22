import java.awt.event.*; 
import java.io.*; 
import javax.swing.*;

public class Menu {

	/**
	 * Sources: 
	 * - https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
	 * - https://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
	 * - https://sites.google.com/site/teachmemrxymon/java/export-records-from-jtable-to-ms-excel
	 */
	
	private Vocabulary v; 
	private JTable table; 
	
	public Menu(Vocabulary v, JTable table) {
		this.v = v; 
		this.table = table; 
	}
	
	public JMenuBar createMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar(); 
		JMenu menu = new JMenu("Menu"); 
		menuBar.add(menu); 
		
		JMenuItem menuItem = new JMenuItem("Go to main page"); 
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						frame.setVisible(false);
						GUI.frame.setVisible(true);
					}
				});
			}
		});
		
		menu.add(menuItem); 
		
		JMenuItem seeStudyList = new JMenuItem("See Study List"); 
		seeStudyList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SeeListsGUI studyList = new SeeListsGUI(v, v.getStudyList()); 
				studyList.frame.setVisible(true);
			}
			
		});
		
		JMenuItem seeComfortableList = new JMenuItem("See Comfortable List"); 
		seeComfortableList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SeeListsGUI comfortableList = new SeeListsGUI(v, v.getComfortableList()); 
				comfortableList.frame.setVisible(true);
			}
			
		});
		
		JMenuItem saveData = new JMenuItem("Save Data"); 
		saveData.addActionListener(new SaveDataActionListener()); 
		
		JMenuItem loadData = new JMenuItem("Load Save Data");
		loadData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		menu.add(seeStudyList); 
		menu.add(seeComfortableList); 
		menu.add(saveData); 
		menu.add(loadData); 
		
		return menuBar; 
	}
	
	public JPopupMenu createPopupMenu(Vocabulary v, String word) {
		JPopupMenu popup = new JPopupMenu(); 
		JMenuItem menuItem = new JMenuItem("Add to study list"); 
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Word w : v.getTotalList()) {
					if (w.getDefinition().equals(word) || 
						w.getWord().equals(word)) {
						if (!(v.getStudyList().contains(w))) {
							v.addToStudyList(w); 
						}
					}
				}
				
				for (Word w : v.getStudyList()) {
					System.out.println(w + " ");
				}
			}
		});
		
		JMenuItem menuItem2 = new JMenuItem("Add to comfortable list"); 
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Word w : v.getTotalList()) {
					if (w.getDefinition().equals(word) || 
						w.getWord().equals(word)) {
						if (!(v.getComfortableList().contains(w))) {
							v.addToComfortableList(w);
						}
					}
				}
			}
		});
		
		popup.add(menuItem); 
		popup.add(menuItem2); 
		return popup; 
	}
	
	class SaveDataActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			File file = new File("tabledata.xls"); 
			try {
				file.createNewFile();
			} catch (IOException i) {
				i.printStackTrace();
			} 
				
			FileWriter excel; 
			try {
				excel = new FileWriter(file);
				for (int i = 0; i < v.size(); i++) {
					try {
						excel.write(v.get(i).getWord() + "\t");
						excel.write(v.get(i).getDefinition());
						excel.write("\n");
							
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				excel.close(); 
					
			} catch (IOException e2) {
				e2.printStackTrace();
			} 
		}
	}
	
	class LoadDataActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File file = new File("tabledata.xls"); 
			table.clearSelection(); 
			
			
		}
	}
}