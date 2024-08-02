package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.*;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    //private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public PageDTO<PessoaDTO> list(Integer pagina, Integer tamanho, String filter, String sort) throws RegraDeNegocioException {
        Sort sortType;

        if(sort.equals("ASCENDING")) {
            sortType = Sort.by(filter).ascending().and(Sort.by("idPessoa").ascending());
        } else {
            sortType = Sort.by(filter).ascending().and(Sort.by("idPessoa").ascending());
        }

        Pageable pageable = PageRequest.of(pagina, tamanho, sortType);
        Page<Pessoa> pessoaPage = pessoaRepository.findAll(pageable);

        Page<PessoaDTO> pessoaDTOPage = pessoaPage.map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class));

        if (pessoaDTOPage.isEmpty()) {
            throw new RegraDeNegocioException("Não existem pessoas cadastradas!");
        }

        return new PageDTO<>(
                pessoaDTOPage.getTotalElements(),
                pessoaDTOPage.getTotalPages(),
                pessoaDTOPage.getPageable().getPageNumber(),
                pessoaDTOPage.getSize(),
                pessoaPage.getContent()
        );
    }

    public PessoaDTO findById(Integer id) throws RegraDeNegocioException {
        Pessoa pessoa = getPessoa(id);
        PessoaDTO pessoaDTO = getPessoaDTO(pessoa);
        return pessoaDTO;
    }

    public PessoaDTO findByCpf(String cpf) throws RegraDeNegocioException {
        Pessoa pessoa = pessoaRepository.buscaPessoaPorCpf(cpf)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa com cpf fornecido não encontrada."));

        return objectMapper.convertValue(pessoa, PessoaDTO.class);
    }

    public List<PessoaComContatoDTO> findAllWithContato(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            Pessoa pessoa = getPessoa(idPessoa);
            PessoaComContatoDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComContatoDTO.class);
            pessoaDTO.setContatos(pessoa.getContatos());
            if(pessoaDTO.getContatos().isEmpty()) throw new RegraDeNegocioException("Esta pessoa não possui contatos.");
            return Collections.singletonList(pessoaDTO);
        }

         List<PessoaComContatoDTO> pessoaComContatoDTO =  pessoaRepository.findAll().stream()
                .filter(pessoa -> !pessoa.getContatos().isEmpty())
                .map(pessoa -> {
                    PessoaComContatoDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComContatoDTO.class);
                    pessoaDTO.setContatos(pessoa.getContatos());
                    return pessoaDTO;
                }).toList();

        if(pessoaComContatoDTO.isEmpty()) throw new RegraDeNegocioException("Nenhuma pessoa possui contatos.");
        return pessoaComContatoDTO;
    }

    public List<PessoaComPetsDTO> findAllWithPets(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            Pessoa pessoa = getPessoa(idPessoa);
            PessoaComPetsDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComPetsDTO.class);
            pessoaDTO.setPets(pessoa.getPets());
            if(pessoaDTO.getPets().isEmpty()) throw new RegraDeNegocioException("Esta pessoa não possui pets.");
            return Collections.singletonList(pessoaDTO);
        }

        List<PessoaComPetsDTO> pessoaComPetsDTO = pessoaRepository.findAll().stream()
                .filter(pessoa -> !pessoa.getPets().isEmpty())
                .map(pessoa -> {
                    PessoaComPetsDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComPetsDTO.class);
                    pessoaDTO.setPets(pessoa.getPets());
                    return pessoaDTO;
                }).toList();

        if(pessoaComPetsDTO.isEmpty()) throw new RegraDeNegocioException("Nenhuma pessoa possui pets.");
        return pessoaComPetsDTO;
    }

    public List<PessoaComPetsDTO> findAllWithPetsInRepo(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaRepository.findByIdPessoaAndPetsNotNull(idPessoa)
                    .orElseThrow(() -> new RegraDeNegocioException("Esta pessoa não tem pets"));
            return Collections.singletonList(objectMapper.convertValue(pessoa, PessoaComPetsDTO.class));
        }

        List<PessoaComPetsDTO> pessoaComPetsDTO = pessoaRepository.findAllByPetsNotNull().stream()
                .map(pessoa -> {
                    PessoaComPetsDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComPetsDTO.class);
                    pessoaDTO.setPets(pessoa.getPets());
                    return pessoaDTO;
                }).toList();

        if(pessoaComPetsDTO.isEmpty()) throw new RegraDeNegocioException("Nenhuma pessoa possui pets.");
        return pessoaComPetsDTO;
    }

    public List<PessoaComEnderecoDTO> findAllWithEndereco(Integer idPessoa) throws RegraDeNegocioException {
        if (idPessoa != null) {
            Pessoa pessoa = getPessoa(idPessoa);
            PessoaComEnderecoDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComEnderecoDTO.class);
            pessoaDTO.setEnderecos(pessoa.getEnderecos());
            if(pessoaDTO.getEnderecos().isEmpty()) throw new RegraDeNegocioException("Esta pessoa não possui endereços.");
            return Collections.singletonList(pessoaDTO);
        }

        List<PessoaComEnderecoDTO> pessoaComEnderecoDTO = pessoaRepository.findAll().stream()
                .filter(pessoa -> !pessoa.getEnderecos().isEmpty())
                .map(pessoa -> {
                    PessoaComEnderecoDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaComEnderecoDTO.class);
                    pessoaDTO.setEnderecos(pessoa.getEnderecos());
                    return pessoaDTO;
                }).toList();

        if(pessoaComEnderecoDTO.isEmpty()) throw new RegraDeNegocioException("Nenhuma pessoa possui endereços.");
        return pessoaComEnderecoDTO;
    }

    public List<PessoaDTO> findAllByNome(String nome) throws RegraDeNegocioException {
        List<PessoaDTO> pessoas = pessoaRepository.findAllByNomeContainsIgnoreCase(nome).stream()
                .map(this::getPessoaDTO)
                .toList();

        if (pessoas.isEmpty()) {
            throw new RegraDeNegocioException("Não existem pessoas com o nome " + nome + ".");
        }

        return pessoas;
    }

    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {
        Pessoa pessoaEntidade = objectMapper.convertValue(pessoaCreateDTO, Pessoa.class);
        Pessoa pessoaSalva = pessoaRepository.save(pessoaEntidade);
        //emailService.sendEmailCadastroPessoa(pessoaDTO);
        return getPessoaDTO(pessoaSalva);
    }

    public PessoaDTO update(Integer id,
                            PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = getPessoa(id);
        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        pessoaRecuperada.setEmail(pessoaAtualizar.getEmail());

        Pessoa pessoaSalva = pessoaRepository.save(pessoaRecuperada);
        //emailService.sendEmailUpdatePessoa(pessoaDTO);
        return getPessoaDTO(pessoaSalva);
    }


    public void delete(Integer id) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = getPessoa(id);
        //emailService.sendEmailDeletePessoa(pessoaDTO);
        pessoaRepository.delete(pessoaRecuperada);
    }

    private PessoaDTO getPessoaDTO(Pessoa pessoa) {
        return objectMapper.convertValue(pessoa, PessoaDTO.class);
    }

    public Pessoa getPessoa(Integer id) throws RegraDeNegocioException {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa de id " + id + " não encontrada!"));
    }
}
