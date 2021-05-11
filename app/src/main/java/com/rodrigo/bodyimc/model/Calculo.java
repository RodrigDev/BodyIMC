package com.rodrigo.bodyimc.model;

public class Calculo {

    private double altura;
    private double peso;
    private double resultado;
    private double pesoIdealUm;
    private double pesoIdealDois;

    public void calculoIMC(double altura, double peso) {
        if ( altura > 2.5 ) {
            altura = altura / 100;
        }
            double resultado = peso / ( altura * altura );
            setResultado(resultado);
    }

    public void calculoPesoIdeal( double altura){
        double alturaIdeal = altura;
        if ( alturaIdeal > 2.5 ) {
            alturaIdeal = alturaIdeal / 100;
        }
            pesoIdealUm = ( alturaIdeal * alturaIdeal ) * 18.5;
            pesoIdealDois = ( alturaIdeal * alturaIdeal ) * 25;
        setPesoIdealUm(pesoIdealUm);
        setPesoIdealDois(pesoIdealDois);

    }

    public double getPesoIdealUm() {
        return pesoIdealUm;
    }

    public void setPesoIdealUm(double pesoIdealUm) {
        this.pesoIdealUm = pesoIdealUm;
    }

    public double getPesoIdealDois() {
        return pesoIdealDois;
    }

    public void setPesoIdealDois(double pesoIdealDois) {
        this.pesoIdealDois = pesoIdealDois;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}