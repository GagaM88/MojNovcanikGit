package MojNovcanik;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BankovniRacun {
    double stanjeRacuna = 0;
    int brojRacuna;
    Korisnik vlasnikRacuna;
    ArrayList<Placanja> placanja = new ArrayList<Placanja>();

    public BankovniRacun(Korisnik vlasnikRacuna) {
        Random rand = new Random();
        // Generise random broj izmedju 1 i 10000
        this.brojRacuna = rand.nextInt(10000) + 1;
        this.vlasnikRacuna = vlasnikRacuna;
    }

    // Kreira novi bankovni racun
    public static BankovniRacun KreirajBankovniRacun(Korisnik korisnik) {
        BankovniRacun bankovniRacun = new BankovniRacun(korisnik);
        korisnik.bankovniRacun.add(bankovniRacun);

        System.out.println("\nBankovni racun " + bankovniRacun.brojRacuna + " uspesno kreiran.");
        return bankovniRacun;
    }

    // Zatvara bankovni racun
    public static void ZatvoriBankovniRacun(Korisnik korisnik, Scanner scanner) {
        System.out.println("\nUnesite broj racuna koji zelite da zatvorite");
        int brojRacuna = scanner.nextInt();
        BankovniRacun bankovniRacunZaZatvaranje = null;

        for (BankovniRacun bankovniRacun : korisnik.bankovniRacun) {
            if (bankovniRacun.brojRacuna == brojRacuna) {
                bankovniRacunZaZatvaranje = bankovniRacun;
                korisnik.bankovniRacun.remove(bankovniRacunZaZatvaranje);
                System.out.println("\nBankovni racun " + bankovniRacunZaZatvaranje.brojRacuna + " uspesno zatvoren.");
                return;
            }
        }

        if (bankovniRacunZaZatvaranje == null) {
            System.out.println("\nBankovni racun " + brojRacuna + " ne postoji.");
        }
    }

    public static BankovniRacun PronadjiBankovniRacun(int brojBankovnogRacuna, Korisnik korisnik) {
        for (BankovniRacun bankovniRacun : korisnik.bankovniRacun) {
            if (bankovniRacun.brojRacuna == brojBankovnogRacuna) {
                return bankovniRacun;
            }
        }

        return null;
    }

    // Lista placanja za sve bankovne racune
    public static void ListaPlacanja(Scanner scanner, Korisnik korisnik) {
        System.out.println("\nUnesite broj racuna da biste dobili listu placnja");
        int brojRacuna = scanner.nextInt();

        BankovniRacun bankovniRacun = BankovniRacun.PronadjiBankovniRacun(brojRacuna, korisnik);

        if (bankovniRacun == null) {
            System.out.println("\nBankovni racun " + brojRacuna + " ne postoji.");
            return;
        }

        System.out.println("\nLista placanja za bankovni racun " + bankovniRacun.brojRacuna + ":");
        for (Placanja placanja : bankovniRacun.placanja) {
            System.out.println(
                    "Opis: " + placanja.opis + " | Iznos: " + placanja.iznos + " | Tip: "
                            + placanja.tip);
        }
    }

    // Lista svih bankovnih racuna za MojNovcanik.Korisnik
    public static void ListaSvihBankovnihRacuna(Korisnik korisnik) {
        System.out.println("\nLista svih bankovnih racuna korisnika " + korisnik.korisnickoIme + ":");
        for (BankovniRacun bankovniRacun : korisnik.bankovniRacun) {
            System.out.println(" Bankovni racun " + bankovniRacun.brojRacuna + " stanje na racunu: " + bankovniRacun.stanjeRacuna);
        }
    }

    public static void PrikaziOpcijeBankovnogMenija(Scanner scanner, Korisnik korisnik) {
        // Lista transakcija za Bankovni Racun
        System.out.println(
                "\nIzaberite opciju:"
                        + "\n- 1 za Kreiranje novog Bankovnog Racuna"
                        + "\n- 2 za Zatvaranje Bankovnog Racuna"
                        + "\n- 3 za Listu svih Bankovnih Racuna"
                        + "\n- 4 za Listu svih Transakcija Bankovnog Racuna"
                        + "\n- nazad za vracanje u Glavni Meni"
                        + "\n- izlaz za izlazak iz aplikacije\n");

        String choice = scanner.next();

        switch (choice) {
            case "1":
                KreirajBankovniRacun(korisnik);
                PrikaziOpcijeBankovnogMenija(scanner, korisnik);
                break;
            case "2":
                ZatvoriBankovniRacun(korisnik, scanner);
                PrikaziOpcijeBankovnogMenija(scanner, korisnik);
                break;
            case "3":
                ListaSvihBankovnihRacuna(korisnik);
                PrikaziOpcijeBankovnogMenija(scanner, korisnik);
                break;
            case "4":
                ListaPlacanja(scanner, korisnik);
                PrikaziOpcijeBankovnogMenija(scanner, korisnik);
                break;
            case "izlaz":
                Aplikacija.izadjiIzAplikacije(scanner);
                break;
            case "nazad":
                Aplikacija.prikaziOpcijeGlavnogMenija(scanner, korisnik);
                break;
            default:
                System.out.println("Izbor nije validan\n");
                PrikaziOpcijeBankovnogMenija(scanner, korisnik);
                break;
        }
    }

}
