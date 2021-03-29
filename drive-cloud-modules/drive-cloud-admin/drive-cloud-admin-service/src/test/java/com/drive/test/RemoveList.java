package com.drive.test;

import java.util.*;

/**
 * 去掉重复的后，相同的部分
 */
public class RemoveList {
    private <K, V> void cleanListByMapKey(List<Map<K,V>> list, K toBeRemoved) {
      List<Map<K,V>> tmpList=new ArrayList<>();
      for(Map<K,V> m: list){
        if(m.containsKey(toBeRemoved))
          tmpList.add(m);
      }
      list.removeAll(tmpList);
    }


    public void testCleanListByMapKey(){
      List<Map<String,String>> list=new ArrayList<>();
      for(int i=0;i<10;i++){
        Map<String, String> m=new HashMap<>();
        m.put("key"+i, "value"+i);
        list.add(m);
      }
      Map<String, String> m=new HashMap<>();
      m.put("key100", "value100");
      list.add(m);
      System.out.println(list.contains(m));
      cleanListByMapKey(list, "key100");
      System.out.println(list.contains(m));
    }
    public static void main(String[] args) {
        /*RemoveList remove = new RemoveList();
        remove.testCleanListByMapKey();*/
        // TODO Auto-generated method stub
    Map<String, Map> msp = new HashMap<String, Map>();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("id", "1");
    map1.put("name", "p");
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("id", "3");
    map2.put("name", "h");
    Map<String, Object> map3 = new HashMap<String, Object>();
    map3.put("id", "3");
    map3.put("name", "f");
    list.add(map1);
    list.add(map3);
    list.add(map2);
    System.out.println("初始数据：" + list.toString());
    //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
    for(int i = list.size()-1 ; i>=0; i--){
      Map map = list.get(i);
      String id = (String)map.get("id");
      map.remove("id");
      msp.put(id, map);
    }
     //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
    Set<String> mspKey = msp.keySet();
    for(String key: mspKey){
      Map newMap = msp.get(key);
      newMap.put("id", key);
      listMap.add(newMap);
    }
    System.out.println("去掉重复数据后的数据：" + listMap.toString());
    }
}
