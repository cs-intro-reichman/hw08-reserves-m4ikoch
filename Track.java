/** Represents a music track. A track has a title (String), an artist (String), 
 *  and a duration (int), in seconds. */
class Track {
    private String title;
    private String artist;
    private int duration;

    /** Constructs a track from the given values. */
    public Track(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    /** Returns this track's data as "artist, title, minutes:seconds".
     *  For example, "John Lennon, Imagine, 3:07" */
    public String toString() {
        String trackAsString;
        String formatDuration;
        formatDuration = formattedDuration(this.duration);
        trackAsString = artist + ", " + title + ", " + formatDuration;
        return trackAsString;
    }

    /** Returns this track's title. */
    public String getTitle() {
        return title;
    }
    /** Returns this track's artist. */
    public String getArtist() {
        return artist;
    }
    /** Returns this track's duration. */
    public int getDuration() {
        return duration;
    }

    /** If this track's duration is shorter than the other track's duration
     *  returns true; otherwise returns false. */
    public boolean isShorterThan(Track other) {
        return duration < other.duration;
    }

    // Returns a string that represents the totalSeconds as "minutes:seconds",
    // Where seconds is always two digits. For example, "3:17" or "12:05".
    private String formattedDuration(int totalSeconds) {
        int minutes = (totalSeconds / 60);
        int seconds = (totalSeconds % 60); 
        int tens = (seconds / 10);
        int digits = (seconds % 10);
        String durationFixed;
        durationFixed = minutes + ":" + tens + digits;
        return durationFixed;
    }
}