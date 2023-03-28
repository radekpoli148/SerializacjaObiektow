package serializacjaobiektow;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Towar implements Serializable
{
    public Towar()
    {
        this.cena = 0;
        this.nazwa = " ";
        this.dataWydania = new GregorianCalendar().getTime();
    }
    public Towar(double cena, String nazwa)
    {
        this();
        this.cena = cena;
        this.nazwa = nazwa;
    }
    public Towar(double cena, String nazwa, int rok, int m, int dz)
    {
        this(cena, nazwa);
        GregorianCalendar kalendarz = new GregorianCalendar(rok, m-1, dz);
        this.dataWydania = kalendarz.getTime();
    }
    public double pobierzCene()
    {
        return this.cena;
    }
    public String pobierzNazwe()
    {
        return this.nazwa;
    }
    public Date pobierzDate()
    {
        return dataWydania;
    }
     public String pobierzHaslo()
    {
        return this.haslo;
    }
    public void ustawCene(double cena)
    {
        this.cena = cena;
    }
    public void ustawNazwe(String nazwa)
    {
        this.nazwa = nazwa;
    }
    public void ustawDate(int rok, int m, int dz)
    {
        GregorianCalendar kalendarz = new GregorianCalendar(rok, m-1, dz);
        this.dataWydania = kalendarz.getTime();
    }
    public void ustawDate(Date data)
    {
        this.dataWydania = data;
    }
    @Override
    public String toString()
    {
        GregorianCalendar kalendarz = new GregorianCalendar();
        kalendarz.setTime(this.dataWydania);
        return this.cena+" zł; nazwa: "+this.nazwa+" "+kalendarz.get(Calendar.YEAR)+" rok "+(kalendarz.get(Calendar.MONTH)+1)+" miesiąc "+kalendarz.get(Calendar.DAY_OF_MONTH)+" dzień ";
    }
    
    private void readObject(ObjectInputStream inS) throws IOException, ClassNotFoundException
    {
        inS.defaultReadObject();
        if(haslo != "null")
            if(!haslo.equals("tajne"))
                throw new IOException("Dane są nieprawidłowe");
        
        System.out.println("Sprawdzenie czy ta metoda się wywoluje");
    }
    private void writeObject(ObjectOutputStream outS) throws IOException
    {
        outS.defaultWriteObject();
    }
    
    private static final int dlugoscNazwy = 30;
    private static final int dlugoscRekordu = (Character.SIZE * dlugoscNazwy + Double.SIZE + 3 * Integer.SIZE)/8;//80
    private double cena;//8 bajtow
    private String nazwa;//dlugosc nazwy * 2 = 60 bajtow
    private Date dataWydania;//4 + 4 + 4 bajty + 12
    
    private /*transient*/ String haslo = "tajne"; //transient - dane ulotne nie ulegają serializacji
}
