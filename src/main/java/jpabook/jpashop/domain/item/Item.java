package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.CategoryItem;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categories = new ArrayList<>();

    //== 비즈니스 로직 메소드==//
    // 재고수량 증가
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    // 재고수량 감소
    public void removeStock(int stockQuantity) {
        int result = this.stockQuantity - stockQuantity;
        if (result < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.")
        }
        this.stockQuantity = result;
    }


}
