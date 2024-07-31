package br.com.dbc.vemser.aula_01_testes;

public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    public double subtrair(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }

        return a / b;
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
