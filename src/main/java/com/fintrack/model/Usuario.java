package com.fintrack.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Column(nullable = false)
    private BigDecimal saldoAtual =  BigDecimal.ZERO;

    @OneToMany(mappedBy = "tbl_usuario")
    private List<Receita> receitas;

    @OneToMany(mappedBy = "tbl_usuario")
    private List<Despesas> despesas;

    public void atualizarSaldo() {
        BigDecimal totalReceitas = receitas.stream()
                .map(Receita::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = despesas.stream()
                .map(Despesas::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.saldoAtual = totalReceitas.subtract(totalDespesas);
    }
}
