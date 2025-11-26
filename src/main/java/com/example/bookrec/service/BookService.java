package com.example.bookrec.service;

import com.example.bookrec.model.Book;
import com.example.bookrec.repository.BookRepository;
import com.example.bookrec.exception.BookNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class BookService {

    private final BookRepository repo;
    private final Random rnd = new Random();

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book add(Book b) {
        if (b.getRating() < 1 || b.getRating() > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");
        return repo.save(b);
    }

    public List<Book> all() {
        return repo.findAll();
    }

    public Book get(Long id) {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public List<Book> byGenre(String genre) {
        return repo.findByGenreIgnoreCase(genre);
    }

   public Book recommendSmart(String author, String genre, int rating) {

    // 1. Match ALL THREE
    var matchAll = repo
            .findByAuthorIgnoreCaseAndGenreIgnoreCaseAndRatingGreaterThanEqual(author, genre, rating);
    if (!matchAll.isEmpty()) return matchAll.get(0);

    // 2. Match AUTHOR + GENRE
    var matchAuthorGenre = repo
            .findByAuthorIgnoreCaseAndGenreIgnoreCase(author, genre);
    if (!matchAuthorGenre.isEmpty()) return matchAuthorGenre.get(0);

    // 3. Match GENRE + RATING
    var matchGenreRating = repo
            .findByGenreIgnoreCaseAndRatingGreaterThanEqual(genre, rating);
    if (!matchGenreRating.isEmpty()) return matchGenreRating.get(0);

    // 4. Match AUTHOR + RATING
    var matchAuthorRating = repo
            .findByAuthorIgnoreCaseAndRatingGreaterThanEqual(author, rating);
    if (!matchAuthorRating.isEmpty()) return matchAuthorRating.get(0);

    // 5. Match RATING only
    var matchRating = repo.findByRatingGreaterThanEqual(rating);
    if (!matchRating.isEmpty()) return matchRating.get(0);

    // 6. Fallback Top Rated
    return repo.findTopRated().get(0);
}


}
