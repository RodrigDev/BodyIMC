package com.rodrigo.bodyimc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.rodrigo.bodyimc.R;
import com.rodrigo.bodyimc.model.SharedPreferences;

public class LayoutActivity extends IntroActivity {

    SharedPreferences sharedPreferences = new SharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.intro_1);

        if(sharedPreferences.getSharedPrefs(this,"BodyIMC", "primeiroValor") != null){
                // Se tem algum valor guardado, chama o intent pra trocar de tela
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                // primeira vez que abriu, então seta o layout normal...
                //Não escrevi nada para setar o Layout normal, caso queira setar algum layout
                //setContentView(R.layout.layout_desejado);
            }

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .canGoBackward(false)
                .build());
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .canGoForward(false)
                .build());
    }

    public void cliqueButton(View view){

        sharedPreferences.setSharedPrefs(this,"BodyIMC", "primeiroValor", "0101");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}