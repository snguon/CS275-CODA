package com.devteam.coda.coda;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDDocumentCatalog;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.encryption.AccessPermission;
import com.tom_roush.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import com.tom_roush.pdfbox.pdmodel.graphics.image.JPEGFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.image.LosslessFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDCheckbox;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDComboBox;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDListBox;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDRadioButton;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDTextField;
import com.tom_roush.pdfbox.rendering.PDFRenderer;
import com.tom_roush.pdfbox.text.PDFTextStripper;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import org.spongycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfScrapper extends AppCompatActivity {
    File root;
    AssetManager assetManager;
    Bitmap pageImage;
    TextView tv;
    //
    //TextView text_problems;

    //
    TextView usrName;
    private Toolbar toolbar;
    Button pdfScrape;

    static String get_problems="";
    static String get_meds="";
    static String get_apointments="";
    static String get_symptoms="";
    static String get_phone_number="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfscrapper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usrName = findViewById(R.id.usrnameTool);
        usrName.setText(LoginActivity.getUsername());
    }

    @Override
    protected void onStart() {
        super.onStart();
        setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();
        if(res_id == R.id.action_settings){
            Toast.makeText(getApplicationContext(),
                    "You selected settings option", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    /**
     * Initializes variables used for convenience
     */
    private void setup() {
        // Enable Android-style asset loading (highly recommended)
        PDFBoxResourceLoader.init(getApplicationContext());
        // Find the root of the external storage.
        root = android.os.Environment.getExternalStorageDirectory();
        assetManager = getAssets();
        tv = (TextView) findViewById(R.id.statusTextView);
        //text_problems = (TextView) findViewById(R.id.myProblems);
        pdfScrape = (Button) findViewById(R.id.buttonStripText);
    }

    /**
     * Strips the text from a PDF and displays the text on screen
     */

    public void stripText(View v) {

        pdfScrape.setVisibility(View.GONE);
        tv.setText("Please wait while the pdf is being downloaded and converted...");
        PdfScrapper.BackgroundWorker backgroundWorker= new BackgroundWorker(this);
        backgroundWorker.execute();
/*        String parsedText = null;
        PDDocument document = null;
        try {
            document = PDDocument.load(assetManager.open("discharge_instructions.pdf"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(0);
            pdfStripper.setEndPage(1);
            parsedText = pdfStripper.getText(document);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(parsedText);
        tv.setText(parsedText);*/
    }

    boolean button_clicked = false;

    private class BackgroundWorker extends AsyncTask<String, Void, String> {
        Context context;

        public BackgroundWorker(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            final String parsedText;
            PDDocument document = null;
            String pdf_url = "http://snguon.w3.uvm.edu/cs275/discharge_instructions.pdf";
            try {
                URL url = new URL(pdf_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                InputStream is = connection.getInputStream();
                document = PDDocument.load(is);
                // document = PDDocument.load(assetManager.open("discharge_instructions.pdf"));

                PDFTextStripper pdfStripper = new PDFTextStripper();
//                pdfStripper.setStartPage(-2);
//                pdfStripper.setEndPage(12);
                parsedText = pdfStripper.getText(document);

                System.out.println(parsedText);

                List<String> test = Arrays.asList(parsedText.split("\n"));


                boolean b=false;
                for (String t : test)
                {
                    if (t.contains("Appointments")){
                        b=true;
                    }
                    if (t.contains("What can I expect from having a ureteral stent?")){
                        //System.out.println(t);
                        b=false;
                    }
                    if(b){
                        get_apointments=get_apointments+"\n"+t;
                    }

                }


                b=false;
                for (String t : test)
                {
                    if (t.contains("What can I expect from having a ureteral stent?")){
                        b=true;
                    }
                    if (t.contains("How long will the stent remain in my body?")){
                        //System.out.println(t);
                        b=false;
                    }
                    if(b){
                        get_problems=get_problems+"\n"+t;
                    }

                }

                b=false;
                for (String t : test)
                {
                    if (t.contains("Symptoms to Call Your Doctor About")){
                        b=true;
                    }
                    if (t.contains("Appointments")){
                        //System.out.println(t);
                        b=false;
                    }
                    if(b){
                        get_symptoms=get_symptoms+"\n"+t;
                    }

                }


                b=false;
                int c=0;
                for (String t : test)
                {
                    if (t.contains("PAIN CONTROL")){
                        b=true;
                    }
                    if (t.contains("BATHING")){
                        //System.out.println(t);
                        b=false;
                        c=1;

                    }
                    if(b&&c==0){
                        get_meds=get_meds+"\n"+t;
                    }

                }

                Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
                Matcher matcher = pattern.matcher(parsedText);
                if (matcher.find()) {
                    get_phone_number=matcher.group(0);
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tv.setText(parsedText);
                      //  text_problems.setText(temp_text);
                    }
                });

                document.close();
                is.close();
                connection.disconnect();
            } catch(IOException e) {
                e.printStackTrace();
            }
            return null;

        }
    }
}
