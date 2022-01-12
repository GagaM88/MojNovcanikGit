package MojNovcanik;

import java.util.Date;
import java.util.Scanner;

public class Placanja {
    BankovniRacun saBankovnogRacuna;
    double iznos;
    String opis;
    String tip;
    Date datum;
    String naBankovniRacun;

    public Placanja(BankovniRacun saBankovnogRacuna, double iznos, String opis, String tip,
                    String naBankovniRacun) {
        this.saBankovnogRacuna = saBankovnogRacuna;
        this.iznos = iznos;
        this.opis = opis;
        this.tip = tip;
        this.datum = new Date();
        this.naBankovniRacun = naBankovniRacun;
    }

    // Uplati novac na Bankovni Racun
    public static void UplatiNovac(Scanner scanner, Korisnik korisnik) {
        System.out.println("\nUnesite broj racuna na koji zelite da izvrsite uplatu");
        int brojRacuna = scanner.nextInt();

        BankovniRacun bankovniRacun = BankovniRacun.PronadjiBankovniRacun(brojRacuna, korisnik);

        if (bankovniRacun == null) {
            System.out.println("\nBankovni racun " + brojRacuna + " ne postoji.");
            return;
        }

        System.out.println("\nUnesite iznos za uplatu");
        double iznos = scanner.nextDouble();

        System.out.println("\nUnesite opis uplate");
        String opis = scanner.next();

        bankovniRacun.stanjeRacuna += iznos;

        System.out.println("\nUplata od " + iznos + " uspesno izvrsena.");
        System.out.println("\nNovo stanje na racunu: " + bankovniRacun.stanjeRacuna);

        Placanja placanja = new Placanja(bankovniRacun, iznos, opis, "uplata", null);
        bankovniRacun.placanja.add(placanja);
    }

    // Podigni novac sa bankovnog racuna
    public static void PodigniNovac(Scanner scanneer, Korisnik korisnik) {
        System.out.println("\nUnesite broj racuna sa kog zeilte da podignete novac");
        int brojBankovnogRacuna = scanneer.nextInt();

        BankovniRacun bankovniRacun = BankovniRacun.PronadjiBankovniRacun(brojBankovnogRacuna, korisnik);

        if (bankovniRacun == null) {
            System.out.println("\nBankovni racun " + brojBankovnogRacuna + " ne postoji.");
            return;
        }

        System.out.println("\nUnesite iznos koji zelite da podignete");
        double iznos = scanneer.nextDouble();

        if (bankovniRacun.stanjeRacuna < iznos) {
            System.out.println("\nNedovoljno sredstava na racunu.");
            return;
        }

        bankovniRacun.stanjeRacuna -= iznos;
        System.out.println("\nIsplata od " + iznos + " uspesno izvrsena.");
        System.out.println("\nNovo stanje na racunu: " + bankovniRacun.stanjeRacuna);
    }

    public static void PrikaziMeniSaOpcijamaPlacanja(Scanner scanner, Korisnik korisnik) {
        System.out.println("\nIzaberite jednu od sledecih opcija:"
                + "\n- 1 za Uplatu novca"
                + "\n- 2 za Podizanje novca"
                + "\n- nazad za vracanje u Glavni Meni"
                + "\n- izlaz za izlazak iz aplikacije\n");

        String choice = scanner.next();

        switch (choice) {
            case "1":
                UplatiNovac(scanner, korisnik);
                PrikaziMeniSaOpcijamaPlacanja(scanner, korisnik);
                break;
            case "2":
                PodigniNovac(scanner, korisnik);
                PrikaziMeniSaOpcijamaPlacanja(scanner, korisnik);
                break;
            case "nazad":
                Aplikacija.prikaziOpcijeGlavnogMenija(scanner, korisnik);
                break;
            case "izlaz":
                Aplikacija.izadjiIzAplikacije(scanner);
                break;
            default:
                System.out.println("Izbor nije validan\n");
                PrikaziMeniSaOpcijamaPlacanja(scanner, korisnik);
                break;
        }

    }

}
