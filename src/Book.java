import java.util.Date;

public class Book {
   private String Name;
    private String Date_written;
    private  String Author;
   private  String Description;
   private String Date_Loan;
   private String Date_End_loan;
    public String getDate_End_loan() {
        return Date_End_loan;
    }

    public String getDate_Loan() {
        return Date_Loan;
    }

    public void setDate_End_loan(String date_End_loan) {
        Date_End_loan = date_End_loan;
    }

    public void setDate_Loan(String date_Loan) {
        Date_Loan = date_Loan;
    }

    public void SetName(String name){
        this.Name=name;
    }

    public String getAuthor() {
        return Author;
    }

    public String getDate_written() {
        return Date_written;
    }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setDate_written(String date_written) {
        Date_written = date_written;
    }

    public void setDescription(String description) {
        Description = description;
    }
    Book(String name,String author,String date_written,String description)
    {
        Name=name;
        Author=author;
        Date_written=date_written;
        Description=description;
        Date_Loan="01.01.2000";
        Date_End_loan="01.01.2000";

    }
    Book(String name,String author,String date_written,String description,String Date_Loan_b,String Date_End_Loan_b)
    {
        Name=name;
        Author=author;
        Date_written=date_written;
        Description=description;
        Date_Loan=Date_Loan_b;
        Date_End_loan=Date_End_Loan_b;
        if(Date_Loan=="") Date_Loan="01.01.2200";
        if(Date_End_loan=="") Date_End_loan="01.01.2200";

    }
    Book(){
        Name=null;
        Author=null;
        Date_written=null;
        Description=null;
        Date_Loan=null;
        Date_End_loan=null;
    }

}
