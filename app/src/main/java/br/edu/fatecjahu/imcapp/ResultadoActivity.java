package br.edu.fatecjahu.imcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle args = getIntent().getExtras();

        Float peso = args.getFloat("peso");
        Float altura = args.getFloat("altura");

        //1. Vincula os TextViews do layout
        TextView tvPesoResultado = findViewById(R.id.tvPesoResultado);
        TextView tvAlturaResultado = findViewById(R.id.tvAlturaResultado);
        TextView tvRecomendacao2 = findViewById(R.id.tvRecomendacao2);

        //2. Define os textos recebidos
        tvPesoResultado.setText(String.valueOf(peso));
        tvAlturaResultado.setText(String.valueOf(altura));

        //3. Configura botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}