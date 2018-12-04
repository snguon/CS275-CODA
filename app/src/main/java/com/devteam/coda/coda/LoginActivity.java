package com.devteam.coda.coda;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    private static EditText email;
    private static EditText username;
    private EditText password;
    private CardView bLogin;
    private TextView info;
    private TextView register;

    private int counter = 5;

    public static String getUsername() {
        return username.getText().toString();
//        return email.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editEmail);
        username = (EditText)findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        register = (TextView) findViewById(R.id.register);
        bLogin = (CardView) findViewById(R.id.bLogin);
        info = (TextView) findViewById(R.id.textInfo);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    public void validateLogin(View view) {
        // String sEmail = email.getText().toString();
        String sUsername = username.getText().toString();
        String sPassword = password.getText().toString();
        String type = "login";

        LoginActivity.BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        // backgroundWorker.execute(type, sEmail, sPassword);
        backgroundWorker.execute(type, sUsername, sPassword);
    }

    private class BackgroundWorker extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;

        BackgroundWorker(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://snguon.w3.uvm.edu/cs275/login.php";
            if (type.equals("login")) {
                try {
                    String username = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                            + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            alertDialog.show();

            if (result.contains("Login Successful")) {
                Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            } else if (result.contains("Login Failure")){
                Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}

