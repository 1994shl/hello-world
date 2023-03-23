import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 施海林
 * @create 2023-03-01 16:16
 * @Description
 */
public class JoinTest {

    public static void main(String[] args) {
        String str = "a,b,c,d,e,";
        List<String> list = Arrays.stream(str.split(",")).distinct().collect(Collectors.toList());

        System.out.println(list.size());

        list = list.subList(1, list.size());

        list.forEach(System.out::println);

        list.add("f");
        list.add(null);
        System.out.println(StringUtils.join(list, ","));

       // list.forEach(System.out::println);
    }
}
