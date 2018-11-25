package ohtu.verkkokauppa;

import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.Kauppa;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class KauppaTest {

    Kauppa kauppa;
    Pankki pankki;
    Varasto varasto;
    Viitegeneraattori viite;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);
        kauppa = new Kauppa(varasto, pankki, viite);

    }

    @Test
    public void aloitetaanAsiointiLisataanTuoteJaTehdaanTilisiirto() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    // aloitetaan asiointi, koriin lisätään kaksi eri tuotetta,
    // joita varastossa on ja suoritetaan ostos, varmistettava että
    // kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja
    // summalla

    @Test
    public void lisataanKaksiTuotettaJaTehdaanTilisiirto() {

        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito ja hinta 5
        // määritellään että tuote numero 2 on piimä ja hinta 5
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(5)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(5)).thenReturn(new Tuote(5, "piimä", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); // lisätään koriin tuote nro. 1 (maito)
        kauppa.lisaaKoriin(5); // lisätään koriin tuote nro. 5 (piimä)
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));

    }

    /**
     * aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta jota on varastossa
     * tarpeeksi ja suoritetaan ostos, varmistettava että kutsutaan pankin metodia
     * tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
     */

    @Test
    public void lisataanKaksiSamaaTuotettaJaTehdaanTilisiirto() {
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); // lisätään tuote numero 1, maito
        kauppa.lisaaKoriin(1); // lisätään tuote numero 2, maito
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));

    }

    /**
     * aloitetaan asiointi, koriin lisätään tuote jota on varastossa tarpeeksi ja
     * tuote joka on loppu ja suoritetaan ostos, varmistettava että kutsutaan pankin
     * metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
     */

    @Test
    public void lisataanKaksiTuotettaToinenVaratossaToinenLoppu() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "suklaa", 3));

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);

        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));

    }

    @Test
    public void aloitaAsiointiJaNollaaOstoskorin() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.saldo(3)).thenReturn(4);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 5));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "suklaa", 4));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(4));

    }

    @Test
    public void viitenumeroJokaiselleOstotapahtumalle() {

        when(viite.uusi()).thenReturn(42);
        when(viite.uusi()).thenReturn(43);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        // verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(eq("pekka"), eq(43), eq("12345"), eq("33333-44455"), eq(10));
    }

    @Test
    public void poistaTuoteKorista() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.poistaKorista(2);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(3));

    }

}