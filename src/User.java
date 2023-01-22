public class User {
    private String name;
    private String email;
    private String password;
    public void SetName(String auxname){
        name=auxname;
    }
    public void SetEmail(String mail){
        email=mail;
    }
    public void SetPassword(String newpassword){
        password=newpassword;
    }
    public String GetName()
    {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public User(String namex,String emailx,String passwordx)
    {
        name=namex;
        email=emailx;
        password=passwordx;
    }
    public User(){
        name="";
        email="";
        password="";
    }

}
