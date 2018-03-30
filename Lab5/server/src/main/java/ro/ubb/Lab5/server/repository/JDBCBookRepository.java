package ro.ubb.Lab5.server.repository;





import ro.ubb.Lab5.common.Domain.Book;
import ro.ubb.Lab5.common.Domain.Validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//package BookStore.repository;

public class JDBCBookRepository extends InMemoryRepository<Long,Book> {
    private static  final String URL="jdbc:postgresql://localhost:5432/Bookstore";
    private static final String user = "postgres";
    private static final String password = "nimeni48";

    public JDBCBookRepository(Validator<Book> validator) {
        super(validator);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find class: " + e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" +
                    "Bookstore", user,password );
        } catch (SQLException sqle) {
            System.out.println("Failed to get connection: " + sqle.getMessage());
        }
    }

    public List<Book> findAll() {
        List<Book> students = new ArrayList<>();
        String sql = "select * from bookss";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("bid");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int price =rs.getInt("price");
                Book book=new Book( title, author,price);
                book.setId(id);
                students.add(book);
            }
            return students;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Book book) {
        String sql1="insert into books("+"ID"+","+"Title"+","+"Author"+","+"Price"+") values (?,?,?,?)";
        String sql = "insert into bookss  values (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1,book.getId());
            statement.setString(2,book.getTitle());
            statement.setString(3,book.getAuthor());
            statement.setInt(4,book.getPrice());
            statement.executeUpdate();
            super.save(book);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void update(Book book){
        String sql = "UPDATE bookss set title=?, author=?,price=? where bid=?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthor());
            statement.setInt(3,book.getPrice());
            statement.setLong(4,book.getId());
            statement.executeUpdate();
            super.update(book);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public void delete(Long bookId){
        String sql = "DELETE from bookss where bid=?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1,bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    /*

    public void findall(){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:Bookstore");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from BookTable");
            String im="",in="";

            while(rs.next())
            {
                im=rs.getString(1);
                in=rs.getString(2);
                System.out.println(im+in);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
*/
}
