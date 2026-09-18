import java.util.ArrayList;
import java.util.List;

// A singly linked list of mentorship sessions, kept sorted by date
public class SessionList {
    private Node head;


    public SessionList() {
        head = null;
    }

    // this adds a session while keeping the list sorted by date
    public boolean addSession(Session session) {

        // this checks for duplicate ID
        if (searchByID(session.getId()) != null) {
            return false;
        }

        Node newNode = new Node(session);

        // this is the empty lis
        if (head == null) {
            head = newNode;
            return true;
        }

        // this is the new session goes before the first session
        if (session.getDate().compareTo(head.data.getDate()) < 0) {
            newNode.next = head;
            head = newNode;
            return true;
        }

        // this finds correct location in the list
        Node current = head;

        while (current.next != null &&
                current.next.data.getDate().compareTo(session.getDate()) <= 0) {

            current = current.next;
        }

        // this part inserts new node
        newNode.next = current.next;
        current.next = newNode;

        return true;
    }

    // this searchs for a session by ID
    public Session searchByID(int id) {

        Node current = head;

        while (current != null) {

            if (current.data.getId() == id) {
                return current.data;
            }

            current = current.next;
        }

        return null;
    }

    // this searchs for all sessions from a mentor
    public List<Session> searchByMentor(String mentor) {

        List<Session> results = new ArrayList<>();

        Node current = head;

        while (current != null) {

            if (current.data.getMentor().equals(mentor)) {
                results.add(current.data);
            }

            current = current.next;
        }

        return results;
    }

    // this removes a session by ID
    public boolean remove(int id) {

        // the empty list
        if (head == null) {
            return false;
        }

        // this removes the first node
        if (head.data.getId() == id) {
            head = head.next;
            return true;
        }

        Node current = head;

        // this search for the node
        while (current.next != null) {

            if (current.next.data.getId() == id) {

                current.next = current.next.next;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    // this registers a participant for a session
    public boolean registerParticipant(int id) {

        Session session = searchByID(id);

        if (session == null) {
            return false;
        }

        return session.register();
    }

    // this gets every session in date order
    public List<Session> getAllSessions() {

        List<Session> results = new ArrayList<>();

        Node current = head;

        while (current != null) {

            results.add(current.data);

            current = current.next;
        }

        return results;
    }
}


// One node in the linked list -- given complete, no need to change
class Node {
    Session data;
    Node next;

    Node(Session data) {
        this.data = data;
        this.next = null;
    }
}
