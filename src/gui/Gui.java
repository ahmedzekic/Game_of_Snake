package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static console.Mapa.getSirina;
import static console.Mapa.getVisina;
import static logika.Jabuka.getJabukax;
import static logika.Jabuka.getJabukay;
import static logika.Zmija.*;

/**
 * Sadrzi varijable i metode za iscrtavanje igrice na ekranu
 * Nasljedjuje JPanel i implementira KeyListner i ActionListener
 */

public class Gui extends JPanel implements KeyListener, ActionListener {

    private Timer timer; //usporavanje
    private int delay=90; //igrica
    private boolean pocetak = false;
    private boolean gore = false;
    private boolean dole = false;
    private boolean lijevo = false;
    private boolean desno = false;
    private Font fontravie = new Font("Ravie", Font.BOLD, 18);
    private Font fontdi = new Font("DialogInput", Font.BOLD, 18);

    /**
     *Inicijalizuje objekt tipa Gui, stavlja KeyListener na njega i pokrece timer
     */
    public Gui(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }

    /**
     * Iscratava mapu, zmiju i jabuku
     * @param g graphics
     */

    public void paint(Graphics g){
        if(!isKrajIgre()) { //crtanje ciste mape bez zmije i jabuke
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GRAY);
            g.fillRect(10, 10, getSirina() * 10, getVisina() * 10);
            g.setColor(Color.BLACK);
            g.drawRect(10, 10, getSirina() * 10, getVisina() * 10);
        }
        if(pocetak == false){ //postavljanje zmije na sredinu ako je pocetak
            Pocetak();
            pocetak = true;
        }
        if(pocetak == true && isKrajIgre() == false) {
            for (int i = 1; i < getVisina()+1; i++)
                for (int j = 1; j < getSirina()+1; j++) {
                    if (i == getJabukay() && j == getJabukax()) { //crtanje jabuke
                        g.setColor(Color.RED);
                        g.fillOval(j * 10, i * 10, 10, 10);
                    } else if (i == getRepy()[0] && j == getRepx()[0]) { //crtanje glave
                        g.setColor(new Color(0,102,0));
                        g.fillRect(j * 10, i * 10, 10, 10);
                        g.setColor(Color.BLACK);
                        g.drawRect(j * 10, i * 10, 10, 10);
                    } else
                        for (int k = 0; k < getDuzinaRepa()+1; k++) { //crtanje repa
                            if (i == getRepy()[k] && j == getRepx()[k]) {
                                g.setColor(Color.GREEN);
                                g.fillRect(j * 10, i * 10, 10, 10);
                                g.setColor(Color.BLACK);
                                g.drawRect(j * 10, i * 10, 10, 10);
                            }
                        }
                }
        }

        if(isKrajIgre()){ //Isipis "KRAJ"
            g.setFont(fontdi);
            g.setColor(Color.BLACK);
            g.drawString("KRAJ", getWidth() - 50, getHeight() - 20);
        }

        g.setFont(fontravie);
        g.setColor(Color.BLACK);
        g.drawString("REZULTAT: " + getDuzinaRepa(), 10, getHeight() - 20); //ispis rezultata
        g.dispose();
        }

    /**
     * Upravalja svim dogadjajima
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            if (isKrajIgre()) {
                gore = false;
                dole = false;
                lijevo = false;
                desno = false;
                // Iscrtavanje prozorcica koji pita za novu igru
                Object[] options = {"DA", "NE"};
                int answer = JOptionPane.showOptionDialog(this, "Da li zelite ponovo igrati?","NOVA IGRA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (answer == JOptionPane.YES_OPTION) {
                    Pocetak();
                    repaint();
                } else if (answer == JOptionPane.NO_OPTION)
                    System.exit(0);
            }
            // Pomjeranje zmije u odnosu na smjer
            if (gore == true) {
                IgraGUI("GORE");
                repaint();
            } else if (dole == true) {
                IgraGUI("DOLE");
                repaint();
            } else if (lijevo == true) {
                IgraGUI("LIJEVO");
                repaint();
            } else if (desno == true) {
                IgraGUI("DESNO");
                repaint();
            }

    }

    /**
     * Nema funkcionalnosti
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Listener za pritiskanje tipke sa tastature
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        pocetak  = true;
        //postavljanje pomocnih varijabli koje se koriste u actionPerformed() u odnosu na pritisnutu tipku
        //igricu je moguce igrati pomocu strelica ili wasd
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            gore = true;
            dole = false;
            lijevo = false;
            desno = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            gore = false;
            dole = true;
            lijevo = false;
            desno = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            gore = false;
            dole = false;
            lijevo = true;
            desno = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            gore = false;
            dole = false;
            lijevo = false;
            desno = true;
        }
    }

    /**
     * Nema funkcionalnosti
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}

