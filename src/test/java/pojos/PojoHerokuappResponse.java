package pojos;

public class PojoHerokuappResponse {
    //1) Tum variable’lari "private" olarak olusturalim

    private  int bookingId;
    private  PojoHerokuappRequestBody booking;
    //2) Tum variable’lar icin getter() ve  setter() metodlari olusturalim

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public PojoHerokuappRequestBody getBooking() {
        return booking;
    }

    public void setBooking(PojoHerokuappRequestBody booking) {
        this.booking = booking;
    }

    //3) Tum parametreleri iceren bir constructor olusturalim.

    public PojoHerokuappResponse(int bookingId, PojoHerokuappRequestBody booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    //4) Default constructor (parametresiz) olusturalim

    public PojoHerokuappResponse() {
    }


    //5) toString() metodu olusturalim


    @Override
    public String toString() {
        return "PojoHerokuappResponse{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
