package ohtu.verkkokauppa;

public class Viitegeneraattori implements Viitegeneraattorintapahtumat {
    
    public int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    public int uusi(){
        return seuraava++;
    }
}
