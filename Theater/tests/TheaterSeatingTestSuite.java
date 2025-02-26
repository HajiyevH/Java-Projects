package theater.tests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@SelectClasses({
    TheaterSeatingBasicTestSuite.class,
    TheaterSeatingExtendedTestSuite.class,
})
@Suite public class TheaterSeatingTestSuite {}

