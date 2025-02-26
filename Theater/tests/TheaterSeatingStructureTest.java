package theater.tests;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.MethodOrderer.*;
import check.*;

@TestMethodOrder(OrderAnnotation.class)
public class TheaterSeatingStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("theater.TheaterSeating")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL);
    }

    @Test @DisabledIf(notApplicable) @Order(1_00)
    public void fieldSeats() {
        it.hasField("seats: array of array of theater.seating.Seat")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
            .thatHas(GETTER)
            .thatHasNo(SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(1_01)
    public void fieldGiftsTotal() {
        it.hasField("giftsTotal: int")
            .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE);
    }

    @Test @DisabledIf(notApplicable) @Order(2_00)
    public void constructorWithRowsAndCols() {
        it.hasConstructor(withParams("rowCount: int", "colCount: int"))
            .thatIs(VISIBLE_TO_ALL)
            .thatThrows("IllegalArgumentException");
    }

    @Test @DisabledIf(notApplicable) @Order(3_00)
    public void methodInitSeating() {
        it.hasMethod("initSeating", withParams("rowCount: int", "colCount: int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_NONE)
            .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_01)
    public void methodBookSeatNoParams() {
        it.hasMethod("bookSeat", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("theater.seating.Seat");
    }

    @Test @DisabledIf(notApplicable) @Order(3_02)
    public void methodBookSeatWithRowCol() {
        it.hasMethod("bookSeat", withParams("row: int", "col: int"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("theater.seating.Seat");
    }

    @Test @DisabledIf(notApplicable) @Order(3_03)
    public void methodBookTailoredEmptySeat() {
        it.hasMethod("bookTailoredEmptySeat", withParams("theater.seating.SeatType", "boolean"))
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("theater.seating.Seat");
    }

    @Test @DisabledIf(notApplicable) @Order(3_04)
    public void methodTotalTakenGifts() {
        it.hasMethod("totalTakenGifts", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test @DisabledIf(notApplicable) @Order(3_05)
    public void methodDecreaseGifts() {
        it.hasMethod("decreaseGifts", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_06)
    public void methodGetAmountOfGifts() {
        it.hasMethod("getAmountOfGifts", withNoParams())
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .thatReturns("int");
    }

    @Test @DisabledIf(notApplicable) @Order(3_06)
    public void text() {
        it.has(TEXTUAL_REPRESENTATION);
    }
}
