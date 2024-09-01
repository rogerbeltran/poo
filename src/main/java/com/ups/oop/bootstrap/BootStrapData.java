package com.ups.oop.bootstrap;


import com.ups.oop.dto.Person;
import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Author;
import com.ups.oop.entity.Book;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.AuthorRepository;
import com.ups.oop.repository.BookRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person pe1 = new Person();
        pe1.setPersonId("0998377188");
        pe1.setName("Luis");
        pe1.setLastName("García");
        pe1.setAge(38);

        Person pe2 = new Person();
        pe2.setPersonId("0987311633");
        pe2.setName("Sandra");
        pe2.setLastName("Olivares");
        pe2.setAge(42);

        Person pe3 = new Person();
        pe3.setPersonId("0987311633");
        pe3.setName("Juliana");
        pe3.setLastName("Sanchez");
        pe3.setAge(29);

        personRepository.save(pe1);
        personRepository.save(pe2);
        personRepository.save(pe3);


        Animal ani1 = new Animal();
        ani1.setPetName("Lady");
        ani1.setName("Golden");
        ani1.setBreed("Retriever");
        ani1.setColor("Brown");
        ani1.setLength(1.45);
        ani1.setHeight(0.70);
        ani1.setWeight(12);

        Animal ani2 = new Animal();
        ani2.setPetName("Max");
        ani2.setName("French");
        ani2.setBreed("Puddle");
        ani2.setColor("White");
        ani2.setLength(0.90);
        ani2.setHeight(0.25);
        ani2.setWeight(6);

        Animal ani3 = new Animal();
        ani3.setPetName("June");
        ani3.setName("Siberian");
        ani3.setBreed("Husky");
        ani3.setColor("Gray");
        ani3.setLength(1.40);
        ani3.setHeight(0.50);
        ani3.setWeight(15);

        animalRepository.save(ani1);
        animalRepository.save(ani2);
        animalRepository.save(ani3);

        Author author1 = new Author();
        author1.setName("Lewis ");
        author1.setLastName("Carroll");
        authorRepository.save(author1);

        Book book1 = new Book();
        book1.setTitle("Alicia en el país de las maravillas");
        book1.setEditorial("Macmillan Publishers");
        book1.setAuthor(author1);
        bookRepository.save(book1);

        author1.getBooks().add(book1);
        authorRepository.save(author1);

        //2
        Author author2 = new Author();
        author2.setName("Gabriel");
        author2.setLastName("García Márquez");
        authorRepository.save(author2);

        Book book2 = new Book();
        book2.setTitle("Cien años de soledad");
        book2.setEditorial("Editorial Sudamericana");
        book2.setAuthor(author2);
        bookRepository.save(book2);

        author2.getBooks().add(book2);
        authorRepository.save(author2);


        //3

        Author author3 = new Author();
        author3.setName("Jane");
        author3.setLastName("Austen");
        authorRepository.save(author3);

        //3
        Book book3 = new Book();
        book3.setTitle("Orgullo y prejuicio");
        book3.setEditorial("T. Egerton");
        book3.setAuthor(author3);
        bookRepository.save(book3);

        author3.getBooks().add(book3);
        authorRepository.save(author3);

        System.out.println("--------------Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());
        System.out.println("Number of Animals: " + animalRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
    }
}