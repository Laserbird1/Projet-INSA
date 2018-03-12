import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;


public class PanelPrincipalJeu extends JPanel implements ActionListener, MouseListener,MouseMotionListener{
	
    JButton bMenu;
	FenetreJeu Frame;
    Chateau castle;
    Timer t;
    long temps; //temps qui passe
    int H_TERRAIN;  //hauteur zone de déplacement des monstres
    int H_SOL;      //hauteur de la zone d'information 
    int L;          //longueur de la fenetre et du terrain
    int H;          //hauteur de la fenetre
    
    int XMouse0;       //pos souris quand front montant du clic souris
    int YMouse0;        
    int XMouse1;       //pos souris a instant t s'il y a un clic enclenché de la souris
    int YMouse1;
    boolean shooting;//est on en train de tirer
    long temps1;//utile pour le temps de reload d'une arme
    boolean shootReady;
    
	public PanelPrincipalJeu(int L, int H,FenetreJeu Frame) {
		
        //initialisation paramètres
        
		this.Frame=Frame;
        temps=-40;
        shooting=false;
        shootReady=true;
		this.setLayout(null);
		this.setSize(L,H);
		
		H_SOL=(int)(H/6);
		H_TERRAIN=H-H_SOL;
        this.L=L;
        this.H=H;
        
		/*
        JPanel panelSol = new JPanel();
		add(panelSol);
		panelSol.setBounds(0,(int)(5*H/6),L_SOL,H_SOL);
		panelSol.setLayout(null);
		panelSol.setBackground(new Color(160, 82, 45));//couleur sienne
        */
		
        bMenu=new JButton("Menu");//bouton de retour au menu
        bMenu.setBounds((int)(L*7/8),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*2/8));
        bMenu.addActionListener(this);
        add(bMenu);
		
    	 	//panneau Tour
		
		/*
        JPanel panelTerrain  = new JPanel();
		add(panelTerrain);
		panelTerrain.setBounds(0,0,L_TERRAIN,H_TERRAIN);
		panelTerrain.setLayout(null);
		panelTerrain.setBackground(new Color(135,206,235));//bleu ciel
        */
		
		castle= new Chateau(L,H_TERRAIN,H_SOL);
        addMouseListener(this);
        addMouseListener(this);
        
        t=new Timer(40,this);
		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==bMenu){
            Frame.setContentPane(Frame.panelMenu);
            t.stop();
        }//si bMenu, retour menu et pause dans le jeu
        
        if(e.getSource()==t){
            
            temps+=40;//temps defile
            castle.move();//tout bouge
            
            if(temps%8000==0){//toutes les 8 sec
                Monstre newMonstre;
                
                newMonstre = new Gobelin(L-10,H_TERRAIN*7/8,(int)(L/15),(int)(H_TERRAIN/10),L/500);
                
                castle.listEnemis.add(newMonstre);//creer un monstre
            }
            
            castle.collisions();//tester les collisions
            
            
            repaint();
        }
        
        if(!shootReady && temps>temps1) shootReady=true;//si on a passé le temps de reload
                                                        // et qu'il était pas pret a tirer alors on est re-pret a tirer
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        
        castle.dessinTerCha(g);//dessin terrain, monstre chateau projectiles...
        
        if(shooting){

            g2.setColor(new Color(204,0,102));
            g2.setStroke(new BasicStroke(10));
            //g2.draw(new Line2D.Float(30, 20, 80, 90));
            g2.draw(new Line2D.Float(XMouse0,YMouse0,XMouse1,YMouse1));
            System.out.println
            
            //
            //g.setColor(Color.RED);
            //g.drawLine(XMouse0,YMouse0,XMouse1,YMouse1);
        }//dessin de la ligne pour le tir, aide au tir
    }
	
 

    
    public void mousePressed(MouseEvent e){
        
            XMouse0=e.getX();
            YMouse0=e.getY();//definition des param initiaux pour la souris
            XMouse1=XMouse0;
            YMouse1=YMouse0;//et param secondaires mais juste par précaution
            shooting=true;
    }
    
    public void mouseDragged(MouseEvent e){
        if(shooting){
            XMouse1=e.getX();
            YMouse1=e.getY();//actualisation permanente de la pos souris pour dessiner la ligne d'aide au tir
        }
    }
    
	public void mouseReleased(MouseEvent e){
        if(shooting && shootReady){
            XMouse1=e.getX();
            YMouse1=e.getY();
            
            shooting=false;//on est plus en cours de tir
            
            Projectile monProj;//donc on peut tirer un projectile
            
            //segment = vecteur avec x= Xmouse1-XMouse0 et y = YMouse1-YMouse0
            int x=XMouse1-XMouse0;
            int y=YMouse1-YMouse0;
            
            //assimilation du segment a un vecteur cartesien (x,y) que l'on exprime en coord polaire (r,teta)
            double VIni=-Math.sqrt(x*x+y*y)/10;//r=sqrt(x²+y²)
            
            if(VIni>180) VIni=180;//limitation de la vitesse sinon c'est cheaté
            
            double tetaIni = Math.atan2(y,x); //teta=atan2(x,y)
            
            monProj=new Pierre(tetaIni,VIni,(int)(L/50),castle.L,castle.H_TERRAIN-castle.H);
            
            castle.listArmes.add(monProj);//ajout du nouveau projectile 
            shootReady=false;
            temps1=temps+monProj.reloadTime;
        }
    }
    

	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}	
	
    public void mouseMoved(MouseEvent e){}
	

}

