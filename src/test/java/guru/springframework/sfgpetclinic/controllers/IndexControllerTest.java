package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test proper view name is returned for index page")
    @Test
    void index() {
        assertEquals("index",controller.index());
        assertEquals("index",controller.index(),"Wrong View Returned");
        assertTrue("index".equals(controller.index()),()-> "Some message to build for test");

        //assertj
        assertThat(controller.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test Exception")
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class,()->controller.oopsHandler());
    }

    @Disabled
    @Test
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100),()->Thread.sleep(5000));
        System.out.println("Reached end");
    }

    @Disabled("Disabled Timeout Demo")
    @Test
    void testTimeoutPreempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100),()->Thread.sleep(5000));
        System.out.println("Reached end preempt");
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrue1() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMac() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testOnWindows() {

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testOnJava8() {

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testOnJava11() {

    }

    @EnabledIfEnvironmentVariable(named = "username",matches = "bhagyashree")
    @Test
    void testIfUserBhagyashree() {
    }

    @EnabledIfEnvironmentVariable(named = "username",matches = "john")
    @Test
    void testIfUserJohn() {
    }

}