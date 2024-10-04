package io.github.mthsilva98.model;

public class User {
    private String name;
    private Book  rentedBook;

    public User (String nome) {
        this.name = name;
        this.rentedBook = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(Book rentedBook) {
        this.rentedBook = rentedBook;
    }
    public void rentBook (Book book) {
        if (book.isAvailable()) {
            rentedBook = book;
            book.setAvailable(false);
            System.out.println(name + "alugou o livro: " + book.getTitle());
        } else {
            System.out.println("O livro " + book.getTitle() + " não está disponivel.");
        }
    }
    public void backBook() {
        if (rentedBook != null) {
            rentedBook.setAvailable(false);
            System.out.println(name + " devolveu o livro " + rentedBook.getTitle());
            rentedBook = null;
        } else {
            System.out.println(name + " não tem nenhum livro para devolver. ");
        }

    }
}
