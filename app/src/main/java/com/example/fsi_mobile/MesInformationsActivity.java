package com.example.fsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MesInformationsActivity extends AppCompatActivity {
    private EtudiantDataSource eds;
    private Bilan1DataSource b1d;
    private Bilan2DataSource b2d;
    private int idEtuConnecte;

    private TextView tNomEtu, tPreEtu, tMailEtu, tTelEtu, tClasse, tSpecialite, tAdresse;
    private TextView tNomEnt, tAdresseEnt, tNomMaitre, tPreMaitre, tTelMaitre, tMailMaitre;
    private TextView tNomTut, tPreTut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mes_informations);

        idEtuConnecte = getIntent().getIntExtra("idEtu", 0);
        eds = new EtudiantDataSource(this);
        b1d =  new Bilan1DataSource(this);
        b2d = new Bilan2DataSource(this);

        eds.open();
        b1d.open();
        b2d.open();

        initialisation();
        afficherInformations();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialisation(){
        tNomEtu = findViewById(R.id.tNomEtu);
        tPreEtu = findViewById(R.id.tPreEtu);
        tMailEtu = findViewById(R.id.tMailEtu);
        tTelEtu = findViewById(R.id.tTelEtu);
        tAdresse = findViewById(R.id.tAdresse);
        tClasse = findViewById(R.id.tClasse);
        tSpecialite = findViewById(R.id.tSpecialite);

        tNomEnt = findViewById(R.id.tNomEnt);
        tAdresseEnt = findViewById(R.id.tAdresseEnt);
        tNomMaitre = findViewById(R.id.tNomMaitre);
        tPreMaitre = findViewById(R.id.tPreMaitre);
        tTelMaitre = findViewById(R.id.tTelMaitre);
        tMailMaitre = findViewById(R.id.tMailMaitre);

        tNomTut = findViewById(R.id.tNomTut);
        tPreTut = findViewById(R.id.tPreTut);

        ImageView imageViewReturn = findViewById(R.id.imageViewReturn);
        ImageView imageViewEscape = findViewById(R.id.imageViewEscape);

        imageViewReturn.setOnClickListener(view -> {
            Intent intent = new Intent(MesInformationsActivity.this, AccueilActivity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
            finish();
        });
        imageViewEscape.setOnClickListener(view -> {
            Intent intent = new Intent(MesInformationsActivity.this, MainActivity.class);
            startActivity(intent);
            eds.deleteAllEtudiants();
            b1d.deleteAllBilan1();
            b2d.deleteAllBilan2();
            finish();
        });
    }

    private void afficherInformations() {
        Etudiant etu = eds.getByIdEtu(idEtuConnecte);
        if (etu != null) {
            tNomEtu.setText(etu.getNomEtu());
            tPreEtu.setText(etu.getPreEtu());
            tMailEtu.setText(etu.getMailEtu());
            tTelEtu.setText(etu.getTelEtu());
            tAdresse.setText(etu.getAdrEtu());
            tClasse.setText(etu.getClasse());
            tSpecialite.setText(etu.getSpecialite());

            tNomEnt.setText(etu.getNomEnt());
            tAdresseEnt.setText(etu.getAdrEnt());
            tNomMaitre.setText(etu.getNomMai());
            tPreMaitre.setText(etu.getPreMai());
            tTelMaitre.setText(String.valueOf(etu.getTelMai()));
            tMailMaitre.setText(etu.getMailMai());

            tNomTut.setText(etu.getNomTut());
            tPreTut.setText(etu.getPreTut());
        }
    }

    @Override
    protected void onDestroy() {
        b1d.close();
        b2d.close();
        eds.close();
        super.onDestroy();
    }
}
