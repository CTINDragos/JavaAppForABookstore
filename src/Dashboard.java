import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Dashboard extends  JFrame{
    private JPanel dashboard;
    private JButton registerButton;
    private JLabel lbAdmin;
    private JButton loginButton;

    public Dashboard(){
        setTitle("Dashboard");
        setContentPane(dashboard);
        setMinimumSize(new Dimension(500,500));
        setSize(1200,700);
        setDefaultCloseOperation(Dashboard.EXIT_ON_CLOSE);
        boolean hasRegisteredUsers=connectToDatabase();
        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginForm loginForm=new LoginForm(Dashboard.this);
              loginForm.setVisible(true);
            }
        });
    }
private boolean connectToDatabase(){
        boolean hasRegisteredUsers=false;
        final String MYSQL_SERVER_URL="jdbc:mysql://localhost:3306/";
    final String DB_URL="jdbc:mysql://localhost:3306/myshop";
    final String USERNAME="root";
    final String PASSWORD="Asdqwe11@";
    try {
        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        Statement statement=conn.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT COUNT(*) FROM users");
        if(resultSet.next())
        {
                int numUsers=resultSet.getInt(1);
                if(numUsers>0) hasRegisteredUsers=true;
        }
    }catch (Exception e)
    {
        e.printStackTrace();
    }
  return hasRegisteredUsers;
}
}
