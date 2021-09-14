package com.library.controller;

import com.library.entity.Author;
import com.library.service.author.AuthorDeleterService;
import com.library.service.author.AuthorRetrieverService;
import com.library.service.author.AuthorSaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRetrieverService authorRetrieverService;
    private final AuthorDeleterService authorDeleterService;
    private final AuthorSaverService authorSaverService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/save")
    public String create(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "author-form";
    }

    @PostMapping("/save")
    public String create(@Validated @ModelAttribute Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author-list";
        }

        authorSaverService.save(author);

        return "redirect:/author";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        Author author = authorRetrieverService.findByFetchBooks(id);

        model.addAttribute("author", author);

        return "author-info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        authorDeleterService.remove(id);

        return "redirect:/author";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping
    public String getAll(Model model) {
        List<Author> authors = authorRetrieverService.findAll();

        model.addAttribute("authors", authors);

        return "author-list";
    }
}