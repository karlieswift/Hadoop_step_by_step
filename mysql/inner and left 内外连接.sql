/*
by @karlieswift
--   left 与inner 的区别
-- 事实上 当表格没有null时 结果是一样的,left 只是可以显示空的数据，让null的数据也去匹配
*/
--  多表查询---
SHOW TABLES;
CREATE DATABASE db1;
USE db1;
USE db1;
# 创建部门表
CREATE TABLE dept(
 id INT PRIMARY KEY AUTO_INCREMENT,
 NAME VARCHAR(20)
)
SELECT *FROM dept;

     id  NAME    
------  --------


--  插入三条数据
INSERT INTO dept (NAME) VALUES ('开发部'),('市场部'),('财务部');
SELECT *FROM dept;
+----+-----------+
| id | NAME      |
+----+-----------+
|  1 | 开发部    |
|  2 | 市场部    |
|  3 | 财务部    |
+----+-----------+
     
DROP TABLE emp     
     
# 创建员工表
CREATE TABLE emp (
 id INT PRIMARY KEY AUTO_INCREMENT,
 NAME VARCHAR(10),
 gender CHAR(1), -- 性别
 salary DOUBLE, -- 工资
 join_date DATE, -- 入职日期
 dept_id INT,
 FOREIGN KEY (dept_id) REFERENCES dept(id) -- 外键，关联部门表(部门表的主键)
)

--  插入数据
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('Machel','男',7200,'2013-02-24',1);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('Tom','男',3600,'2010-12-02',2);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('邢倩怡','男',9000,'2008-08-08',2);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('沈青','女',5000,'2015-10-07',3);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('Karlie','女',4500,'2011-03-14',1);
SELECT *FROM emp;


+----+-----------+--------+--------+------------+---------+
| id | NAME      | gender | salary | join_date  | dept_id |
+----+-----------+--------+--------+------------+---------+
|  1 | Machel    | 男     |   7200 | 2013-02-24 |       1 |
|  2 | Tom       | 男     |   3600 | 2010-12-02 |       2 |
|  3 | 邢倩怡    | 男     |   9000 | 2008-08-08 |       2 |
|  4 | 沈青      | 女     |   5000 | 2015-10-07 |       3 |
|  5 | Karlie    | 女     |   4500 | 2011-03-14 |       1 |
+----+-----------+--------+--------+------------+---------+

--  笛卡尔积现象  产生15条数据两个表的组合
SELECT * FROM emp,dept  LIMIT 4;

+----+--------+--------+--------+------------+---------+----+-----------+
| id | NAME   | gender | salary | join_date  | dept_id | id | NAME      |
+----+--------+--------+--------+------------+---------+----+-----------+
|  1 | Machel | 男     |   7200 | 2013-02-24 |       1 |  1 | 开发部    |
|  1 | Machel | 男     |   7200 | 2013-02-24 |       1 |  2 | 市场部    |
|  1 | Machel | 男     |   7200 | 2013-02-24 |       1 |  3 | 财务部    |
|  2 | Tom    | 男     |   3600 | 2010-12-02 |       2 |  1 | 开发部    |
+----+--------+--------+--------+------------+---------+----+-----------+

-- 如何清除笛卡尔积现象的影响

-- 隐式内连接  隐式内连接：看不到 JOIN 关键字，条件使用 WHERE 指定
SELECT * FROM emp,dept WHERE emp.dept_id=dept.id;

+----+-----------+--------+--------+------------+---------+----+-----------+
| id | NAME      | gender | salary | join_date  | dept_id | id | NAME      |
+----+-----------+--------+--------+------------+---------+----+-----------+
|  1 | Machel    | 男     |   7200 | 2013-02-24 |       1 |  1 | 开发部    |
|  2 | Tom       | 男     |   3600 | 2010-12-02 |       2 |  2 | 市场部    |
|  3 | 邢倩怡    | 男     |   9000 | 2008-08-08 |       2 |  2 | 市场部    |
|  4 | 沈青      | 女     |   5000 | 2015-10-07 |       3 |  3 | 财务部    |
|  5 | Karlie    | 女     |   4500 | 2011-03-14 |       1 |  1 | 开发部    |
+----+-----------+--------+--------+------------+---------+----+-----------+



-- 内连接:用左边表的记录去匹配右边表的记录，如果符合条件的则显示。如：从表.外键=主表.主键
-- 显示内连接：使用 INNER JOIN ... ON 语句, 可以省略 INNER

SELECT * FROM emp INNER JOIN dept ON emp.`dept_id` = dept.`id`;

 

-- 确定查询条件，我们查询的是唐僧的信息，员工表.name=karlie
SELECT e.name,d.name FROM emp e INNER JOIN dept d ON d.id=e.dept_id  WHERE e.name="karlie"
+--------+-----------+
| NAME   | NAME      |
+--------+-----------+
| Karlie | 开发部    |
+--------+-----------+

