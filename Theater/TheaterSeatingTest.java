package theater;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import theater.seating.Seat;
import theater.seating.SeatType;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TheaterSeatingTest {

    @Test
    @Order(1)
    public void testInitialization() {
        TheaterSeating theaterSeating = new TheaterSeating(4, 5);
        Seat[][] seats = theaterSeating.getSeats();
    
        assertEquals(4, seats.length); 
        assertEquals(5, seats[0].length); 
    
        // Verify SeatTypes
        assertEquals(SeatType.OT, seats[0][0].getSeatType()); 
        assertEquals(SeatType.MT, seats[2][2].getSeatType()); 
        assertEquals(SeatType.IT, seats[1][1].getSeatType());
        assertThrows(IllegalArgumentException.class, () -> new TheaterSeating(-3, 5));

    }
    @ParameterizedTest
    @Order(2)
    @CsvSource({
        "2, 4, 4",
        "5, 5, 12",
        "3, 1, 1"
    })
    public void testGiftsInitialization(int rows, int cols, int expectedGifts) {
        TheaterSeating theaterSeating = new TheaterSeating(rows, cols);
        int giftCount = 0;
        int condition = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (theaterSeating.bookSeat(row, col).isHasGift()) {
                    giftCount++;
                }
                if ((row + col) % 2 == 1) {
                    condition++;
                }
            }
        }
        assertEquals(condition, giftCount); 
        assertEquals(expectedGifts, giftCount);
    }

    @Test
    @Order(3)
    public void testBookSeat() {
        TheaterSeating theaterSeating = new TheaterSeating(4, 5);
        Seat seat = theaterSeating.bookSeat(2,2);
        assertNotNull(seat); 
        assertTrue(seat.getIsOccupied()); 
        assertThrows(IllegalStateException.class, () -> theaterSeating.bookSeat(2, 2));
    }

    @Test
    @Order(4)
    public void testBookTailoredEmptySeat() {
        TheaterSeating theaterSeating = new TheaterSeating(4, 5);
        SeatType desiredType = SeatType.IT;
        Seat seat = theaterSeating.bookTailoredEmptySeat(desiredType, true);
        assertNotNull(seat); 
        assertTrue(seat.getIsOccupied()); 
        assertEquals(desiredType, seat.getSeatType());
        TheaterSeating theaterSeating2 = new TheaterSeating(4, 5);
        SeatType desiredType2 = SeatType.OT;
        Seat seat2 = theaterSeating2.bookTailoredEmptySeat(desiredType2, true);
        assertNotNull(seat2);
        assertTrue(seat2.getIsOccupied()); 
        assertEquals(desiredType2, seat2.getSeatType()); 
    }

    @Test
    @Order(5)
    public void testText() {
        TheaterSeating theaterSeating = new TheaterSeating(4, 5);
        String expectedOutput = """
                [A][A][A][A][A]
                [A][A][A][A][A]
                [A][A][A][A][A]
                [A][A][A][A][A]
                """;
        assertEquals(expectedOutput, theaterSeating.toString()); 
        theaterSeating.bookSeat();
        String afterBooking = """
                [B][A][A][A][A]
                [A][A][A][A][A]
                [A][A][A][A][A]
                [A][A][A][A][A]
                """;
        assertEquals(afterBooking, theaterSeating.toString());
    }
}