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
    float g;
    int score;
    boolean vagueBoss;
    
    Image imgTerrain;//toutes les images utilisées, 
    Image imgGobelin;//on les charge ici pour éviter d'avoir a charger
    Image imgPierre;//autant d'images que d'objets créés au 
    Image imgFleche;//cours de la partie
    Image imgChateau;
    Image imgGobelinVolant;
    Image imgBoss;
    Image imgPierreBig;
    
    int R;
    int l;
    int L_monstre;
    int H_monstre;//paramètres sur les objets mouvants (servent surtout ici a retailler les images)
    
    int XMouse0;       //pos souris quand front montant du clic souris
    int YMouse0;        
    int XMouse1;       //pos souris a instant t s'il y a un clic enclenché de la souris
    int YMouse1;
    boolean shooting;//est on en train de tirer
    long temps1;//utile pour le temps de reload d'une arme
    boolean shootReady;
    int reloadTime;
    boolean angleShootLow;
    int tpsApp;//ms
    
    String arme;
    String shootingMode;
    
    boolean playing;//pour faire pause
    JLabel labelPause;
    JButton bPause;
    JButton bHighAngle;
    JButton bLowAngle;
    JLabel labelScore;
    
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
        angleShootLow=true;
        reloadTime=400;//arbitraire, sera changé au premier shoot
        g=2;
        tpsApp=3000;
        score=0;

        
		this.setLayout(null);
		this.setSize(L,H);
		
        //initialisation param terrain
		H_SOL=(int)(H/6);
		H_TERRAIN=H-H_SOL;
        this.L=L;
        this.H=H;
        
        //initialisation param des objets
        castle= new Chateau(L,H_TERRAIN,H_SOL,this);
        R=(int)(L/50);//rayon d'une pierre lancée
        l=(int)(L/25);//longueur d'une fleche
        L_monstre=(int)(L/25);
        H_monstre=(int)(H_TERRAIN/8);
        
        //chargements des images
        imgTerrain=ImageWorker.loadImage("terrain.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgTerrain,this);
        imgTerrain=ImageWorker.resizeImage(imgTerrain,L,H);
        
        imgGobelin=ImageWorker.loadImage("gobelin.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgGobelin,this);
        imgGobelin=ImageWorker.resizeImage(imgGobelin,L_monstre,H_monstre);
        
        imgChateau=ImageWorker.loadImage("chateau.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgChateau,this);
        imgChateau=ImageWorker.resizeImage(imgChateau,castle.L,castle.H);
        
        imgFleche=ImageWorker.loadImage("fleche.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgFleche,this);
        imgFleche=ImageWorker.resizeImage(imgFleche,l,l/2);
        
        imgPierre=ImageWorker.loadImage("pierre.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgPierre,this);
        imgPierre=ImageWorker.resizeImage(imgPierre,2*R,2*R);
        imgPierreBig=ImageWorker.resizeImage(imgPierre,2.5*R,2.5*R);
        
        imgGobelinVolant=ImageWorker.loadImage("gobelinVolant.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgGobelinVolant,this);
        imgGobelinVolant=ImageWorker.resizeImage(imgGobelinVolant,L_monstre,H_monstre);
        
        imgBoss=ImageWorker.loadImage("boss.png","IMAGES");
        ImageWorker.waitUtilFullyLoaded(imgBoss,this);
        imgBoss=ImageWorker.resizeImage(imgBoss,3*L_monstre,3*H_monstre);
        

		
        //initialisation des jbutton et jlabel
        bMenu=new JButton("Menu");//bouton de retour au menu
        bMenu.setBounds((int)(L*14/16),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*2/8));
        bMenu.addActionListener(this);
        add(bMenu);
        
        labelPause = new JLabel("Appuyer sur start pour jouer");
		labelPause.setBounds((int)(L/2),(int)((H_TERRAIN+H_SOL)/2),(int)(L*8/16),(int)(H_SOL));
		Font parametre = new Font("Arial",Font.BOLD,75);
		labelPause.setFont(parametre);
		add(labelPause);
        
        bPause=new JButton("Start");//bouton start/pause
        bPause.setBounds((int)(L*13/16),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*1/4));
        bPause.addActionListener(this);
        add(bPause);
        
        labelArme = new JLabel(arme);
		labelArme.setBounds((int)(L*12/16),(int)(H_TERRAIN+(H_SOL/2)),(int)(L*8/16),(int)(H_SOL/4));
        Font parametre2 = new Font("Arial",Font.BOLD,30);
		labelArme.setFont(parametre2);
		add(labelArme);
        
        bArme=new JButton("Changer arme");//bouton de retour au menu
        bArme.setBounds((int)(L*12/16),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/16),(int)(H_SOL*1/4));
        bArme.addActionListener(this);
        add(bArme);
        
        bHighAngle=new JButton("H");//
        bHighAngle.setBounds((int)(L*10/16),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/30),(int)(L*1/30));
        bHighAngle.addActionListener(this);
        add(bHighAngle);
        
        bLowAngle=new JButton("L");//
        bLowAngle.setBounds((int)(L*10/16-L*1/30),(int)(H_TERRAIN+H_SOL/4),(int)(L*1/30),(int)(L*1/30));
        bLowAngle.addActionListener(this);
        bLowAngle.setBackground(Color.green);
        add(bLowAngle);
        
        labelScore= new JLabel(Integer.toString(score));
		labelScore.setBounds((int)(L*1/16),(int)(H_TERRAIN/15),(int)(L*8/16),(int)(H_SOL/4));
		labelScore.setFont(parametre2);
		add(labelScore);
        
        if(shootingMode=="pulling"){
            bHighAngle.setVisible(false);
            bLowAngle.setVisible(false);
        }
		
        //initialisation des events
        addMouseListener(this);
        addMouseMotionListener(this);
        t=new Timer(40,this);
		
		this.setVisible(true);
        
        castle.listEnemis.add(new GobelinVolant(L-10,H_TERRAIN*2.0/8.0,L_monstre,H_monstre,L/500));
		
		
	}
    
    public void reset(){//pour recommencer le jeu
        castle.vie=castle.VIE_MAX;
        castle.listEnemis.clear();
        castle.listArmes.clear();
        pause();
        labelPause.setText("Appuyer sur start pour jouer");
        score=0;
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
            else if(arme=="Fleche")arme="Pierre Frag";
            else if(arme=="Pierre Frag")arme="Pierre";
            else arme="Fleche";
            labelArme.setText(arme);
        }
        
        if(e.getSource()==bHighAngle){
            bHighAngle.setBackground(Color.green);
            bLowAngle.setBackground(Color.white);
            angleShootLow=false;
        }
        
        if(e.getSource()==bLowAngle){
            bHighAngle.setBackground(Color.white);
            bLowAngle.setBackground(Color.green);
            angleShootLow=true;
        }
        
        if(e.getSource()==t){
            
            temps+=40;//temps defile
            castle.move();//tout bouge
            
            if(temps%tpsApp==0){//toutes les tpsApp sec
				
				tpsApp=(int)((6000/40.0)+(Math.random()*(3000/40.0)))*40;//le monstre suivant apparait dans entre 6 et 9 sec
				
                Monstre newMonstre;
				newMonstre = new Gobelin(L-10,H_TERRAIN*7.0/8.0,L_monstre,H_monstre,L/500.0,false);
                castle.listEnemis.add(newMonstre);//creer un monstre
            }
            
            if(Math.random()<0.01) 
				castle.listEnemis.add(new GobelinVolant(L-10,H_TERRAIN*2.0/8.0,L_monstre,H_monstre,L/500.0));
                
            if(!vagueBoss && score%15==0){
                    for(int k=1;k<=score/15;k++)
                    castle.listEnemis.add(new Gobelin((int)(L-10+(k-1)*3*L_monstre),H_TERRAIN*7.0/8.0,L_monstre,H_monstre,L/500.0,true));//ajout de boss en fonction du score pour rendre le jeu plus difficile
                    vagueBoss=true;//la vague de boss est en cours
                }
            
            if(score%15==1){
                vagueBoss=false;//la vague de boss est passée, on attend la suivante
            }

            
            castle.collisions();//tester les collisions
            if(castle.vie<=0){ 
                pause();
                Frame.setContentPane(Frame.panelDefaite);//si on a plus de vie, afficher le panel defaite
            }
            
            repaint();
        }
        
        if(!shootReady && temps>temps1) shootReady=true;
        //si on a passé le temps de reload et qu'il était pas pret a tirer alors on est re-pret a tirer
    }
    
    public void paintComponent(Graphics g){
        
        g.drawImage(imgTerrain,0,0,this);

        castle.dessinTerCha(g);//dessin terrain, monstre chateau projectiles...
        
        
        Graphics2D g2 = (Graphics2D) g;//dessin de la ligne pour le tir, aide au tir
        if(shooting && shootingMode=="pulling"){

            g2.setColor(new Color(204,0,102));
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Float(XMouse0,YMouse0,XMouse1,YMouse1));
            
        }
        
        //dessin barre de rechargement du tir
        Color  couleur=new Color(30, 144, 255);//bleu
        int lBarre=(int)(L*2/18); //longeur barre (2/3 de la longueur du chateau)
        int X=(int)(L/24); //debut barre (coord x)
        int Y=(int)(H_TERRAIN+H_SOL*4/10);//(coord y)
        float var=(temps-temps1+reloadTime);
        int h=(int)(H_SOL/5);//hauteur barre
       
        dessinBarre(g,lBarre,h,X,Y,var,reloadTime,couleur);


        
    }
    
    public void dessinBarre (Graphics g,int lBarre, int hBarre, int X, int Y, float var, float ref, Color couleur){//pour dessiner barres de vie etc..
        
        int X2=(int)(X+(lBarre*var/ref)); //coord x séparation couleur/noir
        int X3=lBarre+X;//coord x en pixel de la fin de la barre
        
        if(X2>=X && X2<=X3){
            
            g.setColor(couleur);//couleur
            g.fillRect(X,Y,X2-X,hBarre);//barre de la couleur choisie
            
            g.setColor(Color.black);//noir (reste de la barre)
            g.fillRect(X2,Y,X3-X2,hBarre);
            
        }else if(var<0){
            
            g.setColor(Color.black);//noir (reste de la barre)
            g.fillRect(X,Y,lBarre,hBarre);
            
        }else{
            
            g.setColor(couleur);
            g.fillRect(X,Y,lBarre,hBarre);
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
            int XTir=(int)(castle.L*0.7);
            int YTir=(int)(castle.H_TERRAIN-castle.H*0.95);//coord (x,y) du point de tir du projectile
            
            double [] paramIni;//[0] -> Vitesse initiale / [1] ->angle de tir initial
            
            if(shootingMode=="pulling"){
                //segment = vecteur avec x= Xmouse1-XMouse0 et y = YMouse1-YMouse0
                //assimilation du segment a un vecteur cartesien (x,y) que l'on exprime en coord polaire (r,teta)
                int x=XMouse1-XMouse0;
                int y=YMouse1-YMouse0;
                
                paramIni = Projectile.shootingPulling(x,y);
                
            }else if(shootingMode=="pointing"){//pointer un endroit ou le projectile passera
                paramIni = Projectile.shootingPointing(XMouse1,YMouse1,XTir,YTir,angleShootLow,g);
                
            }else{
                paramIni = Projectile.shootingPointing(XMouse1,XMouse1,XTir,YTir,angleShootLow,g);
            }
            
            if(arme=="Pierre") monProj=new Pierre(paramIni[1],paramIni[0],R,XTir,YTir,g);//pierre simple
            else if(arme=="Fleche") monProj=new Fleche(paramIni[1],paramIni[0],l,XTir,YTir,g);//fleche
            else if(arme=="Pierre Frag") monProj=new PierreFrag(paramIni[1],paramIni[0],(int)(R*3/2.0),XTir,YTir,g);//pierre frag
            else monProj=new Fleche(paramIni[1],paramIni[0],l,XTir,YTir,g);//si pb alors fleche
            
            castle.listArmes.add(monProj);//ajout du nouveau projectile 
            shootReady=false;
            reloadTime=monProj.reloadTime;
            temps1=temps+reloadTime;
            
        }
    }
    

	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}	
	
    public void mouseMoved(MouseEvent e){}
	

}

