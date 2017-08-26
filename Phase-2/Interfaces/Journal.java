package team7;

public class Journal {

    private  String journalName;
    private  Author author;
    private  Publications publications;
    private  String volume;

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publications getPublications() {
        return publications;
    }

    public void setPublications(Publications publications) {
        this.publications = publications;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
