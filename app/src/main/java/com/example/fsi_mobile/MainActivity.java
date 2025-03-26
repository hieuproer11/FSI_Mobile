package com.example.fsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textLogin, textMdp;
    private EditText editTextLogin, editTextMdp;
    private Button bConnecter;

    private EtudiantDataSource etudiantDS;
    private Bilan1DataSource bilan1DS;
    private Bilan2DataSource bilan2DS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etudiantDS = new EtudiantDataSource(this);
        bilan1DS = new Bilan1DataSource(this);
        bilan2DS = new Bilan2DataSource(this);

        initialisation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialisation() {
        imageView = findViewById(R.id.imageView);
        textLogin = findViewById(R.id.textLogin);
        textMdp = findViewById(R.id.textMdp);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextMdp = findViewById(R.id.editTextPassword);
        bConnecter = findViewById(R.id.bConnecter);

        bConnecter.setOnClickListener(view -> {
            String login = editTextLogin.getText().toString();
            String mdp = editTextMdp.getText().toString();

            if (!login.isEmpty() && !mdp.isEmpty()) {
                authenticateUser(login, mdp);
            } else {
                Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void authenticateUser(String login, String mdp) {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.0.2.2/FSI_PHP/API/")
                .baseUrl("http://capibara.maxirrx-website.fr/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiFSI api = retrofit.create(ApiFSI.class);
        api.login(login, mdp).enqueue(new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getEtudiant() != null) {
                    Etudiant etudiant = response.body().getEtudiant();
                    Bilan1 bilan1 = response.body().getBilan1();
                    Bilan2 bilan2 = response.body().getBilan2();

                    etudiantDS.open();
                    bilan1DS.open();
                    bilan2DS.open();

                    if (!etudiantDS.etudiantExists(etudiant.getIdEtu())) {
                        etudiantDS.insertEtu(etudiant);

                        if (bilan1 != null) {
                            bilan1DS.insertBil1(bilan1, etudiant.getIdEtu());
                        }

                        if (bilan2 != null) {
                            bilan2DS.insertBil2(bilan2, etudiant.getIdEtu());
                        }
                    }

                    etudiantDS.close();
                    bilan1DS.close();
                    bilan2DS.close();

                    Toast.makeText(MainActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                    intent.putExtra("idEtu", etudiant.getIdEtu());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Identifiants incorrects ou données manquantes", Toast.LENGTH_SHORT).show();
                    Log.e("API_RESPONSE", "Erreur Retrofit: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("API_FAILURE", "Retrofit Failure", t);
            }
        });
    }
}