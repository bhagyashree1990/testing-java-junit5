package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
@Tag("repeated")
public interface ModelRepeatedTests {
    /**
     * NOTE: RepetitionInfo is not available for non-repeated test so those tests will fail if we use @BeforeEach with RepetitionInfo parameter
     * @param testInfo
     * @param repetitionInfo
     */
    @BeforeEach
    default void beforeEachConsoleLog(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println("Running Before Each ==> "
                +testInfo.getDisplayName()
                +" : "+repetitionInfo.getCurrentRepetition()
                +" | "+repetitionInfo.getTotalRepetitions()
        );
    }
}
