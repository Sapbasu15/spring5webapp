package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapFile implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public BootStrapFile(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design","35973259896");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author martin = new Author("Martin","Fowler");
        Book ref = new Book("Refactoring","989786543");
        martin.getBooks().add(ref);
        ref.getAuthors().add(martin);

        authorRepository.save(martin);
        bookRepository.save(ref);

        System.out.printf("Books  : %d, Authors: %d\n",bookRepository.count(), authorRepository.count());

    }
}
