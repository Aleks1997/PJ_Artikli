import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Main
{
    private static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    public static void main(String [ ] args)
    {
        disableWarning();
/*
        try(OutputStream output = new FileOutputStream("src/config.properties")){
            Properties pro = new Properties();

            pro.setProperty("url","jdbc:mysql://127.0.0.1:3306/");
            pro.setProperty("strDbUser","root");
            pro.setProperty("strDbPassword","");

            pro.store(output,null);

            System.out.println(pro);

        }catch(IOException e){
            e.printStackTrace();
        }
*/
/*
        try(InputStream input = Main.class.getClassLoader().getResourceAsStream("src/config.properties")){
            Properties pro = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            pro.load(input);
            System.out.println(pro.getProperty("url"));
            System.out.println(pro.getProperty("strDbUser"));
            System.out.println(pro.getProperty("strDbPassword"));

        }catch(IOException e){
            e.printStackTrace();
        }
*/

        Companies companies = new Companies(); //podjetja
        Invoices invoices = new Invoices(); //računi
        Artikli artikli = new Artikli();
        InternalArtikli internalArtikli = new InternalArtikli();

        //artikli.fromJson("E:\\Faks\\Artikli.json");
        //companies.fromJson("E:\\Faks\\Companies.json");
        //invoices.fromJson("E:\\Faks\\Invoices.json");

        Podjetje pod = new Podjetje("Henkel Maribor d.o.o.",58665765,6261752000L,true,"02 2222100", "Industrijska ulica 23, Maribor, 2000 Maribor");
        companies.seznam.add(pod);
        companies.setSeznam(companies.seznam);

        Artikel A1 = new Artikel("prvoime","0020",12.13,0.22,1000,"10293212921011");
        Artikel A2 = new Artikel("drugoime","0300",8.12,0.22,1000,"10293212921012");
        Artikel A3 = new Artikel("tretjeime","5000",0.45,0.22,1000,"10293212921012");
        Artikel A4 = new Artikel("cetrtoime","0001",340.11,0.22,1000,"10293212921012");

        InternalArtikel I1 = new InternalArtikel("200879500200");//oddelek = 200, id=8795, teza=00200
        InternalArtikel I2 = new InternalArtikel("201889203000");//oddelek= 201, id=8892, teza=03000
        InternalArtikel I3 = new InternalArtikel("203809500001");//oddelek=203, id=8795, teza=00001

       Račun r = new Račun();
       r.setIzdajatelj(pod);
       artikli.seznam.add(A1);
       artikli.seznam.add(A2);
       artikli.seznam.add(A3);
       artikli.seznam.add(A4);
       internalArtikli.seznam.add(I1);
       internalArtikli.seznam.add(I2);
       internalArtikli.seznam.add(I3);
       artikli.setSeznam(artikli.seznam);
       internalArtikli.setSeznam(internalArtikli.seznam);
       r.setSeznam(artikli);
       r.setInternalArtikli(internalArtikli);
       System.out.println(r.toString());


       r.checkBarcode("99061915",artikli,internalArtikli);
       r.checkBarcode("99051915",artikli,internalArtikli);
       System.out.println("Skupna cena brez DDV" + " " + r.izracunaj(artikli.seznam,internalArtikli.seznam)+ "€." + "\n");
       System.out.println("Skupna cena z DDV" + " " + r.izracunajDDV(artikli.seznam,internalArtikli.seznam)+ "€." + "\n");

        invoices.seznam.add(r);
        invoices.setSeznam(invoices.seznam);

       artikli.toJson();
       companies.toJson();
       invoices.toJson();
       invoices.toJson();
        System.out.println(pod.search("58665765"));

        DBHelper.get();
    }

}
