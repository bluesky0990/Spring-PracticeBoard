# Only for me
# 이 레파지토리를 만든이유
다른 프로젝트할 때 내가 참조하려고
# Setup
DB는 mysql 8.0<br>
임포트 파일은 레파지토리 최상단에 'ExampleSpringBoard.sql'
# 소스간 역할 및 상호관계, 수정부분
### prj\pom.xml
이 파일은 해당 프로젝트 및 각 라이브러리 관리 파일<br>
내가 추가한 부분은 <!- - 아래부터 내가 추가함 - -> 이라고 주석 쳐놨음
이외에는 전부 기본 라이브러리
### prj/src/main/webapp/WEB-INF/web.xml
보다보면 문자인코딩이라고 주석처리 된 부분은 내가 넣었음<br>
이거 없으면 DB에 넣을때 깨지더라
### prj/src/main/webapp/WEB-INF/spring/root-context.xml
13번줄부터 끝까지 내가 추가한건데
13번줄에 jdbc 설정하니까 mybatis에 설정할 필요가 없었음 신기방기<br>
20번줄은 mybatis의 sqlSessionFactory 만들어놓음<br>
26번줄은 20번줄에서 만든거 사용
 - mybatis 특성상 sqlSession을 사용하기 위해서 sqlSessionFactory가 필요한걸로 기억함

30번줄부턴 소스풀더 내의 내가 만든 자바패키지로 의존성주입을 사용하기 위해서 ```<context:component-scan base-package="com.example"/>```를 썻다.<br>
해당 패키지들 내부의 소스들도 잘보면 이 소스가 맞다고하기 위해서 @Repository, @Service를 썻다.

### prj/src/main/resources/mybatis-config.xml
비워놨는데 잘되더라
루트컨텍스트에 했던 SpringMybatisJDBC 세팅 덕분인것 같음(line 20)
# 배운점
컨트롤러에 @Controller 어노테이션이나 @RequestMapping 같은건 알고있었으니 말안함<br>
근데 스프링하면서 알게된 사실은 통신메소드에 따라서 실행 메소드를 다르게 할 수 있더라<br>
Rest 설계할때 쓰일 수 있을 것 같음
### prj/src/main/java/com/board/controller/BoardController.java
보드컨트롤러에 40, 45번 보면 알 수 있음

DAO엔 @Repository 쓰고, Service엔 @Service 쓰는데<br>
기능엔 차이가 없지만 명시적으로 알기위해서 이렇게 한다고함

소스내의 @Inject는 위에서 말한 의존성 주입 사용할 때 쓰는것 같음<br>
주입할게 없으면 예외가 발생하는데 @Autowired 는 예외발생을 방지할 수 있다고함 (```@Autowired(required=false)```)
<br><br><hr><br>
### 기본적인 프로젝트 만들기는 블로그 참조하면서 했고
https://kuzuro.blogspot.com/2019/08/1.html
### 모르는 것들은 추가로 서칭했음
의존성 주입 키워드 https://codevang.tistory.com/256<br>
컨텍스트:컴포넌트-스캔 https://jayviii.tistory.com/10<br>
