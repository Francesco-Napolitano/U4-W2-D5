package Main;

import archivio.Archivio;
import catalogo.ElementoCatalogo;
import catalogo.Libri;
import catalogo.Riviste;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        boolean archivioPieno = false;

        while (running) {
            System.out.println(" Catalogo bibliografico ");
            System.out.println("1 - Aggiungi qualcosa di nuovo");
            System.out.println("2 - Rimuovi elemento");
            System.out.println("3 - Ricerca per ISBN");
            System.out.println("4 - Ricerca per anno di pubblicazione");
            System.out.println("5 - Ricerca per autore");
            System.out.println("6 - Aggiorna elemento");
            System.out.println("7 - Stampa statistiche");
            System.out.println("8 - Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            if(!archivioPieno && scelta != 1 && scelta != 8) {
                System.out.println("Aggiungi prima qualche elemento all'archivio. ");
                continue;
            }

            try {
                switch (scelta) {
                    case 1:
                        aggiungiElemento(archivio, scanner);
                        archivioPieno = true;
                        break;
                    case 2:
                        rimuoviElemento(archivio, scanner);
                        break;
                    case 3:
                        ricercaPerIsbn(archivio, scanner);
                        break;
                    case 4:
                        ricercaPerAnno(archivio, scanner);
                        break;
                    case 5:
                        ricercaPerAutore(archivio, scanner);
                        break;
                    case 6:
                        aggiornaElemento(archivio, scanner);
                        break;
                    case 7:
                        archivio.stampaStatistiche();
                        break;
                    case 8:
                        running = false;
                        System.out.println("Arrivederci!");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void aggiungiElemento(Archivio archivio, Scanner scanner) throws Exception {
        System.out.print("Inserisci un libro o una rivista: ");
        String tipo = scanner.nextLine().toLowerCase();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno di pubblicazione: ");
        int anno = scanner.nextInt();
        System.out.print("Numero di pagine: ");
        int pagine = scanner.nextInt();
        scanner.nextLine();
        if (tipo.equals("libro")) {
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();
            archivio.aggiungiElemento(new Libri(isbn, titolo, anno, pagine, autore, genere));
            System.out.println("Libro aggiunto con successo!");
        } else if (tipo.equals("rivista")) {
            System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
            String periodicita = scanner.nextLine().toUpperCase();
            Riviste.Periodicita period =Riviste.Periodicita.valueOf(periodicita);
            archivio.aggiungiElemento(new Riviste(isbn, titolo, anno, pagine, period));
            System.out.println("Rivista aggiunta con successo!");
        } else {
            System.out.println("Tipo di elemento non valido.");
        }
    }

    private static void rimuoviElemento(Archivio archivio, Scanner scanner) {
        System.out.print("Inserisci l'ISBN dell'elemento da rimuovere: ");
        String isbn = scanner.nextLine();
        archivio.rimuoviElemento(isbn);
        System.out.println("Elemento rimosso");
    }

    private static void ricercaPerIsbn(Archivio archivio, Scanner scanner) {
        System.out.print("Inserisci l'ISBN: ");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = archivio.ricercaPerIsbn(isbn);
        if (elemento != null) {
            System.out.println("Elemento trovato: " + elemento);
        } else {
            System.out.println("Elemento non trovato.");
        }
    }

    private static void ricercaPerAnno(Archivio archivio, Scanner scanner) {
        System.out.print("Inserisci l'anno: ");
        int anno = scanner.nextInt();
        scanner.nextLine();
        List<ElementoCatalogo> risultati = archivio.ricercaPerAnno(anno);
        if (!risultati.isEmpty()) {
            System.out.println("Elementi trovati:");
            risultati.forEach(System.out::println);
        } else {
            System.out.println("Nessun elemento trovato per l'anno specificato.");
        }
    }

    private static void ricercaPerAutore(Archivio archivio, Scanner scanner) {
        System.out.print("Inserisci il nome dell'autore: ");
        String autore = scanner.nextLine();
        List<Libri> risultati = archivio.ricercaPerAutore(autore);
        if (!risultati.isEmpty()) {
            System.out.println("Elementi trovati:");
            risultati.forEach(System.out::println);
        } else {
            System.out.println("Nessun elemento trovato per l'autore specificato.");
        }
    }

    private static void aggiornaElemento(Archivio archivio, Scanner scanner) throws Exception {
        System.out.print("Inserisci l'ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();
        System.out.println("Inserisci i nuovi dati per l'elemento.");
        aggiungiElemento(archivio, scanner);
        archivio.rimuoviElemento(isbn);
        System.out.println("Elemento aggiornato con successo.");
    }
}
