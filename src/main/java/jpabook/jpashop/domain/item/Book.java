package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book() {
    }

    public Book(String author, String isbn, int price, int quantity) {
        this.author = author;
        this.isbn = isbn;
        this.setPrice(price);
        this.setStockQuantity(quantity);
    }
}
