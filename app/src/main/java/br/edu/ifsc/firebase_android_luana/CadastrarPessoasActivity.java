package br.edu.ifsc.firebase_android_luana;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarPessoasActivity extends AppCompatActivity {
    EditText edNome, edCpf, edSexo;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoas);

        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edSexo = findViewById(R.id.edSexo);

        mRef = FirebaseDatabase.getInstance().getReference("pessoas");
    }


    public void cadastrar(View view) {
        Pessoa p = new Pessoa(edNome.getText().toString(), edCpf.getText().toString(), edSexo.getText().toString());
        mRef
                .push()
                .setValue(p)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Cadastro Salvo", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Deu m", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}

