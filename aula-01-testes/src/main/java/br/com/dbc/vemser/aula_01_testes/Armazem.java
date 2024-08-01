package br.com.dbc.vemser.aula_01_testes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Armazem {
    private Integer idArmazem;
    private String localizacao;
    private List<Produto> produtos;


    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public Produto buscarProdutoPorId(Integer idProduto) throws Exception {
        Produto produtoEncontrado = produtos.stream().filter(produto -> produto.getIdProduto() == idProduto)
                .findFirst().orElseThrow(() -> new Exception("Produto não encontrado!"));

        return produtoEncontrado;
    }

    public Produto alterarProduto(Integer idProduto, Produto produtoAlterado) throws Exception {
        Produto produtoEncontrado = buscarProdutoPorId(idProduto);

        produtoEncontrado.setPeso(produtoAlterado.getPeso());
        produtoEncontrado.setNome(produtoAlterado.getNome());
        produtoEncontrado.setTipo(produtoAlterado.getTipo());
        produtoEncontrado.setStatus(produtoAlterado.getStatus());

        return produtoEncontrado;
    }

    public void fecharArmazem(){
        for (Produto produto : produtos){
            produto.setStatus(StatusProduto.INATIVO);
        }
    }

    public void removerProdutoPorCategoria(TipoProduto tipoProduto) throws Exception {

    }


}
