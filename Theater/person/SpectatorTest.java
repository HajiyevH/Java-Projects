package theater.person;

import org.junit.jupiter.api.*;
import theater.person.Spectator;
import theater.TheaterSeating;
import theater.seating.Seat;
import theater.seating.SeatType;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpectatorTest {
    private static final int ROW_COUNT = 4;
    private static final int COL_COUNT = 5;

    @Test
    @Order(1)
    public void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Spectator(null));
        assertThrows(IllegalArgumentException.class, () -> new Spectator(""));
    }

    @Test
    @Order(2)
    public void testBookAnySeat() {
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);
        Spectator spectator = new Spectator("Yousef");
        spectator.bookAnySeat(seating);

        assertNotNull(spectator.getSeat());
        assertTrue(spectator.getSeat().getIsOccupied());
    }

    @Test
    @Order(3)
    public void testBookSpecificSeat() {
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);
        Spectator spectator = new Spectator("Yousef");

        int row = 3, col = 3;
        spectator.bookSpecificSeat(seating, row, col);

        assertEquals("R3C3", spectator.getSeat().getId());
        assertTrue(spectator.getSeat().getIsOccupied());
    }
    @Test
    @Order(4)
    public void testGetName() {
        Spectator spectator = new Spectator("Hajiyev");
        assertEquals("Hajiyev", spectator.getName());
    }

    @Test
    @Order(5)
    public void testGetSeatInitialState() {
        Spectator spectator = new Spectator("Haji");
        assertNull(spectator.getSeat());
    }
    @Test 
    @Order(6)
    public void testTakeGiftWithoutGift(){
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);

        Spectator person = new Spectator("Haji");

        person.bookAnySeat(seating);

        person.getSeat().setHasGift(false);

        assertFalse(person.takeGift());
    }
    @Test 
    @Order(7)
    public void testTakeGiftWithGift(){
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);

        Spectator person = new Spectator("Haji");

        person.bookAnySeat(seating);

        person.getSeat().setHasGift(true);

        assertTrue(person.takeGift());
    }
    @Test 
    @Order(8)
    public void testBookATailoredSeatEvenNameLength(){
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);

        Spectator person = new Spectator("Haji");

        person.bookTailoredSeat(seating);

        assertEquals(person.getSeat().getId(),"R1C2");
        assertEquals(person.getSeat().getSeatType(),SeatType.MT);

        // Because they both are in middle we don't get differen values both get the same value which is the first row in middle

    }
    @Test 
    @Order(9)
    public void testBookATailoredSeatOddNameLength(){
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);

        Spectator person = new Spectator("Hajiaga");

        person.bookTailoredSeat(seating);

        assertEquals(person.getSeat().getId(),"R1C2");
        assertEquals(person.getSeat().getSeatType(),SeatType.MT);

        // Because they both are in middle we don't get differen values both get the same value which is the first row in middle

    }
    @Test 
    @Order(10)
    public void testBookTailoredSeatShortName(){
        TheaterSeating seating = new TheaterSeating(ROW_COUNT,COL_COUNT);

        Spectator person = new Spectator("HH");

        person.bookTailoredSeat(seating);

        assertTrue(person.getSeat().getSeatType() ==SeatType.OT);
    }
}