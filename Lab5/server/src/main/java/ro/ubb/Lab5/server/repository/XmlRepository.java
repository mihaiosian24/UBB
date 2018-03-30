package ro.ubb.Lab5.server.repository;


import BookStore.Domain.Book;
import BookStore.Domain.Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//import com.sun.xml.internal.bind.v2.model.core.Element;
//import com.sun.xml.internal.bind.v2.model.core.ID;
//import org.w3c.dom.NodeList;

public class XmlRepository extends InMemoryRepository<Long,Book> {
    private String name;

    public XmlRepository(Validator<Book> validator, String name) {
        super(validator);
        this.name = name;
        try {
            loadData();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public void loadData() throws Exception{
       // Path path= Paths.get(name);
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
       // org.w3c.dom.Document xmlDoc = dbBuilder.parse(new InputSource(this.name));
        Document xmlDoc = dbBuilder.parse("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml");
        Element root=  xmlDoc.getDocumentElement();
        NodeList childNodes=root.getChildNodes();

        for (int index = 0; index < childNodes.getLength(); index++){
            Node child = childNodes.item(index);
            if(child instanceof Element) {
                Element bookElement = (Element) child;

                Node idNode=(Node) bookElement.getElementsByTagName("id").item(0);
                Node titleNode= (Node) bookElement.getElementsByTagName("title").item(0);
                Node authorNode= (Node) bookElement.getElementsByTagName("author").item(0);
                Node priceNode= (Node) bookElement.getElementsByTagName("price").item(0);

                String id=idNode.getTextContent();
                String author=authorNode.getTextContent();
                String title=titleNode.getTextContent();
                String price=priceNode.getTextContent();

                /*
                String author = getTextByTagName(bookElement,"author");
                String title = getTextByTagName(bookElement, "title");
                String price = getTextByTagName(bookElement, "price");
                */
                Book book=new Book(title,author,Integer.parseInt(price));
                book.setId((long) Integer.parseInt(id));
                super.save(book);



            }}

    }
    private static void appendChildToElement(Document doc, Element parent, String tag, String value){
        Element element = doc.createElement(tag);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    public  void save(Book myBook)  {
        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        super.save(myBook);
        try {
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document xmlDoc = dbBuilder.parse("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml");
            Element root = xmlDoc.getDocumentElement();

            Element ourBook = xmlDoc.createElement("book");
            appendChildToElement(xmlDoc, ourBook, "id", String.valueOf(myBook.getId()));
            appendChildToElement(xmlDoc, ourBook, "title", myBook.getTitle());
            appendChildToElement(xmlDoc, ourBook, "author", myBook.getAuthor());
            appendChildToElement(xmlDoc, ourBook, "price", String.valueOf(myBook.getPrice()));

            root.appendChild(ourBook);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root), new StreamResult("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void saveToXml(Book book){
        DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document xmlDoc = dbBuilder.parse("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml");
            Element root = xmlDoc.getDocumentElement();

            Element ourBook = xmlDoc.createElement("book");
            appendChildToElement(xmlDoc, ourBook, "id", String.valueOf(book.getId()));
            appendChildToElement(xmlDoc, ourBook, "title", book.getTitle());
            appendChildToElement(xmlDoc, ourBook, "author", book.getAuthor());
            appendChildToElement(xmlDoc, ourBook, "price", String.valueOf(book.getPrice()));

            root.appendChild(ourBook);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root), new StreamResult("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void saveAllToXml() {
        Path path = Paths.get("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><books></books>");

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Book book : super.findAll()) {
            saveToXml(book);
        }
    }

    public void delete(Long id){
        super.delete(id);
        saveAllToXml();
    }


    public void update(Book book){
        super.update(book);
        saveAllToXml();
    }

    public static void removeAll(Node node, short nodeType, String name) {
        if (node.getNodeType() == nodeType && (name == null || node.getNodeName().equals(name))) {
            node.getParentNode().removeChild(node);
        } else {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                removeAll(list.item(i), nodeType, name);
            }
        }
    }

}
