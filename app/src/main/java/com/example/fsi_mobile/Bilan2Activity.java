package com.example.fsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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

public class Bilan2Activity extends AppCompatActivity {

    private Bilan2DataSource bilan2DS;
    private int idEtuConnecte;

    private FrameLayout frameLayout;
    private ImageView imageViewLogo;
    private FrameLayout frameLayout2;
    private ImageView imageViewEscape;
    private ImageView imageViewReturn;
    private TextView textMesBilans;

    private Button bViewBilan2;
    private TextView textDateVis2, noteDos2, noteOral2, noteEnt2, noteMoyenne2, viewDate1, viewNoteDoss1, viewNoteOral1, viewNoteEnt1, viewMoyenne1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bilan2);
        bilan2DS = new Bilan2DataSource(this);
        bilan2DS.open();
        idEtuConnecte = getIntent().getIntExtra("idEtu", 0);

        initialisation();
        afficherBilan2();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialisation(){
        frameLayout = findViewById(R.id.frameLayout);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        frameLayout2 = findViewById(R.id.frameLayout2);
        imageViewEscape = findViewById(R.id.imageViewEscape);
        imageViewReturn = findViewById(R.id.imageViewReturn);
        textMesBilans = findViewById(R.id.textMesBilans);
        bViewBilan2= findViewById(R.id.bViewBilan2);

        viewDate1 = findViewById(R.id.viewDate1);
        viewNoteDoss1 = findViewById(R.id.viewNoteDoss1);
        viewNoteOral1 = findViewById(R.id.viewNoteOral1);
        viewNoteEnt1 = findViewById(R.id.viewNoteEnt1);
        viewMoyenne1 = findViewById(R.id.viewMoyenne1);

        textDateVis2 = findViewById(R.id.textDateVis2);
        noteDos2 = findViewById(R.id.NoteDos2);
        noteOral2 = findViewById(R.id.NoteOral2);
        noteEnt2 = findViewById(R.id.NoteEnt2);
        noteMoyenne2 = findViewById(R.id.NoteMoyenne2);

        imageViewReturn.setOnClickListener(view -> {
            Intent intent = new Intent(Bilan2Activity.this, MesBilansActivity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
            finish();
        });

        imageViewEscape.setOnClickListener(view -> {
            Intent intent = new Intent(Bilan2Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void afficherBilan2() {
        Bilan2 bilan2 = bilan2DS.getBilan2ByEtudiantId(idEtuConnecte);
        if (bilan2 != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            textDateVis2.setText(sdf.format(bilan2.getDateVis2()));
            noteDos2.setText(String.valueOf(bilan2.getNoteDoss2()));
            noteOral2.setText(String.valueOf(bilan2.getNoteOral2()));
            noteEnt2.setText(String.valueOf(bilan2.getNoteEnt2()));
            noteMoyenne2.setText(String.valueOf(bilan2.getMoyenne2()));
        } else {
            Toast.makeText(this, "Aucun Bilan 2 trouvé pour cet étudiant", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        bilan2DS.close();
        super.onDestroy();
    }
}
