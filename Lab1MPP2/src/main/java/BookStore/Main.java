package BookStore;

import BookStore.Controller.Controller;
import BookStore.Domain.Book;
import BookStore.Domain.Client;
import BookStore.Domain.Validators.BookValidator;
import BookStore.Domain.Validators.ClientValidator;
import BookStore.Domain.Validators.Validator;
import BookStore.Repository.InMemoryRepository;
import BookStore.Repository.NewXmlRepository;
import BookStore.Repository.Repository;
import BookStore.Repository.XmlRepository;
import BookStore.View.UI;
import sun.security.validator.ValidatorException;

public class Main {
    public static void main(String[] args){
        Validator<Book> bookValidator=new BookValidator();
        Validator<Client> clientValidator=new ClientValidator();
        Repository<Long,Book> bookRepository=new NewXmlRepository(bookValidator,"./data/xml.xml");
        Repository<Long,Client>clientRepository=new InMemoryRepository<>(clientValidator);
        Repository<Long,Book>purchaseRepository=new InMemoryRepository<>(bookValidator);
        Controller ctrl=new Controller(bookRepository,clientRepository,purchaseRepository);
        UI ui=new UI(ctrl);
        try {
            ui.runApp();
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }
}
