import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class PanelOptions extends JPanel implements ActionListener {
    
    FenetreJeu Frame;
    JButton bMenu;
    CheckboxGroup choixArmes ; 
    Checkbox pierre ;
    Checkbox fleche;
	
	public PanelOptions (int L,int H,FenetreJeu Frame) {
		
       		this.Frame=Frame;
        
		this.setLayout(null);
		this.setSize(L,H);
        
       		int L_BOUTON=(int)(L/12);
        	int H_BOUTON=(int)(H/12);
		
		JLabel mesOptions = new JLabel("Options");
		mesOptions.setBounds((int)(2*L/5),(int)(H/7), (int)(L/5), (int)(H/7));
		Font parametre = new Font("Arial",Font.BOLD,75);
		mesOptions.setFont(parametre);
		add(mesOptions);
        
       		bMenu=new JButton("Menu");
        	bMenu.setBounds((int)(L*9/20),(int)(H*8/10),L_BOUTON,H_BOUTON);
        	bMenu.addActionListener(this);
        	this.add(bMenu);
		
		choixArmes =new CheckboxGroup();  
		pierre = new Checkbox("Pierre",choixArmes,true);  
		add(pierre);  
		pierre.setBounds((int)(2*L/5),(int)(3*H/8), (int)(L/8), (int)(H/16));
		fleche =new Checkbox("Fleche",choixArmes,false);  
		add(fleche);    
		fleche.setBounds((int)(2*L/5),(int)(3*H/8)+50, (int)(L/8), (int)(H/16));
        
		setVisible(true);
        
		
	}
	public void actionPerformed ( ActionEvent e){
        	if(e.getSource()==bMenu){
            	Frame.setContentPane(Frame.panelMenu);
      		}
		if(choixArmes.getSelectedCheckbox().getLabel() == "Pierre"){
		//a completer		
			}else{
				// a completer
			}
	}
}


