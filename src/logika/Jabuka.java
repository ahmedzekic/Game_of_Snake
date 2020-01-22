package logika;

import static console.Mapa.getSirina;
import static console.Mapa.getVisina;
import static logika.Zmija.*;

/**
 * Cuva informacije o jabuci
 */

public class Jabuka {
    private static int  Jabukax; //x koordinata jabuke
    private static int Jabukay;  //y koordinata jabuke

    /**
     * @return x koordinatu jabuke
     */

    public static int getJabukax() {
        return Jabukax;
    }

    /**
     * @return y koordinatu jabuke
     */

    public static int getJabukay() {
        return Jabukay;
    }

    /**
     * Metoda koja postavlja jabuku na nasumicno mjesto
     */

    public static void RandomGenerisanje(){
        // postavljanje jabuke u okviru granica mape
        Jabukax = (int) (Math.random() * (getSirina()-2) + 1);
        Jabukay = (int) (Math.random() * (getVisina()-2) + 1);

        // provjerava da li je jabuka postavljena u tijelo zmije
        // ako jeste ponovo se postavlja jabuka te se petlja vraca na pocetak
        for(int i=0; i < getDuzinaRepa(); i++)
            if((Jabukax == getRepx()[i] && Jabukay == getRepy()[i])) {
                i = 0;
                Jabukax = (int) (Math.random() * (getSirina()-2) + 1);
                Jabukay = (int) (Math.random() * (getVisina()-2) + 1);
            }
    }
}
