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
import androidx.core.content.ContextCompat;

public class ResultadoActivity extends AppCompatActivity {

    public String cor_fundo = "";

    // Metodo para calcular o imc
    private double calcularIMC(float peso, float altura) {
        double imc = peso / (altura * altura);
        return Math.round(imc * 10.0) / 10.0;
    }

    // Metodo para classificar o IMC
    private String classificarIMC(double imc) {
        if (imc < 19.0) {
            cor_fundo = "red";
            return "Abaixo do Peso Normal";
        } else if (imc >= 19.0 && imc <= 24.9) {
            cor_fundo = "green";
            return "Peso Normal";
        } else if (imc >= 25.0 && imc <= 29.9) {
            cor_fundo = "yellow";
            return "Sobrepeso";
        } else if (imc >= 30.0 && imc <= 34.9) {
            cor_fundo = "orange";
            return "Obesidade Grau I";
        } else if (imc >= 35.0 && imc <= 39.9) {
            cor_fundo = "dark_orange";
            return "Obesidade Grau II";
        } else {
            cor_fundo = "dark_red";
            return "Obesidade Grau III ou Obesidade Mórbida";
        }
    }

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

        Bundle meusDadosIMC = getIntent().getExtras();

        Float peso = meusDadosIMC.getFloat("peso");
        Float altura = meusDadosIMC.getFloat("altura");

        // Armazenando cálculo do imc
        double imc = calcularIMC(peso, altura);

        // Armazenando classificação do imc
        String classificacaoIMC = classificarIMC(imc);

        //1. Vincula os TextViews do layout
        TextView tvPesoResultado = findViewById(R.id.tvPesoResultado);
        TextView tvAlturaResultado = findViewById(R.id.tvAlturaResultado);
        TextView tvRecomendacao2 = findViewById(R.id.tvRecomendacao2);
        TextView tvResultado2 = findViewById(R.id.tvResultado2);

        //2. Define os textos para serem exibidos na activity
        tvPesoResultado.setText(String.valueOf(peso));
        tvAlturaResultado.setText(String.valueOf(altura));
        tvResultado2.setText("O IMC é igual a " + imc + ", portanto, você está na faixa de " + classificacaoIMC);

        // Configurando cores na parte de resultado
        tvResultado2.setTextColor(ContextCompat.getColor(this, R.color.white));
        int colorId = getResources().getIdentifier(cor_fundo, "color", getPackageName());
        tvResultado2.setBackgroundColor(ContextCompat.getColor(this, colorId));

        // Configurando cor de texto da recomendação
        tvRecomendacao2.setTextColor(ContextCompat.getColor(this, R.color.white));

        // Definir se precisa ou não fazer regime e selecionar sua cor de fundo
        if (imc >= 25.0) {
            tvRecomendacao2.setText("Sim é necessário fazer regime");
            tvRecomendacao2.setBackgroundColor(ContextCompat.getColor(this, R.color.recomendacao_red));
        } else {
            tvRecomendacao2.setText("Não precisa fazer regime");
            tvRecomendacao2.setBackgroundColor(ContextCompat.getColor(this, R.color.recomendacao_green));
        }

        //3. Configura botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}