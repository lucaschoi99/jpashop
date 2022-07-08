package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address { // 값 타입 클래스는 웬만하면 변경 불가능하게 설계할 것. (@Setter X)

    private String city;
    private String street;
    private String zipcode;

    // JPA가 객체를 생성할 때 리플렉션이나 프록시 같은 기술을 사용할 수 있도록
    // 빈 생성자 생성
    protected Address() {
    }


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
