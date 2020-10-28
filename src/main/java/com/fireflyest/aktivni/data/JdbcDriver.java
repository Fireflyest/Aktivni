package com.fireflyest.aktivni.data;

import com.fireflyest.aktivni.util.ConvertUtils;
import com.fireflyest.aktivni.util.JdbcUtils;
import com.fireflyest.aktivni.util.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDriver implements DataDriver{

    private String url;
    private String user;
    private String password;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public JdbcDriver(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * 更新数据
     * @param obj 对象
     */
    @Override
    public void update(Object obj){
        StringBuilder update = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        String priKey = fields[0].getName().toLowerCase();
        int amount = 0;
        for(Field field : fields){
            if(amount > 0) update.append(",");
            if(priKey.equalsIgnoreCase(field.getName()))continue;
            update.append(field.getName()).append("=").append("'").append(ReflectUtils.invokeGet(obj, field.getName())).append("'");
            amount++;
        }
        String table = obj.getClass().getSimpleName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, fields[0].getName()));
        String value = update.toString().replace("'true'", "1").replace("'false'", "0");
        String sql = String.format("update %s set %s where %s='%s'", table, value, priKey, target);
        //执行指令
        try {
            this.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }
    }

    /**
     * 插入数据
     * @param obj 对象
     */
    @Override
    public int insert(Object obj){
        //拼接sql指令
        StringBuilder fieldString = new StringBuilder();
        StringBuilder valueString = new StringBuilder();
        int amount = 0;
        for(Field field : obj.getClass().getDeclaredFields()){
            if(amount > 0){
                fieldString.append(",");
                valueString.append(",");
            }
            fieldString.append(field.getName());
            valueString.append("'").append(ReflectUtils.invokeGet(obj, field.getName())).append("'");
            amount++;
        }
        String values = valueString.toString().replace("'true'", "1").replace("'false'", "0");
        String table = obj.getClass().getSimpleName().toLowerCase();
        String sql = String.format("insert into %s (%s) values (%s)", table, fieldString.toString(), values);
        //执行指令
        try {
            return this.executeInsert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, statement, connection);
        }
        return 0;
    }

    @Override
    public List<?> queryList(Class<?>clazz, Object target, String key){
        List<Object> list = new ArrayList<>();
        String table = clazz.getSimpleName().toLowerCase();
        String value = target.toString();
        if(value.equals("true") || value.equals("false")){
            value = value.replace("true", "1").replace("false", "0");
        }
        String sql = String.format("select * from %s where %s='%s'", table, key, value);
        //执行指令
        try {
            this.executeQuery(sql);
            while (resultSet.next()){
                Object obj = clazz.newInstance();
                for(Field field : clazz.getDeclaredFields()){
                    if("java.sql.Date".equalsIgnoreCase(field.getType().getTypeName())){
                        ReflectUtils.invokeSet(obj, field.getName(), resultSet.getDate(field.getName()));
                    }else {
                        ReflectUtils.invokeSet(obj, field.getName(), resultSet.getObject(field.getName()));
                    }
                }
                list.add(obj);
            }
            return list;
        } catch (InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, statement, connection);
        }
        return list;
    }

    /**
     *
     * 查询数据
     * @param clazz 对象的class
     * @param target 查询对象
     * @param key 查询健
     * @return 查询数据
     */
    @Override
    public Object query(Class<?>clazz, Object target, String key){
        return this.queryList(clazz, target, key).get(0);
    }

    /**
     * 查询数据
     * @param clazz 对象的class
     * @param target 查询对象
     * @return 查询数据
     */
    @Override
    public Object query(Class<?>clazz, Object target){
        String priKey = clazz.getDeclaredFields()[0].getName().toLowerCase();
        return this.query(clazz, target, priKey);
    }

    @Override
    public Object query(Object obj){
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        return query(obj.getClass(), target);
    }

    /**
     * 删除数据
     * @param clazz 对象的class
     * @param target 删除对象
     */
    @Override
    public void delete(Class<?>clazz, Object target, String key){
        //拼接sql指令
        String table = clazz.getSimpleName().toLowerCase();
        String sql = String.format("delete from %s where %s='%s'", table, key, target);
        //执行指令
        try {
            this.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(statement, connection);
        }
    }

    @Override
    public void delete(Object obj) {
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        delete(obj.getClass(), target, priKey);
    }

    /**
     * 判断是否存在对象的数据
     * @param clazz 对象的class
     * @param target 查询对象
     * @return 是否存在
     */
    @Override
    public boolean contain(Class<?>clazz, Object target, String key){
        //拼接sql指令
        String table = clazz.getSimpleName().toLowerCase();
        String sql = String.format("select name from %s where %s='%s'", table, key, target);
        //执行指令
        try {
            this.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, statement, connection);
        }
        return false;
    }

    @Override
    public boolean contain(Object obj){
        String priKey = obj.getClass().getDeclaredFields()[0].getName().toLowerCase();
        String target = String.valueOf(ReflectUtils.invokeGet(obj, priKey));
        return contain(obj.getClass(), target, priKey);
    }

    public void execute(String sql) throws SQLException {
        this.connection();
        statement.execute(sql);
    }

    public void executeQuery(String sql) throws SQLException {
        this.connection();
        resultSet = statement.executeQuery(sql);
    }

    public int executeInsert(String sql) throws SQLException {
        this.connection();
        int id = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            id = resultSet.getInt(1);//返回主键值
        }
        preparedStatement.close();
        return id;
    }

    public void connection() throws SQLException {
        connection = JdbcUtils.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    /**
     * 若未创建表则创建
     * @param clazz 对象class
     */
    @Override
    public void initTable(Class<?> clazz) {
        //建表指令
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuilder builder = new StringBuilder("create table if not exists ").append(tableName).append("(");
        List<String>fields = new ArrayList<String>(){
            {
                for(Field filed : clazz.getDeclaredFields()){
                    add(filed.getName());
                }
            }
        };
        //获取所有成员变量
        for (int i = 0; i < fields.size(); i++) {
            String fieldName = fields.get(i);
            String type = ConvertUtils.javaType2SQLType(clazz.getDeclaredFields()[i].getType().getTypeName());
            builder.append(fieldName).append(" ").append(type);
            if(fields.contains("id")){
                if(fieldName.equals("id")){
                    builder.append(" primary key not null auto_increment");
                }
            }else {
                if(i == 0){
                    builder.append(" primary key not null");
                }
            }
            if(i != clazz.getDeclaredFields().length-1)builder.append(",");
        }
        builder.append(");");
        String sql = builder.toString();
//        System.out.println(sql);
        //执行指令
        try {
            this.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(statement, connection);
        }

    }

}
