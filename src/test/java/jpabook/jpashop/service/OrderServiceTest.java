package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void orderCheck() throws Exception {
        // Given
        Member member = new Member("bin", new Address("Seoul", "Gangnam", "123"));
        em.persist(member);

        Book book = new Book("choi's", "isbn123", 10000, 10);
        em.persist(book);

        // When
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // Then
        Order order = orderRepository.findOrder(orderId);

        assertEquals(OrderStatus.ORDER, order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(10000 * orderCount, order.getTotalPrice());
        assertEquals(8, book.getStockQuantity());

    }

    @Test
    public void notEnoughQuantity() throws Exception {
        // Given
        Member member = new Member("bin", new Address("Seoul", "Gangnam", "123"));
        Book book = new Book("choi's", "isbn123", 10000, 10);

        em.persist(member);
        em.persist(book);

        // When
        int orderCount = 11;
        NotEnoughStockException thrown = assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));

        // Then
        assertEquals("재고가 부족합니다.", thrown.getMessage());

    }


     @Test
     public void cancelCheck() throws Exception {
         // Given
         Member member = new Member("bin", new Address("Seoul", "Gangnam", "123"));
         Book book = new Book("choi's", "isbn123", 10000, 10);

         em.persist(member);
         em.persist(book);

         int orderCount = 2;
         Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

         // When
         orderService.cancelOrder(orderId);

         // Then
         Order order = orderRepository.findOrder(orderId);

         assertEquals(OrderStatus.CANCEL, order.getStatus());
         assertEquals(10, book.getStockQuantity());

      }




}