package Pojo;

public class Bookingdates {

   private String checkin;
   private String checkout;


   public Bookingdates (String checkin, String checkout) {
      this.checkin = checkin;
      this.checkout = checkout;
   }

   public Bookingdates() {

   }

   @Override
   public String toString() {
      return "Pojo.Bookingdates{" +
              "checkin='" + checkin + '\'' +
              ", checkout='" + checkout + '\'' +
              '}';
   }

   public void setCheckin(String checkin) {
      this.checkin = checkin;
   }

   public void setCheckout(String checkout) {
      this.checkout = checkout;
   }

   public String getCheckout() {
      return checkout;
   }

   public String getCheckin() {
      return checkin;
   }
}
