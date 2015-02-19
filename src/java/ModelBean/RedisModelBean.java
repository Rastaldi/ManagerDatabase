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
    

    
    // Con este metodo sacamos todos los nombres de las keys
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
    
    //este método de con un name de una key concreta saca su contenido.
    public List extraerKeyRedis(String nameExtraeKey){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        List resultKey = new ArrayList();
        String type = connJedis.type(nameExtraeKey);
        switch (type){
                case "set":
                    Set set = connJedis.smembers(nameExtraeKey);
                    resultKey.add(set);
                    break;
                case "hash":
                    Map<String, String> hash = connJedis.hgetAll(nameExtraeKey);
                    resultKey.add(hash);
                    break;
                case "list":
                    List<String> list = connJedis.lrange(nameExtraeKey,0,-1);
                    resultKey.add(list);
                    
                    break;  
            }
        
        return resultKey;

  }
        
    }
