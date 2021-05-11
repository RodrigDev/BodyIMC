package com.rodrigo.bodyimc.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rodrigo.bodyimc.model.Calculo;
import com.rodrigo.bodyimc.R;
import com.rodrigo.bodyimc.model.SharedPreferences;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calculo calculo = new Calculo();

    SharedPreferences sharedPreferences = new SharedPreferences();
    LinearLayout linearLayoutTabelaPeso, linearLayoutImagem;
    EditText editTextIdade, editTextAltura, editTextPeso;
    TextView pesoUmC, pesoDoisC, pesoTresC, pesoQuatroC, pesoCincoC, pesoSeisC,
            pesoUmIMC, pesoDoisIMC, pesoTresIMC, pesoQuatroIMC, pesoCincoIMC, pesoSeisIMC,
            pesoIdeal, seuIMC;

    double resultado;
    double pesoIdealFinalUm;
    double pesoIdealFinalDois;
    ArrayList<TextView> cores = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(sharedPreferences.getSharedPrefs(this,"BodyIMC", "primeiroValor") != null){
            // Se tem algum valor guardado, chama o intent pra trocar de tela
        } else {
            startActivity(new Intent(this, LayoutActivity.class));
            finish();
            // primeira vez que abriu, então seta o layout normal...
            //Não escrevi nada para setar o Layout normal, caso queira setar algum layout
            //setContentView(R.layout.layout_desejado);
        }

        inicializarComponentes();

    }

    private void calculo() {

        String campoIdade = editTextIdade.getText().toString();
        String campoAltura = editTextAltura.getText().toString();
        String campoPeso = editTextPeso.getText().toString();

        if (campoIdade.equals("") || campoAltura.equals("") || campoPeso.equals("")) return;

        double altura = Double.parseDouble(editTextAltura.getText().toString());
        double peso = Double.parseDouble(editTextPeso.getText().toString());

        calculo.calculoIMC(altura, peso);
        calculo.calculoPesoIdeal(altura);

        resultado = calculo.getResultado();
        pesoIdealFinalUm = calculo.getPesoIdealUm();
        pesoIdealFinalDois = calculo.getPesoIdealDois();
        resultadoFinal();
    }

    private void resultadoFinal(){
        String campoIdade = editTextIdade.getText().toString();
        int idade = Integer.parseInt(campoIdade);
        if ( idade >= 16 && idade < 60 ){
            seuIMC.setText(String.valueOf(String.format("seu IMC é: %.2f", resultado)));
            if ( pesoIdealFinalUm > 0 && pesoIdealFinalDois > 0 || pesoIdealFinalUm < 2.5 && pesoIdealFinalDois < 2.5 ) {
                String texto1 = String.valueOf(String.format("%.1f" , pesoIdealFinalUm));
                String texto2 = String.valueOf(String.format("%.1f" , pesoIdealFinalDois));
                pesoIdeal.setText(String.valueOf(String.format("Peso ideal é entre: %1$s e %2$s", texto1, texto2 )));
            }else {
                pesoIdeal.setText("Insira uma altura que seja válida!");
            }
            linearLayoutTabelaPeso.setVisibility(View.VISIBLE);
            pesoIdeal.setVisibility(View.VISIBLE);
            linearLayoutImagem.setVisibility(View.GONE);
            mudarCorTexto();
        } else {
            seuIMC.setText("Por favor, visite um médico especializado para informações adequadas referente a sua idade!");
            linearLayoutTabelaPeso.setVisibility(View.INVISIBLE);
            pesoIdeal.setVisibility(View.INVISIBLE);
            linearLayoutImagem.setVisibility(View.VISIBLE);

        }

    }


    private void mudarCorTexto() {

        if ( resultado > 40.00){
            pesoSeisC.setTextColor(getResources().getColor(R.color.vermelhoTres));
            pesoSeisIMC.setTextColor(getResources().getColor(R.color.vermelhoTres));
        }else if ( resultado <= 39.99 && resultado >= 35 ) {
            pesoCincoC.setTextColor(getResources().getColor(R.color.vermelhoDois));
            pesoCincoIMC.setTextColor(getResources().getColor(R.color.vermelhoDois));
        }else if ( resultado <= 34.99 && resultado >= 30 ) {
            pesoQuatroC.setTextColor(getResources().getColor(R.color.vermelhoUm));
            pesoQuatroIMC.setTextColor(getResources().getColor(R.color.vermelhoUm));
        }else if ( resultado <= 29.9 && resultado >= 25 ) {
            pesoTresC.setTextColor(getResources().getColor(R.color.laranja));
            pesoTresIMC.setTextColor(getResources().getColor(R.color.laranja));
        }else if ( resultado <= 24.99 && resultado >= 18.50 ) {
            pesoDoisC.setTextColor(getResources().getColor(R.color.verde));
            pesoDoisIMC.setTextColor(getResources().getColor(R.color.verde));
        }else if ( resultado < 18.50 ) {
            pesoUmC.setTextColor(getResources().getColor(R.color.azul_fraco));
            pesoUmIMC.setTextColor(getResources().getColor(R.color.azul_fraco));
        }
    }

    private void inicializarComponentes() {
        //EditTexts
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextAltura = findViewById(R.id.editTextAltura);
        editTextPeso = findViewById(R.id.editTextPeso);

        //Categoria Peso Text
        pesoUmC = findViewById(R.id.textPesoUmC);
        pesoDoisC = findViewById(R.id.textPesoDoisC);
        pesoTresC = findViewById(R.id.textPesoTresC);
        pesoQuatroC = findViewById(R.id.textPesoQuatroC);
        pesoCincoC = findViewById(R.id.textPesoCincoC);
        pesoSeisC = findViewById(R.id.textPesoSeisC);

        //IMC Peso Text
        pesoUmIMC = findViewById(R.id.textPesoUmIMC);
        pesoDoisIMC = findViewById(R.id.textPesoDoisIMC);
        pesoTresIMC = findViewById(R.id.textPesoTresIMC);
        pesoQuatroIMC = findViewById(R.id.textPesoQuatroIMC);
        pesoCincoIMC = findViewById(R.id.textPesoCincoIMC);
        pesoSeisIMC = findViewById(R.id.textPesoSeisIMC);

        //Text Resultados
        pesoIdeal = findViewById(R.id.textPesoIdeal);
        seuIMC = findViewById(R.id.textIMC);

        //LinearLayout
        linearLayoutTabelaPeso = findViewById(R.id.linearLayoutTabelaPeso);
        linearLayoutImagem = findViewById(R.id.linearLayoutImagem);

        //ArrayList textos
        ArrayList<TextView> cores = new ArrayList<TextView>();
        cores.add(pesoUmC);
        cores.add(pesoUmIMC);
        cores.add(pesoDoisC);
        cores.add(pesoDoisIMC);
        cores.add(pesoTresC);
        cores.add(pesoTresIMC);
        cores.add(pesoQuatroC);
        cores.add(pesoQuatroIMC);
        cores.add(pesoCincoC);
        cores.add(pesoCincoIMC);
        cores.add(pesoSeisC);
        cores.add(pesoSeisIMC);

        //Calcular em tempo real
        ArrayList<EditText> array = new ArrayList<EditText>();
        array.add(editTextAltura);
        array.add(editTextPeso);
        array.add(editTextIdade);

        for (EditText field : array) {
            field.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    for (TextView c : cores) {
                        c.setTextColor(getResources().getColor(R.color.cinza));
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    calculo();

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }
}