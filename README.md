# Data Display Web(DDW)

DDW는 프로그램 등의 데이터 상품을 등록하고 판매할 수 있는 사이트입니다.  
관리자는 상품을 판매하기 위한 게시글을 작성할 수 있고, 회원은 결제 및 다운로드가 가능합니다.  
더불어 커뮤니티 게시판에서 다른 회원들과 소통할 수 있습니다.  

![main](https://github.com/dasl1101/DDW/assets/102366257/f87f48b5-2ec8-4465-975d-9e33d6078634)

## 프로젝트 기능
* 게시판 - CRUD 기능, 파일 업로드, 썸네일 업로드, 페이징, 조회수  
* 회원 - Security 회원가입 및 로그인, OAuth 2.0 구글로그인, 회원 정보 수정 및 탈퇴, 유효성 검사  
* 댓글 - CRUD 기능

  
![image](https://github.com/dasl1101/DDW/assets/102366257/fdec49d8-79b0-4abf-9656-b33d56c045b1)  
상품 리스트  
  
![image](https://github.com/dasl1101/DDW/assets/102366257/731c24d3-e59b-47de-9377-5b30b63a44b9)  
커뮤니티 게시판  

## 기술 스택
기술 설명 및 선택한 이유 ( https://dsdsds.tistory.com/118 )

### 백엔드
* JAVA 11  
* SpringBoot 2.7.12  
* JPA(Spring Data JPA)  
* Spring Security  
* OAuth 2.0  
* Gradle 7.6.1  

### DB
* MariaDB  

### 프론트
* Thymeleaf  
* Html/Css  
* JavaScript  
* Summernote  
* Bootstrap 5.0.2  

### 인프라
* AWS(EC2, RDS)  


## 후기

### 1. 보완 및 추가 계획

EC2인스턴스에 프로젝트를 배포하였기 때문에 로컬에 있던 이미지가 뜨지 않고 있습니다.  
S3 스토리지를 사용해 이미지 경로를 수정하고 재업로드할 계획입니다.  

조회수가 게시글을 클릭할 때마다 늘어나고 있어 쿠키/세션을 사용하여 해당 사항을 방지할 예정입니다.  

결제 Api를 사용하여 결제 기능을 만들 예정입니다.  

### 2. 후기

DDW 프로젝트는 많은 것을 배울 수 있었던 프로젝트 입니다.  
먼저 최신 기술들을 사용해서 프로젝트를 만들었다는 의의가 있습니다.  
기존에 진행했던 프로젝트는 Maven으로 빌드한 Spring 프로젝트였으며, JSP와 서블릿을 사용하였고 자바 코드 내에 쿼리를 그대로 입력하는 형식이었습니다.  
이번 프로젝트에서는 Gradle을 사용한 SpringBoot 프로젝트이며, 템플릿 엔진은 Thymeleaf를, DB연결은 JPA를 사용했습니다.  
이러한 변화로 jar 파일로 export할 수 있게 되어 EC2에서 배포가 가능했고, JPA(ORM)을 사용하여 DBMS에서 종속적인 문제를 해결하였고 보다 객체지향적인 개발을 할 수 있게 되었습니다.  
  
두 번째로는 강의 등에서 사용하는 프로젝트의 코드를 그대로 따라하는 것이 아닌 스스로 만들어낸 프로그램이라는 점에서 개발의 즐거움을 알려주었습니다.  
막연하게만 알고 있던 기능들과 흩어져 있던 지식들을 이번 프로젝트를 진행하며 집약할 수 있었고, 개발의 흐름을 볼 수 있게 되었습니다.  
검색하는 법 및 Git사용법, Test code 작성법부터 다시 배우며 스스로의 부족함을 느꼈지만 프로젝트를 진행하며 더 공부하며 성장해야겠다는 의욕과 앞으로 더욱 좋은 결과물을 만들 수 있겠다는 자신감이 생겼습니다.  
  
끝까지 읽어주셔서 감사합니다.  