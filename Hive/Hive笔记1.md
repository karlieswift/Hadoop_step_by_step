 

 

## Sort

### 1- order by

**emp表：通过员工的部门编号升序排列，默认为升序（asc）,降序(desc)**

**0: jdbc:hive2://hadoop2:10000> select \*from emp order by deptno asc;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image002.jpg)

### 2- set reducetask

**手动设置reduces的个数默认为-1：**

**0: jdbc:hive2://hadoop2:10000> set mapreduce.job.reduces=3;**

**No rows affected (0.141 seconds)**

**0: jdbc:hive2://hadoop2:10000> set mapreduce.job.reduces;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image004.jpg)

### 3- sort by

**order by是全局排序，需要对整个table进行排序时，当有多个reducetask任务时，可以通过sort by只在各个reducetask内进行排序**

**0: jdbc:hive2://hadoop2:10000> select \*from emp sort by deptno;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image006.jpg)

 

### 4- distribute by

**分区排序，是将数据进行按照reducetask的个数分成部分文件，对各个部分文件进行文件内部排序。与sort by结合使用。**

**0: jdbc:hive2://hadoop2:10000> insert overwrite local directory '/opt/data/sort' row format delimited fields terminated by '\t' select \*from emp**

**. . . . . . . . . . . . . . .> distribute by deptno sort by sal desc;**

 

 

**查看/opt/data/sort目录下的文件**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image008.jpg)

 

### 5- cluster by

**当distribute by 与sort by 的字段一样时，可以直接用cluster by。具有一定的局限性。**

**0: jdbc:hive2://hadoop2:10000> select \*from emp cluster by deptno;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image010.jpg)

 

## Partition

### 1-创建分区表

 

**0: jdbc:hive2://hadoop2:10000> create table dept_partition(**

**. . . . . . . . . . . . . . .> dept_no int ,dept_name string,loc string)**

**. . . . . . . . . . . . . . .> partitioned by (day string)**

**. . . . . . . . . . . . . . .> row format delimited fields terminated by '\t';**

 

**分区表：本质就是添加列，对数据进行过滤。**

 

**查看分区表的具体信息。**

**0: jdbc:hive2://hadoop2:10000> desc dept_partition;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image012.jpg)

**可以看到table是按day<string>进行分区**

### 2-加载数据

**通过加载数据对table进行插入数据，按day<string>s生成三个partitions.**

**load data local inpath '/opt/data/logs/dept_20200603.log' into table dept_partition partition(day='20200603');**

**load data local inpath '/opt/data/logs/dept_20200604.log' into table dept_partition partition(day='20200604');**

**load data local inpath '/opt/data/logs/dept_20200605.log' into table dept_partition partition(day='20200605');**

 

**查看table dept_partition的分区。**

**0: jdbc:hive2://hadoop2:10000> show partitions dept_partition;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image014.jpg)

 

**table的数据**

**0: jdbc:hive2://hadoop2:10000> select \*from dept_partition;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image016.jpg)

 

### 3-按区查找数据

**通过day=20200603的条件进行查询（本质就是又加了一个列，对数据进行过滤）**

**0: jdbc:hive2://hadoop2:10000> select \*from dept_partition where day=20200603;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image018.jpg)

### 4-在原表的基础上添加分区

**1.添加多个分区（各个分区之间不用”,”）：**

**0: jdbc:hive2://hadoop2:10000> alter table dept_partition add partition(day='20200606') partition(day='20200607');**

 

**2.查看目前的分区情况：**

**0: jdbc:hive2://hadoop2:10000> show partitions dept_partition;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image020.jpg)

 

### 5-删除分区

**删除分区（注意：各个区之间需要有”,”）**

**alter table dept_partition drop partition(day='20200605'), partition(day='20200607');**

**查看分区情况：**

**0: jdbc:hive2://hadoop2:10000> show partitions dept_partition;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image022.jpg)

 

**查看表的具体信息：**

**0: jdbc:hive2://hadoop2:10000> desc formatted dept_partition;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image024.jpg)

 

 

### 6-多级分区

**1.多级分区：只需要在partitioned by（）添加多个字段。**

**0: jdbc:hive2://hadoop2:10000> create table dept_partition1(**

