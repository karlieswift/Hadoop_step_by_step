/* 
by@ kerlieswift
单表使用索引常见的索引失效
emp数据表有50w数据
初始化目前只有id为primarykey
本案例是测试加入其它索引后  select查询的效率变高

emp的格式创建

CREATE TABLE IF NOT EXISTS `emp` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `empno` INT NOT NULL ,
 `name` VARCHAR(20) DEFAULT NULL,
 `age` INT(3) DEFAULT NULL,
 `deptId` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


mysql> select count(*) from emp;
+----------+
| count(*) |
+----------+
|   500000 |
+----------+

*/
USE mydb;
SELECT *FROM emp LIMIT 10;
+----+--------+--------+------+--------+
| id | empno  | NAME   | age  | deptId |
+----+--------+--------+------+--------+
|  1 | 100001 | iHKDMZ |   44 |   5673 |
|  2 | 100002 | LWANnY |   32 |   7397 |
|  3 | 100003 | pntgzg |   32 |   1272 |
|  4 | 100004 | rsKwfm |   47 |   4937 |
|  5 | 100005 | XoAKZT |   38 |   3426 |
|  6 | 100006 | BJBMiJ |   42 |    223 |
|  7 | 100007 | tSfZJf |   45 |   2740 |
|  8 | 100008 | kjoUEo |   42 |   1382 |
|  9 | 100009 | VklASO |   36 |   2783 |
| 10 | 100010 | vofLpq |   45 |   6618 |
+----+--------+--------+------+--------+


--  目前查看按主键id查询 发现ROWS =1，查询一行，效率高
mysql> EXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE emp.id=111;
+----+-------------+-------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
| id | select_type | TABLE | PARTITIONS | TYPE  | possible_keys | KEY     | key_len | ref   | ROWS | filtered | Extra |
+----+-------------+-------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
|  1 | SIMPLE      | emp   | NULL       | const | PRIMARY       | PRIMARY | 4       | const |    1 |   100.00 | NULL  |
+----+-------------+-------+------------+-------+---------------+---------+---------+-------+------+----------+-------+

--  目前查看按普通索引age查询 发现ROWS =499086，效率低
EXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE emp.age=30  
+----+-------------+-------+------------+------+---------------+------+---------+------+--------+----------+-------------+
| id | select_type | TABLE | PARTITIONS | TYPE | possible_keys | KEY  | key_len | ref  | ROWS   | filtered | Extra       |
+----+-------------+-------+------------+------+---------------+------+---------+------+--------+----------+-------------+
|  1 | SIMPLE      | emp   | NULL       | ALL  | NULL          | NULL | NULL    | NULL | 499086 |    10.00 | USING WHERE |
+----+-------------+-------+------------+------+---------------+------+---------+------+--------+----------+-------------+



-- 加入(age,deptid,NAME)索引，再次查询
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-------+
| id | select_type | TABLE | PARTITIONS | TYPE | possible_keys         | KEY                   | key_len | ref   | ROWS  | filtered | Extra |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-------+
|  1 | SIMPLE      | emp   | NULL       | ref  | index_age_deptid_name | index_age_deptid_name | 5       | const | 47972 |   100.00 | NULL  |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-------+






-- 
SHOW INDEX FROM emp  
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| TABLE | Non_unique | Key_name | Seq_in_index | Column_name | COLLATION | Cardinality | Sub_part | Packed | NULL | Index_type | COMMENT | Index_comment |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| emp   |          0 | PRIMARY  |            1 | id          | A         |      499086 |     NULL | NULL   |      | BTREE      |         |               |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+

-- 加入(age,deptid,NAME)索引
CREATE INDEX index_age_deptid_name ON emp(age,deptid,NAME);
SHOW INDEX FROM emp  ;
+-------+------------+-----------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| TABLE | Non_unique | Key_name              | Seq_in_index | Column_name | COLLATION | Cardinality | Sub_part | Packed | NULL | Index_type | COMMENT | Index_comment |
+-------+------------+-----------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| emp   |          0 | PRIMARY               |            1 | id          | A         |      499086 |     NULL | NULL   |      | BTREE      |         |               |
| emp   |          1 | index_age_deptid_name |            1 | age         | A         |          20 |     NULL | NULL   | YES  | BTREE      |         |               |
| emp   |          1 | index_age_deptid_name |            2 | deptId      | A         |      188910 |     NULL | NULL   | YES  | BTREE      |         |               |
| emp   |          1 | index_age_deptid_name |            3 | NAME        | A         |      499086 |     NULL | NULL   | YES  | BTREE      |         |               |
+-------+------------+-----------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
-- 加入(age,deptid,NAME)索引，再次查询(只用一个index_age) rows变小，效率变高
EXPLAIN SELECT SQL_NO_CACHE * FROM emp WHERE emp.age=30  
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-------+
| id | select_type | TABLE | PARTITIONS | TYPE | possible_keys         | KEY                   | key_len | ref   | ROWS  | filtered | Extra |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-------+
|  1 | SIMPLE      | emp   | NULL       | ref  | index_age_deptid_name | index_age_deptid_name | 5       | const | 47972 |   100.00 | NULL  |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-------+

-- 加入(age,deptid,NAME)索引，再次查询(只用一个index_age,index_deptid) rows变小，效率变高
EXPLAIN SELECT  * FROM emp WHERE emp.age=30 AND deptid=4;
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------------+------+----------+-------+
| id | select_type | TABLE | PARTITIONS | TYPE | possible_keys         | KEY                   | key_len | ref         | ROWS | filtered | Extra |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------------+------+----------+-------+
|  1 | SIMPLE      | emp   | NULL       | ref  | index_age_deptid_name | index_age_deptid_name | 10      | const,const |    1 |   100.00 | NULL  |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------------+------+----------+-------+



-- 加入(age,deptid,NAME)索引，再次查询(只用index_age,index_name) 此时索引效率只有age起作用，name其实是被deptid截断
EXPLAIN SELECT  * FROM emp WHERE emp.age=30 AND NAME="aaaa";
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-----------------------+
| id | select_type | TABLE | PARTITIONS | TYPE | possible_keys         | KEY                   | key_len | ref   | ROWS  | filtered | Extra                 |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-----------------------+
|  1 | SIMPLE      | emp   | NULL       | ref  | index_age_deptid_name | index_age_deptid_name | 5       | const | 47972 |    10.00 | USING INDEX CONDITION |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------+-------+----------+-----------------------+


-- 加入(age,deptid,NAME)索引，再次查询(只用index_age,index_deptid，name 注意只要三个全写上，顺序没要求，依然可以提高效率) 
EXPLAIN SELECT  * FROM emp WHERE emp.age=30 AND NAME="aaaa" AND deptId=111;

+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------------------+------+----------+-------+
| id | select_type | TABLE | PARTITIONS | TYPE | possible_keys         | KEY                   | key_len | ref               | ROWS | filtered | Extra |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------------------+------+----------+-------+
|  1 | SIMPLE      | emp   | NULL       | ref  | index_age_deptid_name | index_age_deptid_name | 73      | const,const,const |    1 |   100.00 | NULL  |
+----+-------------+-------+------------+------+-----------------------+-----------------------+---------+-------------------+------+----------+-------+

--   视图
-- 创建视图
CREATE VIEW temp_table AS  SELECT  e.id,e.name FROM emp e LIMIT 11;
-- 删除视图(虽然视图在磁盘不存在，但还是逻辑存在)
DROP VIEW temp_table;
-- 也可以查询desc
DESC temp_table;
SELECT *FROM temp_table;
SELECT *FROM temp_table WHERE id>460000
