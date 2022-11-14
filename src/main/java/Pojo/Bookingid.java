package Pojo;

public class Bookingid {

        private int bookingid;
        private Booking booking;

    public Bookingid(int bookingid, Booking booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Bookingid(){

    }


    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getBookingid() {
        return bookingid;
    }

    public Booking getBooking() {
        return booking;
    }



    @Override
    public String toString() {
        return "Bookingid{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}



