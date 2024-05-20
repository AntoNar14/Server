import java.util.ArrayList;

public class Utente
{
    String nomeUtente;
    String IDUtente;
    public Utente(String nomeUtente, String IDUtente)
    {
        this.nomeUtente = nomeUtente;
        this.IDUtente = IDUtente;
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

