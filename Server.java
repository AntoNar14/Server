import java.net.*;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Server {
    // Variabili globali del server necessarie ad elaborare la risposta
    static ArrayList<Utente> listaUtentiCollegati = new ArrayList<Utente>();
    static ArrayList<Messaggio> listaMessaggiInoltrati = new ArrayList<Messaggio>();

    public static void main(String args[]) {
        ServerSocket ss;
        try {
            ss = new ServerSocket(55555);
            try {
                while(true)
                {
                    Socket client = ss.accept();
                    System.out.println("Accettata connessione da: " + client.getRemoteSocketAddress().toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    // Lettura richiesta dal client
                    String str = in.readLine();
                    String richiesta = "";
                    String informazioni = "";
                    if (str != null && str.length() > 1) {
                        richiesta=str.substring(0, 1);
                        informazioni=str.substring(1);
                    }

                    String risposta = elaborazione(richiesta, informazioni);
                    //trasmissione risposta del server
                    out.write(risposta);
                    out.flush();
                    // chiusura connessione
                    client.close();
                    in.close();
                    out.close();
                }

            } catch (Exception e) {
                System.out.println("COMUNICAZIONE FALLITA!\nErrore: " +
                        e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("APERTURA ServerSocket FALLITA!\nErrore: " + e.getMessage());
        }
    }

    private static String elaborazione(String richiesta, String informazioni) throws Exception {
        switch (richiesta) {
            case "L": return registraUtente(informazioni);
            case "I": return inoltroMessaggio(informazioni);
            case "R": return inviaListaMessaggi(informazioni);
            case "V": return restituisciElencoUtenti(informazioni);
            case "T": return logout(informazioni);
            default:
                return "E";
        }
    }

    public static String logout(String id)
    {
        if(cercaUtente(id)=="")
            return "E";

        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            if(listaUtentiCollegati.get(i).IDUtente.equals(id))
            {
                listaUtentiCollegati.remove(i);
                return "P";
            }
        }

        return "E";
    }

    public static String restituisciElencoUtenti(String id)
    {
        if(cercaUtente(id)=="")
            return "E";

        String listaUtenti="";
        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            listaUtenti+=(listaUtentiCollegati.get(i).nomeUtente+"|");
        }

        return "G"+listaUtenti;
    }


    public static String inviaListaMessaggi(String id)
    {
        if(cercaUtente(id)=="")
            return "E";

        int inizioListaMessaggi=0;

        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            if(listaUtentiCollegati.get(i).IDUtente.equals(id))
                inizioListaMessaggi=listaUtentiCollegati.get(i).ultimoMessaggioInviato;
        }

        String messaggiUtente="";

        for(int i=inizioListaMessaggi; i<listaMessaggiInoltrati.size(); i++)
        {
            messaggiUtente+=("|"+listaMessaggiInoltrati.get(i).toString());
        }

        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            if(listaUtentiCollegati.get(i).IDUtente.equals(id))
                listaUtentiCollegati.get(i).ultimoMessaggioInviato=listaMessaggiInoltrati.size()-1;
        }

        return "U"+messaggiUtente;
    }

    public static String inoltroMessaggio(String informazioni)
    {
        String idMittente=informazioni.substring(0,10);
        String messaggio=informazioni.substring(11);
        if(messaggio.contains("|"))
            return "E";

        LocalTime orario=LocalTime.now();
        String nomeUtente=cercaUtente(idMittente);

        if(nomeUtente=="")
            return "E";

        listaMessaggiInoltrati.add(new Messaggio(messaggio,orario,nomeUtente));

        return "M";
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
        listaUtentiCollegati.add(new Utente(nomeUtente,id,0));
        return "S"+id;
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

    private static String cercaUtente(String id)
    {
        for(int i=0; i<listaUtentiCollegati.size(); i++)
        {
            if(id.equals(listaUtentiCollegati.get(i).IDUtente))
                return listaUtentiCollegati.get(i).getNomeUtente();
        }

        return "";
    }
}