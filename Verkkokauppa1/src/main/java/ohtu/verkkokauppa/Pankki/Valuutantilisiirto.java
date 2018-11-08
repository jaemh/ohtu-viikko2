package ohtu.verkkokauppa;

public interface Valuutantilisiirto {

  public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);

}