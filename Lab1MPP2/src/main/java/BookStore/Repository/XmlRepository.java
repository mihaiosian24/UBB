package BookStore.Repository;


import BookStore.Domain.BaseEntity;
import BookStore.Domain.Book;
import BookStore.Domain.Validators.Validator;
//import com.sun.xml.internal.bind.v2.model.core.Element;
//import com.sun.xml.internal.bind.v2.model.core.ID;
//import org.w3c.dom.NodeList;
import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        org.w3c.dom.Document xmlDoc = dbBuilder.parse("C:/Users/Mihai/Desktop/Lab1MPP2/src/xml.xml");
        Element root=  xmlDoc.getDocumentElement();
        NodeList childNodes=root.getChildNodes();

        for (int index = 0; index < childNodes.getLength(); index++){
            Node child = childNodes.item(index);
            if(child instanceof Element) {
                Element bookElement = (Element) child;

                Node idNode=(Node) bookElement.getElementsByTagName("id");
                Node authorNode= (Node) bookElement.getElementsByTagName("author");
                Node titleNode= (Node) bookElement.getElementsByTagName("title");
                Node priceNode= (Node) bookElement.getElementsByTagName("price");

                String id=idNode.getTextContent();
                String author=authorNode.getTextContent();
                String title=titleNode.getTextContent();
                String price=priceNode.getTextContent();

                /*
                String author = getTextByTagName(bookElement,"author");
                String title = getTextByTagName(bookElement, "title");
                String price = getTextByTagName(bookElement, "price");
                */
                Book book=new Book(author,title,Integer.parseInt(price));
                book.setId((long) Integer.parseInt(id));
                super.save(book);



            }}

    }

}
