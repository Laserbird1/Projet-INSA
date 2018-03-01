import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Gobelin extends Monstre{
    
    Gobelin(int centrex, int centrey){
        super();
        this.vie=2;
        this.vitesse=10;
        this.degats=1;
        this.centrex=centrex;
        this.centrey=centrey;
        this.couleur=Color.black;
        
    }
    
    
}
