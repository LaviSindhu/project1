import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session(
                101,
                "demo",
                "shreya",
                "2026-09-17",
                "building 14",
                3
        );
    }

    @Test
    void registerSucceedsWhenSpaceAvailable() {
        boolean result = session.register();

        assertTrue(result);
        assertEquals(1, session.getCurrentParticipants());
    }
    @Test
    void gettersReturnCorrectValues() {
        assertEquals(101, session.getId());
        assertEquals("demo", session.getTitle());
        assertEquals("shreya", session.getMentor());
        assertEquals("2026-09-17", session.getDate());
        assertEquals("building 14", session.getLocation());
        assertEquals(3, session.getMaxParticipants());
        assertEquals(0, session.getCurrentParticipants());
    }

    @Test
    void registerMultipleParticipants() {
        assertTrue(session.register());
        assertTrue(session.register());
        assertTrue(session.register());

        assertEquals(3, session.getCurrentParticipants());
    }

    @Test
    void registerFailsWhenSessionIsFull() {
        session.register();
        session.register();
        session.register();

        boolean result = session.register();

        assertFalse(result);
        assertEquals(3, session.getCurrentParticipants());
    }
}

