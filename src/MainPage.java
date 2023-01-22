import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class MainPage extends JFrame {

    private JLabel ShowUserName;
    private JButton clickHereToRegisterButton;
    private JPanel mainpanel;
    private JTable BooksData;
    private JTextField textField1;
    private JButton searchABookButton;

    private void CreateTable(){

BooksData.setModel(new DefaultTableModel(
         null,
        new String[]{"Name","Author","Loaner"}
));

    }
    private void showTable() {
        ArrayList<Book> books=new ArrayList<Book>();
        final String DB_URL="jdbc:mysql://localhost:3306/myshop";
        final String USERNAME="root";
        final String PASSWORD="Asdqwe11@";
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();

            String SQL_STATEMENT="SELECT * FROM  books";
            PreparedStatement preparedStatement=conn.prepareStatement(SQL_STATEMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                books.add(new Book(resultSet.getString("Name"),
                        resultSet.getString("Author"),
                        resultSet.getString("Date_Written"),
                        resultSet.getString("Description")));
            }

            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    DefaultTableModel model=(DefaultTableModel) BooksData.getModel();
    model.setRowCount(0);
    for(Book book1:books)
    {

        String dateloan=book1.getDate_End_loan();
        char[] dateloanchar=dateloan.toCharArray();
        char[] charday=new char[2];
        charday[0]=dateloanchar[0];
        charday[1]=dateloanchar[1];
        String day=String.valueOf(charday);
        char[] charmonth=new char[2];
        charmonth[0]=dateloanchar[3];
        charmonth[1]=dateloanchar[4];
        String month=String.valueOf(charmonth);
        char[] charyear=new char[4];
        charyear[0]=dateloanchar[6];
        charyear[1]=dateloanchar[7];
        charyear[2]=dateloanchar[8];
        charyear[3]=dateloanchar[9];
        String year=String.valueOf(charyear);
        int day1=Integer.parseInt(day);
        int month1=Integer.parseInt(month);
        int year1=Integer.parseInt(year);
       java.sql.Date bookloaneddate=new Date(year1,month1-1,day1);
       LocalDate localDate = bookloaneddate.toLocalDate();
        LocalDate now = LocalDate.now();
        if(localDate.isAfter(now)) {
        model.addRow(new Object[]{
                book1.getName(),
                book1.getAuthor(),
                book1.getDescription()

    });
    }}
    }
    public MainPage(JFrame parent, User user){

        setTitle("This is the main page");
        setMinimumSize(new Dimension(1000,600));
        setDefaultCloseOperation(LoginForm.EXIT_ON_CLOSE);
        setContentPane(mainpanel);
        ShowUserName.setText("You are connected as "+user.GetName()+"! ");
        ShowUserName.update(this.getGraphics());
        CreateTable();
        showTable();
        setVisible(true);
        clickHereToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm=new RegisterForm(null);
                registerForm.setVisible(true);
            }
        });

        searchABookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book b1=null;
                String NameofBook=textField1.getText();
                final String DB_URL="jdbc:mysql://localhost:3306/myshop";
                final String USERNAME="root";
                final String PASSWORD="Asdqwe11@";
                try{
                    Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                    Statement stmt=conn.createStatement();
                    String sql="SELECT * FROM books WHERE Name=? ";
                    PreparedStatement preparedStatement=conn.prepareStatement(sql);
                    preparedStatement.setString(1,NameofBook);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next())
                    {
                        b1=new Book(resultSet.getString("Name"),
                                resultSet.getString("Author"),
                                resultSet.getString("Date_Written"),
                                resultSet.getString("Description"));
                    }

                    stmt.close();
                    conn.close();
                }
      catch(Exception e1)
            {
                e1.printStackTrace();
            }
                if(b1==null)
                {
                    JOptionPane.showMessageDialog(null,
                            "The name is not in the database",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    setVisible(false);
                    BookView bookView=new BookView(null,b1,user);
                }

            }
        });
    }
}
