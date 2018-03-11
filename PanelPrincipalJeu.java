import java.awt.*;
import java.awt.event.*;


public class PanelPrincipalJeu extends JPanel implements ActionListener, MouseListener{
	
	Chateau castle;
	JButton bMenu;
	FenetreJeu Frame;
	
	public PanelPrincipalJeu(int L, int H) {
		
		this.Frame=Frame;
		this.setLayout(null);
		this.setSize(L,H);
		
		
		//panneau au sol
		int L_SOL=L;
        	int H_SOL=(int)(H/6);
		
		JPanel panelSol = new JPanel();
		add(panelSol);
		panelSol.setBounds(0,(int)(5*H/6),L_SOL,H_SOL);
		panelSol.setLayout(null);
		panelSol.setBackground(new Color(160, 82, 45));//couleur sienne
		
		bMenu=new JButton("Menu");
		bMenu.setBounds((int)(L_SOL*7/8),(int)(H_SOL/4),(int)(L_SOL*1/16),(int)(H_SOL*2/8));
		bMenu.addActionListener(this);
		panelSol.add(bMenu);
		
    	 	//panneau Tour
		
		int L_TERRAIN=L;
		int H_TERRAIN=H-H_SOL;
		
		JPanel panelTerrain  = new JPanel();
		add(panelTerrain);
		panelTerrain.setBounds(0,0,L_TERRAIN,H_TERRAIN);
		panelTerrain.setLayout(null);
		panelTerrain.setBackground(new Color(135,206,235));//bleu ciel
		
		castle= new Chateau(L_TERRAIN,H_TERRAIN);	//Dimensions du panneau de terrain
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==bMenu){
		    Frame.setContentPane(Frame.panelMenu);
		}
	}
	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	
	

}
