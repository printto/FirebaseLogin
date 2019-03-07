package com.printto.printmov.loginfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email,password;
    private Button signin,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signin = findViewById(R.id.signInBtn);
        signup = findViewById(R.id.signUpBtn);
        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);

        //check if theuser is logged in
        if (mAuth.getCurrentUser() != null){
            // User not login
            finish();
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }

    }

    public void signinClicked(View view){
        String getEmail = email.getText().toString();
        String getPassword = password.getText().toString();
        callSignIn(getEmail, getPassword);
    }

    public void signupClicked(View view){
        String getEmail = email.getText().toString();
        String getPassword = password.getText().toString();
        callSignUp(getEmail, getPassword);
    }

    private void callSignUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Test", "createUserWithEmail:success");
                            userProfile();
                            Toast.makeText(MainActivity.this, "Account created.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Test","Account created");
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Test", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    private void userProfile(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(email.getText().toString()).build();
            user.updateProfile( profileUpdates ).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Log.d("Test", "User profile updated.");
                    }
                }
            });
        }
    }

    private void callSignIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Test", "Signed in successfully: " + task.isSuccessful());

                if(!task.isSuccessful()){
                    Log.d("Test", "Signed in with email failed: "+task.getException());
                    Toast.makeText(MainActivity.this, "Logging in failed.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(MainActivity.this, SignIn.class);
                    finish();
                    startActivity(i);
                }

            }
        });
    }

}
