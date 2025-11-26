package com.example.bookrec.jdbc;

import com.example.bookrec.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookJdbcDao {

    private final JdbcTemplate jdbc;

    public BookJdbcDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Book> findAll() {
        return jdbc.query("SELECT id, title, author, genre, rating FROM books", new RowMapper<Book>() {
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book b = new Book();
                b.setId(rs.getLong("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setGenre(rs.getString("genre"));
                b.setRating(rs.getInt("rating"));
                return b;
            }
        });
    }
}
