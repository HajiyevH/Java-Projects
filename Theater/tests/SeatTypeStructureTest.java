
package theater.tests;
import static check.CheckThat.*;
import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.MethodOrderer.*;
import check.*;

@TestMethodOrder(OrderAnnotation.class)
public class SeatTypeStructureTest {
    @BeforeAll
    public static void init() {
        CheckThat.theEnum("theater.seating.SeatType")
                 .hasEnumElements("OT", "IT", "MT");
    }
}

