package HW3;

import HW3.models.MyHashMap;

public class Main {

    public static void main(String[] args) {
        
        MyHashMap<Integer, Integer> myMap = new MyHashMap<>();

        System.out.println(myMap.size());
        System.out.println(myMap.isEmpty());
        System.out.println(myMap.put(0, 1));
        System.out.println(myMap.put(0, 1));
        System.out.println(myMap.get(0));
        System.out.println(myMap.put(1, 21));
        System.out.println(myMap.put(0, 92));
        System.out.println(myMap.get(0));
        System.out.println(myMap.put(10, 92));
        System.out.println(myMap.get(10));
        System.out.println(myMap.containsKey(1));
        System.out.println(myMap.containsValue(0));
        System.out.println(myMap.containsValue(1));
        System.out.println(myMap.containsValue(92));
        System.out.println(myMap.remove(0));
        System.out.println(myMap.containsKey(0));
        System.out.println(myMap.size());
        System.out.println(myMap.replace(1, 100));
        System.out.println(myMap.get(1));
        System.out.println(myMap.values());

    }
    
}
