package com.it;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.entity.User;
import com.it.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Mpdemo1ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User user;

    //查询user表中所有的数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加操作
    @Test
    public void addUser() {
        user.setName("werr");
        user.setAge(25);
        user.setEmail("55317332@qq.com");

        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }

    //修改操作
    @Test
    public void updateUser() {
        user.setId(1298452002465820673L);
        user.setAge(22);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //测试乐观锁修改操作
    @Test
    public void OptimisticLocker() {
        //根据id查询数据
        User user1 = userMapper.selectById(1298511797008904193l);

        // 进行修改
        user1.setAge(24);
        userMapper.updateById(user1);
    }

    //多个id批量查询
    @Test
    public void testSelectDemo1() {
        //第一种方法，创建arraylist集合，添加id
        /*ArrayList<Long> list = new ArrayList<Long>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        List<User> users = userMapper.selectBatchIds(list);*/

        //第二种方法，直接在selectBatchIds里传集合参数Arrays.asList(1L,2L,3L)
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L,2L,3L));
        System.out.println(users);
    }

    //分页查询
    @Test
    public void testPage(){
        //1. 创建page对象
        //传入两个参数 分别是current：当前页 和 size：每页显示记录数
        Page<User> page = new Page<>(2,3);
        /**
         * 调用mp中分页查询的方法
         * 调用mp分页查询过程中，底层封装
         * 把分页所有数据封装到page对象里面
         */
        userMapper.selectPage(page,null);
        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//当前总页数

        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }
    //删除操作(物理删除，配置逻辑删除插件后变成逻辑删除)
    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(1298511797008904193L);
        System.out.println(i);
    }
    //批量删除
    @Test
    public void testDeleteByIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(4L, 5L));
        System.out.println(i);
    }
    //mp实现复杂查询操作
    @Test
    public void testSelectQuery(){
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //1.ge,gt,le,lt(大于等于，大于，小于等于，小于)
        //查询age>=20的记录，第一个参数column是字段名，第二个是数值

        /*wrapper.ge("age",20);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //2.eq,ne(等于，不等于 )

        /*wrapper.eq("name","aaa");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //3.between(查询某范围之间的记录)
        //查询年龄20-30范围的

       /* wrapper.between("age",20,30);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //4.like(模糊查询)

        /*wrapper.like("name", "o");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/


        //5.orderByDesc(排序：降序)  orderByAsc(排序：升序)

        /*wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //6.last(拼接sql语句)

        /*wrapper.last("limit 3");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        //7.查询指定的列

        /*wrapper.select("id","name");
        wrapper.last("limit 2");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/

        /**
         * 其他
         * isNotNull，isNull(某字段不为空，某字段为空)
         *notBetween(查询某范围之外的记录)
         * allEq(多个字段都等于，eq是某一个字段等于)
         *notLike(like的取反)
         *
         */
        wrapper.likeLeft("name","o");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
}