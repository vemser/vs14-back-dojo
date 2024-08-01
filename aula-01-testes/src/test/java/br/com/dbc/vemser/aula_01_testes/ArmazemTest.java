package br.com.dbc.vemser.aula_01_testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmazemTest {
    private Armazem armazem;

    @BeforeEach
    void setup() {
        Produto produto1 = new Produto(1, "Escova de dentes", 0.3, StatusProduto.ATIVO, TipoProduto.HIGIENE);
        Produto produto2 = new Produto(2, "Notebook", 1.4, StatusProduto.ATIVO, TipoProduto.ELETRONICO);
        Produto produto3 = new Produto(3, "Tortuguita ed. Especial", 0.1, StatusProduto.INATIVO, TipoProduto.ALIMENTO);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);

        armazem = new Armazem(1, "Caldeira - Porto Alegre", produtos);
    }

    @Test
    void deveAdicionarUmProdutoComSucesso() {
        //Arrange
        Produto produtoParaAdicioanar = new Produto(4, "Barra de cereal", 0.1, StatusProduto.ATIVO, TipoProduto.ALIMENTO);

        //Act
        armazem.adicionarProduto(produtoParaAdicioanar);

        //Assert
        assertEquals(4, armazem.getProdutos().size());
        assertEquals("Barra de cereal", armazem.getProdutos().get(armazem.getProdutos().size() - 1).getNome());
        assertTrue(armazem.getProdutos().contains(produtoParaAdicioanar));
    }

    @Test
    void deveAlterarUmProdutoComSucesso() throws Exception {
        //Arrange
        Produto produtoParaAlterar = new Produto("Barra de cereal", 0.1, StatusProduto.ATIVO, TipoProduto.ALIMENTO);

        //Act
        Produto produto = armazem.alterarProduto(1, produtoParaAlterar);

        //Assert
//        assertEquals(4, armazem.getProdutos().size());
        assertEquals("Barra de cereal", armazem.getProdutos().get(0).getNome());
        assertEquals(0.1, armazem.getProdutos().get(0).getPeso());
        assertEquals(StatusProduto.ATIVO, armazem.getProdutos().get(0).getStatus());
        assertEquals(TipoProduto.ALIMENTO, armazem.getProdutos().get(0).getTipo());
        assertTrue(produto.getIdProduto() == 1);
    }

    @Test
    void deveLancarUmaExcecaoParaProdutoInexistente() {
        Produto produtoParaAlterar = new Produto("Barra de cereal", 0.1, StatusProduto.ATIVO, TipoProduto.ALIMENTO);

        assertThrows(Exception.class, () -> armazem.alterarProduto(6, produtoParaAlterar));
    }

    @Test
    void removerProdutoComSucesso() throws Exception {
        Produto produtoRemover = armazem.buscarProdutoPorId(1);

        Integer tamanhoLista = armazem.getProdutos().size();

        armazem.removerProduto(produtoRemover);

        assertEquals(armazem.getProdutos().size(), tamanhoLista-1);
    }

    @Test
    void deveRetornarUmProdutoPeloId() throws Exception {


        Integer idProduto = 1;

        armazem.buscarProdutoPorId(idProduto);

        assertEquals(armazem.getProdutos().);
    }

}