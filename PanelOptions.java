import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class PanelOptions extends JPanel implements ActionListener {
	FenetreJeu Frame;
    	JButton bMenu;
	
	public PanelOptions (int L,int H) {
		
	 	this.Frame=Frame;
        
		this.setLayout(null);
		this.setSize(L,H);
		
		JLabel mesOptions = new JLabel("Options");
		mesOptions.setBounds((int)((2/5)*L),(int)((3/7)*H), (int)(L/5), (int)(H/7));
		Font parametre = new Font("Arial",Font.BOLD,75);
		mesOptions.setFont(parametre);
		add(mesOptions);
		
		bMenu=new JButton("Menu");
		bMenu.setBounds((int)(L*3/5),(int)(H*1/10),L_BOUTON,H_BOUTON);
		bMenu.addActionListener(this);
		this.add(bMenu);
		
		setVisible(true);
		
	}
	public void actionPerformed ( ActionEvent e){
		
		if(e.getSource()==bMenu){
		    Frame.setContentPane(Frame.panelMenu);
		}
	}
}

