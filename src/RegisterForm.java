import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegisterForm extends JDialog {
    private JTextField tfName;

    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerpanel;
    private JTextField tfDate;
    private JTextField Authorfield;
    private JTextField descriptionfield;
    public User user;

    public RegisterForm(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerpanel);
        setMinimumSize(new Dimension(500,500));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(RegisterForm.DISPOSE_ON_CLOSE);
        setVisible(true);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Book user1=new Book();
              Book users[]={user1};
              registerUser(users);
                if(users[0]!=null)
                dispose();
                else {JOptionPane.showMessageDialog(null,
                        "There's already an account used with this email",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
                    return;
            }}
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              dispose();
            }
        });

    }

    private Book addUserToDatabase(String name,String Date,String Author,String Description)
    {
        Book book=null;
        final String DB_URL="jdbc:mysql://localhost:3306/myshop";
        final String USERNAME="root";
        final String PASSWORD="Asdqwe11@";
        String Loan_Date="01.01.2200";
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="INSERT INTO books(Name,Date_Written,Author,Description,Loan_Date,Loan_End_Date)"+"VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,Date);
            preparedStatement.setString(3,Author);
            preparedStatement.setString(4,Description);
            preparedStatement.setString(5,Loan_Date);
            preparedStatement.setString(6,Loan_Date);
            int addedRow=preparedStatement.executeUpdate();
            if(addedRow>0)
            {
                book=new Book(name,Author,Date,Description);
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    private void registerUser(Book user[]) {
        String name=tfName.getText();
        String Date= tfDate.getText();
        String Author=Authorfield.getText();
        String Description="Enid - 0700000000";
        if (name.isEmpty()||Date.isEmpty()||Author.isEmpty()||Description.isEmpty())
        {JOptionPane.showMessageDialog(this,
                    "One or more fields are empty",
                            "Try again",
                    JOptionPane.ERROR_MESSAGE);
        return;
        }
    Book book1;
       book1= addUserToDatabase(name,Date,Author,Description);
       user[0]=book1;
    }
}
