package test.Optional;

import test.domain.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Null과 관련된 class
// Optional은 좋은 프로그램을 관행을 유지하면서 nullable 값을 명시적으로 처리가능.
public class OPtional_Test {
    public static void main(String[] args) {
        /*
        * empty()
        * 빈 Optional 객체를 생성
        * Optional<T>는 정적클래스로 구현이 되어있어 인스턴스 생성이 불가능.
        * 그래서 Optional에 정적 메소드를 사용할 수 있다.
        */
        Optional<String> empty = Optional.empty();
        // Optional<String> Instance = new Optional<String>();
        System.out.println(empty.isPresent());
        System.out.println("-----------------------------");

        /*
        * of()
        * 전달된 인수를 랩핑하여 Optional 객체 생성
        * 메서드에 전달된 인수는 null 이 들어가면 안됨.
        * 들어가면 NullPointerException 발생.
        */
        String notNull = "String injection";
        // String notNull = null;
        Optional<String> optOf = Optional.of(notNull);

        System.out.println(optOf.isPresent());
        System.out.println("---------------------------");

        /*
        * ofNullable() -> null이 필요한 경우
        * null 값이 들어오면 빈 Optional 객체 생성
        */
        String incNull = "String injection";
        // String incNull = null;
        Optional<String> optOfNull = Optional.ofNullable(incNull);

        System.out.println(optOf.isPresent());
        System.out.println("--------------------------");

        /*
        * isPresent() -> 안에 Optional이 존재한지 존재하지않는지
        * ifPresent() -> 조건부 동작처리
        */

        String nullCheck = "is Null check";
        // String nullCheck = null;
        if (nullCheck != null) {
            System.out.println(nullCheck.length());
        }
        // 아래와 같은동작.

        Optional<String> isNullCheck = Optional.ofNullable(nullCheck);
        // Optional<String> isNullCheck = Optional.of(nullCheck);
        isNullCheck.ifPresent(value -> System.out.println(value.length()));
        System.out.println("----------------------------");

        /*
        * orElse()
        * 기본값으로 처리
        * Optional 인스턴스에 립핑된 값이 존재한다면 그값을 리턴하고 없으면
        * 메소드에 전달된 매개변수 값을 리턴
        * */
        // String str1 = null;
        String str1 = "null이 아닐 경우";
        System.out.println("str1 = " + str1);

        Optional<String> ofNullable1 = Optional.ofNullable(str1);
        str1 = ofNullable1.orElse("null일 경우 기본값 생성");
        System.out.println("str1 = " + str1);
        System.out.println("-----------------------------");

        /*
        * orElseGet()
        * 기본값으로 처리
        * eoElse()와 유사하지만 Optional 값이 존재하지않으면
        * 인수로 전달된 공급자 함수의 결과값 반환

        // 본문
        public T orElseGet(Supplier<? extends T> other) {
            return value != null ? value : other.get();
        }

        * orElseGet()와 eoElse()의 차이
        * orElseGet()는 Optional객체가 비어있을 경우만 실행.
        * eoElse()는 Optional객체가 비어있던 비어있지않던 반드시 실행.
        */

        // String str2 = null;
        String str2 = "null이 아님";
        Optional<String> ofNullable2 = Optional.ofNullable(str2);
        str2 = ofNullable2.orElseGet(() -> "null일 경우만 기본값 생성");
        System.out.println(str2);
        System.out.println("---------------------------");

        /*
        * orElseGet(), eoElse()와 비슷하지만 빈 값을 처리하게위한 새로운 접근법
        * */
        // String str3 = null;
        // Optional.ofNullable(str3).orElseThrow(IllegalArgumentException::new);

        /*
        * get()
        * Optional에 랩핑된 객체의 연산이 끝나고 최종적으로 값을 가져옴
        * */
        Optional<String> opt = Optional.of("HappyDaddy");
        String str3 = opt.get();
        System.out.println("get() 호출 = " + str3);
        System.out.println("-------------------------------------");

        /*
        * filter()
        * 값이 있고 값이 주어진 술어와 일치하면 Optional값을 반환하고,
        * 그렇지 않으면 비어 있는 값을 반환
        * */
        Integer year = 2019;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2019 = yearOptional.filter(y -> y == 2019).isPresent();
        System.out.println(is2019);
        boolean is2020 = yearOptional.filter(y -> y == 2020).isPresent();
        System.out.println(is2020);
        System.out.println("-------------------------------------");

        /*
        * map()
        * 값이 있으면 제공된 매핑 함수를 적용하고 결과를 반환
        * */
        List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
        // List<String> companyNames = null;

        Optional<List<String>> listOptional = Optional.of(companyNames);
        // int size = listOptional.get().size();
        int size = listOptional.map(List::size).orElse(0);
        System.out.println("size = " + size);

        // filter()와 응용
        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(pass -> pass.equals("password")).isPresent();
        System.out.println("filter만 사용 = " + correctPassword);
        correctPassword = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
        System.out.println("map과 filter만 사용 = " + correctPassword);
        System.out.println("-----------------------------------------");

        /*
        * flatMap()
        * map()은 래핑 되지않은 경우에만 값을 변환,
        * 하지만 flatMap()는 래핑된 값을 가져와서 변환하기전에 래핑 해제.
        * */
        Person person = new Person("john", 26);
        Optional<Person> personOptional = Optional.of(person);                                          // person을 Optional객체에 매핑
        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);           // 래핑된 Optional객체에 getName 메서드 호출 후 다시 래핑
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new); // null일 경우 Exception처리 아니면 Optional객체에 매핑
        String name1 = nameOptional.orElse("");                                                   // null일 경우 빈객체
        System.out.println("map()을 사용한 name = " + name1);

        String name = personOptional.flatMap(Person::getName).orElse("");
        System.out.println("flatMap()을 사용한 name = " + name);

    }

}
