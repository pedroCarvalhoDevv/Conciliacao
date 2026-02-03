package com.demanda.conciliador.demo.controller;

import com.demanda.conciliador.demo.model.dto.UsuarioDTO;
import com.demanda.conciliador.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/usuarios/novo")
    public String novoUsuario(Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", new UsuarioDTO());
        return "usuarios/form";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(UsuarioDTO usuarioDTO, Model model, HttpSession session) {

        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        if (usuarioService.emailJaExiste(usuarioDTO.getEmail())) {
            model.addAttribute("erro", "Email já cadastrado");
            return "usuarios/form";
        }

        usuarioService.salvar(usuarioDTO);
        return "redirect:/dashboard";
    }
}
