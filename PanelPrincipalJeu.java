import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;


public class PanelPrincipalJeu extends JPanel implements ActionListener, MouseListener,MouseMotionListener{
	
    JButton bMenu;
    JButton bArme;
    JLabel labelArme;
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
    String arme;
    
    boolean playing;//pour faire pause
    JLabel labelPause;
    JButton bPause;
    
	public PanelPrincipalJeu(int L, int H,FenetreJeu Frame) {
		
        //initialisation paramètres
        
	this.Frame=Frame;
        temps=-40;
        shooting=false;
        shootReady=true;
	playing=false;
	arme="Fleche";
		
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
	
	labelPause = new JLabel("Appuyer sur start pour jouer");
	labelPause.setBounds((int)(L/2),(int)((H_TERRAIN+H_SOL)/2),(int)(L*8/16),(int)(H_SOL));
	Font parametre = new Font("Arial",Font.BOLD,75);
	labelPause.setFont(parametre);
	add(labelPause);
        
        bPause=new JButton("Start");//bouton start/pause
        bPause.setBounds((int)(L*6/8),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*2/8));
        bPause.addActionListener(this);
        add(bPause);
	
	labelArme = new JLabel(arme);
	labelArme.setBounds((int)(L*5/8),(int)(H_TERRAIN+(H_SOL/2)),(int)(L*8/16),(int)(H_SOL/4));
        Font parametre2 = new Font("Arial",Font.BOLD,30);
	labelArme.setFont(parametre2);
	add(labelArme);
        
        bArme=new JButton("Changer arme");//bouton de retour au menu
        bArme.setBounds((int)(L*5/8),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*1/4));
        bArme.addActionListener(this);
        add(bArme);
		
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
        addMouseMotionListener(this);
        
        t=new Timer(40,this);
		
	// Pour rendre la fenêtre visible
	this.setVisible(true);
		
		
	}
	
	public void reset(){//pour recommencer le jeu
        	castle.vie=castle.VIE_MAX;
        	castle.listEnemis.clear();
        	castle.listArmes.clear();
       	 	pause();
        	labelPause.setText("Appuyer sur start pour jouer");
   	 }
    
   	 public void pauseJouer(){
       		if(playing) pause();
        	else jouer();
   	 }
    
    	public void pause(){
        	t.stop();
        	bPause.setText("Start");
       		labelPause.setText("Pause");
        	labelPause.setVisible(true);
        	playing=false;
   	 }
   	public void jouer(){
        	t.start();
        	bPause.setText("Pause");
        	labelPause.setVisible(false);
        	playing=true;
   	}
	
	public void actionPerformed(ActionEvent e){
        
        	if(e.getSource()==bMenu){
            		Frame.setContentPane(Frame.panelMenu);
            		t.stop();
        	}//si bMenu, retour menu et pause dans le jeu
		
		if(e.getSource()==bPause){
            		pauseJouer();
        	}
		
		if(e.getSource()==bArme){
		    if(arme=="Pierre")arme="Fleche";
		    else if(arme=="Fleche")arme="Pierre";
		    else arme="Fleche";
		    labelArme.setText(arme);
		}
        
        	if(e.getSource()==t){
            
           		temps+=40;//temps defile
            		castle.move();//tout bouge
            
            	if(temps%8000==0){//toutes les 8 sec
                	Monstre newMonstre;
                
			newMonstre = new Gobelin(L-10,H_TERRAIN*7/8,(int)(L/15),(int)(H_TERRAIN/10),L/500);

			castle.listEnemis.add(newMonstre);//creer un monstre
		}

		if(castle.collisions()){//tester les collisions
			pause();
			Frame.setContentPane(Frame.panelDefaite);//si on a plus de vie, afficher le panel defaite
		}
			
		    repaint();
		}
        
        if(!shootReady && temps>temps1) shootReady=true;
		//si on a passé le temps de reload et qu'il était pas pret a tirer alors on est re-pret a tirer
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;        
        castle.dessinTerCha(g);//dessin terrain, monstre chateau projectiles...
        
        if(shooting){

            g2.setColor(new Color(204,0,102));
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Float(XMouse0,YMouse0,XMouse1,YMouse1));
            
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
        if(shooting && shootReady && playing){
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
            
            if(arme=="Pierre")
            monProj=new Pierre(tetaIni,VIni,(int)(L/50),castle.L,castle.H_TERRAIN-castle.H);
            else if(arme=="Fleche")
            monProj=new Fleche(tetaIni,VIni,(int)(L/25),castle.L,castle.H_TERRAIN-castle.H);
            else
            monProj=new Fleche(tetaIni,VIni,(int)(L/25),castle.L,castle.H_TERRAIN-castle.H);
            
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

