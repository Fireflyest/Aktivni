package com.fireflyest.aktivni.data;

import com.fireflyest.aktivni.util.ReflectUtils;
import com.fireflyest.aktivni.util.YamlUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class YamlDriver implements DataDriver {

    public YamlDriver(){
    }

    @Override
    public void update(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String priKey = fields[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        String table = obj.getClass().getSimpleName().toLowerCase();

        for(Field field : fields){
            if(priKey.equalsIgnoreCase(field.getName()))continue;
            Object value = ReflectUtils.invokeGet(obj, field.getName());
            YamlUtils.setPlayerData(table, target, field.getName(), value);
        }
    }

    @Override
    public int insert(Object obj) {
        int id = 0;
        String table = obj.getClass().getSimpleName().toLowerCase();
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        if("id".equalsIgnoreCase(priKey) && "0".equals(String.valueOf(ReflectUtils.invokeGet(obj, priKey)))){
            id = this.getNewId(table);
            ReflectUtils.invokeSet(obj, priKey, id);
        }
        update(obj);
        return id;
    }

    @Override
    public List<?> queryList(Class<?> clazz, Object target, String key) {
        List<Object> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        String table = clazz.getSimpleName().toLowerCase();
        String priKey = clazz.getDeclaredFields()[0].getName().toLowerCase();

        if(priKey.equalsIgnoreCase(key)){
            Object obj = this.buildObject(clazz, fields, table, target, priKey);
            list.add(obj);
        }else {
            for (String yml : YamlUtils.getPlayerDataKeys(table)) {
                if("###".equalsIgnoreCase(yml))continue;
                if(target.equals(String.valueOf(YamlUtils.getPlayerData(table, yml).get(key)))){
                    Object obj;
                    if("id".equals(priKey)){
                        obj = this.buildObject(clazz, fields, table, Integer.parseInt(yml), priKey);
                    }else {
                        obj = this.buildObject(clazz, fields, table, yml, priKey);
                    }
                    list.add(obj);
                }
            }
        }
        return list;
    }

    @Override
    public Object query(Class<?> clazz, Object target, String key) {
        return queryList(clazz, target, key).get(0);
    }

    @Override
    public Object query(Class<?> clazz, Object target) {
        String priKey = clazz.getDeclaredFields()[0].getName().toLowerCase();
        return this.query(clazz, target, priKey);
    }

    @Override
    public Object query(Object obj) {
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        return query(obj.getClass(), target);
    }

    @Override
    public void delete(Class<?> clazz, Object target, String key) {
        String table = clazz.getSimpleName().toLowerCase();
        String priKey = clazz.getDeclaredFields()[0].getName().toLowerCase();
        if(priKey.equalsIgnoreCase(key)){
            YamlUtils.deletePlayerData(table, String.valueOf(target));
        }else {
            for (String yml : YamlUtils.getPlayerDataKeys(table)) {
                if("###".equalsIgnoreCase(yml))continue;
                if(target.equals(String.valueOf(YamlUtils.getPlayerData(table, yml).get(key)))) YamlUtils.deletePlayerData(table, yml);
            }
        }
    }

    @Override
    public void delete(Object obj) {
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        delete(obj.getClass(), target, priKey);
    }

    @Override
    public boolean contain(Class<?> clazz, Object target, String key) {
        String table = clazz.getSimpleName().toLowerCase();
        String priKey = clazz.getDeclaredFields()[0].getName().toLowerCase();
        if(priKey.equalsIgnoreCase(key)){
            return YamlUtils.containsData(table, String.valueOf(target));
        }else {
            for(String yml : YamlUtils.getPlayerDataKeys(table)){
                if("###".equalsIgnoreCase(yml))continue;
                if(target.equals(String.valueOf(YamlUtils.getPlayerData(table, yml).get(key))))return true;
            }
        }
        return false;
    }

    @Override
    public boolean contain(Object obj) {
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        return contain(obj.getClass(), target, priKey);
    }

    @Override
    public void initTable(Class<?> clazz) {
        String table = clazz.getSimpleName().toLowerCase();
        YamlUtils.addTable(table);
    }

    public int getNewId(String table){
        int id = YamlUtils.getPlayerData(table, "###").getInt("id")+1;
        YamlUtils.setPlayerData(table, "###", "id", id);
        return id;
    }

    public Object buildObject(Class<?> clazz, Field[] fields, String table, Object target, String priKey){
        try {
            Object obj = clazz.newInstance();
            for(Field field:fields){
                Object value;
                if(field.getType().equals(Long.class)){
                    value = YamlUtils.getPlayerData(table, String.valueOf(target)).getLong(field.getName());
                }else {
                    value = YamlUtils.getPlayerData(table, String.valueOf(target)).get(field.getName());
                }
                if(value!=null) ReflectUtils.invokeSet(obj, field.getName(), value);
            }
            ReflectUtils.invokeSet(obj, priKey, target);
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
