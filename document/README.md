#스프링 부트 어플리케이션 작성 방법을 배우기 위한 프로젝트

##* 패키지 구조
- domain            도메인 패키지
	- model        	모델 패키지 : POJO로 구현       
    - repository   	model에 대한 repository 인터페이스
    	-dto      	인터페이스에서 사용하는 Data Transfer Object
- infra             Infrastructure 패키지
	- jparepository	jpa 저장소 
	- mapper		mybatis
- service           서비스 패키지 
- web               web controller 패키지

##개발 환경
- Server : Spring Boot 1.4.3
- Client : Angular 4
