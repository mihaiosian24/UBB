package BookStore.Controller;

import BookStore.Domain.Book;
import BookStore.Domain.Client;
import BookStore.Domain.Validators.ValidatorException;
import BookStore.Repository.Repository;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Controller {
    private Repository<Long,Book> bookRepository;
    private Repository<Long,Client> clientRepository;
    private Repository<Long,Book> purchaseRepository;

    public Controller(Repository<Long, Book> bookRepository, Repository<Long, Client> clientRepository, Repository<Long, Book> purchaseRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void addBook(Book book) throws ValidatorException, sun.security.validator.ValidatorException {
        bookRepository.save(book);
    }

    public Set<Book> getAllBooks(){
        Iterable<Book> books=bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(),false).collect(Collectors.toSet());
    }

    public void updateBook(Book book) throws sun.security.validator.ValidatorException{
        bookRepository.update(book);
    }
    public void deleteBook(Long id){
        bookRepository.delete(id);
    }

    public Set<Book> filterBooksByAuthor(String s){
        Iterable<Book> books=bookRepository.findAll();
        Set<Book> filteredBooks=new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book->!book.getAuthor().contains(s));
        return filteredBooks;
    }

//

    public void addPurchased(Long id,Book book){
        purchaseRepository.savePurchased(id,book);
    }

    public Set<Book> getAllPurchased(){
        Iterable<Book> books=purchaseRepository.findAll();
        return StreamSupport.stream(books.spliterator(),false).collect(Collectors.toSet());
    }
//
    public void addClient(Client client) throws ValidatorException, sun.security.validator.ValidatorException {
    clientRepository.save(client);
    }

    public Set<Client> getAllClients(){
        Iterable<Client> clients=clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(),false).collect(Collectors.toSet());
    }

    public void updateClient(Client client) throws sun.security.validator.ValidatorException{
        clientRepository.update(client);
    }
    public void deleteClient(Long id){
        clientRepository.delete(id);
    }

    public Set<Client> filterClientsByName(String s){
        Iterable<Client> clients=clientRepository.findAll();
        Set<Client> filteredClients=new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(client->!client.getName().contains(s));
        return filteredClients;
    }

    public Set<Book> sortByAmount(){
        Iterable<Book> books=bookRepository.findAll();
        Set<Book> sortedBooks=new HashSet<>();
        books.forEach(sortedBooks::add);
        sortedBooks.stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toSet());
        return sortedBooks;

    }

}
