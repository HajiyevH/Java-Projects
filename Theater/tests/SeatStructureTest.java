package theater.tests;

import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.MethodOrderer.*;
import check.*;

@TestMethodOrder(OrderAnnotation.class)
public class SeatStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("theater.seating.Seat")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                 ;
    }

    @Test @DisabledIf(notApplicable) @Order(1_00)
    public void fieldId() {
        it.hasField("id: String")
          .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
          .thatHas(GETTER)
          .thatHasNo(SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(1_01)
    public void fieldHasGift() {
        it.hasField("hasGift: boolean")
          .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
          .thatHas(GETTER, SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(1_02)
    public void fieldSeatType() {
        it.hasField("seatType: theater.seating.SeatType")
          .thatIs(INSTANCE_LEVEL, NOT_MODIFIABLE, VISIBLE_TO_NONE)
          .thatHas(GETTER)
          .thatHasNo(SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(1_03)
    public void fieldIsOccupied() {
        it.hasField("isOccupied: boolean")
          .thatIs(INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
          .thatHas(GETTER, SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(2_0)
    public void constructor() {
        it.hasConstructor(withParams("id: String", "hasGift: boolean", "seatType: SeatType"))
          .thatIs(VISIBLE_TO_ALL);
    }

}