**. . . . . . . . . . . . . . .> dept_no int ,dept_name string,loc string)**

**. . . . . . . . . . . . . . .> partitioned by (day string,hour string)**

**. . . . . . . . . . . . . . .> row format delimited fields terminated by '\t';**

 

 

**load data local inpath '/opt/data/logs/dept_20200603.log' into table dept_partition1 partition(day='20200603',hour=1);**

**load data local inpath '/opt/data/logs/dept_20200604.log' into table dept_partition1 partition(day='20200603',hour=2);**

**load data local inpath '/opt/data/logs/dept_20200604.log' into table dept_partition1 partition(day='20200604',hour=1);**

**load data local inpath '/opt/data/logs/dept_20200605.log' into table dept_partition1 partition(day='20200604',hour=2);**

 

**2.查看web端的元数据默认目录**

**/user/hive/warehouse/mydb.db/dept_partition1/day=20200603/其中day=20200603是一级目录（分区），在他day=20200603下面有两个二级目录（二级分区）。**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image026.jpg)

**3.查看数据：**

 

**0: jdbc:hive2://hadoop2:10000> select \*from dept_partition1;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image028.jpg)

 

 

 

 

### 7-分区表与数据的三种关联方式

**1.先在HDFS上建立数据分区,并上传到HDFS数据,再通过msck repair进行修复**

 

**0: jdbc:hive2://hadoop2:10000>** 

**dfs -mkdir -p /user/hive/warehouse/mydb.db/dept_partition1/day=20200603/hour=3;**

 

**0:jdbc:hive2://hadoop2:10000>dfs -put /opt/data/logs/dept_20200603.log /user/hive/warehouse/mydb.db/dept_partition1/day=20200603/hour=3;**

**0:jdbc:hive2://hadoop2:10000>msck repair table dept_partition2**

**0: jdbc:hive2://hadoop2:10000> select \*from dept_partition1;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image030.jpg)

 

 

 

**2. 先在HDFS上建立数据分区,并上传到HDFS数据,再通过alter add进行添加刚才建立的分区。**

**dfs -mkdir -p /user/hive/warehouse/mydb.db/dept_partition1/day=20200603/hour=4;**

 

**0:jdbc:hive2://hadoop2:10000>dfs -put /opt/data/logs/dept_20200604.log /user/hive/warehouse/mydb.db/dept_partition1/day=20200603/hour=4;**

 

**0: jdbc:hive2://hadoop2:10000> alter table dept_partition1 add partition(day='20200603',hour=4);**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image032.jpg)

 

 

 

 

 

**3．先在HDFS上建立数据分区，通过加载时指定分区。**

**0: jdbc:hive2://hadoop2:10000> dfs -mkdir -p /user/hive/warehouse/mydb.db/dept_partition1/day=20200603/hour=5;**

 

**0: jdbc:hive2://hadoop2:10000> load data local inpath '/opt/data/logs/dept_20200605.log' into table dept_partition1 partition(day='20200603',hour=5);**

 

### 8-动态分区

**1.创建表**

**0: jdbc:hive2://hadoop2:10000> create table dept_dy_partition(**

**. . . . . . . . . . . . . . .> dept_no int ,dept_name string)**

**. . . . . . . . . . . . . . .> partitioned by (loc string)**

**. . . . . . . . . . . . . . .> row format delimited fields terminated by '\t';**

 

**0: jdbc:hive2://hadoop2:10000> set hive.exec.dynamic.partition.mode;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image034.jpg)

**2.设置动态分区非严格模式：**

**0: jdbc:hive2://hadoop2:10000> set hive.exec.dynamic.partition.mode=nostrict;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image036.jpg)

 

 

**3.插入一条数据，按loc=10000分区。**

**0: jdbc:hive2://hadoop2:10000> insert into table dept_dy_partition values(11,'ALI','10000');**

**0: jdbc:hive2://hadoop2:10000> show partitions dept_dy_partition;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image038.jpg)

 

 

**4.也可以从其他表（列的形式相同）插入数据**

**0: jdbc:hive2://hadoop2:10000> insert into table dept_dy_partition partition(loc) select \*from dept;**

**5.查看分区：**

