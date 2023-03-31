package serializacjaobiektow;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SerializacjaObiektow
{
    public static void main(String[] args) 
    {
        Towar[] towar = new Towar[3];
        
        towar[0] = new Towar();
        towar[1] = new Towar(29.99, "Videokurs java");
        towar[2] = new Towar(39.99, "Videokurs C++", 2018, 9, 5);
        
        try
        {
            ObjectOutputStream outS = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("baza.txt")));
            
            outS.writeObject(towar);
            
            outS.close();
            
            ObjectInputStream inS = new ObjectInputStream(new GZIPInputStream(new FileInputStream("baza.txt")));
            
            Towar[] a = (Towar[])inS.readObject();
            
            for(int i = 0; i < a.length; i++)
            {
                System.out.println(a[i].pobierzCene());
                System.out.println(a[i].pobierzNazwe());
                System.out.println(a[i].pobierzDate());
                System.out.println("----------------------");
            }
            
            inS.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}