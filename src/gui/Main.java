package gui;

import javax.swing.*;
import java.awt.*;

import static console.Mapa.getSirina;
import static console.Mapa.getVisina;

// created by Ahmed Zekic on 22/3/2018

/**
 * Sadrzi main funkciju za GUI
 */

public class Main{
    /**
     * Pokrece GUI
     * @param args
     */
    public static void main(String[] args){
        JFrame okvir = new JFrame("ZMIJA");
        Gui gui = new Gui();
        // Postavljanje JFrame objekta na sredinu ekrana
        Dimension velcinaEkrana = Toolkit.getDefaultToolkit().getScreenSize();
        double sirinaEkrana = velcinaEkrana.getWidth();
        double visinaEkrana = velcinaEkrana.getHeight();
        int sirinaMape = getSirina()*10 + 40;
        int visinaMape = getVisina()*10 + 100;
        int x = (int) (sirinaEkrana - sirinaMape)/2;
        int y = (int) (visinaEkrana - visinaMape)/2;
        okvir.setBounds(x,y,sirinaMape,visinaMape);
        //
        okvir.setBackground(Color.WHITE);
        okvir.setResizable(false);
        okvir.setVisible(true);
        okvir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okvir.add(gui);
    }
}

