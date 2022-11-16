package Data;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DataGenerator {
    private  String firstname ;
    private  String lastname ;


    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {return lastname;}


    public DataGenerator() {
        this.firstname = generateFirstName();
        this.lastname = generateLastName();
    }


    private String generateFirstName () {
        String name = "tech_firstname";
        return name + getCurrentDateAndTime();
    }

    private String generateLastName () {
        String name = "tech_lastname";
        return name + getCurrentDateAndTime();
    }

    private String getCurrentDateAndTime () {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd-HH-mm-ss");
        LocalDateTime now  = LocalDateTime.now();
        return dtf.format(now);
    }
}
