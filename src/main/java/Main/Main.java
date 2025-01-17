package Main;

import archivio.Archivio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Rimuovi elemento");
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per anno");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna elemento");
            System.out.println("7. Stampa statistiche");
            System.out.println("8. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma newline

            try {
                switch (scelta) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Scelta non valida!");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
