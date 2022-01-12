package MojNovcanik;

import java.util.ArrayList;
import java.util.Scanner;

public class Autentikacija {
    // Registrovani korisnici
    ArrayList<Korisnik> registrovaniKorisnici = new ArrayList<Korisnik>();

    // Registruj novog MojNovcanik.Korisnik
    public Korisnik registrujSe(Scanner scanner) {
        System.out.println("\nUneti korisnicko ime");
        String korisnickoIme = scanner.next();

        System.out.println("\nUneti lozinku");
        String lozinka = scanner.next();

        Korisnik postojeciKorisnik = korisnikPostoji(korisnickoIme);
        if (postojeciKorisnik != null) {
            System.out.println("\nMojNovcanik.Korisnik vec postoji");
            return null;
        }

        Korisnik korisnik = new Korisnik(korisnickoIme, lozinka);
        registrovaniKorisnici.add(korisnik);

        System.out.println("\nMojNovcanik.Korisnik \"" + korisnik.korisnickoIme + "\" je uspesno registrovan.");
        return korisnik;

    }

    // Loginuj MojNovcanik.Korisnik
    public Korisnik loginovanje(Scanner scanner) {
        System.out.println("\nUneti korisnicko ime");
        String korisnickoIme = scanner.next();

        System.out.println("\nUneti lozinku");
        String lozinka = scanner.next();

        Korisnik korisnik = korisnikPostoji(korisnickoIme);
        if (korisnik == null) {
            System.out.println("\nMojNovcanik.Korisnik ne postoji");
            return null;
        }

        if (!korisnik.lozinka.equals(lozinka)) {
            System.out.println("\nPogresni kredencijali");
            return null;
        }

        System.out.println("\nMojNovcanik.Korisnik \"" + korisnik.korisnickoIme + "\" uspesno loginovan.");
        return korisnik;
    }

    // Proveri da li je MojNovcanik.Korisnik vec registrovan
    public Korisnik korisnikPostoji(String korisnickoIme) {
        for (Korisnik korisnik : this.registrovaniKorisnici) {
            if (korisnik.korisnickoIme.equals(korisnickoIme)) {
                return korisnik;
            }
        }

        return null;
    }
}