**0: jdbc:hive2://hadoop2:10000> select \*from dept_dy_partition;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image040.jpg)

 

 

 

 

## Bucket

**分桶的注意事项：当指定的分桶个数大于设置的reduce个数就会出错，会出现空指针异常。**

**需要将reduce的个数设置为大于bucket的个数，即reduce>=bucket**

**当然也可以把reduce设置为-1.**

**set mapreduce.job.reduces=-1；**     

 

### 1-创建分桶表

**1.按id进行分到4个桶。**

**0: jdbc:hive2://hadoop2:10000> create table stu_bucket(**

**. . . . . . . . . . . . . . .> id int,name string)**

**. . . . . . . . . . . . . . .> clustered by(id)  into 4 buckets**

**. . . . . . . . . . . . . . .> row format delimited fields terminated by '\t';**

 

 

### 2-加载数据到table的2种方法：

**1．直接加载文件的数据**

**0: jdbc:hive2://hadoop2:10000> load data local inpath '/opt/data/bucket.txt' into table stu_bucket;**

**0: jdbc:hive2://hadoop2:10000> truncate table stu_bucket;**

**2．从其他表直接插入**

**0: jdbc:hive2://hadoop2:10000> insert into table stu_bucket select \*from student;**

**0: jdbc:hive2://hadoop2:10000> select \*from stu_bucket;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image042.jpg)

 

### 3-抽样调查

**0: jdbc:hive2://hadoop2:10000> select \*from stu_bucket tablesample(bucket 2 out of 4 on id);**

 

**0: jdbc:hive2://hadoop2:10000> select \*from stu_bucket tablesample(0.5 percent);**

 

 

 

## 函数

### 1- nvl

**对空的字段进行赋值**

**0: jdbc:hive2://hadoop2:10000> select comm,nvl(comm,0) as new_comm from emp;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image044.jpg)

 

 

### 2-case when then else end

**1.创建表：**

**0: jdbc:hive2://hadoop2:10000> create table emp_sex(**

**. . . . . . . . . . . . . . .> name string,dept_id string,sex string)**

**. . . . . . . . . . . . . . .> row format delimited fields terminated by '\t';**

**0: jdbc:hive2://hadoop2:10000> load data local inpath '/opt/data/emp_sex.txt' into table emp_sex;**

**2.查看表：**

**0: jdbc:hive2://hadoop2:10000> select \*from emp_sex;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image046.jpg)

 

 

**3.练习语法：按部门分组统计各个部门的男女个数**

**0: jdbc:hive2://hadoop2:10000> select dept_id,**

**. . . . . . . . . . . . . . .> sum(case sex when '男' then 1 else 0 end) as  man,**

**. . . . . . . . . . . . . . .> sum(case sex when '女' then 1 else 0 end) as  woman**

**. . . . . . . . . . . . . . .> from emp_sex group by dept_id;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image048.jpg)

 

### 3-列转行

**1.Concat：将任意类型的字段进行拼接**

**0: jdbc:hive2://hadoop2:10000> select concat(ename," is ",sal) from emp;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image050.jpg)

 

**2.Concat_ws：按照指定格式进行拼接（字段必须是string or array<string>）**

**0: jdbc:hive2://hadoop2:10000> select concat_ws(' - ',ename,cast(sal as string)) from emp;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image052.jpg)

 

**3.Collect_set字段去重：**

**0: jdbc:hive2://hadoop2:10000> select collect_set(ename) from emp;**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image054.jpg)

 

 

 

**4.语法练习：**

**需求：对下面的table,列出星座与血型的组合的所有**

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image056.jpg)

 

 

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image058.jpg)

 

 

**先求出星座与血型的组合**

 

**0: jdbc:hive2://hadoop2:10000> select t1.cb from (select concat_ws(',',constellation,blood_type) as cb,name from person_info) as t1 group by t1.cb;**

 

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image060.jpg)

**最终答案：**

**0: jdbc:hive2://hadoop2:10000> select t1.cb, concat_ws('|',collect_set(t1.name)) from (select concat_ws(',',constellation,blood_type) as cb,name from person_info) as t1 group by t1.cb;**

 

![img](file:///C:/Users/KARLIE~1/AppData/Local/Temp/msohtmlclip1/01/clip_image062.jpg)