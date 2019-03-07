package com.printto.printmov.loginfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button signout;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.button);
        statusText = findViewById(R.id.statusText);
        statusText.setText("Signed in using email:\n" + mAuth.getCurrentUser().getEmail());
    }

    public void signOutClicked(View view){
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        this.finish();
    }

}
