package br.com.fiap.globalsolution.model.Pessoa;

import br.com.fiap.globalsolution.model.Especialidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "TB_MEDICO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_MEDICO_CRM", columnNames = "NR_CRM")
})
@DiscriminatorValue("MEDICO")
public class Medico extends Pessoa{

    @Column(name = "NR_CRM")
    @NotBlank
    private String crm;

    @Column(name = "SENHA_MEDICO")
    @NotBlank
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ESPECIALIDADE",
            referencedColumnName = "ID_ESPECIALIDADE",
            foreignKey = @ForeignKey(name = "TB_MEDICO_FK_ESPECIALIDADE")
    )
    private Especialidade especialidade;

    public Medico(Long id, String nome, String email, String crm, String senha, Especialidade especialidade){
        super(id, nome, email);
        this.crm = crm;
        this.senha = senha;
        this.especialidade = especialidade;
    }

    
    
}
