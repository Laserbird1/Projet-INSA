import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public abstract class Monstre{
    int vie;        //points de vie
    int VIE_MAX;
    double vitesse;    //vitesse de déplacement
    int degats;     //degats infligés lors de la rencontre avec le chateau
    
    double centrex;    //coord du centre de gravité, référentiel a partir duquel est 
    double centrey;    //créé l'image du monstre, permet le déplacement facile du monstre
                    //Est en réalité le coin supérieur droit du monstre
    
    int L;          //dimensions hitbox
    int H;          //Largeur,Hauteur
    
    Color couleur;///tant que pas de photos
    
   /// BufferedImage image ; //image associee au monstre
    
    Monstre(int L,int H){
        this.L=L;//en pixel
        this.H=H;//en pixel
    }
    
    public void move(){
        this.centrex-=vitesse;
    }
    
    public boolean collisionChateau(Chateau castle){
        boolean res=false;
        
        if(centrex<=castle.L)
        res=true;
        
        return res;
    }
    
    public void dessin(Graphics g){
        
        g.setColor(this.couleur);
        g.fillRect((int)(centrex), (int)(centrey),L,H);
        
        //barre de vie
        int lBarre=L; //longeur barre (longueur du monstre)
        int l1=(int)(centrex);              //debut barre (coord x)
        int y1=(int)(centrey-30);           //(coord y)
        int l2=(int)(l1+(lBarre*(vie)/VIE_MAX)); //coord x séparation vert/noir
        int l3=lBarre+l1;//coord x en pixel de la fin de la barre
        int h=(int)(H/6);//hauteur barre
        
        g.setColor(new Color(46, 139, 87));//vert océan (vie restante)
        g.fillRect(l1,y1,l2-l1,h);
        
        g.setColor(Color.black);//noir (vie perdue)
        g.fillRect(l2,y1,l3-l2,h);
    }
}

