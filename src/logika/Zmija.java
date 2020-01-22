package logika;


import static console.Mapa.getSirina;
import static console.Mapa.getVisina;
import static logika.Jabuka.*;

/**
 * Cuva informacije o zmiji i sadrzi kljucne metode za logiku igrice
 */
public class Zmija {
    private static int[] repx;  // niz koji cuva x koordinate repa zmije
    private static int[] repy;  // niz koji cuva y koordinate repa zmije
    private static boolean postavka = false;
    private static boolean krajIgre = false;
    private static int duzinaRepa;
    private static boolean gore = false;  // varijable koje
    private static boolean dole = false;  // pokazuju u
    private static boolean lijevo = false; // kojem se pravcu
    private static boolean desno = false;  // zmija trenutno krece

    /**
     *
     * @return da li je igra zavrsena
     */

    public static boolean isKrajIgre() {
        return krajIgre;
    }

    /**
     *
     * @return niz x koordinata repa
     */

    public static int[] getRepx() {
        return repx;
    }

    /**
     *
     * @return niz y koordinata repa
     */
    public static int[] getRepy() {
        return repy;
    }

    /**
     *
     * @return duzinu repa
     */
    public static int getDuzinaRepa() {
        return duzinaRepa;
    }

    /**.
     * Postavlja glavu zmije na sredinu i generise jabuku na random mjesto
     */

    public static void Pocetak(){
        krajIgre = false;
        repx = new int[getVisina()*getSirina()];
        repy = new int[getVisina()*getSirina()];
        repx[0] = getSirina()/2;
        repy[0] = getVisina()/2;
        Jabuka.RandomGenerisanje();
        duzinaRepa = 0;
    }

    /**
     * Metoda pomocu koje ce rep prati pomjerenu glavu u funkciji {@link #KretanjeGUI(String)} ili {@link #KretanjeUI()}
     */

    private static void RepPratiGlavu(){
        int prethodnix = repx[0];
        int prethodniy = repy[0];
        int prethodnix2;
        int prethodniy2;

        for (int i = 1; i < duzinaRepa + 1; i++) {
            prethodnix2 = repx[i];
            prethodniy2 = repy[i];
            repx[i] = prethodnix;
            repy[i] = prethodniy;
            prethodnix = prethodnix2;
            prethodniy = prethodniy2;
        }
    }

    /**
     * Metoda koja provjerava da li je zmija pojela jabuku.
     * Ako jeste povecava duzinu repa i poziva {@link Jabuka#RandomGenerisanje()} }
     */

    private static void DaLiJePojela(){
        if (repx[0] == getJabukax() && repy[0] == getJabukay()) {
            duzinaRepa++;
            Jabuka.RandomGenerisanje();
        }
    }

    /**
     * Metoda koja provjerava da li je kraj igrice
     */

    private static void DaLiJeKraj(){
        if(repy[0]==getVisina()+1 || repy[0]==0 || repx[0]==getSirina()+1 || repx[0] == 0)
            krajIgre=true;

        for(int i=1; i < duzinaRepa; i++)
            if(repx[0] == repx[i] && repy[0] == repy[i])
                krajIgre = true;
    }

    /**
     * Metoda koja implementira umjetnu inteligenciju zmije pomocu koje se ona krece.
     * Pomjera se samo glava zmije.
     */
    private static void KretanjeUI(){
        boolean zabranaLijevo = false;  //varijable koje
        boolean zabranaGore = false;    //pokazuju smije li
        boolean zabranaDesno = false;   //zmija skrenuti
        boolean zabranaDole = false;    //u neku stranu

        //for petlja koja provjerava da li ce u slucaju skretanja u jednu od strana zmija udariti u sebe ili u zid
        for (int i = 0; i < duzinaRepa; i++) {
            if ((repx[0] - 1 == repx[i] && repy[0] == repy[i]) || repx[0] - 1 == 0)
                zabranaLijevo = true;
            if ((repx[0] == repx[i] && repy[0] - 1 == repy[i]) || repy[0] - 1 == 0)
                zabranaGore = true;
            if ((repx[0] + 1 == repx[i] && repy[0] == repy[i]) || repx[0] + 1 == (getSirina() - 1))
                zabranaDesno = true;
            if ((repx[0] == repx[i] && repy[0] + 1 == repy[i]) || repy[0] + 1 == (getVisina() - 1))
                zabranaDole = true;
        }

        // uslovi pomocu kojih se zmija priblizava jabuci(krecuci se u smijeru koji vodi prema njjo)
        // osim ako nije vec ustanovljeno da se ne smije kretati u tom smjeru
        //ako se ne moze nikako pribliziti jabuci, pokusat ce skrenuti lijevo pa dole pa desno i na kraju gore
        if (repx[0] > getJabukax() && !zabranaLijevo)
            repx[0]--; //lijevo
        else if (repy[0] > getJabukay() && !zabranaGore)
            repy[0]--; //gore
        else if (repx[0] < getJabukax() && !zabranaDesno)
            repx[0]++; //desno
        else if (repy[0] < getJabukay() && !zabranaDole)
            repy[0]++; //dole
        else { // ne moze se pribliziti jabuci
            if (!zabranaLijevo)
                repx[0]--;
            else if (!zabranaDole)
                repy[0]++;
            else if (!zabranaDesno)
                repx[0]++;
            else if (!zabranaGore)
                repy[0]--;
        }
    }

    /**
     * Metoda koja krece zmiju u neku od cetiri strane u zavisnosit od parametra.
     * Pomjera se samo glava zmije.
     * @param strana u koju stranu se zmija treba kretati
     */

    private static void KretanjeGUI(String strana){
        //skrece gore osim ako nije prethodno kretanje bilo prema dole
        // u suprotnom nastavlja sa kretanjem dole
        if(strana.equals("GORE")) {
            if (!dole) {
                repy[0]--;
                gore = true;
                dole = false;
                lijevo = false;
                desno = false;
            } else
                repy[0]++;
        }
        //skrece dole osim ako nije prethodno kretanje bilo prema gore
        // u suprotnom nastavlja sa kretanjem gore
        else if(strana.equals("DOLE")) {
            if (!gore) {
                repy[0]++;
                gore = false;
                dole = true;
                lijevo = false;
                desno = false;
            } else
                repy[0]--;
        }
        //skrece lijevo osim ako nije prethodno kretanje bilo prema desno
        // u suprotnom nastavlja sa kretanjem desno
        else if(strana.equals("LIJEVO")) {
            if (!desno) {
                repx[0]--;
                gore = false;
                dole = false;
                lijevo = true;
                desno = false;
            } else
                repx[0]++;
        }
        //skrece desno osim ako nije prethodno kretanje bilo prema lijevo
        // u suprotnom nastavlja sa kretanjem lijevo
        else if(strana.equals("DESNO")) {
            if (!lijevo) {
                repx[0]++;
                gore = false;
                dole = false;
                lijevo = false;
                desno = true;
            } else
                repx[0]--;
        }
    }

    /**
     * Metoda koja pokrece i vrti igricu.
     *
     */

    public static void IgraUI(){
        if(!postavka) {
            Pocetak();
            postavka = true;
        }
        else {
            RepPratiGlavu();
            KretanjeUI();
            DaLiJePojela();
            DaLiJeKraj();
        }
    }

    /**
     * Metoda koja vrti igricu.
     * @param strana u koju stranu treba zmija skrenut
     */

    public static void IgraGUI(String strana){
        RepPratiGlavu();
        KretanjeGUI(strana);
        DaLiJePojela();
        DaLiJeKraj();
    }
}

