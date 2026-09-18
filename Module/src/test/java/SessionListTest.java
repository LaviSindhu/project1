import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



class SessionListTest {

    private SessionList sessions;

    @BeforeEach
    void setUp() {
        sessions = new SessionList();
    }

    @Test
    void addSessionTest() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 3
        );

        boolean result = sessions.addSession(s1);

        assertTrue(result);
        assertEquals(1, sessions.getAllSessions().size());
        assertEquals(101, sessions.getAllSessions().get(0).getId());
    }

    @Test
    void sessionsAreSortedByDate() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 3
        );

        Session s2 = new Session(
                102, "Math Help", "Viraj",
                "2026-09-22", "Library", 5
        );

        Session s3 = new Session(
                103, "Resume Help", "Shreya",
                "2026-09-15", "Room 2", 4
        );

        // this adds them out of order
        sessions.addSession(s2);
        sessions.addSession(s1);
        sessions.addSession(s3);

        List<Session> result = sessions.getAllSessions();

        // Sept 15  Sept 20 Sept 22
        assertEquals(103, result.get(0).getId());
        assertEquals(101, result.get(1).getId());
        assertEquals(102, result.get(2).getId());
    }

    @Test
    void duplicateIdIsNotAllowed() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 3
        );

        Session s2 = new Session(
                101, "Math Help", "Viraj",
                "2026-09-22", "Library", 5
        );

        assertTrue(sessions.addSession(s1));
        assertFalse(sessions.addSession(s2));

        assertEquals(1, sessions.getAllSessions().size());
    }

    @Test
    void searchByIDFindsSession() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 3
        );

        sessions.addSession(s1);

        Session result = sessions.searchByID(101);

        assertNotNull(result);
        assertEquals("Demo", result.getTitle());
    }

    @Test
    void searchByIDReturnsNullWhenNotFound() {
        Session result = sessions.searchByID(999);

        assertNull(result);
    }

    @Test
    void searchByMentorFindsAllSessions() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 3
        );

        Session s2 = new Session(
                102, "Math Help", "Viraj",
                "2026-09-22", "Library", 5
        );

        Session s3 = new Session(
                103, "Resume Help", "Shreya",
                "2026-09-15", "Room 2", 4
        );

        sessions.addSession(s1);
        sessions.addSession(s2);
        sessions.addSession(s3);

        List<Session> result = sessions.searchByMentor("Shreya");

        assertEquals(2, result.size());

        // this checks if Shreya's sessions are chronological
        assertEquals(103, result.get(0).getId());
        assertEquals(101, result.get(1).getId());
    }

    @Test
    void searchByMentorReturnsEmptyList() {
        List<Session> result = sessions.searchByMentor("Nobody");

        assertTrue(result.isEmpty());
    }

    @Test
    void removeExistingSession() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 3
        );

        sessions.addSession(s1);

        assertTrue(sessions.remove(101));
        assertNull(sessions.searchByID(101));
    }

    @Test
    void removeNonexistentSession() {
        assertFalse(sessions.remove(999));
    }

    @Test
    void registerParticipantTest() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 2
        );

        sessions.addSession(s1);

        assertTrue(sessions.registerParticipant(101));
        assertEquals(1, s1.getCurrentParticipants());
    }

    @Test
    void registerFailsWhenFull() {
        Session s1 = new Session(
                101, "Demo", "Shreya",
                "2026-09-20", "Building 14", 1
        );

        sessions.addSession(s1);

        assertTrue(sessions.registerParticipant(101));
        assertFalse(sessions.registerParticipant(101));

        assertEquals(1, s1.getCurrentParticipants());
    }

    @Test
    void registerFailsWhenSessionDoesNotExist() {
        assertFalse(sessions.registerParticipant(999));
    }
}
