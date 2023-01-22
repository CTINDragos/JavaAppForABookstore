import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookView extends JFrame{
    private JLabel titleofBook;
    private JButton button2;
    private JLabel authorlabel;
    private JLabel datelabel;
    private JLabel lonerlabel;
    private JLabel endloanlabel;
    private JLabel startloanlabel;
    private JTextField loanerfield;
    private JTextField loanedupdate;
    private JTextField returndate;
    private JLabel updateloaner;
    private JLabel updateloaned;
    private JLabel updatereturn;
    private JLabel textbox;

    public boolean isValidDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dateFormat.parse(dateString);
        return true;
    }

        public void updateTable(String stringValue, java.util.Date dateValue1, Date dateValue2,Book book) {
            try {
                // Connect to the database
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop", "root", "Asdqwe11@");

                // Create a statement
                Statement st = con.createStatement();
                java.sql.Date sqlDate = new java.sql.Date(dateValue1.getTime());
                java.sql.Date sqlDate2 = new java.sql.Date(dateValue2.getTime());
                // Update the table
                String sql = "UPDATE books SET Description = ?, Loan_Date = ?, Loan_End_Date = ? WHERE Name = ?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, stringValue);
                statement.setDate(2, sqlDate);
                statement.setDate(3,sqlDate2);
                statement.setString(4, book.getName());
                statement.executeUpdate();

                // Close the connection
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public BookView(JFrame parent,Book book,User user)
    {
        setTitle(book.getName());
        setMinimumSize(new Dimension(1020,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        titleofBook.setText(book.getName());
        titleofBook.update(this.getGraphics());
        authorlabel.setText(book.getAuthor());
        authorlabel.update(this.getGraphics());
        lonerlabel.setText(book.getDescription());
        lonerlabel.update(this.getGraphics());
        datelabel.setText(book.getDate_written());
        datelabel.update(this.getGraphics());
        endloanlabel.setText(book.getDate_End_loan());
        endloanlabel.update(this.getGraphics());
        startloanlabel.setText(book.getDate_Loan());
        startloanlabel.update(this.getGraphics());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2)); // 7 rows and 2 columns
        // Add the labels to the panel
        panel.add(titleofBook);
        panel.add(authorlabel);
        panel.add(datelabel);
        panel.add(lonerlabel);
        panel.add(endloanlabel);
        panel.add(startloanlabel);
        panel.add(button2);
        panel.add(loanerfield);
        panel.add(loanedupdate);
        panel.add(returndate);
        panel.add(updateloaner);
        panel.add(updateloaned);
        panel.add(updatereturn);
        panel.add(textbox);
        // Add the panel to the frame
        add(panel);
        setVisible(true);
        pack();


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newLoaner = loanerfield.getText();
                String newDate = loanedupdate.getText();
                String newReturndate = returndate.getText();
                if(newDate!=""&&newReturndate!=""){
                java.util.Date newerDate=new java.util.Date();
                java.util.Date Returneddate=new java.util.Date();
                try {
                    if (isValidDate(newDate)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date utilDate = dateFormat.parse(newDate);
                        newerDate=utilDate;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "The new date is not of a valid format",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (ParseException e12) {
                    e12.printStackTrace();
                }
                try {
                    if (isValidDate(newReturndate)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date utilDate = dateFormat.parse(newReturndate);
                        Returneddate=utilDate;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "The new return date is not of a valid format",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (ParseException e12) {
                    e12.printStackTrace();
                }
               updateTable(newLoaner,newerDate,Returneddate,book);
                setVisible(false);
                MainPage mainPage=new MainPage(null,user);
                mainPage.setVisible(true);
            }
            else { updateTable(null,null,null,book);
                    setVisible(false);
                    MainPage mainPage=new MainPage(null,user);
                    mainPage.setVisible(true);
            }
            }
        });
    }
}
