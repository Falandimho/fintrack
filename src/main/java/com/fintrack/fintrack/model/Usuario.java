package com.fintrack.fintrack.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Column(nullable = false)
    private BigDecimal saldoAtual =  BigDecimal.ZERO;

    @OneToMany(mappedBy = "usuario")
    private List<Receita> receitas;

    @OneToMany(mappedBy = "usuario")
    private List<Despesa> despesas;

    public void atualizarSaldo() {
        BigDecimal totalReceitas = receitas.stream()
                .map(Receita::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = despesas.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.saldoAtual = totalReceitas.subtract(totalDespesas);
    }
}
