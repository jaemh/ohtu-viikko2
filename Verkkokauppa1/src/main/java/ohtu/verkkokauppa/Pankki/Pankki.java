package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ohtu.verkkokauppa.Kirjanpito;

@Component
public class Pankki implements Valuutantilisiirto {

    public Kirjanpitotapahtumat kirjanpito;

    @Autowired
    public Pankki(Kirjanpitotapahtumat kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
