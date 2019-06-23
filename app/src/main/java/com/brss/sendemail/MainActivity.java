package com.brss.sendemail;

import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText emailid, content;
    private String email, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailid =  findViewById(R.id.login_emailid);
        content =  findViewById(R.id.Content);


    Button startBtn = findViewById(R.id.sendBtn);
      startBtn.setOnClickListener(new View.OnClickListener()
    {
        public void onClick (View view){
            email = emailid.getText().toString();
            body = content.getText().toString();
        sendEmail(email,body);
    }
    });
}
    protected void sendEmail(String email, String content) {
        Log.i("Send email", "");
        String[] TO = {""+email};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
