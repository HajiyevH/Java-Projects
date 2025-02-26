package theater.tests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import theater.person.SpectatorTest;

@SelectClasses({
    TheaterSeatingExtendedTestSuite.StructuralTests.class,
    TheaterSeatingExtendedTestSuite.FunctionalTests.class,
})
@Suite public class TheaterSeatingExtendedTestSuite {
    @SelectClasses({
        SpectatorStructureTest.class,
        SpectatorTestStructureTest.class,
    })
    @Suite public static class StructuralTests {}

    @SelectClasses({
        SpectatorTest.class,
    })
    @Suite public static class FunctionalTests {}
}

