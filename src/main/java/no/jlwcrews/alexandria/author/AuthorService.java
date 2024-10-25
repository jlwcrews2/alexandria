package no.jlwcrews.alexandria.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    AuthorRepo repo;

    @Autowired
    public AuthorService(AuthorRepo repo) {
        this.repo = repo;
    }

    public List<Author> getAuthors() {
        return repo.findAll();
    }

    public Author getAuthor(long id) {
        return repo.findById(id).orElse(null);
    }

    public Author addAuthor(Author author) {
        return repo.save(author);
    }

    public void deleteAuthorById(long id) {
        repo.deleteById(id);
    }
}
