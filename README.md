# SPRING BOOT

## JPA

### 엔티티의 생명주기와 영속성 / 1차캐시 / 2차캐시 / 

영속성 컨텍스트는 JPA를 관통하는 가장 중요한 개념이다.

> 엔티티의 생명주기는 다음과 같다.

1. 비영속   / (new/transient) 영속성 컨텍스트와 무관한 새로운 상태
2. 영속     / (managed) 영속성 컨텍스트에 관리되는 상태
3. 준영속   / (detached) 영속성 컨텍스트에 저장되었다가 분리된 상태
4. 삭제     / (removed) 삭제된 상태

비영속 상태는 JPA와 관계가 없는 상태인 것을 말한다.
영속상태는 persist()된 것을 의미한다.

#### 1차캐시

> Java persistance API에서 1차 캐시는 EntityManager가 관리하는 영속성 컨텍스트 내부에있는 첫 번째 캐시이다.

#### 1차 캐시의 조회 동작

1. 조회 시 1차 캐시에 데이터가 있는지 확인, 데이터가 있으면 가져옴. (비교는 PK로 함)
2. 1차 캐시에 데이터가 없다면, 데이터베이스에 데이터를 요청.
3. 데이터 베이스에서 받은 데이터를 다음에 재사용할 수 있도록 1차 캐시에 저장.

> Q. Update가 발생하면 데이터가 갱신될텐데, 캐시에 있는 것을 조회한다면 과거의 데이터를 조회하는 꼴이 아닌가?
> 
> A. 1차 캐시는 영속성 컨텍스트 내부에 있다. 영속성 컨텍스트 내부에 있는 엔티티의 변화가 즉시 1차 캐시에 저장되기 때문에, 캐시로부터 갱신된 데이터를 조회할 수 있다. 


#### 2차 캐시

애플리케이션에서 공유하는 캐시를 공유캐시(Shared Cache)라고 하는데,
일반적으로 2차 캐시라고 불리운다.

2차캐시는 애플리케이션 범위의 캐시이다.
따라서 애플리케이션이 종료될 때 까지 유지된다.

지금은 이정도만 인지하자.


#### 수정 변경 감지

영속성 컨텍스트가 관리하는 엔티티가 수정되면 지연SQL저장소에 쿼리문이 저장된다.

#### 플러시

> Flush가 발생하면 쌓여있던 지연 SQL저장소에 있는 쿼리들을 즉시 DB로 날린다.
>
> 즉, 변경사항을 한 번에 기록하는 것이다.

아래 3가지 중 한 가지라도 발생되면 Flush가 발생한다.
- Transaction.commit();
- EntityManager.flush();
- JPQL 쿼리 실행

> 즉 쓰기 동작은 다음 순서대로 동작한다.

1. 데이터가 변경되면 즉시 1차캐시에 반영된다.
2. 변경사항이 지연SQL 저장소에 저장된다.
3. Transaction이 commit되면 flush가 발생한다.
4. 지연SQL저장소에 있는 SQL문을 DB에 요청한다.


### 주의 
> JPA는 트랜잭션이 commit되면 Entity의 변경을 감지하여 DB를 갱신하기 때문에. Entity의 필드를 생각없이 변경하면 큰일난다.

---

## DTO 와 Entity

### Entity란?

> DB 테이블에 존재하는 Column들을 필드로 가지는 객체이다. (DB와 맞닿는 핵심 클래스)

- Entity는 DB의 테이블과  1대 1로 대응된다. 때문에 테이블이 가지지 않는 컬럼을 필드로 가져선 안된다.
- 다른 클래스를 상속받거나 인터페이스의 구현체여서는 안된다.

### DTO란?

> Data Transfer Object

- 게층간 데이터 교환역할 수행
- Entity를 Controller 같은 Client 단과 직접 마주하는 계층에 다이렉트로 전달하는 대신, DTO를 사용해 데이터를 교환한다.
- DTO는 계층간 데이터 교환이 이루어질수있도록하는 객체이며 로직을 갖지않아야한다.
- DB에서 꺼낸 값을 조작할 필요가 없기에 Setter를 만들 필요가 없고 생성자에서 값을 할당한다.

### Entity와 DTO 뜻은 알겠는데 어느 Layer에서 변환해주어야할까?
> Entity클래스를 기준으로 테이블이 생성되고, 스키마가 변경된다.

- 그렇기 때문에 다양한 계층에서 직접 Entity클래스를 사용하게되면 Entity의 속성을 변경시키거나 Entity의 모든 속성이 불필요하게 외부로 노출되는 위험한 상황이 연출될 수 있다.
-  그래서 DTO를 사용한다.

- Entity클래스에서 필요한 데이터만 선택적으로 DTO에 담아서 생성하여 사용하면 Entity클래스를 감추며 보호할 수 있다.

#### Service Layer에서 변환작업을 진행하도록 하자.


---

### EntityManager란?

> Entity를 관리하는 역할을 수행하는 클래스이다.

 - EntityManager내부에 Persistence Context(영속성 컨텍스트)를 통해 Entity들을 관리한다.


구조는 다음과 같다.

```java

public class EntityTemplate {

	// EntityManagerFactory 는 thread safe 하기 때문에 static에 올려놓고 사용하거나 singleTon 으로 사용해도 안전하다.
  // 데이터베이스당 하나
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("a_jpa"); // 엔티티 매니저 팩토리 생성
	
	
	// EntityManager 는 thread safe하지 않기 때문에 반드시 지역에서 생성한다.
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	
}

```



``` java

  public boolean createBook(BookDto dto) {
		
		EntityManager em = EntityTemplate.getEntityManager(); // EntityManager 호출
		EntityTransaction tx = em.getTransaction(); // 트랜잭션 기능 획득 / 데이터베이스의 모든 변경은 트랜잭션 안에서 일어나야한다.
		tx.begin(); // 트랜잭션 시작
		
		try {
			Book book = Book.createBook(dto);
			em.persist(book); // persist시 영속상태가 된다. DB에 저장되는 것은 아니며, commit하는 순간 DB에 저장된다.
			tx.commit(); // 트랜잭션을 commit 하는 순간 DB에 저장된다.
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // 에러 시 롤백
		}finally {
			em.close(); // 엔티티 매니저 종료
		}
		
    // 여기엔 구현하지 않았으나,  엔티티 매니저 팩토리 종료
		return false;
	}


```

---

## @annotation 
> velog에 정리하였다.