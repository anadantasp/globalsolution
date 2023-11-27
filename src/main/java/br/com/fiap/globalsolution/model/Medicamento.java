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
@Table(name = "TB_MEDICAMENTO", uniqueConstraints = {
    @UniqueConstraint(name = "UK_MEDICAMENTO_NOME", columnNames = "NM_MEDICAMENTO")
})
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEDICAMENTO")
    @SequenceGenerator(name = "SQ_MEDICAMENTO", sequenceName = "SQ_MEDICAMENTO", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_MEDICAMENTO")
    private Long id;

    @Column(name = "NM_MEDICAMENTO")
    @NotBlank
    private String nome;
    
}