SELECT *FROM emp e INNER JOIN dept d ON d.id=e.dept_id  WHERE e.name="karlie"
+----+--------+--------+--------+------------+---------+----+-----------+
| id | NAME   | gender | salary | join_date  | dept_id | id | NAME      |
+----+--------+--------+--------+------------+---------+----+-----------+
|  5 | Karlie | 女     |   4500 | 2011-03-14 |       1 |  1 | 开发部    |
+----+--------+--------+--------+------------+---------+----+-----------+


-- 左外连接
-- 左外连接：使用 LEFT OUTER JOIN ... ON，OUTER 可以省略
--
INSERT INTO dept (NAME) VALUES("销售部");
SELECT *FROM dept;
--   left 与inner 的区别
 -- 事实上 当表格没有null时 结果是一样的
SELECT e.name,d.name FROM emp e LEFT JOIN dept d ON d.id=e.dept_id  WHERE e.name="karlie"
+--------+-----------+
| NAME   | NAME      |
+--------+-----------+
| Karlie | 开发部    |
+--------+-----------+

-- 以下2条sql语句都会返回20条数据4*5
SELECT * FROM emp INNER JOIN dept  ;
SELECT * FROM dept INNER JOIN emp  ;

-- 用左边表的记录去匹配右边表的记录，如果符合条件的则显示；否则，显示 NULL
-- 可以理解为：在内连接的基础上保证左表的数据全部显示(左表是部门，右表员工)

SELECT * FROM emp e LEFT JOIN dept d ON e.dept_id=d.id;
+----+-----------+--------+--------+------------+---------+------+-----------+
| id | NAME      | gender | salary | join_date  | dept_id | id   | NAME      |
+----+-----------+--------+--------+------------+---------+------+-----------+
|  1 | Machel    | 男     |   7200 | 2013-02-24 |       1 |    1 | 开发部    |
|  5 | Karlie    | 女     |   4500 | 2011-03-14 |       1 |    1 | 开发部    |
|  2 | Tom       | 男     |   3600 | 2010-12-02 |       2 |    2 | 市场部    |
|  3 | 邢倩怡    | 男     |   9000 | 2008-08-08 |       2 |    2 | 市场部    |
|  4 | 沈青      | 女     |   5000 | 2015-10-07 |       3 |    3 | 财务部    |
+----+-----------+--------+--------+------------+---------+------+-----------+

SELECT * FROM dept d LEFT JOIN emp e ON e.dept_id=d.id;
+----+-----------+------+-----------+--------+--------+------------+---------+
| id | NAME      | id   | NAME      | gender | salary | join_date  | dept_id |
+----+-----------+------+-----------+--------+--------+------------+---------+
|  1 | 开发部    |    1 | Machel    | 男     |   7200 | 2013-02-24 |       1 |
|  2 | 市场部    |    2 | Tom       | 男     |   3600 | 2010-12-02 |       2 |
|  2 | 市场部    |    3 | 邢倩怡    | 男     |   9000 | 2008-08-08 |       2 |
|  3 | 财务部    |    4 | 沈青      | 女     |   5000 | 2015-10-07 |       3 |
|  1 | 开发部    |    5 | Karlie    | 女     |   4500 | 2011-03-14 |       1 |
|  4 | 销售部    | NULL | NULL      | NULL   |   NULL | NULL       |    NULL |
+----+-----------+------+-----------+--------+--------+------------+---------+


-- 右外连接
-- 用右边表的记录去匹配左边表的记录，如果符合条件的则显示；否则，显示 NULL
-- 可以理解为：在内连接的基础上保证右表的数据全部显示
 SELECT * FROM emp e RIGHT JOIN dept d ON e.dept_id=d.id;
+------+-----------+--------+--------+------------+---------+----+-----------+
| id   | NAME      | gender | salary | join_date  | dept_id | id | NAME      |
+------+-----------+--------+--------+------------+---------+----+-----------+
|    1 | Machel    | 男     |   7200 | 2013-02-24 |       1 |  1 | 开发部    |
|    2 | Tom       | 男     |   3600 | 2010-12-02 |       2 |  2 | 市场部    |
|    3 | 邢倩怡    | 男     |   9000 | 2008-08-08 |       2 |  2 | 市场部    |
|    4 | 沈青      | 女     |   5000 | 2015-10-07 |       3 |  3 | 财务部    |
|    5 | Karlie    | 女     |   4500 | 2011-03-14 |       1 |  1 | 开发部    |
| NULL | NULL      | NULL   |   NULL | NULL       |    NULL |  4 | 销售部    |
+------+-----------+--------+--------+------------+---------+----+-----------+
