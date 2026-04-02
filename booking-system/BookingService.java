import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface BookingInterface {
    Booking bookSeat(Customer customer, Show show, List<Integer> seatIds);
    void cancelBooking(int bookingId);
}

class BookingService implements BookingInterface {

    Map<Integer, Booking> bookings = new HashMap<>();
    int bookingId = 1;

    public Booking bookSeat(Customer customer, Show show, List<Integer> seatIds) {

        Booking booking = new Booking(bookingId++, customer, show);

        for (int seatId : seatIds) {

            ShowSeat seat = show.seats.get(seatId);
            if (seat == null) return null;

            synchronized (seat) {

                if (seat.status != SeatStatus.AVAILABLE)
                    return null;

                seat.status = SeatStatus.IN_PROGRESS;
            }

            booking.bookedSeats.add(seat);

            booking.amount +=
                show.theater.pricingStrategy.getPrice(seat.seat, show);
        }

        bookings.put(booking.id, booking);
        return booking;
    }

    boolean makePayment(int bookingId, Payment payment) {

        Booking booking = bookings.get(bookingId);
        if (booking == null) return false;

        boolean ok = payment.makePayment(booking.amount);

        if (ok) {

            for (ShowSeat seat : booking.bookedSeats)
                seat.status = SeatStatus.BOOKED;

            booking.status = BookingStatus.BOOKED;

        } else {

            for (ShowSeat seat : booking.bookedSeats)
                seat.status = SeatStatus.AVAILABLE;
        }

        return ok;
    }

    public void cancelBooking(int bookingId) {

        Booking booking = bookings.get(bookingId);
        if (booking == null) return;

        for (ShowSeat seat : booking.bookedSeats)
            seat.status = SeatStatus.AVAILABLE;

        booking.status = BookingStatus.CANCELLED;
    }
}