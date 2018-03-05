import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class GobelinVolant extends Monstre{
    
    GobelinVolant (int centrex, int centrey){
    
        super();
        this.vie=1;
        this.vitesse=20;
        this.degats=2;
        this.centrex=centrex;
        this.centrey=centrey;
        this.couleur=Color.blue;
        
    }
    
    
}
