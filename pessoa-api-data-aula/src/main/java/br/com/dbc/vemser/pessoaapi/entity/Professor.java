package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.pk.ProfessorPK;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "professor")
public class Professor {


    @EmbeddedId
    @JsonProperty("professorChave")
    private ProfessorPK professorPK;

    @Column(name = "nome")
    private String nome;

    @Column(name = "salario")
    private Double salario;
}
