import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class JavaPDFTest {

    public static void main(String args[]) throws IOException {

        //Loading an existing document
        File file = new File("/Users/ishanverma 1/Desktop/discharge_instructions.pdf");
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        List<String> test = Arrays.asList(text.split("\n"));

        String heading="";
        String texts="";

        for(int i =0; i< test.size();i++) {
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
        /*
        for (Map.Entry entry : map.entrySet())
        {
            if((entry.toString()).contains(" call us if you experience any of the following")){
            System.out.println(entry.getKey() +":::"+ entry.getValue());}
        }
        */
        System.out.println("------Get only appointments and its content----------");
        String appointments="";
        boolean b=false;
        for (String t : test)
        {
            if (t.contains("Appointments:")){
                b=true;
            }
            if (t.contains("What can I expect from having a ureteral stent")){
                //System.out.println(t);
                b=false;
            }
            if(b){
                appointments=appointments+"\n"+t;
            }

        }
        System.out.println(appointments);

        System.out.println("---------- end  -------- end  -------- end----------");



        System.out.println("--------Get each heading and its content------------");

        for (Map.Entry entry : map.entrySet())
        {
            System.out.println(entry.getKey() +":::"+ entry.getValue());
        }


        System.out.println("---------- end  -------- end  -------- end----------");


        System.out.print("PHONE NO: ");
        String phone_number="";
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            phone_number=matcher.group(0);
        }
        System.out.print(phone_number);

        /*
        for(String item : test) {
            if (item.contains(":")|| item.contains("?")){
                System.out.println("test");
            }
            System.out.println(item);
        }
        */
        //Closing the document
        document.close();

    }
}


