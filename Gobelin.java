import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Gobelin extends Monstre{
    
    Gobelin(double centrex, double centrey,int L,int H,double vitesse){
        super(L,H);
        
        this.VIE_MAX=4;
        this.degats=3;
        
        this.vie=VIE_MAX;
        this.vitesse=vitesse;
        this.centrex=centrex;
        this.centrey=centrey;
        this.couleur=Color.black;
        
    }
    
    
    
    
}

