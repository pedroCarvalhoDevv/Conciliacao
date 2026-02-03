package com.demanda.conciliador.demo.service;

import com.demanda.conciliador.demo.model.Empresa;
import com.demanda.conciliador.demo.model.dto.EmpresaDTO;
import com.demanda.conciliador.demo.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public List<EmpresaDTO> listarTodas() {
        return empresaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO buscarPorId(Long id) {
        return empresaRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public void salvar(EmpresaDTO dto) {

        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setIdMateus(dto.getIdMateus());
        empresa.setNome(dto.getNome());

        empresaRepository.save(empresa);
    }

    public void excluir(Long id) {
        empresaRepository.deleteById(id);
    }

    private EmpresaDTO toDTO(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getIdMateus(),
                empresa.getNome()
        );
    }
}
