import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import model.Activitat;
import model.GuiaEntitats;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class App {
    static final String pathXML = "http://justicia.gencat.cat/web/.content/tramits/entitats/llistatEntitats-federacions.xml";
    static final String pathJSON = "https://analisi.transparenciacatalunya.cat/resource/rhpv-yr4f.json";
    static final String pathCSV ="https://analisi.transparenciacatalunya.cat/resource/rhpv-yr4f.csv";


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("App MP3-UF5-A3");

        int op;
        do{
            System.out.println("1-XML, 2-CSV, 3-JSON, 0-Exit");
            op = sc.nextInt();

            switch (op) {
                case 1 : readXML(); break;
                case 2 : readCSV(); break;
                case 3 : readJSON(); break;
                default:
                    System.out.println("opció incorrecte");
            }
        }while( op != 0 );


    }

    private static void readCSV() {
        System.out.println("reading CSV");
        URL url = null;
        BufferedReader in = null;
        CSVReader reader = null;
        try {
            url = new URL(pathCSV);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            reader = new CSVReader(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvToBean<Activitat> activitatCsvToBean = new CsvToBeanBuilder(reader)
                .withType(Activitat.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Iterator<Activitat> csvIterator = activitatCsvToBean.iterator();
        while(csvIterator.hasNext()) {
            Activitat activitat = csvIterator.next();
            System.out.println(activitat);
        }

    }

    private static void readJSON()  {
        System.out.println("reading JSON");

    }

    private static void readXML() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            guiaEntitats.getEntitats().getLlistaEntitats().forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
