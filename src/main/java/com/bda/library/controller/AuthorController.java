package com.bda.library.controller;

import com.bda.library.entity.Author;
import com.bda.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    // ── READ: list all authors ────────────────────────────────────────────────
    @GetMapping
    public String list(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    // ── CREATE: show form ─────────────────────────────────────────────────────
    @GetMapping("/new")
    public String newAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("formAction", "/authors");
        model.addAttribute("pageTitle", "Add New Author");
        return "authors/form";
    }

    // ── CREATE: handle submission ─────────────────────────────────────────────
    @PostMapping
    public String createAuthor(@Valid @ModelAttribute("author") Author author,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/authors");
            model.addAttribute("pageTitle", "Add New Author");
            return "authors/form";
        }
        try {
            authorService.save(author);
            redirectAttrs.addFlashAttribute("successMessage", "Author created successfully.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("formAction", "/authors");
            model.addAttribute("pageTitle", "Add New Author");
            model.addAttribute("errorMessage", "An author with this email already exists.");
            return "authors/form";
        }
        return "redirect:/authors";
    }

    // ── UPDATE: show pre-filled form ──────────────────────────────────────────
    @GetMapping("/{id}/edit")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id)
                .orElseThrow(() -> new javax.persistence.EntityNotFoundException("Author not found: " + id));
        model.addAttribute("author", author);
        model.addAttribute("formAction", "/authors/" + id + "/update");
        model.addAttribute("pageTitle", "Edit Author");
        return "authors/form";
    }

    // ── UPDATE: handle submission ─────────────────────────────────────────────
    @PostMapping("/{id}/update")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") Author author,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/authors/" + id + "/update");
            model.addAttribute("pageTitle", "Edit Author");
            return "authors/form";
        }
        try {
            authorService.update(id, author);
            redirectAttrs.addFlashAttribute("successMessage", "Author updated successfully.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("formAction", "/authors/" + id + "/update");
            model.addAttribute("pageTitle", "Edit Author");
            model.addAttribute("errorMessage", "An author with this email already exists.");
            return "authors/form";
        }
        return "redirect:/authors";
    }
}
