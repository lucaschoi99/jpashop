package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    public final EntityManager em;

    // 상품 저장
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // 새로운 상품 신규 등록
        } else {
            em.merge(item); // 이미 등록된 상품 DB에서 가져와서 업데이트 하는 개념
        }
    }

    public Item findItem(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
