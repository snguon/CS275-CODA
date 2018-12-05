package com.devteam.coda.coda;
import android.support.v7.app.AppCompatActivity;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PdfScrapper extends AppCompatActivity {
        public static String pdfFiller;

        public static String pdftotext() throws IOException {


            try {
                URL url = new URL("http://snguon.w3.uvm.edu/cs275/discharge_instructions.pdf");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();

                // read from your scanner


                PDDocument document = PDDocument.load(is);
                //Instantiate PDFTextStripper class
                PDFTextStripper pdfStripper = new PDFTextStripper();

                //Retrieving text from PDF document
                String text = pdfStripper.getText(document);

                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                List<String> test = Arrays.asList(text.split("\n"));
                String heading = "";
                String texts = "";
                for (int i = 0; i < test.size(); i++) {
                    if (test.get(i).contains(":") || test.get(i).contains("?")) {
                        if (heading != "") {
                            map.put(heading, texts);
                        }
                        heading = test.get(i);
                        texts = "";
                    } else {
                        texts = texts + "\n" + test.get(i);
                    }

                }
                for (Map.Entry entry : map.entrySet()) {
                    //System.out.println(entry.getKey() +":::"+ entry.getValue());
                    pdfFiller = (entry.getKey() + ":::" + entry.getValue());
                    System.out.println(pdfFiller);
                    //return pdfFiller;
                }


                System.out.println("--------------------");
                document.close();
                return pdfFiller;

            } catch (IOException ex) {
                // there was some connection problem, or the file did not exist on the server,
                // or your URL was not in the right format.
                // think about what to do now, and put it here.
                ex.printStackTrace(); // for now, simply output it.
            }
            return "Error nothing read in...";
        }
    //public static String getpdfString(){return pdfFiller;}

}



