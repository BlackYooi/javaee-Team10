package com.kindblack.team10.Utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;


/**
* @author black
* @date 2020/1/5 - 18:32
*/
@Component
public class OrgodicObject {

    //拼接查询的sql语句（多条件模糊查询）
    //参数 表名 需要遍历的对象
    public String getSql(String tableName,Object object){
        StringBuilder sql = new StringBuilder("select * from " + tableName + " where 1=1");
        String[] strings = this.OrgodicEmployee(object);
        //sql.append(" and name like '%" + findName + "%'");
        for(int i = 0;i < strings.length; i += 2){
            if(strings[i+1] != null && !strings[i+1].trim().isEmpty()) {
                sql.append(" and " + strings[i] + " like '%" + strings[i+1] + "%'");
            }
        }
        return sql.toString();
    }

    public String[] OrgodicEmployee(Object object){
        // 获取实体类的所有属性，返回Field数组
        Field[] field = object.getClass().getDeclaredFields();
        try {
            String[] returnStringArray = new String[2*field.length];
            for (int j = 0; j < field.length; j++) {// 遍历所有属性
                String name = field[j].getName();// 获取属性的名字
                returnStringArray[2*j] = name;
                name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString();// 获取属性的类型
                // 如果type是类类型，则前面包含"class "，后面跟类名
                if (type.equals("class java.lang.String")) {
                    Method m = object.getClass().getMethod("get" + name);
                    String value = (String) m.invoke(object);// 调用getter方法获取属性值
                    if (value == null) {
                        m = object.getClass().getMethod("set"+name,String.class);
                        m.invoke(object, "");
                    }
                    returnStringArray[2*j + 1] = value;
                }
                if (type.equals("class java.lang.Integer")) {
                    Method m = object.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(object);
                    if (value == null) {
                        m = object.getClass().getMethod("set"+name,Integer.class);
                        m.invoke(object, 0);
                        returnStringArray[2*j + 1] = null;
                    }
                    else {
                        returnStringArray[2*j + 1] = value.toString();
                    }
                }
            }
            return returnStringArray;

        } catch (NoSuchMethodException e) {

            e.printStackTrace();

        } catch (SecurityException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            e.printStackTrace();

        } catch (InvocationTargetException e) {

            e.printStackTrace();

        }
        return null;

    }

    /*public void OrgodicOb(Object object){

        // object换成需要遍历的实体
        // 获取实体类的所有属性，返回Field数组
        Field[] field = object.getClass().getDeclaredFields();

        try {

            // 遍历所有属性

            for (int j = 0; j < field.length; j++) {

                // 获取属性的名字

                String name = field[j].getName();

                // 将属性的首字符大写，方便构造get，set方法

                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                // 获取属性的类型

                String type = field[j].getGenericType().toString();

                // 如果type是类类型，则前面包含"class "，后面跟类名

                if (type.equals("class java.lang.String")) {

                    Method m = object.getClass().getMethod("get" + name);

                    // 调用getter方法获取属性值

                    String value = (String) m.invoke(object);

                    if (value == null) {

                        m = object.getClass().getMethod("set"+name,String.class);

                        m.invoke(object, "");

                    }

                }

                if (type.equals("class java.lang.Integer")) {

                    Method m = object.getClass().getMethod("get" + name);

                    Integer value = (Integer) m.invoke(object);

                    if (value == null) {

                        m = object.getClass().getMethod("set"+name,Integer.class);

                        m.invoke(object, 0);

                    }

                }

                if (type.equals("class java.lang.Boolean")) {

                    Method m = object.getClass().getMethod("get" + name);

                    Boolean value = (Boolean) m.invoke(object);

                    if (value == null) {

                        m = object.getClass().getMethod("set"+name,Boolean.class);

                        m.invoke(object, false);

                    }

                }

                if (type.equals("class java.util.Date")) {

                    Method m = object.getClass().getMethod("get" + name);

                    Date value = (Date) m.invoke(object);

                    if (value == null) {

                        m = object.getClass().getMethod("set"+name,Date.class);

                        m.invoke(object, new Date());

                    }

                }// 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断

            }

        } catch (NoSuchMethodException e) {

            e.printStackTrace();

        } catch (SecurityException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            e.printStackTrace();

        } catch (InvocationTargetException e) {

            e.printStackTrace();

        }

    }*/

}
