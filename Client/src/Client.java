import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // L'indirizzo del server
        int port = 55555; // La porta del server

        try {
            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Scegli l'operazione:");
                System.out.println("1. Login");
                System.out.println("2. Invia messaggio");
                System.out.println("3. Richiedi messaggi");
                System.out.println("4. Richiedi elenco utenti");
                System.out.println("5. Logout");
                System.out.println("6. Esci");

                String scelta = scanner.nextLine();
                String richiesta = "";

                switch (scelta) {
                    case "1":
                        System.out.print("Inserisci nome utente: ");
                        String nomeUtente = scanner.nextLine();
                        richiesta = "L" + nomeUtente;
                        break;
                    case "2":
                        System.out.print("Inserisci ID utente: ");
                        String idMittente = scanner.nextLine();
                        System.out.print("Inserisci messaggio: ");
                        String messaggio = scanner.nextLine();
                        richiesta = "I" + idMittente + " " + messaggio;
                        break;
                    case "3":
                        System.out.print("Inserisci ID utente: ");
                        String idMessaggi = scanner.nextLine();
                        richiesta = "R" + idMessaggi;
                        break;
                    case "4":
                        System.out.print("Inserisci ID utente: ");
                        String idElenco = scanner.nextLine();
                        richiesta = "V" + idElenco;
                        break;
                    case "5":
                        System.out.print("Inserisci ID utente: ");
                        String idLogout = scanner.nextLine();
                        richiesta = "T" + idLogout;
                        break;
                    case "6":
                        socket.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Scelta non valida.");
                        continue;
                }

                // Invio richiesta al server
                out.write(richiesta);
                out.newLine();
                out.flush();

                // Lettura risposta dal server
                String risposta = in.readLine();
                System.out.println("Risposta del server: " + risposta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

