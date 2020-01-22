package console;

import java.util.concurrent.TimeUnit;

import static logika.Jabuka.getJabukax;
import static logika.Jabuka.getJabukay;
import static logika.Zmija.*;


/**
 * Crta mapu, zmiju i jabuku u konzolu
 */
public class Mapa {
    private static final int visina = 40;  //dimenzije mape
    private static final int sirina = 40;

    /**
     *
     * @return visinu mape
     */
    public static int getVisina() {
        return visina;
    }

    /**
     *
     * @return sirinu mape
     */
    public static int getSirina() {
        return sirina;
    }

    /**
     *
     * Ispisuje mapu, zmiju, jabuku i trenutni rezultat u konzolu
     */
    static void Crtanje() {
        for(int i=0; i<visina; i++) {
            for (int j = 0; j < sirina; j++) {
                if (i == 0 || i == visina - 1 || j == 0 || j == sirina - 1)
                    System.out.print("#");  // granice mape
                else if (i==getRepy()[0] && j==getRepx()[0])
                    System.out.print("0");  // glava zmije
                else if (i==getJabukay() && j==getJabukax())
                    System.out.print("x");  //jabuka
                else {  // ispis tijela zmije i praznog prostora
                    boolean pom = false;
                    for(int k=0;k < getDuzinaRepa()+1;k++)
                        if(i== getRepy()[k] && j== getRepx()[k]){
                        System.out.print("o"); // tijelo zmije
                        pom = true;
                        }
                    if(pom == false)
                    System.out.print(" ");
                }
            }
            System.out.println();

        }
        try {  //usporavanje ispisa
            TimeUnit.MILLISECONDS.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Rezultat: " + getDuzinaRepa());
        System.out.println();
    }

    /**
     * Pokrece igricu i iscrtava zmiju
     * @param args
     */
    public static void main(String[] args){

        while(!isKrajIgre()){
            IgraUI();
            Crtanje();
        }
    }
}