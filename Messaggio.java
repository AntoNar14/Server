import java.time.Clock;

public class Messaggio
{
    String testo;
    Clock orario;
    String nomeUtente;
    public Messaggio(String testo, Clock orario, String nomeUtente)
    {
        this.testo = testo;
        this.orario = orario;
        this.nomeUtente = nomeUtente;
    }

    public Clock getOrario()
    {
        return orario;
    }
    public void setOrario(Clock orario)
    {
        this.orario = orario;
    }
    public String getNomeUtente()
    {
        return nomeUtente;
    }
    public void setNomeUtente(String nomeUtente)
    {
        this.nomeUtente = nomeUtente;
    }
    public String getTesto()
    {
        return testo;
    }
    public void setTesto(String testo)
    {
        this.testo = testo;
    }
    public String toString()
    {
        return (nomeUtente+"|"+testo+"|"+orario);
    }

}
