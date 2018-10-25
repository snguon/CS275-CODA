package com.devteam.coda.coda;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class PDFScraper {
    public static void main(String args[]) throws Exception {

        InputStream is = null;
        try {
            // Still working out on relative paths but here's an absolute path right now
            is = new FileInputStream("C:\\Users\\snguo\\AndroidStudioProjects\\CS275-CODA\\app\\src\\main\\java\\com\\devteam\\coda\\coda\\discharge_instructions.pdf");
            ContentHandler contenthandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            PDFParser pdfparser = new PDFParser();
            pdfparser.parse(is, contenthandler, metadata, new ParseContext());
            System.out.println(contenthandler.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) is.close();
        }
    }
}
