import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Fleche extends Projectile{
    
    float l;     //longueur de la fleche
    double teta; //orientation de la flèche
    
    int centre2x;
    int centre2y;//deuxième centre de la flèche (=point centre(t-1)) pour definir teta
    
    //centrex et centrey definissent la pointe de la fleche, centre2 le point qu'ocuppait centre a l'instant (t-1)

    Fleche(double tetaIni,double vitesseIni,int l,int centrex,int centrey){ 
        super();
        this.vitessex=(int)(vitesseIni*Math.cos(tetaIni));
        this.vitessey=(int)(vitesseIni*Math.sin(tetaIni));  //assignation des valeurs de vitesses initiales
        
        this.centrex=centrex; //positionnement initial
        this.centrey=centrey;
        
        this.reloadTime=400;
        this.degats=2;
        
        this.l=l;
        ///image
    }
    
    public void move(){
        centre2x=centrex;
        centre2y=centrey;
        
        centrex+=vitessex;      
        vitessey+=g;            
        centrey+=vitessey;
        
        teta = Math.PI+Math.atan2((centre2y-centrey),(centre2x-centrex));
        
    }
    
    public void dessin(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(222, 184, 135));//brun bois
        g2.setStroke(new BasicStroke(10));
        g2.draw(new Line2D.Float(centrex,centrey,(float)(centrex+l*Math.cos(teta)),(float)(centrey+l*Math.sin(teta))));
        
    }
    
    public boolean collisionMonstre(Monstre monster){
        
        Line2D.Double box1 = new Line2D.Double(centrex,centrey,centrex+l*Math.cos(teta),centrey+l*Math.sin(teta));    //On crée une hitbox pour le projectile
        Rectangle box2 = new Rectangle((int)(monster.centrex),(int)(monster.centrey),monster.L,monster.H); //on crée une hitbox pour le monstre
        return box1.intersects(box2);                                                        //sont-ils en collision ?
        
    }
    
    public boolean collisionTerrain(Chateau castle){
        boolean res=false;
        
        if(centrey>castle.H_TERRAIN){//si le bout de la fleche est trop bas (planté dans le sol)
            res=true;
        }
        
        return res;
    }
    
    
}
