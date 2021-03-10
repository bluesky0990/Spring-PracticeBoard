# 이 레파지토리 만든이유
다른 프로젝트할 때 참조하려고
# 소스간 역할 및 상호관계, 수정부분
### prj\pom.xml
해당 프로젝트 및 각 라이브러리 관리 파일
내가 추가한 부분은 <!-- 아래부터 내가 추가함 --> 이라고 주석 쳐놨음
이외에는 전부 기본 라이브러리
### prj/src/main/webapp/WEB-INF/web.xml
보다보면 문자인코딩이라고 주석처리 된 부분있는데 이거 없으면 DB에 넣을때 깨지더라
### prj/src/main/webapp/WEB-INF/spring/root-context.xml
13번줄부터 끝까지 내가 추가한건데
13번줄에 jdbc 설정하니까 mybatis에 설정할 필요가 없었음 신기방기
20번줄은 mybatis 세팅
26번줄은 뭔지 모르겠네
30번줄부턴 명시적으로 루트컨텍스트에 등록하는 것 같음(뇌피셜)
  이렇게 써야 bean으로 사용할 수 있데
### prj/src/main/resources/mybatis-config.xml
비워놨는데 잘되더라
루트컨텍스트에 했던 SpringMybatisJDBC 세팅 때문인가?(line 20)
# 배운점
컨트롤러에 @Controller 어노테이션이나 @RequestMapping 같은건 알고있었으니 말안함
근데 스프링하면서 알게된 사실은 통신메소드에 따라서 실행 메소드를 다르게 할 수 있더라
### prj/src/main/java/com/board/controller/BoardController.java
보드컨트롤러에 40, 45번 보면 알 수 있음

DAO엔 @Repository 쓰고, Service엔 @Service 쓰는데
꼭 이렇게 하는건지는 모르겠음.

@Inject 는 뭐 넣어주는거까진 알겠는데 그게 다임 모르겠다.
일단 블로그 말보니깐
'주입하려고 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.'
라는데 말이 주입이지 어떻게 굴러가는지는 모르겠다.

## 블로그 참조하면서 했음
https://kuzuro.blogspot.com/2019/08/1.html
