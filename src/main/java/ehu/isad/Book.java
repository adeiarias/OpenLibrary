package ehu.isad;

public class Book {

    String isbn;
    String title;
    String info_url;
    String bib_key;
    String preview_url;
    String thumbnail_url;
    Details details;

    public Book(String izenburua, String ISBN){
        isbn = ISBN;
        title = izenburua;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public Details getDetails() {
        return details;
    }

    public String getIsbn() { return isbn; }
}
