package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PageDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
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
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        Pessoa pessoa = pessoaService.getPessoa(idPessoa);

        Endereco enderecoEntidade = objectMapper.convertValue(enderecoCreateDTO, Endereco.class);

        Endereco enderecoSalvo = enderecoRepository.save(enderecoEntidade);

        pessoa.addEndereco(enderecoSalvo);

        pessoaRepository.save(pessoa);

        //emailService.sendEmailEnderecoCadastrado(enderecoDTO, pessoaDTO);

        return getEnderecoDTO(enderecoEntidade);
    }

    public List<EnderecoDTO> listar() throws RegraDeNegocioException {
        List<EnderecoDTO> enderecos = enderecoRepository.findAll()
                .stream()
                .map(this::getEnderecoDTO)
                .toList();

        if (enderecos.isEmpty()) {
            throw new RegraDeNegocioException("Não existem enderecos cadastrados!");
        }

        return enderecos;
    }

    public EnderecoDTO listarPorId(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = getEndereco(idEndereco);
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public PageDTO<EnderecoDTO> listarPaginado(Integer pagina, Integer tamanho) throws RegraDeNegocioException {

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("tipo"));
        Page<Endereco> enderecos = enderecoRepository.findAll(pageable);

        Page<EnderecoDTO> enderecoDTOPage = enderecos.map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class));

        if (enderecoDTOPage.isEmpty()) {
            throw new RegraDeNegocioException("Não existem enderecos cadastrados!");
        }

        return new PageDTO<>(
                enderecoDTOPage.getTotalElements(),
                enderecoDTOPage.getTotalPages(),
                enderecoDTOPage.getPageable().getPageNumber(),
                enderecoDTOPage.getSize(),
                enderecoDTOPage.getContent()
        );
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException {
        Endereco endereco = getEndereco(idEndereco);

        Endereco enderecoEntradaUsuario = objectMapper.convertValue(enderecoAtualizar, Endereco.class);

        endereco.setCep(enderecoEntradaUsuario.getCep());
        endereco.setLogradouro(enderecoEntradaUsuario.getLogradouro());
        endereco.setComplemento(enderecoEntradaUsuario.getComplemento());
        endereco.setNumero(enderecoEntradaUsuario.getNumero());
        endereco.setCidade(enderecoEntradaUsuario.getCidade());
        endereco.setEstado(enderecoEntradaUsuario.getEstado());
        endereco.setPais(enderecoEntradaUsuario.getPais());
        endereco.setTipo(enderecoEntradaUsuario.getTipo());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        //emailService.sendEmailEnderecoUpdate(enderecoDTO, pessoaService.getPessoaDTO(enderecoDTO.getIdPessoa()));

        return getEnderecoDTO(enderecoSalvo);
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        Endereco enderecoDeletar = getEndereco(idEndereco);

        //emailService.sendEmailEnderecoDeletado(enderecoDTO, pessoaDTO);

        enderecoRepository.delete(enderecoDeletar);
    }

    private EnderecoDTO getEnderecoDTO(Endereco endereco) {
         return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }


    private Endereco getEndereco(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço de id " + id + " não encontrado!"));
    }
}
