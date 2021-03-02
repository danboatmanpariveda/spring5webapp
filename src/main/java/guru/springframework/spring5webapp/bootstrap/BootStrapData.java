package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pub = new Publisher(
                "Penguin Random House",
                "123 Main St",
                "",
                "New York",
                "New York",
                "12345"
        );

        publisherRepository.save(pub);

        Author fred = new Author("Fredrik", "Backman");
        Book ap = new Book("Anxious People", "9781797105826");
        fred.getBooks().add(ap);
        ap.getAuthors().add(fred);
        ap.setPublisher(pub);
        pub.getBooks().add(ap);

        authorRepository.save(fred);
        bookRepository.save(ap);
        publisherRepository.save(pub);

        Author john = new Author("John", "Lewis");
        Author mike = new Author("Michael", "D'Orso");
        Book wwtw = new Book("Walking with the Wind", "9781476797717");
        john.getBooks().add(wwtw);
        wwtw.getAuthors().add(john);
        wwtw.getAuthors().add(mike);
        wwtw.setPublisher(pub);
        pub.getBooks().add(wwtw);

        authorRepository.save(john);
        authorRepository.save(mike);
        bookRepository.save(wwtw);
        publisherRepository.save(pub);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Books: " + publisherRepository.count());
    }
}
