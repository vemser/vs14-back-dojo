package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PetDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.entity.Pet;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public PetDTO create(Integer idPessoa, PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        Pessoa pessoa = pessoaService.getPessoa(idPessoa);

        Pet petEntidade = objectMapper.convertValue(petCreateDTO, Pet.class);

        petEntidade.setPessoa(pessoa);

        Pet petSalvo = petRepository.save(petEntidade);

        return getPetDTO(petSalvo);
    }

    public List<PetDTO> findAll() {
        return petRepository.findAll().stream()
                .map(this::getPetDTO)
                .toList();
    }

    public PetDTO findById(Integer idPet) throws RegraDeNegocioException {
        Pet pet = petRepository.findById(idPet)
                .orElseThrow(() -> new RegraDeNegocioException("Pet de id " + idPet + " não encontrado." ));

        return getPetDTO(pet);
    }

    private PetDTO getPetDTO(Pet pet) {
        PetDTO petDTO = objectMapper.convertValue(pet, PetDTO.class);
        petDTO.setIdPessoa(pet.getPessoa().getIdPessoa());
        return petDTO;
    }
}
