package theater;
 
import theater.seating.Seat;
import theater.seating.SeatType;
import java.lang.IllegalArgumentException;
public class TheaterSeating {
    private Seat[][] seats;
    private int giftsTotal;
 
    public TheaterSeating(int rowCount, int colCount) {
        if (rowCount <= 0 || colCount <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than 0");
        }
 
        initSeating(rowCount, colCount);
    }
    private void initSeating(int rowCount, int colCount) {
        seats = new Seat[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                SeatType type;
                if (row == 0 || row == (rowCount - 1) || col == 0 || col == (colCount - 1)) {
                    type = SeatType.OT;
                } else if ((col - 0) == (colCount - 1 - col)) {
                    type = SeatType.MT;
                } else {
                    type = SeatType.IT;
                }
                seats[row][col] = new Seat("R" + row + "C" + col, (row + col) % 2 != 0, type);
            }
        }
    }
 
    public Seat bookSeat() {
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                if (!seat.getIsOccupied()) {
                    seat.book();
                    return seat;
                }
            }
        }
        return null;
    }
 
    public Seat bookSeat(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            throw new IllegalArgumentException("Invalid seat position");
        }
        Seat seat = seats[row][col];
        if (!seat.getIsOccupied()) {
            seat.book();
            return seat;
        }else{throw new IllegalStateException("Already booked seat");}
 
    }
    public Seat bookTailoredEmptySeat(SeatType type, boolean isLeft) {
        if (isLeft) {
            for (int col = 0; col < seats[0].length; col++) {
                for (int row = 0; row < seats.length; row++) {
                    Seat seat = seats[row][col];
                    if (!seat.getIsOccupied() && seat.getSeatType() == type) {
                        seat.book();
                        return seat; 
                    }
                }
            }
        } else {
            for (int col = seats[0].length - 1; col >= 0; col--) {
                for (int row = 0; row < seats.length; row++) {
                    Seat seat = seats[row][col];
                    if (!seat.getIsOccupied() && seat.getSeatType() == type) {
                        seat.book();
                        return seat;
                    }
                }
            }
        }
    
        return null; 
    }
 
    public int totalTakenGifts() {
        return giftsTotal;
    }
 
    public void decreaseGifts() {
        if (giftsTotal > 0) {
            giftsTotal--;
        } else {
            throw new IllegalStateException("Cannot decrease gifts below 0");
        }
    }
 
    public int getAmountOfGifts() {
        return giftsTotal;
    }
 
    public Seat[][] getSeats(){return seats;}
 
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Seat[] row : seats) {
            for (Seat seat : row) {
                builder.append(seat.getIsOccupied() ? "[B]" : "[A]");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
