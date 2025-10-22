package br.edu.fatecjahu.imcapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log; // mostrar log

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EntradaActivity extends AppCompatActivity {

    protected static final String TAG = "Marca"; //tag para log

    //1. Declaração dos objetos Java (elementos de interface).
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

        //2. Associar os elementos de tela (VIEW) aos objetos Java.
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        btnCalcular = findViewById(R.id.btnCalcular);

        //3. Criar o metodo do botão Calcular.
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //4. Captura o conteúdo dos campos de texto
                String pesoStr = edtPeso.getText().toString();
                String alturaStr = edtAltura.getText().toString();

                //5. Verifica se os campos estão preenchidos
                if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                    alert("Preencha peso e altura!");
                    return;
                }

                Log.i(TAG, "Peso:" + pesoStr + " | Altura:" + alturaStr); //enviar log

                try {
                    //6. Converte os valores em float
                    float peso = Float.parseFloat(pesoStr.replace(",", "."));
                    float altura = Float.parseFloat(alturaStr.replace(",", "."));

                    //7. Cria o canal de comunicação Intent (da tela EntradaActivity para ResultadoActivity)
                    Intent intent = new Intent(getContext(0), ResultadoActivity.class);

                    //8. Criar Bundle com os dados(altura, peso)
                    Bundle dadosIMC = new Bundle();

                    dadosIMC.putFloat("peso", peso);
                    dadosIMC.putFloat("altura", altura);

                    //9. Executa a próxima tela levando os dados
                    intent.putExtras(dadosIMC);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    alert("Insira valores numéricos válidos!");
                }
            }
        });
    }

    //10. Metodo alert para mostrar mensagens de notificação do aplicativo.
    private void alert(String msg) {
        //11. A classe Toast mostra uma alerta temporário muito comum no Android.
        Toast toast = Toast.makeText(getContext(0), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0,0);
        toast.show();

        // Toast.LENGTH_LONG tem a duração de 3 a 5 segundos da mensagem de TOAST.
        // Toast.LENGTH_SHORT tem a duração de 1 a 3 segundos da mensagem de TOAST.
        Log.i(TAG, getClassName() + " => Mensagem mostrada ao usuário.");
    }

    private Context getContext(int value) {
        if (value == 1) {
            Log.i(TAG, getClassName() + " => Passou pelo Contexto atual: " + getClassName());
        }
        return this;
    }

    //12. Cria o metodo que retorna o nome da classe atual
    protected String getClassName() {
        String s = getClass().getName();

        return s.substring(s.lastIndexOf("."));
    }

    //13. Metodo onBackPressedDispatcher() para sair do aplicativo.
    @SuppressLint({"MissingSuperCall", "GestureBackNavigation"})
    @Override
    public void onBackPressed() {
        // Instaciação de um objeto alertDialogBuilder (Caixa de Diálogo Personalizada)
        // da classe do tipo AlertDialog.Builder.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        Log.i(TAG, getClassName() + " => Objeto Criado: alertDialogBuilder.");

        //14. Configuração de uma Caixa de Diálogo Personalizada.
        alertDialogBuilder.setTitle("Confirmar Saída");
        alertDialogBuilder.setIcon(R.drawable.ic_exit); // R.drawable.ic_exit;
        alertDialogBuilder.setMessage("Você tem certeza que deseja sair ?");
        alertDialogBuilder.setCancelable(false);

        Log.i(TAG, getClassName() + " => Objeto Configurado: alertDialogBuilder.");

        //15. Configuração do botão "Sim" da Caixa de Diálogo Personalizada...
        // ...e acionamento do metodo onClick permitindo a saída do Aplicativo,
        // através do encerramento da Activity.

        // Metodo do botão "Sim"
        //  setPositiveButton() => DialogInterface.OnClickListener() => onClick()
        alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // Encerra a Activity.
                // Metodo finish faz chamada de forma programática ao
                // metodo OnDestroy() do ciclo de vida da Activity.
                Log.i(TAG, getClassName() + ".onBackPressed() chamado. Botão Sim pressionado.");
            }
        });

        //16. Configuração do botão "Não" da Caixa de Diálogo Personalizada...
        // ...e mostra mensagem de cancelamento de saída do Aplicativo.

        // Metodo do botão "Não"
        //  setNegativeButton() => DialogInterface.OnClickListener() => onClick()
        alertDialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert("Você clicou em não!!!");
                Log.i(TAG, getClassName() + ".onBackPressed() chamado. Botão Não pressionado.");
            }
        });

        //17. Construção de uma Caixa de Diálogo Personalizada
        // e mostra a Caixa de Diálogo criada para o usuário.
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
