package com.devteam.coda.coda;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static EditText username;
    private EditText password;
    private CardView bLogin;
    private TextView info;
    private int counter = 5;

    public static String getUsername() {
        return username.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

        username = (EditText)findViewById(R.id.editUsername);
        password = (EditText)findViewById(R.id.editPassword);
        //bLogin = (Button)findViewById(R.id.login_button);
        bLogin = (CardView) findViewById(R.id.cardView);
        info = (TextView)findViewById(R.id.textInfo);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void validateLogin(String userName, String userPassword) {
        if ((userName.equals("test")) && (userPassword.equals("1234"))) {
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);
        } else {
            counter--;

            info.setText("No. of attempts remaining: " + String.valueOf(counter));


            if (counter == 0) {
                bLogin.setEnabled(false);
            }
        }
    }
}
