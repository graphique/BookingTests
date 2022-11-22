package Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class DataGenerator {
    private  String firstname ;
    private  String lastname ;
    private String checkin;
    private String checkout;



    public String getCheckin() {
        return checkin;
    }
    public String getCheckout() {
        return checkout;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {return lastname;}


    public DataGenerator() {
        this.firstname = generateFirstName();
        this.lastname = generateLastName();
        this.checkin = generateCheckin();
        this.checkout = generateCheckout();
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

    DateTimeFormatter date = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    LocalDate today = LocalDate.now();

    private String generateCheckin () {
        return date.format(today);
    }

    private String generateCheckout () {
        LocalDate then =  today.plusDays(10);
        return date.format(then);
    }
}
