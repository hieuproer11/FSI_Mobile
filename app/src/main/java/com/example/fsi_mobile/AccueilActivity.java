package com.example.fsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccueilActivity extends AppCompatActivity {

    private EtudiantDataSource eds;
    private int idEtuConnecte;

    private FrameLayout frameLayout;
    private ImageView imageViewLogo;
    private TextView textAccueil;
    private FrameLayout frameLayout2;
    private ImageView imageViewEscape;
    private ImageView imageViewReturn;
    private ImageView imageViewUser;
    private Button bMesBilans;
    private Button bMesInfos;
    private TextView textBonjour;
    private TextView eNom_etu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);

        eds = new EtudiantDataSource(this);
        eds.open();

        // Récupère l'id de l'étudiant passé depuis l'intent
        idEtuConnecte = getIntent().getIntExtra("idEtu", 0);

        initialisation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialisation() {
        frameLayout = findViewById(R.id.frameLayout);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        textAccueil = findViewById(R.id.textAccueil);
        frameLayout2 = findViewById(R.id.frameLayout2);
        imageViewEscape = findViewById(R.id.imageViewEscape);
        imageViewReturn = findViewById(R.id.imageViewReturn);
        imageViewUser = findViewById(R.id.imageViewUser);
        bMesBilans = findViewById(R.id.bMesBilans);
        bMesInfos = findViewById(R.id.bMesInfos);
        textBonjour = findViewById(R.id.textBonjour);
        eNom_etu = findViewById(R.id.eNom_etu);

        // Afficher le prénom de l'étudiant connecté depuis SQLite avec l'ID récupéré
        Etudiant etudiant = eds.getByIdEtu(idEtuConnecte);
        if (etudiant != null) {
            eNom_etu.setText(etudiant.getPreEtu());
        } else {
            eNom_etu.setText("Étudiant non trouvé");
        }

        // Gestion des clics sur les boutons
        bMesInfos.setOnClickListener(view -> {
            Intent intent = new Intent(AccueilActivity.this, MesInformationsActivity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
        });

        bMesBilans.setOnClickListener(view -> {
            Intent intent = new Intent(AccueilActivity.this, MesBilansActivity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
        });

        imageViewReturn.setOnClickListener(view -> {
            startActivity(new Intent(AccueilActivity.this, MainActivity.class));
            finish();
        });

        imageViewEscape.setOnClickListener(view -> {
            startActivity(new Intent(AccueilActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        eds.close();
        super.onDestroy();
    }
}
