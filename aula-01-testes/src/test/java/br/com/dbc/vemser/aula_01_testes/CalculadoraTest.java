package br.com.dbc.vemser.aula_01_testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    void deveSomarDoisAlgarismosPositivosComSucesso() {
        //Arrange
        double n1 = 3;
        double n2 = 2;

        //Act
        double resultado = calculadora.somar(n1, n2);

        //Assert
        assertEquals(5, resultado);
    }

    @Test
    void deveSubtrairDoisAlgarismosPositivosComSucesso() {
        //Arrange
        double n1 = 10;
        double n2 = 5;

        //Act
        double resultado = calculadora.subtrair(n1, n2);

        //Assert
        assertEquals(5, resultado);
    }

    @Test
    @DisplayName("Deve subtrair com um algarismo positivo e um algarismo negativo")
    void deveSubtrairComUmAlgarismoPositivoEUmAlgarismoNegativo() {
        //Arrange
        double n1 = 3;
        double n2 = -3;

        //Act
        double resultado = calculadora.subtrair(n1, n2);

        //Assert
        assertEquals(6, resultado);
    }

    @Test
    void deveMultiplicarDoisAlgarismosPositivos() {
        //Arrange
        double n1 = 5;
        double n2 = 5;

        double resultado = calculadora.multiplicar(n1, n2);

        assertEquals(25, resultado);
    }

    @Test
    void deveMultiplicarUmNumeroPorZero() {
        //Arrange
        double n1 = 5;
        double n2 = 0;

        double resultado = calculadora.multiplicar(n1, n2);

        assertEquals(0, resultado);
    }

    @Test
    void deveDividirDoisAlgarismosComSucesso() {
        //Arrange
        double n1 = 6;
        double n2 = 2;

        BigDecimal resultado = calculadora.dividir(n1, n2);

        assertEquals(3, resultado);
    }

    @Test
    void deveDividirAlgarismoQueResultaEmMultiplasCasasDecimaisEArredondar() {
        //Arrange
        double n1 = 10;
        double n2 = 3;

        BigDecimal resultado = calculadora.dividir(n1, n2);

        assertEquals(3.33, resultado.doubleValue());
    }

    @Test
    void naoDeveLancarExcecaoAoDividirDoisAlgarismosPositivos() {
        //Arrange
        double n1 = 6;
        double n2 = 2;

        assertDoesNotThrow(() -> calculadora.dividir(n1, n2));
    }

    @Test
    void deveDividirPorZeroELancarExcecao() {
        double n1 = 4;
        double n2 = 0;

        assertThrows(ArithmeticException.class, () -> calculadora.dividir(n1, n2));
    }

    @Test
    void deveCalcularPotenciaDeDosNumerosPositios() {
        //Arrange
        double base = 2;
        double expoente = 3;

        //Act
        double resultado = calculadora.potencia(base, expoente);

        //Assert
        assertEquals(8, resultado);
    }

    @Test
    void deveCalcularPotenciaDezero(){
        //Arrange
        double base = 2;
        double expoente = 0;

        //Act
        double resultado = calculadora.potencia(base, expoente);

        //Assert
        assertEquals(1, resultado);

    }
    @Test
    void deveCalcularPotencianegativa(){
        //Arrange
        double base = 2;
        double expoente = -2;

        //Act
        double resultado = calculadora.potencia(base, expoente);

        //Assert
        assertEquals(0.25, resultado);

    }

    @Test
    void deveCalcularRaizQuadradaPositiva(){
        //Arrange
        double base = 2;
        double expoente = -2;

        //Act
        double resultado = calculadora.potencia(base, expoente);

        //Assert
        assertEquals(0.25, resultado);

    }

}