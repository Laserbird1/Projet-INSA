import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Pierre extends Projectile{
    int R;      //rayon de la pierre, pour la hitbox

    Pierre(double tetaIni,double vitesseIni){ 
        super();
        this.vitessex=(int)(vitesseIni*Math.cos(tetaIni));
        this.vitessey=(int)(vitesseIni*Math.sin(tetaIni));  //assignation des valeurs de vitesses initiales
        
        this.centrex=100; //positionnement initial
        this.centrey=150;///assigner valeurs de la pos initiale, haut du chateau
        
        this.reloadTime=900;
        this.degats=3;
        
        this.R=50;
        ///image
    }
    
    public void dessin(Graphics g){
        g.setColor(new Color(192, 192, 192));//gris argent
        g.drawOval(centrex,centrey,2*R,2*R);
    }
    
    public boolean collisionMonstre(Monstre monster){
        
        Ellipse2D.Double box1 = new Ellipse2D.Double(centrex,centrey,2*R,2*R);               //On crée une hitbox pour le projectile
        Rectangle box2 = new Rectangle(monster.centrex,monster.centrey,monster.L,monster.H); //on crée une hitbox pour le monstre
        return box1.intersects(box2);                                                        //sont-ils en collision ?
        
    }
    
    public boolean collisionTerrain(Chateau castle){
        boolean res=false;
        
        if(centrey+2*R>castle.H_TERRAIN){
            res=true;
        }
        
        return res;
    }
    
    
}
