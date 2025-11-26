package com.example.bookrec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.bookrec.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    // 1. Match author + genre + rating
    List<Book> findByAuthorIgnoreCaseAndGenreIgnoreCaseAndRatingGreaterThanEqual(
            String author, String genre, int rating);

    // 2. Match author + genre
    List<Book> findByAuthorIgnoreCaseAndGenreIgnoreCase(String author, String genre);

    // 3. Match genre + rating
    List<Book> findByGenreIgnoreCaseAndRatingGreaterThanEqual(String genre, int rating);

    // 4. Match author + rating
    List<Book> findByAuthorIgnoreCaseAndRatingGreaterThanEqual(String author, int rating);

    // 5. Match rating only
    List<Book> findByRatingGreaterThanEqual(int rating);

    // --- ADD THIS METHOD (fixes your compilation error) ---
    // simple genre-only finder used by some parts of the service
    List<Book> findByGenreIgnoreCase(String genre);

    // 6. Top rated fallback
    @Query("SELECT b FROM Book b ORDER BY b.rating DESC")
    List<Book> findTopRated();
}
