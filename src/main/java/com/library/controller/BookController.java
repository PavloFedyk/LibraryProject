package com.library.controller;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.service.author.AuthorDeleterService;
import com.library.service.author.AuthorRetrieverService;
import com.library.service.author.AuthorSaverService;
import com.library.service.book.BookDeleterService;
import com.library.service.book.BookRetrieverService;
import com.library.service.book.BookSaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {

    private final BookRetrieverService bookRetrieverService;
    private final BookSaverService bookSaverService;
    private final BookDeleterService bookDeleterService;
    private final AuthorRetrieverService authorRetrieverService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/save")
    public String save(Model model) {
        Book book = new Book();
        List<Author> authors = authorRetrieverService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);

        return "book-form";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute Book book,
                       BindingResult result,
                       @RequestParam(name = "author_id") Long id) {
        if (result.hasErrors()) {
            return "book-form";
        }
        book.setMainAuthor(authorRetrieverService.findById(id));

        bookSaverService.save(book);

        return "redirect:/" + book.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Book book = new Book();
        List<Author> authors = authorRetrieverService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);

        return "book-form";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute Book book, BindingResult result, @RequestParam(name = "author_id") Long id) {
        Book book1 = bookRetrieverService.findByIdFetchCoAuthors(id);
        if (result.hasErrors()) {
            book.setMainAuthor(authorRetrieverService.findById(id));
            book.getCo_authors().addAll(book1.getCo_authors());
            return "book-update";
        }
        book.setMainAuthor(authorRetrieverService.findById(id));
        book.getCo_authors().addAll(book1.getCo_authors());

        bookSaverService.save(book);

        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping("/{id}/add")
    public String addCoAuthor(@PathVariable Long id, @RequestParam(name = "author_id") Long author_id) {
        Book book = bookRetrieverService.findByIdFetchCoAuthors(id);

        book.getCo_authors().add(authorRetrieverService.findById(author_id));
        bookSaverService.save(book);
        return "redirect:/" + id;
    }

    @DeleteMapping("/{id}")
    public String removeCoAuthor(@PathVariable Long id, @RequestParam(name = "author_id") Long author_id) {
        Book book = bookRetrieverService.findByIdFetchCoAuthors(id);

        book.getCo_authors().remove(authorRetrieverService.findById(author_id));

        bookSaverService.save(book);

        return "redirect:/" + id;
    }
    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        Book book = bookRetrieverService.findByIdFetchCoAuthors(id);
        List<Author> areNotCoAuthors = findAllNotCoAuthor(book);
        Optional<Double> averageTimeOfReadingBook = bookRetrieverService.averageTimeOfReadingBook(book.getTitle());

        model.addAttribute("book", book);
        model.addAttribute("areNotCoAuthors", areNotCoAuthors);
        model.addAttribute("averageTimeOfReadingBook", averageTimeOfReadingBook.orElse((double) 0));

        return "book-info";
    }

    @GetMapping("/findBook")
    public String findBook(@RequestParam("findBook") String str, Model model) {
        List<Book> booksByAuthorOrTitle = bookRetrieverService.findBooksByAuthorAndTitle(str);

        if (!isEmpty(booksByAuthorOrTitle)) {
            model.addAttribute("books", booksByAuthorOrTitle);
        }

        return "book-list";
    }

    @PostMapping("/mostPopular")
    public String findMostPopular(Model model, @RequestParam String from,
                                  @RequestParam String to) {
        List<Book> mostPopularBooks = bookRetrieverService.findMostPopularBook(
                LocalDate.parse(from).atTime(0,0,0),
                LocalDate.parse(to).atTime(23,59,59)
        );

        model.addAttribute("books", mostPopularBooks);

        return "book-list";
    }

    @PostMapping("/mostUnpopular")
    public String findMostUnpopular(Model model, @RequestParam String from,
                                    @RequestParam String to) {
        List<Book> mostPopularBooks = bookRetrieverService.findMostUnpopularBook(
                LocalDate.parse(from).atTime(0,0,0),
                LocalDate.parse(to).atTime(23,59,59)
        );

        model.addAttribute("books", mostPopularBooks);

        return "book-list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookDeleterService.remove(id);
        return "redirect:/";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Book> books = bookRetrieverService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "login";
    }

    private List<Author> findAllNotCoAuthor(Book book) {
        return authorRetrieverService.findAll().stream()
                .filter(author -> !(book.getCo_authors().contains(author)))
                .filter(author -> !book.getMainAuthor().equals(author))
                .collect(Collectors.toList());
    }
}

