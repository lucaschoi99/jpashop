package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void memberJoin() throws Exception {
        // Given
        Member member = new Member("choi", new Address("Seoul", "Gangnam", "123"));

        // When
        Long id = memberService.join(member);

        // Then
        assertEquals(member, memberRepository.findMember(id));

     }

     @Test
     public void duplicateMemberCheck() throws Exception {
         // Given
         Member m1 = new Member("choi", new Address("Seoul", "Gangnam", "123"));
         Member m2 = new Member("choi", new Address("Seoul", "Gangnam", "123"));

         // When
         memberService.join(m1);

         // Then
         IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(m2));
         System.out.println("--------------------------------");
         System.out.println("exception.getMessage() = " + exception.getMessage());
         System.out.println("--------------------------------");
         assertEquals("이미 존재하는 회원입니다.", exception.getMessage());

      }


}