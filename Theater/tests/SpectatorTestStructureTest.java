package theater.tests;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.MethodOrderer.*;
import check.*;

@TestMethodOrder(OrderAnnotation.class)
public class SpectatorTestStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theClass("theater.person.SpectatorTest")
                 .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                 ;
    }

    @Test @DisabledIf(notApplicable) @Order(1_00)
    public void fieldROW_COUNT() {
        it.hasField("ROW_COUNT: int")
        	.withInitialValue(4)
            .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_NONE)
            .thatHasNo(GETTER, SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(1_01)
    public void fieldCOL_COUNT() {
    	it.hasField("COL_COUNT: int")
    	  .withInitialValue(5)
    	  .thatIs(USABLE_WITHOUT_INSTANCE, NOT_MODIFIABLE, VISIBLE_TO_NONE)
    	  .thatHasNo(GETTER, SETTER);
    }

    @Test @DisabledIf(notApplicable) @Order(3_00)
    public void methodTestConstructor() {
        it.hasMethod("testConstructorWithNullName", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_01)
    public void methodTestBookAnySeat() {
        it.hasMethod("testBookAnySeat", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_02)
    public void methodTestBookSpecificSeat() {
        it.hasMethod("testBookSpecificSeat", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_03)
    public void methodTestGetName() {
        it.hasMethod("testGetName", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_04)
    public void methodTestGetSeatInitialState() {
        it.hasMethod("testGetSeatInitialState", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }


    // -------------------------------------------------
    // The following methods do not need to be implemented.
    // However, if you implement them, they must have the following structure.

    @Test @DisabledIf(notApplicable) @Order(3_11)
    public void methodTestGiftWithoutGift() {
        it.hasMethod("testTakeGiftWithoutGift", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_12)
    public void methodTestTakeGiftWithGift() {
        it.hasMethod("testTakeGiftWithGift", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_13)
    public void methodTestATailoredSeatEvenNameLength() {
        it.hasMethod("testBookATailoredSeatEvenNameLength", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_14)
    public void methodTestATailoredSeatOddNameLength() {
        it.hasMethod("testBookATailoredSeatOddNameLength", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }

    @Test @DisabledIf(notApplicable) @Order(3_15)
    public void methodTestTailoredSeatShortName() {
        it.hasMethod("testBookTailoredSeatShortName", withNoParams())
          .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
          .thatReturnsNothing();
    }
}
