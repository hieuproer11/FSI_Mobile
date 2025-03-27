package com.example.fsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Bilan1Activity extends AppCompatActivity {

    private Bilan1DataSource bilan1DS;
    private EtudiantDataSource eds;
    private Bilan2DataSource bilan2DS;
    private int idEtuConnecte;

    private TextView textDateVis1, NoteDos1, NoteOral1, NoteEnt1, NoteMoyenne1, Remarque1, viewDate, viewNoteDoss, viewNoteOral, viewNoteEnt, viewMoyenne, viewRemarque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bilan1);
        idEtuConnecte = getIntent().getIntExtra("idEtu", 0);

        bilan1DS = new Bilan1DataSource(this);
        eds = new EtudiantDataSource(this);
        bilan2DS = new Bilan2DataSource(this);

        bilan1DS.open();
        bilan2DS.open();
        eds.open();


        initialisation();
        afficherBilan1();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialisation(){
        ImageView imageViewReturn = findViewById(R.id.imageViewReturn);
        ImageView imageViewEscape = findViewById(R.id.imageViewEscape);

        viewDate = findViewById(R.id.viewDate);
        viewNoteDoss = findViewById(R.id.viewNoteDoss);
        viewNoteOral = findViewById(R.id.viewNoteOral);
        viewNoteEnt = findViewById(R.id.viewNoteEnt);
        viewMoyenne = findViewById(R.id.viewMoyenne);
        viewRemarque = findViewById(R.id.viewRemarque);

        textDateVis1 = findViewById(R.id.textDateVis1);
        NoteDos1 = findViewById(R.id.NoteDos1);
        NoteOral1 = findViewById(R.id.NoteOral1);
        NoteEnt1 = findViewById(R.id.NoteEnt1);
        NoteMoyenne1 = findViewById(R.id.NoteMoyenne1);
        Remarque1 = findViewById(R.id.Remarque1);

        imageViewReturn.setOnClickListener(view -> {
            Intent intent = new Intent(Bilan1Activity.this, MesBilansActivity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
            finish();
        });

        imageViewEscape.setOnClickListener(view -> {
            Intent intent = new Intent(Bilan1Activity.this, MainActivity.class);
            startActivity(intent);
            eds.deleteAllEtudiants();
            bilan1DS.deleteAllBilan1();
            bilan2DS.deleteAllBilan2();
            finish();
        });
    }

    private void afficherBilan1() {
        Bilan1 bilan1 = bilan1DS.getBilan1ByEtudiantId(idEtuConnecte);
        if (bilan1 != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            textDateVis1.setText(sdf.format(bilan1.getDateVis1()));
            NoteDos1.setText(String.valueOf(bilan1.getNoteDoss1()));
            NoteOral1.setText(String.valueOf(bilan1.getNoteOral1()));
            NoteEnt1.setText(String.valueOf(bilan1.getNoteEnt1()));
            NoteMoyenne1.setText(String.valueOf(bilan1.getMoyenne1()));
            Remarque1.setText(bilan1.getRemarque());
        } else {
            Toast.makeText(this, "Aucun bilan trouvé pour cet étudiant.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        bilan1DS.close();
        bilan2DS.close();
        eds.close();
        super.onDestroy();
    }
}
