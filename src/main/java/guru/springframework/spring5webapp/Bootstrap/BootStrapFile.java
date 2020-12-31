package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapFile implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapFile(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {

        Publisher publisher = new Publisher("Penguin Random House", "lombard avenue", "San Francisco",
                "California", 56709);
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design","35973259896");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author martin = new Author("Martin","Fowler");
        Book ref = new Book("Refactoring","989786543");
        martin.getBooks().add(ref);
        ref.getAuthors().add(martin);
        ref.setPublisher(publisher);
        publisher.getBooks().add(ref);

        authorRepository.save(martin);
        bookRepository.save(ref);
        publisherRepository.save(publisher);


        System.out.printf("#Books  : %d, #Authors: %d, #Publishers : %d \n",bookRepository.count(),
                authorRepository.count(), publisherRepository.count());

        System.out.printf("Book Count for Publisher ID %d is %d\n", publisher.getId(), publisher.getBooks().size());
    }
}
