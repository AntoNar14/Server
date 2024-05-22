import java.util.ArrayList;

public class Utente
{
    String nomeUtente;
    String IDUtente;
    int ultimoMessaggioInviato;
    public Utente(String nomeUtente, String IDUtente, int ultimoMessaggioInviato)
    {
        this.nomeUtente = nomeUtente;
        this.IDUtente = IDUtente;
        this.ultimoMessaggioInviato=ultimoMessaggioInviato;
    }
    public String getIDUtente()
    {
        return IDUtente;
    }
    public void setIDUtente(String IDUtente)
    {
        this.IDUtente = IDUtente;
    }
    public String getNomeUtente()
    {
        return nomeUtente;
    }
    public void setNomeUtente(String nomeUtente)
    {
        this.nomeUtente = nomeUtente;
    }

}

