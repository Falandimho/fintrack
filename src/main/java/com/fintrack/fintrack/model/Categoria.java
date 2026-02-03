package com.fintrack.fintrack.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="tbl_categoria",
    uniqueConstraints =  {
        @UniqueConstraint(
                name = "uk_categoria_nome_tipo_usuario",
                columnNames = {"nome", "tipo", "usuario_id"}
        )
    }
)
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private CategoriaTipo tipo;

    private Boolean defaultCategory = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
