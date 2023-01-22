import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField emailfield;
    private JButton cancelButton;
    private JButton connectButton;


    private JPanel LoginPanel;
    private JPasswordField passwordField1;

    public LoginForm(JFrame parent)
    {

        super(parent);
        setTitle("Login to your account");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(700,500));
        setDefaultCloseOperation(LoginForm.DISPOSE_ON_CLOSE);
        setVisible(true);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=emailfield.getText();
                String password=String.valueOf(passwordField1.getText());
               user= getAuthenticatedUser(email,password);
               if(user!=null){
               setVisible(false);
              MainPage mainPage=new MainPage(null,user);
               mainPage.setVisible(true);
            }
               else {
                   return;
               }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
    static public User user;
  private User getAuthenticatedUser(String email,String password)
  {
      User user=null;
      final String DB_URL="jdbc:mysql://localhost:3306/myshop";
      final String USERNAME="root";
      final String PASSWORD="Asdqwe11@";
      try{
      Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
      Statement stmt=conn.createStatement();
      String sql="SELECT * FROM users WHERE email=? AND password=?";
          PreparedStatement preparedStatement=conn.prepareStatement(sql);
          preparedStatement.setString(1,email);
          preparedStatement.setString(2,password);

          ResultSet resultSet = preparedStatement.executeQuery();
          if(resultSet.next())
          {
              user=new User(resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("password"));
stmt.close();
conn.close();
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      if(user==null)
      {
          JOptionPane.showMessageDialog(this,
                  "The username or the password are wrong",
                  "Try again",
                  JOptionPane.ERROR_MESSAGE);
      }

      return user;
  }
}
