package BookStore.View;

import BookStore.Controller.Controller;
import BookStore.Domain.Book;
import BookStore.Domain.Client;
import BookStore.Domain.Validators.ValidatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class UI {

    private Controller ctrl;

    public UI(Controller ctrl) {
        this.ctrl = ctrl;
    }

    private void showMenu(){
        System.out.println("Choose option:");
        System.out.println("1.Add book");
        System.out.println("2.Show all books");
        System.out.println("3.Update book");
        System.out.println("4.Delete book");
        System.out.println("5.Filter books by author");
        System.out.println("6.Purchase book");
        System.out.println("7.Show all purchased book");
        System.out.println("8.Add Client");
        System.out.println("9.Show all clients");
        System.out.println("10.Update client");
        System.out.println("11.Delete client");
        System.out.println("12.Filter clients by name");
        System.out.println("13.Sort Books by price");


    }

    private Book readBook(){
        System.out.println("Read book {id,title, author, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String title = bufferRead.readLine();
            String author = bufferRead.readLine();
            int price = Integer.parseInt(bufferRead.readLine());// ...

            Book book = new Book(title, author, price);
            book.setId(id);

            return book;

        } catch (IOException ex ) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addBook()throws sun.security.validator.ValidatorException{
        Book book=readBook();
        try{
            ctrl.addBook(book);
        }catch(ValidatorException e){
            e.printStackTrace();
        }
    }

    private void printAllBooks(){
        Set<Book> books=ctrl.getAllBooks();
        books.stream().forEach(System.out::println);
    }

    private void updateBook()throws sun.security.validator.ValidatorException{
        Book book=readBook();
        try{
            ctrl.updateBook(book);
        }catch(ValidatorException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteBook(){
        System.out.println("Give id.");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
           try {
               Long id = Long.valueOf(bufferRead.readLine());
               ctrl.deleteBook(id);
           }catch  (IOException e) {
               e.printStackTrace();



        }
    }

    public void filterBooks(){
        System.out.println("Give author.");
        BufferedReader bufferRead=new BufferedReader(new InputStreamReader(System.in));
        try{
            String author=bufferRead.readLine();
            Set<Book>books=ctrl.filterBooksByAuthor(author);
            books.stream().forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
//
    private void addPurchased()throws sun.security.validator.ValidatorException{
        System.out.println("Give id of the person buying the book.");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Long id;
        try {
             id = Long.valueOf(bufferRead.readLine());

        Book book=readBook();
        try{
            ctrl.addPurchased(id,book);
        }catch(ValidatorException e){
            e.printStackTrace();
        }
        }catch  (IOException e) {
            e.printStackTrace();
        }
    }

    private void printAllPurchased(){
        Set<Book> books=ctrl.getAllPurchased();
        books.stream().forEach(System.out::println);
    }
//

    private Client readClient(){
        System.out.println("Read client {id,name}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String name = bufferRead.readLine();

            Client client = new Client(name);
            client.setId(id);

            return client;

        } catch (IOException ex ) {
            ex.printStackTrace();
        }
        return null;
    }
    private void addClient()throws sun.security.validator.ValidatorException{
        Client client=readClient();
        try{
            ctrl.addClient(client);
        }catch(ValidatorException e){
            e.printStackTrace();
        }
    }

    private void printAllClients(){
        Set<Client> clients=ctrl.getAllClients();
        clients.stream().forEach(System.out::println);
    }

    private void updateClient()throws sun.security.validator.ValidatorException{
        Client client=readClient();
        try{
            ctrl.updateClient(client);
        }catch(ValidatorException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteClient(){
        System.out.println("Give id.");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            ctrl.deleteClient(id);
        }catch  (IOException e) {
            e.printStackTrace();



        }
    }

    public void filterClients(){
        System.out.println("Give name.");
        BufferedReader bufferRead=new BufferedReader(new InputStreamReader(System.in));
        try{
            String name=bufferRead.readLine();
            Set<Client>clients=ctrl.filterClientsByName(name);
            clients.stream().forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace();
        }


    }
    public void sortBooks(){
        Set<Book>books=ctrl.sortByAmount();
        books=books.stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toSet());
        books.stream().forEach(System.out::println);
    }

    public void runApp()throws sun.security.validator.ValidatorException{
        int option=-1;
        Scanner scan=new Scanner(System.in);
        while(true)
        {
            showMenu();
            option=scan.nextInt();

            if(option==1) addBook();
            else if(option==2)printAllBooks();
            else if(option==3) updateBook();
            else if(option==4) deleteBook();
            else if(option==5) filterBooks();
            else if(option==6) addPurchased();
            else if(option==7) printAllPurchased();
            else if(option==8) addClient();
            else if(option==9) printAllClients();
            else if(option==10) updateClient();
            else if(option==11) deleteClient();
            else if(option==12) filterClients();
            else if(option==13) sortBooks();
            else if (option==0)break;

        }
        scan.close();
    }
}
