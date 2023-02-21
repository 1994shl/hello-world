package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 施海林
 * @create 2022-08-05 16:57
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Man implements Serializable {

    private static final long serialVersionUID = -8324141786814254389L;

    private String name;

    private String address;

    public static void main(String[] args) {
        //listToJson();
        jsonToList();
    }

    public static void jsonToList(){
        String str = "{\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"address\":\"南京\",\n" +
                "            \"name\":\"shl\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"address\":\"南京\",\n" +
                "            \"name\":\"ylw\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);

        List<Man> list = JSON.parseArray(jsonObject.get("data").toString(), Man.class);
        list.forEach(System.out::println);
    }

    public static void listToJson() {
        List<Man> list = Stream.of(
                new Man("shl", "南京"),
                new Man("ylw", "南京")).collect(Collectors.toList());
        System.out.println(JSON.toJSON(list));

    }

}
