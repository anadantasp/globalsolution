package br.com.fiap.globalsolution.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ESPECIALIDADE", uniqueConstraints = {
    @UniqueConstraint(name = "UK_ESPECIALIDADE_NOME", columnNames = "NM_ESPECIALIDADE")
})
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESPECIALIDADE")
    @SequenceGenerator(name = "SQ_ESPECIALIDADE", sequenceName = "SQ_ESPECIALIDADE", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ESPECIALIDADE")
    private Long id;

    @Column(name = "NM_ESPECIALIDADE")
    @NotBlank
    private String nome;
    
}
