package com.demanda.conciliador.demo.controller;

import com.demanda.conciliador.demo.model.dto.UsuarioDTO;
import com.demanda.conciliador.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;

    // Redireciona a raiz para login
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Exibe a tela de login
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Processa o login
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String senha,
            Model model,
            HttpSession session
    ) {

        UsuarioDTO usuario = usuarioService.validarLogin(email, senha);

        if (usuario == null) {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }

        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
