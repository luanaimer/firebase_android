package br.edu.ifsc.firebase_android_luana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    ArrayList<Pessoa> arrayListPessoa;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        if(mUser != null) {
            Log.d("FirebaseAuth", mUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuario não autenticado");
            finish();
        }

        findViewById(R.id.buttonCadPessoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CadastrarPessoasActivity.class);
                startActivity(i);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(),
                        DividerItemDecoration.VERTICAL));

        arrayListPessoa = new ArrayList<Pessoa>();

        mRef = FirebaseDatabase.getInstance().getReference("pessoas");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pessoa p;

                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    p = objSnapshot.getValue(Pessoa.class);
                    p.setId(objSnapshot.getKey());
                    arrayListPessoa.add(p);
                }
                PessoaAdapter pessoaAdapter = new PessoaAdapter(
                        getApplication(),
                        R.layout.item_pessoa_list,
                        arrayListPessoa
                );

                recyclerView.setAdapter(pessoaAdapter);

                Log.d("RetrieveData", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void logout(View view) {
        mAuth.signOut();
        finish();
    }
}

