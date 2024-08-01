package br.com.dbc.vemser.aula_01_testes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    public double subtrair(double n1, double n2) {
        return n1 - n2;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public BigDecimal dividir(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }

        BigDecimal resultado = new BigDecimal(a / b).setScale(2, RoundingMode.HALF_EVEN);

        return resultado;
    }

    public double raizQuadrada(double a) throws IllegalArgumentException {
        if (a < 0) {
            throw new IllegalArgumentException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(a);
    }

    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }
}
