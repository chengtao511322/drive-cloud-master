package com.drive.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 去重并排序
 */
public class ListMapSort {
     /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO 自动生成方法存根
    List<Map<String,Object>> listMap1 = new LinkedList<Map<String,Object>>();
    Map<String,Object> map = new HashMap<String, Object>();
    map.put("date", 20121010);
    listMap1.add(map);
    map = new HashMap<String, Object>();
    map.put("date", 20011213);
    listMap1.add(map);
    listMap1.add(map);
    map = new HashMap<String, Object>();
    map.put("date", 20130502);
    listMap1.add(map);
    System.out.println("原始"+listMap1);
    List<Map<String,Object>> listMap2 = new LinkedList<Map<String,Object>>();
    Set<Map> setMap = new HashSet<Map>();
    for(Map<String,Object> map1 : listMap1){
      if(setMap.add(map1)){
        listMap2.add(map1);
      }
    }
    System.out.println("去重"+listMap2);
    Collections.sort(listMap2, new Comparator<Map<String,Object>>(){
      public int compare(Map<String,Object> o1,Map<String,Object> o2){
        return o1.get("date").toString().compareTo(o2.get("date").toString());
      }
    });
    System.out.println("排序:"+listMap2);
  }
}