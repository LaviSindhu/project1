// Represents one mentorship session: an ID, a title, a mentor, a date,
// a location, and a count of current vs. maximum participants.
public class Session {

    private int id;
    private String title;
    private String mentor;
    private String date;
    private String location;
    private int maxParticipants;
    private int currentParticipants;

    public Session(int id, String title, String mentor,
                   String date, String location, int maxParticipants) {

        this.id = id;
        this.title = title;
        this.mentor = mentor;
        this.date = date;
        this.location = location;
        this.maxParticipants = maxParticipants;

        currentParticipants = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMentor() {
        return mentor;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public boolean register() {
        if (currentParticipants < maxParticipants) {
            currentParticipants++;
            return true;
        }

        return false;
    }
}
