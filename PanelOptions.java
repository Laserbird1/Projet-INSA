import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class PanelOptions extends JPanel implements ActionListener {
    
    FenetreJeu Frame;
    JButton bMenu;
    CheckboxGroup choixArmes; 
    Checkbox pierre ;
    Checkbox fleche;
    CheckboxGroup choixModeTir;
    Checkbox pulling;
    Checkbox pointing;
    CheckboxGroup choixMusique;
	
	public PanelOptions (int L,int H,FenetreJeu Frame) {
		
        this.Frame=Frame;
        
        this.setLayout(null);
		this.setSize(L,H);
        
        int L_BOUTON=(int)(L/12);
        int H_BOUTON=(int)(H/12);
		
		JLabel mesOptions = new JLabel("Options");
		mesOptions.setBounds((int)(2*L/5),(int)(H/7), (int)(L/5), (int)(H/14));
		Font parametre = new Font("Arial",Font.BOLD,75);
		mesOptions.setFont(parametre);
		add(mesOptions);
        
        bMenu=new JButton("Menu");
        bMenu.setBounds((int)(L*9/20),(int)(H*8/10),L_BOUTON,H_BOUTON);
        bMenu.addActionListener(this);
        this.add(bMenu);
		
        JLabel armes = new JLabel("Arme");
		armes.setBounds((int)(L/5),(int)(5*H/24), (int)(L/5), (int)(H/24));
		Font parametre2 = new Font("Arial",Font.BOLD,35);
		armes.setFont(parametre2);
		add(armes);
        
		choixArmes =new CheckboxGroup();  
		pierre = new Checkbox("Pierre",choixArmes,true);  
		add(pierre);  
		pierre.setBounds((int)(L/5),(int)(6*H/24), (int)(L/8), (int)(H/24));
		fleche =new Checkbox("Fleche",choixArmes,false);  
		add(fleche);    
		fleche.setBounds((int)(L/5),(int)(7*H/24), (int)(L/8), (int)(H/24));
        
        JLabel modeTir = new JLabel("Mode de tir");
		modeTir.setBounds((int)(L/5),(int)(8*H/24), (int)(L/5), (int)(H/24));
		modeTir.setFont(parametre2);
		add(modeTir);
        
        choixModeTir=new CheckboxGroup();
        pulling = new Checkbox("pulling",choixModeTir,true);  
		add(pulling);  
		pulling.setBounds((int)(L/5),(int)(9*H/24), (int)(L/8), (int)(H/24));
		pointing =new Checkbox("pointing",choixModeTir,false);  
		add(pointing);    
		pointing.setBounds((int)(L/5),(int)(10*H/24), (int)(L/8), (int)(H/24));
        
        JLabel son = new JLabel("Musique");
		son.setBounds((int)(3*L/5),(int)(5*H/24), (int)(L/5), (int)(H/24));
		son.setFont(parametre2);
		add(son);
        
        choixMusique=new CheckboxGroup();
        Checkbox musicON = new Checkbox("ON",choixMusique,true);  
		add(musicON);  
		musicON.setBounds((int)(3*L/5),(int)(6*H/24), (int)(L/8), (int)(H/24));
		Checkbox musicOFF =new Checkbox("OFF",choixMusique,false);  
		add(musicOFF);    
		musicOFF.setBounds((int)(3*L/5),(int)(7*H/24), (int)(L/8), (int)(H/24));
        
		setVisible(true);
        
		
	}
	public void actionPerformed ( ActionEvent e){
        	if(e.getSource()==bMenu){
                
                Frame.panelJeu.arme=choixArmes.getSelectedCheckbox().getLabel();
                Frame.panelJeu.labelArme.setText(choixArmes.getSelectedCheckbox().getLabel());
                Frame.panelJeu.shootingMode=choixModeTir.getSelectedCheckbox().getLabel();
                
                if(choixMusique.getSelectedCheckbox().getLabel()=="ON")
                    Frame.musicONOFF=true;
			Frame.playSound();
                else
                    Frame.musicONOFF=false;
			Frame.stopSound();
                
            	Frame.setContentPane(Frame.panelMenu);
      		}
           
	}
}


