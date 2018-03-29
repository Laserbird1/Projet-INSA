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
    boolean angleShootLow;
    int reloadTime;
    String shootingMode; 
    
    boolean playing;//pour faire pause
    JLabel labelPause;
    JButton bPause;
    
	public PanelPrincipalJeu(int L, int H,FenetreJeu Frame) {
		
        //initialisation paramètres
        
		this.Frame=Frame;
        temps=0;
        temps1=0;
        shooting=false;
        shootReady=true;
        playing=false;
        arme="Fleche";
        shootingMode="pointing";
        reloadTime=400;
        angleShootLow=true;
        
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
		labelPause.setBounds((int)(2*L/5),(int)((H_TERRAIN+H_SOL)/2),(int)(L*8/16),(int)(H_SOL));
		Font parametre = new Font("Arial",Font.BOLD,50);
		labelPause.setFont(parametre);
		add(labelPause);
        
        bPause=new JButton("Start");//bouton start/pause
        bPause.setBounds((int)(L*6/8),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*1/4));
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
            pause();
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
            
            boolean plusDeVie=castle.collisions();//tester les collisions
            if(plusDeVie){ 
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
        
        if(shooting && shootingMode=="pulling"){

            g2.setColor(new Color(204,0,102));
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Float(XMouse0,YMouse0,XMouse1,YMouse1));
            
        }//dessin de la ligne pour le tir, aide au tir
        
        //dessin barre de rechargement du tir
       
        int lBarre=(int)(L*2/18); //longeur barre (2/3 de la longueur du chateau)
        int l1=(int)(L/24);              //debut barre (coord x)
        int y1=(int)(H_TERRAIN+H_SOL*4/10);//(coord y)
        int l2=(int)(l1+(lBarre*(temps-temps1+reloadTime)/reloadTime)); //coord x séparation bleu/noir
        int l3=lBarre+l1;//coord x en pixel de la fin de la barre
        int h=(int)(H_SOL/5);//hauteur barre
        
        g.setColor(new Color(30, 144, 255));//bleu electrique
        
        if(temps>temps1){
            g.fillRect(l1,y1,lBarre,h);
        }else{
            g.fillRect(l1,y1,l2-l1,h);
        
            g.setColor(Color.black);//noir (vie perdue)
            g.fillRect(l2,y1,l3-l2,h);
        }
        
    }
	
 

    //methodes pour tirer
    public void mousePressed(MouseEvent e){
        
        XMouse0=e.getX();
        YMouse0=e.getY();//definition des param initiaux pour la souris
        XMouse1=XMouse0;
        YMouse1=YMouse0;//et param secondaires mais juste par précaution
        shooting=true;
    }
    
    public void mouseDragged(MouseEvent e){
            if(shooting && shootingMode=="pulling"){
                XMouse1=e.getX();
                YMouse1=e.getY();//actualisation permanente de la pos souris pour dessiner la ligne d'aide au tir
            }
    }
    
	public void mouseReleased(MouseEvent e){
        if(shootReady && shooting && playing){
            XMouse1=e.getX();
            YMouse1=e.getY();
            
            shooting=false;//on est plus en cours de tir
            
            Projectile monProj;//donc on peut tirer un projectile
            int XTir=castle.L;
            int YTir=castle.H_TERRAIN-castle.H;//coord (x,y) du point de tir du projectile
            
            double VIni;
            double tetaIni;
            
            if(shootingMode=="pulling"){
                //segment = vecteur avec x= Xmouse1-XMouse0 et y = YMouse1-YMouse0
                int x=XMouse1-XMouse0;
                int y=YMouse1-YMouse0;
                
                //assimilation du segment a un vecteur cartesien (x,y) que l'on exprime en coord polaire (r,teta)
                 VIni=-Math.sqrt(x*x+y*y)/5;//r=sqrt(x²+y²)
                
                if(VIni<-100) VIni=-100;//limitation de la vitesse sinon c'est cheaté
                
                 tetaIni = Math.atan2(y,x); //teta=atan2(x,y)
                 
            }else if(shootingMode=="pointing"){//pointer un endroit ou le projectile passera
                VIni=110;//vitesse initiale imposée
                
                if(angleShootLow){//on veut l'angle bas
                    tetaIni=0.5*Math.PI;//parcours depuis le bas
                    
                    while(tetaIni<1.5*Math.PI && tetaIni>-0.5*Math.PI){//parcours de tous les angles jusqu'a ce qu'un angle convienne pour l'equation de trajectoire associée 
                                                            //Le parcours de fait a droite ou a gauche selon la position du point visé
                    
                        double y=0.5*7*(XMouse1-XTir)*(XMouse1-XTir)/(VIni*VIni*Math.cos(tetaIni)*Math.cos(tetaIni))+(XMouse1-XTir)*Math.tan(tetaIni)+YTir;
                        
                        if( YMouse1-2<y && y<2+YMouse1) break;//pour ce teta on peut atteindre le point fixé
                        
                        if(((XMouse1-castle.L)>=0)) tetaIni-=0.001; //incrément du teta // si la souris est a droite du chateau increment par teta décroissant
                        else  tetaIni+=0.001; 
                    }
                    
                }else{//on veut l'angle haut
                
                    tetaIni=-0.5*Math.PI;;//parcours depuis le haut
                
                    while(tetaIni<0.5*Math.PI && tetaIni>-1.5*Math.PI){//parcours de tous les angles jusqu'a ce qu'un angle convienne pour l'equation de trajectoire associée 
                                               //Le parcours de fait a droite ou a gauche selon la position du point visé
                                               
                        double y=0.5*7*(XMouse1-XTir)*(XMouse1-XTir)/(VIni*VIni*Math.cos(tetaIni)*Math.cos(tetaIni))+(XMouse1-XTir)*Math.tan(tetaIni)+YTir;
                        
                        if( YMouse1-5<y && y<5+YMouse1) break;//pour ce teta on peut atteindre le point fixé
                        
                        if(((XMouse1-castle.L)>=0)) tetaIni+=0.00001; //incrément du teta // si la souris est a droite du chateau increment par teta croissant
                        else  tetaIni-=0.00001; 
                    }
                }
                
            }else{
                VIni=110;
                tetaIni=0.5*Math.PI;
                
                boolean trouve=false;
                
                while(!trouve || tetaIni>6){
                    double y=0.5*7*(XMouse1-XTir)*(XMouse1-XTir)/(VIni*VIni*Math.cos(tetaIni)*Math.cos(tetaIni))+(XMouse1-XTir)*Math.tan(tetaIni)+YTir;
                    if(y<3+YMouse1 && y>YMouse1-3) break;
                    else tetaIni-=0.001;
                }
            }
            
            if(arme=="Pierre") monProj=new Pierre(tetaIni,VIni,(int)(L/50),XTir,YTir);
            else if(arme=="Fleche") monProj=new Fleche(tetaIni,VIni,(int)(L/25),XTir,YTir);
            else monProj=new Fleche(tetaIni,VIni,(int)(L/25),XTir,YTir);
            
            castle.listArmes.add(monProj);//ajout du nouveau projectile 
            shootReady=false;
            temps1=temps+monProj.reloadTime;
            reloadTime=monProj.reloadTime;
        }
    }
    

	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}	
	
    public void mouseMoved(MouseEvent e){}
	
    
}


