package com.fintrack.fintrack.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
