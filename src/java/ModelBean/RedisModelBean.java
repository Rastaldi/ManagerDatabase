/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Rastaldi
 */
@ManagedBean (name="RedisModelBean")
@SessionScoped
public class RedisModelBean  implements Serializable {
    
    
    
     public RedisModelBean(){
       
    }

    
    
//    public static void conexionRedis(){
//        Jedis connJedis = new Jedis("localhost");
//        connJedis.select(2);
//    }
    
    public boolean addRedisSet(String nameSet, String txtSetRedis){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        connJedis.sadd(nameSet, txtSetRedis);
        connJedis.bgsave();
         return (connJedis.sadd(nameSet, txtSetRedis)) == 1;
        
        
    }
    
    public void removeSet(String nameRemoveSet, String valorRemoveSet){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        
        connJedis.srem(nameRemoveSet, valorRemoveSet);
        connJedis.bgsave();
        
    }
    
    public void removeKey(String nameKeyRemove) {
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);

        connJedis.del(nameKeyRemove);
        connJedis.bgsave();
        
    }
    
    public void addHashRedis(String nameHash, String nameKeyHash, String nameValueHash){
         Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        
        connJedis.hset(nameHash, nameKeyHash, nameValueHash);
        connJedis.bgsave();
    }
    
    public void deleteHashRedis(String nameHash, String nameKeyHash, String nameValueHash){
         Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        
        connJedis.hdel(nameHash, nameKeyHash, nameValueHash);
        connJedis.bgsave();
    }
    
    public void addListRedis(String modoList, String nameList, String nameValueList){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        if(modoList.equals("rpush")){
            connJedis.rpush(nameList, nameValueList);
        }else {
            connJedis.lpush(nameList, nameValueList);
        }
        connJedis.bgsave();
    }
    
    public void addListIndexRedis(Long indexList, String nameList, String nameValueList){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        connJedis.lset(nameList, indexList, nameValueList);
    }
    
    public void deleteListRedis(Long indexList, String nameList, String nameValueList){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        connJedis.lrem(nameList, indexList, nameValueList);
    }
    

//    public List verListRedis() {
//       
//        Jedis connJedis = new Jedis("localhost");
//        connJedis.select(2);
//        
//        Map<String, String> keys = connJedis.hgetAll("contenedor:tipo");
//        
//        List result = new ArrayList();
//        List resultSet = new ArrayList();
//        List resultList = new ArrayList();
//        List resultHash = new ArrayList();
//        for (Map.Entry<String, String> entry : keys.entrySet()) {
//            String nameKey = entry.getKey();
//            String tipoKey = entry.getValue();
//            switch (tipoKey){
//                case "set":
//                    Set set = connJedis.smembers(nameKey);
//                    resultSet.addAll(set);
//                    break;
//                case "hash":
//                    Map<String, String> hash = connJedis.hgetAll(nameKey);
//                    resultHash.add(hash);
//                    break;
//                case "list":
//                    List<String> list = connJedis.lrange(nameKey,0,-1);
//                    resultList.add(list);
//                    break;  
//            }
//        }
//        result.addAll(resultSet);
////       
//        
//        result.addAll(resultHash);
//        result.addAll(resultList);
//               
//         return result;
//    }
    
    public Set extraerKeysRedis(){
        Set resultAllKeys;
        
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        
        resultAllKeys = connJedis.keys("*");
//        Iterator iterator = resultAllKeys.iterator();
//         while (iterator.hasNext()){
//             System.out.println(resultAllKeys);
//         }
        return resultAllKeys;
    }
    
    //Ahora no retorna nada se lo asigna a una lista que ya tienes
    public List<String> extraerKeyRedis(String nameExtraeKey){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        List resultKey = new ArrayList();
        String type = connJedis.type(nameExtraeKey);
        switch (type){
                case "set":
                    Set<String> set = connJedis.smembers(nameExtraeKey);
                    for (String s : set) {
                        resultKey.add(s);
                    }
                    break;
                case "hash":
                    Map<String, String> hash = connJedis.hgetAll(nameExtraeKey);
                    for (Map.Entry<String, String> entry : hash.entrySet()) {
                            String nameKey = entry.getKey();
                            String tipoKey = entry.getValue();
                            resultKey.add(nameKey+"="+tipoKey);
                    }
                    break;
                case "list":
                    List<String> list = connJedis.lrange(nameExtraeKey,0,-1);
                    for (String s : list) {
                        resultKey.add(s);
                    }
                    
                    break;  
            }
        
        return resultKey;
//        if (type.equals("set")){
//            Set set = connJedis.smembers(name);
//            return set;
//        }else if (type.equals("hash")){
//            Map<String, String> hash = connJedis.hgetAll(name);
//            return hash;
//        }else if (type.equals("list")){
//             List<String> list = connJedis.lrange(name,0,-1);
//             return list;
//        }
  }
        
    }
