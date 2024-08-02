package br.com.dbc.vemser.pessoaapi.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProfessorPK implements Serializable {

    @Column(name = "id_professor")
    private Integer idProfessor;

    @Column(name = "id_universidade")
    private Integer idUniversidade;

    public ProfessorPK(Integer idUniversidade) {
        this.idUniversidade = idUniversidade;
    }
}
