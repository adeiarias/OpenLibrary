package ehu.isad;

import java.util.Arrays;

public class Details {

    String title;
    String number_of_pages;
    String[] publishers;

    @Override
    public String toString() {
        return "Liburua{" +
                "title='" + title + '\'' +
                ", number_of_pages='" + number_of_pages + '\'' +
                ", publishers=" + Arrays.toString(publishers) +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getNumber_of_pages() {
        return number_of_pages;
    }

    public String[] getPublishers() {
        return publishers;
    }

}
