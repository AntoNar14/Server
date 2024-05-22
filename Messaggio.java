import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Messaggio
{
    String testo;
    LocalTime orario;
    String nomeUtente;

    public Messaggio(String testo, LocalTime orario, String nomeUtente)
    {
        this.testo = testo;
        this.orario = orario;
        this.nomeUtente = nomeUtente;
    }

    public LocalTime getOrario()
    {
        return orario;
    }
    public void setOrario(LocalTime orario)
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String orarioFormattato=orario.format(formatter);
        return (nomeUtente+"|"+testo+"|"+orarioFormattato);
    }

}
