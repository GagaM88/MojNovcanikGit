package MojNovcanik;

import java.util.ArrayList;

public class Korisnik {
    String korisnickoIme;
    String lozinka;
    ArrayList<BankovniRacun> bankovniRacun = new ArrayList<BankovniRacun>();

    public Korisnik(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }
}
