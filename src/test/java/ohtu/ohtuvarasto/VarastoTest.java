package ohtu.ohtuvarasto;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoViallisenVaraston() {
        Varasto uusiVarasto = new Varasto(-1);

        assertEquals(0.0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonJollaAlkusaldo() {
        Varasto uusiVarasto = new Varasto(10, 2);

        assertEquals(2, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoViallisenVarastonAlkuSaldolla() {
        Varasto uusiVarasto = new Varasto(-1, 2);
        
        assertEquals(0.0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
    } 

    @Test
    public void konstruktoriLuoVarastonNegatiivisellaAlkuSaldolla() {
        Varasto uusiVarasto = new Varasto(10, -2);

        assertEquals(0.0, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liianTavaranOttaminenVapauttaaTilan(){
        varasto.lisaaVarastoon(2);
        
        varasto.otaVarastosta(3);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void varastoonLaitetaanLiikaaTavaraa(){
        varasto.lisaaVarastoon(11);
        
        varasto.otaVarastosta(0);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void varastoonLaitetaanNegatiivinenMaaraTavaraa(){
        double saldo = varasto.getSaldo();
        
        varasto.lisaaVarastoon(-1);
        
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaOtetaanNegatiivinenMaaraTavaraa(){
       
        
        assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimii() {
       assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
}

