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

    public static final String EXTRA_PESO = "br.edu.fatecjahu.mensagemapp.PESO";
    public static final String EXTRA_ALTURA = "br.edu.fatecjahu.mensagemapp.ALTURA";
    public static final String EXTRA_RECOMENDACAO = "br.edu.fatecjahu.mensagemapp.RECOMENDACAO";

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
        assert args != null;
        Float     peso = args.getFloat("peso");
        Float     altura = args.getFloat("altura");

//        Intent intent = getIntent();
//        String peso = intent.getFloatExtra(peso);
//        String altura = intent.getFloatExtra(altura);

        // Vincula os TextViews do layout
        TextView tvPesoResultado = findViewById(R.id.tvPesoResultado);
        TextView tvAlturaResultado = findViewById(R.id.tvAlturaResultado);
        TextView tvRecomendacao2 = findViewById(R.id.tvRecomendacao2);

        // Define os textos recebidos
        tvPesoResultado.setText(String.valueOf(peso));
        tvAlturaResultado.setText(String.valueOf(altura));
        //tvRecomendacao2.setText(recomendacao);

        // Configura botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish()); // Fecha a activity e volta para a anterior
    }
}