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
        try(OutputStream output = new FileOutputStream("config.properties")){
            Properties pro = new Properties();
            pro.setProperty("db.url","si.um.feri.database");
            pro.setProperty("db.user","user");
            pro.setProperty("db.password","user");
            pro.store(output,null);

            System.out.println(pro);

        }catch(IOException e){
            e.printStackTrace();
        }

*/
        try(InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")){
            Properties pro = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            pro.load(input);
            System.out.println(pro.getProperty("db.url"));
            System.out.println(pro.getProperty("db.user"));
            System.out.println(pro.getProperty("db.password"));

        }catch(IOException e){
            e.printStackTrace();
        }



        Companies companies = new Companies(); //podjetja
        Invoices invoices = new Invoices(); //računi
        Artikli artikli = new Artikli();

        artikli.fromJson("E:\\Faks\\Artikli.json");
        companies.fromJson("E:\\Faks\\Companies.json");
        invoices.fromJson("E:\\Faks\\Invoices.json");

        Podjetje pod = new Podjetje("Henkel Maribor d.o.o.",58665765,6261752000L,true,"02 2222100", "Industrijska ulica 23, Maribor, 2000 Maribor");
        companies.seznam.add(pod);
        companies.setSeznam(companies.seznam);

        Artikel A1 = new Artikel("0104","0201",0.85,0.22,"Slovenia","0001");
        Artikel A2 = new Artikel("4789","0202",2.48,0.095,"Slovenia","0001");
        Artikel A3 = new Artikel("6794","0203",6.48,0.22,"Slovenia","0001");
        Artikel A4 = new Artikel("6789","0211",0.67,0.22,"Slovenia","0200");


       Račun r = new Račun();
       r.setIzdajatelj(pod);
       artikli.seznam.add(A1);
       artikli.seznam.add(A2);
       artikli.seznam.add(A3);
       artikli.seznam.add(A4);
       artikli.setSeznam(artikli.seznam);
       r.setSeznam(artikli);
       System.out.println(r.toString());
       r.checkBarcode("99051915",artikli);
       r.checkBarcode("99041915",artikli);
       System.out.println("Skupna cena brez DDV" + " " + r.izracunaj(artikli.seznam)+ "€." + "\n");
       System.out.println("Skupna cena z DDV" + " " + r.izracunajDDV(artikli.seznam)+ "€." + "\n");


        invoices.seznam.add(r);
        invoices.setSeznam(invoices.seznam);

       //artikli.toJson();
       //companies.toJson();
       //invoices.toJson();
        System.out.println(pod.search("58665765"));

    }

}
