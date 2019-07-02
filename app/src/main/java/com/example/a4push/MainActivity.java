package com.example.a4push;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



        import android.app.NotificationChannel;
        import android.app.NotificationManager;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v4.app.NotificationCompat;
        import android.support.v4.app.NotificationManagerCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseAuthUserCollisionException;
        import com.google.firebase.iid.FirebaseInstanceId;
        import com.google.firebase.iid.InstanceIdResult;

        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //1. Notification Channel
    //2. Notification Builder
    //3. Notification Manager

    public static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified Coding";
    private static final String CHANNEL_DESC = "Simplified Coding Notifications";

    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void createUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 char long");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            startProfileActivity();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                userLogin(email, password);
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
    }

    private void userLogin(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startProfileActivity();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startProfileActivity();
        }
    }

    private void startProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
//
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.content.Intent;
//import android.os.Build;
//import android.os.IBinder;
//import android.support.annotation.NonNull;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthActionCodeException;
//import com.google.firebase.auth.FirebaseAuthUserCollisionException;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
//
//public class MainActivity extends AppCompatActivity {
//
//
//    private static final String CHANNEL_ID = "simple_coding";
//    private static final String CHANNEL_NAME = "Simple Coding";
//    private static final String CHANNEL_DESC = "Simple Coding Notification";
//    private TextView textView;
//
//    private EditText emailText,passwordText;
//    private ProgressBar progressBar;
//    private FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription(CHANNEL_DESC);
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//
//        }
//
//        progressBar = findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);
//        emailText = findViewById(R.id.editTextEmail);
//        passwordText = findViewById(R.id.editTextPassword);
//
//        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                createUser();
//
//            }
//        });
//    }
//
////        findViewById(R.id.buttonNotif).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                displayNotification();
////            }
////
////        textView = findViewById(R.id.TextViewToken);
//
//
//
////
//
//
//
//
//
//    private void createUser() {
//        final String email = emailText.getText().toString().trim();
//        final String password = passwordText.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            emailText.setError("Email required");
//            emailText.requestFocus();
//            return;
//        }
//
//        if (password.isEmpty()) {
//            passwordText.setError("password required");
//            passwordText.requestFocus();
//            return;
//        }
//
//        if (password.length()<6) {
//            passwordText.setError("Password's 2 short");
//            passwordText.requestFocus();
//            return;
//        }
//
//        progressBar.setVisibility(View.VISIBLE);
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            startProfileActivity();
//                        } else {
//                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
//
//                                userLogin(email, password);
//                            } else {
//                                progressBar.setVisibility((View.VISIBLE));
//                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//                });
//
//
//    }
//
//    private void userLogin(String email, String password) {
//
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            startProfileActivity();
//                        } else {
//                            progressBar.setVisibility(View.INVISIBLE);
//                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if(firebaseAuth.getCurrentUser() != null) {
//            startProfileActivity();
//        }
//
//
//
//    }
//
//    private void startProfileActivity(){
//
//        Intent intent = new Intent(this, com.example.a2pushnotif.ProfileActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//
//    }
//
////    private void displayNotification(){
////        NotificationCompat.Builder notifBuilder =
////                new NotificationCompat.Builder(this, CHANNEL_ID)
////                        .setSmallIcon(R.drawable.ic_email)
////                        .setContentTitle("Hurray! It is working")
////                        .setContentText("Your First Notification")
////                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
////
////        //displaying notification
////
////        NotificationManagerCompat notifManager = NotificationManagerCompat.from(this);
////        notifManager.notify(1, notifBuilder.build());
////    }
//}
