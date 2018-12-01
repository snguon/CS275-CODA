package com.devteam.coda.coda;
import android.support.v7.app.AppCompatActivity;

import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PdfScrapper extends AppCompatActivity {
    //Loading an existing document
   // PDFBoxResourceLoader.init(getApplicationContext());

        File file = new File("/Users/ishanverma 1/Desktop/discharge_instructions.pdf");
        //PDDocument document;
         //       = PDDocument..load(file);
         String src = "/Users/ishanverma 1/Desktop/discharge_instructions.pdf";
        PDDocument document;

    {
        try {
            document = PDDocument.load(new File(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper;

    {
        try {
            pdfStripper = new PDFTextStripper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Retrieving text from PDF document
        String text;

    {
        try {
            text = pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    LinkedHashMap<String, String> map = new LinkedHashMap<>();
        List<String> test = Arrays.asList(text.split("\n"));
        String heading="";
        String texts="";
        public static void main(String args[]) throws IOException{
            File file = new File("/Users/ishanverma 1/Desktop/discharge_instructions.pdf");
            //PDDocument document;
            //       = PDDocument..load(file);
            String src = "/Users/ishanverma 1/Desktop/discharge_instructions.pdf";
            PDDocument document = PDDocument.load(new File(src));
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);

            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            List<String> test = Arrays.asList(text.split("\n"));
            String heading="";
            String texts="";
            for (int i =0; i< test.size();i++) {
                if (test.get(i).contains(":")|| test.get(i).contains("?")){
                    if(heading!=""){
                        map.put(heading, texts);
                    }
                    heading=test.get(i);
                    texts="";
                }else{
                    texts=texts+"\n"+test.get(i);
                }

            }
            for(Map.Entry entry : map.entrySet())
            {
                System.out.println(entry.getKey() +":::"+ entry.getValue());
            }
            System.out.println("--------------------");
            document.close();

        }
        // public PdfScrapper throws IOException(){}



}
