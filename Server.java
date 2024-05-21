import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Server
{
    // Variabili globali del server necessarie ad elaborare la risposta
    static ArrayList<Utente> listaUtentiCollegati=new ArrayList<Utente>();
    public static void main(String args[])
    {
        ServerSocket ss;
        try{
            ss = new ServerSocket(55555);
                try{
                        Socket client = ss.accept();
                        System.out.println("Accettata connessione da: " + client.getRemoteSocketAddress().toString());
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                        // Lettura richiesta dal client
                        String str=in.readLine();
                        String richiesta="";
                        String informazioni="";
                        if(str!=null && str.length()>1)
                        {
                            richiesta=str.substring(0,1);
                            informazioni=str.substring(1);
                        }

                        String risposta = elaborazione(richiesta,informazioni);
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
        catch(Exception e) {
            System.out.println("APERTURA ServerSocket FALLITA!\nErrore: " +e.getMessage());
        }
    }

    private static String elaborazione(String richiesta, String informazioni)throws Exception
    {
        switch(richiesta)
        {
            case "L": return registraUtente(informazioni);
            default: throw new Exception("Operazione non valida");
        }
    }

    private static String registraUtente(String nomeUtente) throws Exception
    {
        if(nomeUtente.contains("|"))
        {
            return "E";
        }
        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            if(nomeUtente.equals(listaUtentiCollegati.get(i).nomeUtente))
                return "A";
        }
        String id=generaID();
        listaUtentiCollegati.add(new Utente(nomeUtente,id));
        return id;
    }

    private static String generaID()
    {
        Random random = new Random();
        String caratteri="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String id="";
        for(int i=0; i<10; i++)
        {
            id+=caratteri.charAt(random.nextInt(caratteri.length()));
        }

        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            if(id.equals(listaUtentiCollegati.get(i).IDUtente))
                id=generaID();
        }

        return id;
    }
}