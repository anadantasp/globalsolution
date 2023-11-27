package br.com.fiap.globalsolution.model.Pessoa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_PACIENTE", uniqueConstraints = {
    @UniqueConstraint(name = "UK_PACIENTE_CPF", columnNames = "NR_CPF")
})
@DiscriminatorValue("Paciente")
public class Paciente extends Pessoa{

    @Column(name = "NR_CPF")
    @NotBlank
    private String cpf;

    @Column(name = "DT_NASCIMENTO_PACIENTE")  
    private LocalDate dataNascimento;

    public Paciente(Long id, String nome, String email, String cpf, LocalDate dataNascimento){
        super(id, nome, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        
    }
    
}
