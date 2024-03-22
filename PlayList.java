/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (size == maxSize)
        {
            return false;
        }
        else {
            this.tracks[size] = track;
            size++;
            return true;
        }
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.tracks[i].toString()).append("\n");
        }
        return sb.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if(this.size != 0) {
            tracks[this.size - 1] = null;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int i=0;
        int total = 0;
        while (this.tracks[i] != null) {
            total = total + this.tracks[i].getDuration();
            i++;
        }
        return total;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        String fixTitle = "";
        int ch;
        if (title.charAt(0) >= 97) {
            ch = title.charAt(0);
            ch = (ch - 32);
        } 
        else {
            ch = title.charAt(0);
        }
        fixTitle = fixTitle + ch;
        for (int i=1; i<title.length(); i++) {
            ch = title.charAt(i);
            if(ch >= 97) {
                fixTitle = fixTitle + ch;
            } else {
                fixTitle = fixTitle + (ch - 32);
            }
        }
        for (int i=0; i < this.size; i++) {
            String testTitle = this.tracks[i].getTitle();
            boolean same = false;
            if (testTitle.length() == fixTitle.length()) {
                for (int j=0; j < fixTitle.length(); j++) {
                    if (testTitle.charAt(j) != fixTitle.charAt(j)) {
                        same = false;
                        j = fixTitle.length() - 1;
                    }
                }
                if (same) {
                    return i;
                }
            }
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        int index = i;
        if (this.size == this.maxSize || index < 0 || index > this.size){
            return false;
        } else {
            for (int j= this.size -1; j>=index; j--) {
                this.tracks[j+1] = this.tracks[j];
            }
            this.tracks[index] = track; 
        }
        return true;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if (this.size == 0 || i < 0 || i>= this.maxSize) {
            return;
        }
        else {
            for ( int j=i; j<this.size; j++) {
                tracks[j] = tracks[j+1];
            }
            tracks[this.size - 1] = null;
            size--;
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        int index = indexOf(title);
        if(this.size == 0 || index < 0 || index > this.size) return;
        remove(index);
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        remove(0);
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        boolean happend = true; 
        int i = 0;
        if ((this.size + other.getSize()) <= this.maxSize) {
            while (happend) {
                happend = add(other.tracks[i]);
                i++;
            }
        } 
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        if(start < 0 || start > this.size) return -1;
        int shortest = tracks[start].getDuration();
        int index = start;
        for (int i=start+1; i<size; i++) {
            if (tracks[i].getDuration() < shortest) {
                shortest = tracks[i].getDuration();
                index = i;
            }
        }
        return index;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        if (this.size == 0) return null;
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        //// replace this statement with your code
        for(int i=0; i<this.size; i++) {
            int start = i;
            Track temp = tracks[i];
            int indexShort = minIndex(start);
            tracks[i] = tracks[indexShort];
            tracks[indexShort] = temp;
        }
    }
}
