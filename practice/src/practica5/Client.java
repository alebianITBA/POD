package practica5;

import java.util.*;

public class Client {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new LinkedList<>();
        list.add("chau");
        map.put("hola", list);
        System.out.println(Optional.ofNullable(map.get("chau")).orElse(new LinkedList<>()));
    }
}
