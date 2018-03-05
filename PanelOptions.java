import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class PanelOptions extends JPanel implements ActionListener {
	
	public PanelOptions (int L,int H) {
		
		this.setLayout(null);
		this.setSize(L,H);
		
		JLabel mesOptions = new JLabel("Options");
		mesOptions.setBounds((int)((2/5)*L),(int)((3/7)*H), (int)(L/5), (int)(H/7));
		Font parametre = new Font("Arial",Font.BOLD,75);
		mesOptions.setFont(parametre);
		
		add(mesOptions);
		setVisible(true);
		
	}
	public void actionPerformed ( ActionEvent e){
	
	}
}

