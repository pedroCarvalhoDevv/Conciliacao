package com.demanda.conciliador.demo.controller;

import com.demanda.conciliador.demo.model.dto.EmpresaDTO;
import com.demanda.conciliador.demo.service.EmpresaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public String listar(Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("empresa", empresaService.listarTodas());
        return "empresa/list";
    }

    @GetMapping("/novo")
    public String novo(Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("empresa", new EmpresaDTO());
        return "empresa/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("empresa", empresaService.buscarPorId(id));
        return "empresa/form";
    }

    @PostMapping("/salvar")
    public String salvar(
            EmpresaDTO empresaDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        empresaService.salvar(empresaDTO);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Empresa salva com sucesso!"
        );

        return "redirect:/empresa/novo";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, HttpSession session) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        empresaService.excluir(id);
        return "redirect:/empresa";
    }
}