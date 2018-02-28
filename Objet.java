import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Random;

public abstract class Objet { //Tous les objets de la classe découle de cette classe (sauf splash)
    // Attributs
    protected int x; //Sa poisition en x
    protected int y; //Sa position en y
    protected int x0; //Sa position de départ en x
    protected int y0; //Sa position de départ en y
    protected int l; //La largeur de son image
    protected int h; //La hauteur de son image
    protected BufferedImage image; //L'image de l'objet qui lui est associée
    protected double v; //La vitesse de l'objet en 
    protected double a; //L'angle a avec lequel par l'objet
    protected double t0; //Le temps to auquel l'objet est appelé
    protected BufferedImage splash;
    protected int largeurecran;
    protected int hauteurecran;
    protected Jeu j;
   
    // Constructeur de l'objet par défaut
    public Objet(String nomFichierImage, Jeu jeu) {
		this.j=jeu;

        try {
             image= ImageIO.read(new File(nomFichierImage));
        } catch(Exception err) {
            System.out.println(nomFichierImage+" introuvable !");            
            System.exit(0);
        }
        switch(nomFichierImage){
					case "Fruit1.png" : try {
								splash= ImageIO.read(new File("splash1.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					case "Fruit2.png" : try {
								splash= ImageIO.read(new File("splash2.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					case "Fruit3.png" : try {
								splash= ImageIO.read(new File("splash3.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					case "Fruit4.png" : try {
								splash= ImageIO.read(new File("splash4.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					case "Fruit5.png" : try {
								splash= ImageIO.read(new File("splash5.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					case "Fruit6.png" : try {
								splash= ImageIO.read(new File("splash6.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					default : try {
								splash= ImageIO.read(new File("splash1.png"));
							} catch(Exception err) {
								System.out.println(nomFichierImage+" introuvable !");            
								System.exit(0);
							}
						break;
					}      
       	Random e = new Random();
		x0 =(jeu.LARGEUR_FENETRE/6)+e.nextInt(jeu.LARGEUR_FENETRE/2); //On initilialise les coordonnées et les attributs
		y0 = jeu.HAUTEUR_FENETRE;
		v = 500; 
		l = image.getWidth(null); //On récupère la taille de l'image
		h = image.getHeight(null);
		this.setAngle(jeu.LARGEUR_FENETRE); //On initialise a avec la méthode
		this.t0=jeu.temps/1000;
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurecran = (int)dimension.getHeight(); //On récupère la taille de l'écran
		largeurecran  = (int)dimension.getWidth();

    }
    
    public void dessine(Graphics g, JPanel jp) {
        g.drawImage(image,x,y,jp);
    }
    
    public boolean collision(Lame lame,double lamex,double lamey) { //Méthode pour tester la collision
		boolean res = false;
       Rectangle boxO1 = new Rectangle(x,y,l,h); //ON crée une hitbox
       int posfenx=(int)(lamex-(largeurecran/2)+(j.maFenetre.LARGEUR_FENETRE/2))-3; //Nous nous sommes rendus compte que les coordonnées de la souris étaient
       int posfeny=(int)(lamey-(hauteurecran/2)+(j.maFenetre.HAUTEUR_FENETRE/2))-7; //celles de l'écran alors que les coordoonées de la hitbox sont celles 
       Rectangle boxO2 = new Rectangle(posfenx,posfeny,2,2);   						//par rapport au JPanel, sachant qu'il apparait au milieu de l'écran,   
       res= boxO1.intersects(boxO2);   												//je modifie les coordonnées en fonction    
       if(res==true)//Test de contact
		System.out.println("touche"); 
       return res;
    }
    
     public boolean move ( Jeu j) { //Méthode move des objets, avec la partie physique
		boolean res = true;         //sur les équations de mouvement
		double t1=j.temps/1000;
		x= x0+(int)(v*Math.cos(a)*(t1-t0));
		y= (int) (0.5*j.gravite*(t1-t0)*(t1-t0)-v*Math.sin(a)*(t1-t0)+y0);
		if(x>j.LARGEUR_FENETRE || x+l<0 || j.HAUTEUR_FENETRE<=y || y+h<0){
			res = false;
			System.out.println("sortie");
		}
		return res;
    }
    
    //On va devoir faire attention à l'angle a suivant la partie de l'écran où se trouve l'objet
    public void setAngle(int largeurFenetre){ //Méthode pour attribuer un angle en fonction de sa position en x
		double degres;		
		double radians;
		Random r = new Random();
		if (this.x <(largeurFenetre/2)){
			degres = 90 - r.nextInt(100 - 87);
			radians = Math.toRadians(degres);
		}
		else{
			degres = 70+ r.nextInt(90 - 77);
			radians = Math.toRadians(degres);
		}
				
		this.a= radians;
	}

}
	
