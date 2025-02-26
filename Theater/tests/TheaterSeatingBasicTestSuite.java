
package theater.tests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import theater.TheaterSeatingTest;

@SelectClasses({
    TheaterSeatingBasicTestSuite.StructuralTests.class,
    TheaterSeatingBasicTestSuite.FunctionalTests.class,
})
@Suite public class TheaterSeatingBasicTestSuite {
    @SelectClasses({
        SeatTypeStructureTest.class,
        SeatStructureTest.class,

        TheaterSeatingStructureTest.class,
        TheaterSeatingTestStructureTest.class,
    })
    @Suite public static class StructuralTests {}

    @SelectClasses({
        TheaterSeatingTest.class,
    })
    @Suite public static class FunctionalTests {}
}

