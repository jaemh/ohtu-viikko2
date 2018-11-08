package ohtu.verkkokauppa;

public interface Varastontapahtumat {

    public int saldo(int id);
    public Tuote haeTuote(int id);
    public void otaVarastosta(Tuote t);
    public void palautaVarastoon(Tuote t);
}