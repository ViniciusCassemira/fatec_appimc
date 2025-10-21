package br.edu.fatecjahu.imcapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log; // mostrar log

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EntradaActivity extends AppCompatActivity {

    /* 1. Criação de chaves públicas para armazenar os dados de altura e peso.
       Essas chaves públicas serão usadas no objeto Intent (putExtra)
       para enviar as informações da tela de entrada para a tela de resultados. */
    public static final String EXTRA_PESO = "br.edu.fatecjahu.imcapp.PESO";
    public static final String EXTRA_ALTURA = "br.edu.fatecjahu.imcapp.ALTURA";

    protected static final String TAG = "Marca"; //tag para log

    /* 2. Declaração dos objetos Java (elementos de interface). */
    TextView edtPeso, edtAltura;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entrada);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* 3. Associar os elementos de tela (VIEW) aos objetos Java. */
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        btnCalcular = findViewById(R.id.btnCalcular);

        /* 4. Criar o metodo do botão Calcular. */
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Captura o conteúdo dos campos de texto
                String pesoStr = edtPeso.getText().toString();
                String alturaStr = edtAltura.getText().toString();

                // Verifica se os campos estão preenchidos
                if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                    Toast.makeText(getContext(), "Preencha peso e altura!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.i(TAG, "Peso:" + pesoStr + " | Altura:" + alturaStr); //enviar log

                try {
                    // 6. Converte os valores em float
                    float peso = Float.parseFloat(pesoStr.replace(",", "."));
                    float altura = Float.parseFloat(alturaStr.replace(",", "."));

                    // 7. Cria o canal de comunicação Intent (da tela EntradaActivity para ResultadoActivity)
                    Intent intent = new Intent(getContext(), ResultadoActivity.class);

                    // ?. Criar Bundle com os dados(altura, peso, resultado)
                    Bundle dadosIMC = new Bundle();

                    // 8. Carrega os dados (peso e altura) no objeto Intent com as chaves públicas
                    // intent.putExtra(EXTRA_PESO, peso);
                    // intent.putExtra(EXTRA_ALTURA, altura);

                    dadosIMC.putFloat("peso", peso);    //    nome = "Alex Batista";
                    dadosIMC.putFloat("altura", altura);

                    // 9. Executa a próxima tela levando os dados
                    intent.putExtras(dadosIMC);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Insira valores numéricos válidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /* Metodo auxiliar para retornar o contexto da Activity */
    private Context getContext() {
        return this;
    }
}
