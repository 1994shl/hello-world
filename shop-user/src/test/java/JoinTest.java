import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 施海林
 * @create 2022-12-05 17:21
 * @Description
 */
public class JoinTest {

    public static void main(String[] args) {

        List<String> list = Stream.of("1", "2", "2").collect(Collectors.toList());
        System.out.println(StringUtils.join(list, ","));

    }
}
