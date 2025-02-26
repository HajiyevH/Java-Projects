package theater.person;

import theater.TheaterSeating;
import theater.seating.Seat;
import theater.seating.SeatType;

public class Spectator {
    private Seat seat;
    private final String name;

    public Spectator(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public boolean takeGift() {
        if (seat == null || !seat.getHasGift()) {
            return false;
        }
        seat.setHasGift(false);
        return true;
    }

    public void bookAnySeat(TheaterSeating ts) {
        this.seat = ts.bookSeat();
    }

    public void bookSpecificSeat(TheaterSeating ts, int row, int col) {
        this.seat = ts.bookSeat(row, col);
    }

    public void bookTailoredSeat(TheaterSeating ts) {
        SeatType desiredType = name.length() < 3 ? SeatType.OT : SeatType.MT;
        if (name.length() % 2 == 0) {
            seat = ts.bookTailoredEmptySeat(desiredType, true);
        } else {
            seat = ts.bookTailoredEmptySeat(desiredType,false);
        }
    }
}