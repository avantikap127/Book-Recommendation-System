package com.example.bookrec.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookrec.model.Book;
import com.example.bookrec.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book add(@RequestBody Book b) {
        return service.add(b);
    }

    @GetMapping
    public List<Book> all() {
        return service.all();
    }

    @GetMapping("/<built-in function id>")
    public Book get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/genre/{g}")
    public List<Book> byGenre(@PathVariable("g") String g) {
        return service.byGenre(g);
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> recommend(
        @RequestParam String author,
        @RequestParam String genre,
        @RequestParam int rating) {

    Book b = service.recommendSmart(author, genre, rating);
    return ResponseEntity.ok(b);
}


    @GetMapping(value = "/export", produces = "text/plain")
    public ResponseEntity<byte[]> export() {
        List<Book> list = service.all();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos);
        pw.println("Reading List:");
        int i=1;
        for (Book b : list) {
            pw.printf("%d. %s - %s (%d/5)\n", i++, b.getTitle(), b.getAuthor(), b.getRating());
        }
        pw.flush();
        byte[] data = baos.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reading-list.txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(data);
    }
}
