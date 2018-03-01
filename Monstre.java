import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public abstract class Monstre{
    int vie;        //points de vie
    int vitesse;    //vitesse de déplacement
    int degats;     //degats infligés lors de la rencontre avec le chateau
    
    int centrex;    //coord du centre de gravité, référentiel a partir duquel est 
    int centrey;    //créé l'image du monstre, permet le déplacement facile du monstre
                    //Est en réalité le coin supérieur droit du monstre
    
    int L;          //largeur de la hitbox
    int H;          //hauteur de la hitbox
    
    BufferedImage image ; //image associee au monstre
    Color couleur;
    
    Monstre(){
        L=50;//en pixel
        H=70;//en pixel
    }
    
    public void move(){
        this.centrex+=vitesse;///ou -= selon orientation du chateau
    }
    
    public boolean collisionChateau(Chateau castle){
        boolean res=false;
        
        if(centrex<=castle.L)
        res=true;
        
        return res;
    }
    
    public void dessin(Graphics g){
        g.setColor(this.couleur);
        g.fillRect(centrex, centrey,L,H);
    }
}
