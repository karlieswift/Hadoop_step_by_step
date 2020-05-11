USE mydb;
-- by@ kerlieswift
CREATE TABLE `t_dept` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `deptName` VARCHAR(30) DEFAULT NULL,
 `address` VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



 
CREATE TABLE `t_emp` (
 `id` INT(11) NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(20) DEFAULT NULL,
`age` INT(3) DEFAULT NULL,
 `deptId` INT(11) DEFAULT NULL,
  empno INT  NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_dept_id` (`deptId`)
  #CONSTRAINT `fk_dept_id` FOREIGN KEY (`deptId`) REFERENCES `t_dept` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 插入数据
INSERT INTO t_dept(deptName,address) VALUES('华山','华山');
INSERT INTO t_dept(deptName,address) VALUES('丐帮','洛阳');
INSERT INTO t_dept(deptName,address) VALUES('峨眉','峨眉山');
INSERT INTO t_dept(deptName,address) VALUES('武当','武当山');
INSERT INTO t_dept(deptName,address) VALUES('明教','光明顶');
INSERT INTO t_dept(deptName,address) VALUES('少林','少林寺');
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('风清扬',90,1,100001);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('岳不群',50,1,100002);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('令狐冲',24,1,100003);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('洪七公',70,2,100004);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('乔峰',35,2,100005);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('灭绝师太',70,3,100006);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('周芷若',20,3,100007);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('张三丰',100,4,100008);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('张无忌',25,5,100009);
INSERT INTO t_emp(NAME,age,deptId,empno) VALUES('韦小宝',18,NULL,100010);



--	添加CEO字段
ALTER TABLE `t_dept` 
ADD  CEO  INT(11)  ;
UPDATE t_dept SET CEO=2 WHERE id=1;
UPDATE t_dept SET CEO=4 WHERE id=2;
UPDATE t_dept SET CEO=6 WHERE id=3;
UPDATE t_dept SET CEO=8 WHERE id=4;
UPDATE t_dept SET CEO=9 WHERE id=5;






SELECT * FROM t_dept INNER JOIN t_emp ON t_emp.`deptId`=t_dept.`id`;

SELECT * FROM t_dept LEFT JOIN t_emp ON t_emp.`deptId`=t_dept.`id`;





EXPLAIN  SELECT t_dept.`address`  FROM t_dept INNER JOIN t_emp ON t_emp.`deptId`=t_dept.`id`;





EXPLAIN SELECT *FROM t_dept WHERE t_dept.`id`=1;






    
-- 查看数据表
SELECT *FROM t_dept;
SELECT *FROM t_emp;

t_dept
+----+----------+-----------+------+
| id | deptName | address   | CEO  |
+----+----------+-----------+------+
|  1 | 华山     | 华山      |    2 |
|  2 | 丐帮     | 洛阳      |    4 |
|  3 | 峨眉     | 峨眉山    |    6 |
|  4 | 武当     | 武当山    |    8 |
|  5 | 明教     | 光明顶    |    9 |
|  6 | 少林     | 少林寺    | NULL |
+----+----------+-----------+------+
t_emp
+----+--------------+------+--------+--------+
| id | NAME         | age  | deptId | empno  |
+----+--------------+------+--------+--------+
|  1 | 风清扬       |   90 |      1 | 100001 |
|  2 | 岳不群       |   50 |      1 | 100002 |
|  3 | 令狐冲       |   24 |      1 | 100003 |
|  4 | 洪七公       |   70 |      2 | 100004 |
|  5 | 乔峰         |   35 |      2 | 100005 |
|  6 | 灭绝师太     |   70 |      3 | 100006 |
|  7 | 周芷若       |   20 |      3 | 100007 |
|  8 | 张三丰       |  100 |      4 | 100008 |
|  9 | 张无忌       |   25 |      5 | 100009 |
| 10 | 韦小宝       |   18 |   NULL | 100010 |
+----+--------------+------+--------+--------+


--	所有有门派人员的信息（要求显示门派名称）
SELECT e.`name`,d.`deptName` FROM t_emp e INNER JOIN t_dept d ON e.`deptId`=d.`id`;
+--------------+----------+
| NAME         | deptName |
+--------------+----------+
| 风清扬       | 华山     |
| 岳不群       | 华山     |
| 令狐冲       | 华山     |
| 洪七公       | 丐帮     |
| 乔峰         | 丐帮     |
| 灭绝师太     | 峨眉     |
| 周芷若       | 峨眉     |
| 张三丰       | 武当     |
| 张无忌       | 明教     |
+--------------+----------+

--	列出所有人员及其门派信息
SELECT e.`name`,d.`deptName` FROM t_emp e LEFT JOIN t_dept d ON e.`deptId`=d.`id`;
+--------------+----------+
| NAME         | deptName |
+--------------+----------+
| 风清扬       | 华山     |
| 岳不群       | 华山     |
| 令狐冲       | 华山     |
| 洪七公       | 丐帮     |
| 乔峰         | 丐帮     |
| 灭绝师太     | 峨眉     |
| 周芷若       | 峨眉     |
| 张三丰       | 武当     |
| 张无忌       | 明教     |
| 韦小宝       | NULL     |
+--------------+----------+

--	列出所有门派
SELECT * FROM t_dept;

--	所有无门派人士
SELECT * FROM t_emp WHERE deptId IS NULL;
+----+-----------+------+--------+--------+
| id | NAME      | age  | deptId | empno  |
+----+-----------+------+--------+--------+
| 10 | 韦小宝    |   18 |   NULL | 100010 |
+----+-----------+------+--------+--------+

--	所有无人门派
SELECT d.* FROM  t_dept d LEFT JOIN t_emp e ON d.`id`=e.`deptId` WHERE e.`deptId` IS NULL;
+----+----------+-----------+------+
| id | deptName | address   | CEO  |
+----+----------+-----------+------+
|  6 | 少林     | 少林寺    | NULL |
+----+----------+-----------+------+

--	所有人员和门派的对应关系
SELECT * FROM t_emp e LEFT JOIN t_dept d ON e.`deptId`=d.`id`
UNION
SELECT * FROM t_emp e RIGHT JOIN t_dept d ON e.`deptId`=d.`id`;

+------+--------------+------+--------+--------+------+----------+-----------+------+
| id   | NAME         | age  | deptId | empno  | id   | deptName | address   | CEO  |
+------+--------------+------+--------+--------+------+----------+-----------+------+
|    1 | 风清扬       |   90 |      1 | 100001 |    1 | 华山     | 华山      |    2 |
|    2 | 岳不群       |   50 |      1 | 100002 |    1 | 华山     | 华山      |    2 |
|    3 | 令狐冲       |   24 |      1 | 100003 |    1 | 华山     | 华山      |    2 |
|    4 | 洪七公       |   70 |      2 | 100004 |    2 | 丐帮     | 洛阳      |    4 |
|    5 | 乔峰         |   35 |      2 | 100005 |    2 | 丐帮     | 洛阳      |    4 |
|    6 | 灭绝师太     |   70 |      3 | 100006 |    3 | 峨眉     | 峨眉山    |    6 |
|    7 | 周芷若       |   20 |      3 | 100007 |    3 | 峨眉     | 峨眉山    |    6 |
|    8 | 张三丰       |  100 |      4 | 100008 |    4 | 武当     | 武当山    |    8 |
|    9 | 张无忌       |   25 |      5 | 100009 |    5 | 明教     | 光明顶    |    9 |
|   10 | 韦小宝       |   18 |   NULL | 100010 | NULL | NULL     | NULL      | NULL |
| NULL | NULL         | NULL |   NULL |   NULL |    6 | 少林     | 少林寺    | NULL |
+------+--------------+------+--------+--------+------+----------+-----------+------+
--	所有没有入门派的人员和没人入的门派
SELECT * FROM t_emp e  LEFT JOIN t_dept d ON e.`deptId`=d.`id` WHERE e.deptId IS NULL
UNION
SELECT * FROM  t_dept d LEFT JOIN t_emp e ON d.`id`=e.`deptId` WHERE e.`deptId` IS NULL;
+----+-----------+-----------+--------+--------+------+----------+---------+------+
| id | NAME      | age       | deptId | empno  | id   | deptName | address | CEO  |
+----+-----------+-----------+--------+--------+------+----------+---------+------+
| 10 | 韦小宝    | 18        |   NULL | 100010 | NULL | NULL     | NULL    | NULL |
|  6 | 少林      | 少林寺    |   NULL |   NULL | NULL | NULL     | NULL    | NULL |
+----+-----------+-----------+--------+--------+------+----------+---------+------+



-- 查看数据表
SELECT *FROM t_dept;
SELECT *FROM t_emp;


--	求各个门派对应的掌门人名称
SELECT d.deptName,e.name FROM t_dept d LEFT JOIN t_emp e ON d.ceo=e.id
+----------+--------------+
| deptName | NAME         |
+----------+--------------+
| 华山     | 岳不群       |
| 丐帮     | 洪七公       |
| 峨眉     | 灭绝师太     |
| 武当     | 张三丰       |
| 明教     | 张无忌       |
| 少林     | NULL         |
+----------+--------------+

--	求所有当上掌门人的平均年龄
SELECT AVG(e.age) FROM t_dept d LEFT JOIN t_emp e ON d.ceo=e.id

+------------+
| AVG(e.age) |
+------------+
|    63.0000 |
+------------+

--	求所有人物对应的掌门名称  的四种方法，重点第三种
-- 方法1
SELECT ed.name '人物',c.name '掌门' 
FROM (SELECT e.name,d.ceo FROM t_emp e LEFT JOIN t_dept d ON e.deptid=d.id) ed
LEFT JOIN t_emp c ON ed.ceo= c.id;
+--------------+--------------+
| 人物         | 掌门         |
+--------------+--------------+
| 风清扬       | 岳不群       |
| 岳不群       | 岳不群       |
| 令狐冲       | 岳不群       |
| 洪七公       | 洪七公       |
| 乔峰         | 洪七公       |
| 灭绝师太     | 灭绝师太     |
| 周芷若       | 灭绝师太     |
| 张三丰       | 张三丰       |
| 张无忌       | 张无忌       |
| 韦小宝       | NULL         |
+--------------+--------------+

-- 方法2
SELECT e.name '人物',tmp.name '掌门'
FROM t_emp e LEFT JOIN (SELECT d.id did,e.name FROM t_dept d LEFT JOIN t_emp e ON d.ceo=e.id)tmp 
ON e.deptId=tmp.did;
+--------------+--------------+
| 人物         | 掌门         |
+--------------+--------------+
| 风清扬       | 岳不群       |
| 岳不群       | 岳不群       |
| 令狐冲       | 岳不群       |
| 洪七公       | 洪七公       |
| 乔峰         | 洪七公       |
| 灭绝师太     | 灭绝师太     |
| 周芷若       | 灭绝师太     |
| 张三丰       | 张三丰       |
| 张无忌       | 张无忌       |
| 韦小宝       | NULL         |
+--------------+--------------+

-- 方法3
SELECT e1.name '人物',e2.name '掌门' 
FROM t_emp e1 
LEFT JOIN t_dept d ON e1.deptid = d.id
LEFT JOIN t_emp e2 ON d.ceo = e2.id ;
+--------------+--------------+
| 人物         | 掌门         |
+--------------+--------------+
| 风清扬       | 岳不群       |
| 岳不群       | 岳不群       |
| 令狐冲       | 岳不群       |
| 洪七公       | 洪七公       |
| 乔峰         | 洪七公       |
| 灭绝师太     | 灭绝师太     |
| 周芷若       | 灭绝师太     |
| 张三丰       | 张三丰       |
| 张无忌       | 张无忌       |
| 韦小宝       | NULL         |
+--------------+--------------+

-- 方法4
SELECT e2.name '人物',
(SELECT e1.name FROM t_emp e1 WHERE e1.id= d.ceo) '掌门'
FROM t_emp e2 LEFT JOIN t_dept d ON e2.deptid=d.id;

+--------------+--------------+
| 人物         | 掌门         |
+--------------+--------------+
| 风清扬       | 岳不群       |
| 岳不群       | 岳不群       |
| 令狐冲       | 岳不群       |
| 洪七公       | 洪七公       |
| 乔峰         | 洪七公       |
| 灭绝师太     | 灭绝师太     |
| 周芷若       | 灭绝师太     |
| 张三丰       | 张三丰       |
| 张无忌       | 张无忌       |
| 韦小宝       | NULL         |
+--------------+--------------+



