import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public abstract class Projectile {
    
    int vitessex;           //vitesse sur chaque axe
    int vitessey;
    
    int centrex;            //centre de gravité, référentiel à partir duquel est créé l'image du projectile, permet le déplacement facile du projectile
    int centrey;            
    
    BufferedImage image;
    
    int g;                  //gravité, paramètre de mouvement pour la degression de la vitesse verticale
    
    int degats;             //dégats infligés aux monstres
    int reloadTime;         //temps avant de pouvoir tirer à nouveau en ms
    
    Projectile(){
        g=10;
    }
    
    public void move(){
        centrex+=vitessex;      
        vitessey+=g;            
        centrey+=vitessey;      
    }
    
    public abstract boolean collisionMonstre(Monstre monster);
    public abstract boolean collisionTerrain(Chateau castle);
    
    
}
