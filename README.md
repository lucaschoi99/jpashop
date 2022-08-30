# jpashop
Toy project on JPA &amp; Spring Boot

⭐️ JPA shop 프로젝트 구현할 때 신경썼던 점
- Entity 레벨에서 최대한 비지니스 로직을 넣어두려고 했다. (주문 서비스의 주문, 주문 취소 메소드 등)
- 서비스 계층에서는 단순하게 Entity 레벨에 필요한 request를 위임하는 역할만 담당하도록 설계했다.
- 이것이 바로 “도메인 모델 패턴” :  장점 2개
  1. 상황에 따라 다르겠지만 jpa와 도메인 모델 패턴으로 개발하면 객체지향 특성을 잘 살리고 유지 보수에 유리하지 않을까 생각함.
  2. 단위 테스트 검증에 편리함
- JPA 공부했던 개념들을 최대한 녹여보려고 했다. (여러 가지 연관관계 매핑, 상속관계, cascade, jpql 등)

⭐️ 더 공부해야 할 것들
- Jpql, Querydsl 기술로 동적쿼리 다루는 법
- Api 다루는 방법
