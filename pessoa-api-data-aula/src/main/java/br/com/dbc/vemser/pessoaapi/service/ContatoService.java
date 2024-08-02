package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PageDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public ContatoDTO create(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        Pessoa pessoa = pessoaService.getPessoa(idPessoa);

        Contato contato = objectMapper.convertValue(contatoCreateDTO, Contato.class);

        contato.setPessoa(pessoa);
        Contato contatoSalvo = contatoRepository.save(contato);

        return objectMapper.convertValue(contatoSalvo, ContatoDTO.class);
    }

    public List<ContatoDTO> listar() throws RegraDeNegocioException {
        List<ContatoDTO> contatos = contatoRepository.findAll()
                .stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .toList();

        if (contatos.isEmpty()) {
            throw new RegraDeNegocioException("Não existem Contatos Cadastrados!");
        }

        return contatos;
    }

    public ContatoDTO listarPorId(Integer idContato) throws RegraDeNegocioException {
        Contato contato = contatoRepository.findById(idContato)
                .orElseThrow(() -> new RegraDeNegocioException("Contato de id " + idContato + " não encontrado."));

        return objectMapper.convertValue(contato, ContatoDTO.class);
    }

    public PageDTO<ContatoDTO> listarPaginado(Integer pagina, Integer tamanho) throws RegraDeNegocioException {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("idContato").ascending());
        Page<Contato> contatos = contatoRepository.findAll(pageable);

        Page<ContatoDTO> contatoDTOPage = contatos.map(contato -> objectMapper.convertValue(contato, ContatoDTO.class));

        if(contatoDTOPage.isEmpty()) throw new RegraDeNegocioException("Não há nenhum contato cadastrado");

        return new PageDTO<>(
                contatoDTOPage.getTotalElements(),
                contatoDTOPage.getTotalPages(),
                contatoDTOPage.getPageable().getPageNumber(),
                contatoDTOPage.getSize(),
                contatoDTOPage.getContent()
        );
    }
}
