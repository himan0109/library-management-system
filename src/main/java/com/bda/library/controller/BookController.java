package com.bda.library.controller;

import com.bda.library.entity.Author;
import com.bda.library.entity.Book;
import com.bda.library.service.AuthorService;
import com.bda.library.service.BookService;
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
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    // ── READ: list all books ──────────────────────────────────────────────────
    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // ── READ: inner-join view ─────────────────────────────────────────────────
    @GetMapping("/joined")
    public String joinedView(Model model) {
        model.addAttribute("joinedBooks", bookService.findAllBooksWithAuthorDetails());
        return "books/joined";
    }

    // ── CREATE: show form ─────────────────────────────────────────────────────
    @GetMapping("/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("formAction", "/books");
        model.addAttribute("pageTitle", "Add New Book");
        return "books/form";
    }

    // ── CREATE: handle submission ─────────────────────────────────────────────
    @PostMapping
    public String createBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("formAction", "/books");
            model.addAttribute("pageTitle", "Add New Book");
            return "books/form";
        }
        try {
            bookService.save(book);
            redirectAttrs.addFlashAttribute("successMessage", "Book created successfully.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("formAction", "/books");
            model.addAttribute("pageTitle", "Add New Book");
            model.addAttribute("errorMessage", "A book with this ISBN already exists.");
            return "books/form";
        }
        return "redirect:/books";
    }

    // ── UPDATE: show pre-filled form ──────────────────────────────────────────
    @GetMapping("/{id}/edit")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new javax.persistence.EntityNotFoundException("Book not found: " + id));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("formAction", "/books/" + id + "/update");
        model.addAttribute("pageTitle", "Edit Book");
        return "books/form";
    }

    // ── UPDATE: handle submission ─────────────────────────────────────────────
    @PostMapping("/{id}/update")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("formAction", "/books/" + id + "/update");
            model.addAttribute("pageTitle", "Edit Book");
            return "books/form";
        }
        try {
            bookService.update(id, book);
            redirectAttrs.addFlashAttribute("successMessage", "Book updated successfully.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("formAction", "/books/" + id + "/update");
            model.addAttribute("pageTitle", "Edit Book");
            model.addAttribute("errorMessage", "A book with this ISBN already exists.");
            return "books/form";
        }
        return "redirect:/books";
    }
}
