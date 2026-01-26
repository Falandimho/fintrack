package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.despesa.DespesaInput;
import com.fintrack.fintrack.dto.despesa.DespesaOutput;
import com.fintrack.fintrack.model.Despesa;
import com.fintrack.fintrack.repository.DespesaRepository;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Despesa cadastroDespesa(DespesaInput despesaInput) {
        Despesa despesa = new Despesa();
        BeanUtils.copyProperties(despesaInput, despesa);
        return despesaRepository.save(despesa);
    }

    public DespesaOutput getDespesas(Long id) {

    }
}
