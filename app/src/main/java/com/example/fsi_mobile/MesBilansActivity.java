package com.example.fsi_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MesBilansActivity extends AppCompatActivity {

    private int idEtuConnecte;
    private EtudiantDataSource eds;
    private Bilan1DataSource b1d;
    private Bilan2DataSource b2d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mes_bilans);

        idEtuConnecte = getIntent().getIntExtra("idEtu", 0);
        eds = new EtudiantDataSource(this);
        b1d =  new Bilan1DataSource(this);
        b2d = new Bilan2DataSource(this);

        eds.open();
        b1d.open();
        b2d.open();

        initialisation();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialisation(){

        Button bBilan1 = findViewById(R.id.bBilan1);
        Button bBilan2 = findViewById(R.id.bBilan2);
        ImageView imageViewReturn = findViewById(R.id.imageViewReturn);
        ImageView imageViewEscape = findViewById(R.id.imageViewEscape);

        bBilan1.setOnClickListener(view -> {
            Intent intent = new Intent(MesBilansActivity.this, Bilan1Activity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
        });

        bBilan2.setOnClickListener(view -> {
            Intent intent = new Intent(MesBilansActivity.this, Bilan2Activity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
        });

        imageViewReturn.setOnClickListener(view -> {
            Intent intent = new Intent(MesBilansActivity.this, AccueilActivity.class);
            intent.putExtra("idEtu", idEtuConnecte);
            startActivity(intent);
            finish();
        });

        imageViewEscape.setOnClickListener(view -> {
            Intent intent = new Intent(MesBilansActivity.this, MainActivity.class);
            eds.deleteAllEtudiants();
            b1d.deleteAllBilan1();
            b2d.deleteAllBilan2();
            startActivity(intent);
            finish();
        });
    }
}
