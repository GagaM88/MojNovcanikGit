package MojNovcanik;

import java.util.Scanner;

public class Aplikacija {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        prikaziGlavniMeni(scanner);

        scanner.close();
    }

    // Ovaj metod se koristi za dobijanje korisnickog imena i lozinke od strane korisnika
    public static Korisnik korisnickiKredencijali(Scanner scanner) {
        System.out.println("\nUneti korisnicko ime");
        String korisnickoIme = scanner.next();

        System.out.println("\nUneti lozinku");
        String lozinka = scanner.next();

        Korisnik korisnik = new Korisnik(korisnickoIme, lozinka);
        return korisnik;
    }

    public static void izadjiIzAplikacije(Scanner scanner) {
        System.out.println("Gasenje aplikacije...");
        scanner.close();

        return;
    }

    public static void prikaziOpcijeGlavnogMenija(Scanner scanner, Korisnik korisnik) {
        System.out.println("\nIzaberite jednu od sledecih opcija:" +
                "\n- 1 za Bankovne Racune" +
                "\n- 2 for Placanja" +
                "\n- izlaz za izlazak iz aplikacije\n");

        String choice = scanner.next();

        switch (choice) {
            case "1":
                BankovniRacun.PrikaziOpcijeBankovnogMenija(scanner, korisnik);
                break;
            case "2":
                Placanja.PrikaziMeniSaOpcijamaPlacanja(scanner, korisnik);
                break;
            case "izlaz":
                izadjiIzAplikacije(scanner);
                break;
            default:
                System.out.println("Izbor nije validan\n");
                break;
        }
    }

    public static void prikaziGlavniMeni(Scanner scanner) {
        System.out.println("\nIzaberite:"
                + " \n- 1 za Login"
                + "\n- 2 za Registraciju"
                + "\n- izlaz za izlazak iz aplikacije\n");

        String choice = scanner.next();

        if (choice.equals("izlaz")) {
            izadjiIzAplikacije(scanner);
            return;
        }

        Autentikacija autentikacija = new Autentikacija();

        switch (choice) {
            case "1":
                Korisnik postojeciKorisnik = autentikacija.loginovanje(scanner);

                if (postojeciKorisnik == null) {
                    prikaziGlavniMeni(scanner);
                } else {
                    prikaziOpcijeGlavnogMenija(scanner, postojeciKorisnik);
                }
                break;
            case "2":
                Korisnik korisnik = autentikacija.registrujSe(scanner);

                if (korisnik == null) {
                    prikaziGlavniMeni(scanner);
                } else {
                    prikaziOpcijeGlavnogMenija(scanner, korisnik);
                }
                break;
            default:
                System.out.println("Izbor nije validan\n");
                prikaziGlavniMeni(scanner);
                break;
        }
    }
}
