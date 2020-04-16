package app.lani;

public class Book {
    private String id;
    private boolean status;
    private String title;

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
        status = true;
    }

    public boolean equals(Book bookCompare) {
        if(this.id == bookCompare.id && this.title == bookCompare.title) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean equals(String idCompare, String titleCompare) {
        return equals(new Book(idCompare, titleCompare));
    }

    public boolean getStatus() {
        return status;
    }
}