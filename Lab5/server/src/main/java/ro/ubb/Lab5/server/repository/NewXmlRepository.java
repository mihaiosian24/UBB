package ro.ubb.Lab5.server.repository;

import BookStore.Domain.Book;
import BookStore.Domain.Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class NewXmlRepository extends InMemoryRepository<Long,Book> {
    private String filename;

    public NewXmlRepository(Validator<Book> validator, String filename) {
        super(validator);
        this.filename = filename;
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document xmlDoc = dbBuilder.parse("./data/xml.xml");
        Element root = xmlDoc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int index = 0; index < childNodes.getLength(); index++) {
            Node child = childNodes.item(index);
            if (child instanceof Element) {
                Element bookElement = (Element) child;
                //String category = bookElement.getAttribute("category");
                //System.out.println(category);
                /*
                String author = getTextByTagName(bookElement, "author");
                String title = getTextByTagName(bookElement, "title");
                String year = getTextByTagName(bookElement, "year");
                String price = getTextByTagName(bookElement, "price");
                */
                Node idNode = (Node) bookElement.getElementsByTagName("id");
                Node authorNode = (Node) bookElement.getElementsByTagName("author");
                Node titleNode = (Node) bookElement.getElementsByTagName("title");
                Node priceNode = (Node) bookElement.getElementsByTagName("price");

                String id = idNode.getTextContent();
                String author = authorNode.getTextContent();
                String title = titleNode.getTextContent();
                String price = priceNode.getTextContent();
                Book book = new Book(title, author, Integer.parseInt(price));
                book.setId((long) Integer.parseInt(id));
                //books.add(book); // pont ctrl + p
                super.save(book);
            }
        }
    }
}
