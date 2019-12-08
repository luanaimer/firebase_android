package br.edu.ifsc.firebase_android_luana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText edLogin, edSenha;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);
    }

    public void autenticar(View view) {
        mAuth.signInWithEmailAndPassword(
                edLogin.getText().toString(), edSenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login realizado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), Principal.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao realizar login!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cadastrar(View view) {
        mAuth.createUserWithEmailAndPassword(
                edLogin.getText().toString(),
                edSenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao realizar cadastro!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void recuperarSenha(View view) {
        if(!edLogin.getText().toString().trim().equals(""))
            mAuth.sendPasswordResetEmail(edLogin.getText().toString());
    }
}

