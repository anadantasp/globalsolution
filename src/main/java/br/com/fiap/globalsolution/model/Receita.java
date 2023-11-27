package br.com.fiap.globalsolution.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.fiap.globalsolution.model.Pessoa.Medico;
import br.com.fiap.globalsolution.model.Pessoa.Paciente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_RECEITA")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECEITA")
    @SequenceGenerator(name = "SQ_RECEITA", sequenceName = "SQ_RECEITA", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_RECEITA")
    private Long id;

    @Column(name = "DT_RECEITA")
    private LocalDate data;

    @Column(name = "DT_VALIDADE_RECEITA")
    private LocalDate dataValidade;

    @Column(name = "STATUS_RECEITA")
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "MEDICO",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "TB_RECEITA_FK_MEDICO")
    )
    private Medico medico;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "PACIENTE",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "TB_RECEITA_FK_PACIENTE")
    )
    private Paciente paciente;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "TB_RECEITA_ITEM_RECEITA",
            joinColumns = {
                    @JoinColumn(
                            name = "RECEITA",
                            referencedColumnName = "ID_RECEITA",
                            foreignKey = @ForeignKey(name = "FK_TB_RECEITA_RECEITA")
                    )
            },
            inverseJoinColumns = {

                    @JoinColumn(
                            name = "ITEM_PEDIDO",
                            referencedColumnName = "ID_ITEM_RECEITA",
                            foreignKey = @ForeignKey(name = "FK_TB_RECEITA_ITEM_RECEITA")
                    )
            }
    )
    private Set<ItemReceita> medicamentos = new HashSet<>();

    public Receita addSolicitacao(ItemReceita itemReceita){
        medicamentos.add(itemReceita);
        return this;
    }

    public Receita removeSolicitacao(ItemReceita itemReceita){
        medicamentos.remove(itemReceita);
        return this;
    }
    
    public Receita withId(Long id){
        this.id = id;
        return this;
    }

    public Receita withData(LocalDate data){
        this.data = data;
        return this;
    }

    public Receita withDataValidade(LocalDate dataValidade){
        this.dataValidade = dataValidade;
        return this;
    }

    public Receita withStatus(boolean status){
        this.status = status;
        return this;
    }

    public Receita withMedico(Medico medico){
        this.medico = medico;
        return this;
    }

    public Receita withPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;
    }
    


}
