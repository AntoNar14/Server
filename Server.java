import java.net.*;
import java.io.*;
public class Server
{
    // Variabili globali del server necessarie ad elaborare la risposta
    static int cont=0;
    static int pagamenti=0, prelievi=0, raccomandate=0, serviziTelefonici=0;
    public static void main(String args[])
    {
        ServerSocket ss;
        try{
            ss = new ServerSocket(55555);
            while(true)
            {
                try{
                        Socket client = ss.accept();
                        System.out.println("Accettata connessione da: " + client.getRemoteSocketAddress().toString());
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                        // Lettura richiesta dal client
                        String str=in.readLine();
                        String risposta = elaborazione(str);
                        //trasmissione risposta del server
                        out.write(risposta);
                        out.flush();
                        // chiusura connessione
                        client.close();
                        in.close();
                        out.close();
                }
                catch(Exception e) {
                    System.out.println("COMUNICAZIONE FALLITA!\nErrore: " +
                            e.getMessage());
                }
            }
        }
        catch(Exception e) {
            System.out.println("APERTURA ServerSocket FALLITA!\nErrore: " +e.getMessage());
        }
    }

    static private String elaborazione(String richiesta)throws Exception
    {
        switch(richiesta)
        {
            case "PA": pagamenti++;
                return "PA"+pagamenti;
            case "PR": prelievi++;
                return "PR"+prelievi;
            case "RA": raccomandate++;
                return "PR"+raccomandate;
            case "ST": serviziTelefonici++;
                return "PR"+serviziTelefonici;
            default: throw new Exception("Operazione non valida");
        }
    }
}