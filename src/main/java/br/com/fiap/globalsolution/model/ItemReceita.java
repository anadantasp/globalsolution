package br.com.fiap.globalsolution.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ITEM_RECEITA")
public class ItemReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ITEM_RECEITA")
    @SequenceGenerator(name = "SQ_ITEM_RECEITA", sequenceName = "SQ_ITEM_RECEITA", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ITEM_RECEITA")
    private Long id;

    @Column(name = "DESC_ITEM_RECEITA")
    @NotBlank
    private String descricao;

    @Column(name = "PERIODO_ITEM_RECEITA")
    @NotBlank
    private String periodo;

    @Column(name = "DOSAGEM_ITEM_RECEITA")
    @NotBlank
    private String dosagem;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "MEDICAMENTO",
            referencedColumnName = "ID_MEDICAMENTO",
            foreignKey = @ForeignKey(name = "TB_ITEM_RECEITA_FK_MEDICAMENTO")
    )
    private Medicamento medicamento;
    
}
