package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ProfessorDTO;
import br.com.dbc.vemser.pessoaapi.entity.Professor;
import br.com.dbc.vemser.pessoaapi.entity.pk.ProfessorPK;
import br.com.dbc.vemser.pessoaapi.repository.ProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;

    public List<ProfessorDTO> findAll() {
        return professorRepository.findAll().stream().map(this::getProfessorDTO).toList();
    }

    public ProfessorDTO create(ProfessorCreateDTO professorCreateDTO, Integer idUniversidade) {
        Professor professor = new Professor();
        ProfessorPK professorPK = new ProfessorPK(idUniversidade);

        professor.setProfessorPK(professorPK);
        professor.setNome(professorCreateDTO.getNome());
        professor.setSalario(professorCreateDTO.getSalario());

        return objectMapper.convertValue(professorRepository.save(professor), ProfessorDTO.class);
    }


    private ProfessorDTO getProfessorDTO(Professor professor) {
        return objectMapper.convertValue(professor, ProfessorDTO.class);
    }
}
