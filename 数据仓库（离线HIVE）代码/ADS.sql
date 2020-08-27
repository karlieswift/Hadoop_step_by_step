use gmall;
/**
  需求定义：
日活：当日活跃的设备数
周活：当周活跃的设备数
月活：当月活跃的设备数

 */
create external table ads_uv_count
(
    `dt`          string COMMENT '统计日期',
    `day_count`   bigint COMMENT '当日用户数量',
    `wk_count`    bigint COMMENT '当周用户数量',
    `mn_count`    bigint COMMENT '当月用户数量',
    `is_weekend`  string COMMENT 'Y,N是否是周末,用于得到本周最终结果',
    `is_monthend` string COMMENT 'Y,N是否是月末,用于得到本月最终结果'
) COMMENT '活跃设备数'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_uv_count/';

insert overwrite table ads_uv_count
select "2020-06-14" dt,
       sum(if(login_date_last = "2020-06-14", 1, 0)),
       sum(if(date_add(next_day("2020-06-14", "MO"), -7) <= login_date_last and
              date_add(next_day("2020-06-14", "MO"), -1) >= login_date_last, 1, 0)),
       sum(if(date_format("2020-06-14", "yyyy-MM") = date_format(login_date_last, "yyyy-MM"), 1, 0)),
       if(date_add(next_day("2020-06-14", "MO"), -1) = "2020-06-14", 'Y', 'N'),
       if("2020-06-14" = last_day("2020-06-14"), 'Y', 'N')
from dwt_uv_topic;


/**
 每日新增设备
 */

drop table if exists ads_new_mid_count;
create external table ads_new_mid_count
(
    `create_date`   string comment '创建时间',
    `new_mid_count` BIGINT comment '新增设备数量'
) COMMENT '每日新增设备数量'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_new_mid_count/';


insert into table ads_new_mid_count
select count(*)
from dwt_uv_topic
where login_date_first = "2020-06-14";


/**
  留存率
 */
create external table ads_user_retention_day_rate
(
    `stat_date`       string comment '统计日期',
    `create_date`     string comment '设备新增日期',
    `retention_day`   int comment '截止当前日期留存天数',
    `retention_count` bigint comment '留存数量',
    `new_mid_count`   bigint comment '设备新增数量',
    `retention_ratio` decimal(16, 2) comment '留存率'
) COMMENT '留存率'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_user_retention_day_rate/';



insert into table ads_user_retention_day_rate
select "2020-06-18",
       date_add("2020-06-18", -1),
       1,
       sum(if(login_date_first = date_add("2020-06-18", -1) and login_date_last = "2020-06-18", 1, 0)),
       sum(if(login_date_first = date_add("2020-06-18", -1), 1, 0)),
       sum(if(login_date_first = date_add("2020-06-18", -1) and login_date_last = "2020-06-18", 1, 0)) /
       sum(if(login_date_first = date_add("2020-06-18", -1), 1, 0)) * 100

from dwt_uv_topic
union all

select "2020-06-18",
       date_add("2020-06-18", -2),
       2,
       sum(if(login_date_first = date_add("2020-06-18", -2) and login_date_last = "2020-06-18", 1, 0)),
       sum(if(login_date_first = date_add("2020-06-18", -2), 1, 0)),
       sum(if(login_date_first = date_add("2020-06-18", -2) and login_date_last = "2020-06-18", 1, 0)) /
       sum(if(login_date_first = date_add("2020-06-18", -2), 1, 0)) * 100

from dwt_uv_topic
union all

select "2020-06-18",
       date_add("2020-06-18", -3),
       3,
       sum(if(login_date_first = date_add("2020-06-18", -3) and login_date_last = "2020-06-18", 1, 0)),
       sum(if(login_date_first = date_add("2020-06-18", -3), 1, 0)),
       sum(if(login_date_first = date_add("2020-06-18", -3) and login_date_last = "2020-06-18", 1, 0)) /
       sum(if(login_date_first = date_add("2020-06-18", -3), 1, 0)) * 100

from dwt_uv_topic;


/**
   沉默用户数
需求定义：
沉默用户：只在安装当天启动过，且启动时间是在7天前
 */

drop table if exists ads_silent_count;
create external table ads_silent_count
(
    `dt`           string COMMENT '统计日期',
    `silent_count` bigint COMMENT '沉默设备数'
) COMMENT '沉默用户数'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_silent_count';


insert into table ads_silent_count
select '2020-06-14',
       count(*)
from dwt_uv_topic
where login_date_first = login_date_last
  and login_date_last <= date_add('2020-06-14', -7);


/*
需求定义：
本周回流用户：上周未活跃，本周活跃的设备，且不是本周新增设备
1）建表语句
 */
drop table if exists ads_back_count;
create external table ads_back_count
(
    `dt`            string COMMENT '统计日期',
    `wk_dt`         string COMMENT '统计日期所在周',
    `wastage_count` bigint COMMENT '回流设备数'
) COMMENT '本周回流用户数'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_back_count';



--思路：统计本周活跃，然后把本周新增的用户过滤，
--然后求出上周活跃的设备，两个表left join 上周的mid_id 是null 说明本周没有活跃


insert into table ads_back_count
select "2020-06-15" dt,
       concat(date_add(next_day('2020-06-25', 'MO'), -7), '_', date_add(next_day('2020-06-25', 'MO'), -1)),
       count(*)

from (
         select mid_id
         from dwt_uv_topic
         where login_date_last >= date_add(next_day("2020-06-15", "MO"), -7)
           and login_date_last <= date_add(next_day("2020-06-15", "MO"), -1)
           and login_date_first <= date_add(next_day("2020-06-15", "MO"), -7)
     ) this_week
         left join
     (
         select mid_id
         from dws_uv_detail_daycount
         where dt >= date_add(next_day("2020-06-15", "MO"), -7 - 7)
           and dt <= date_add(next_day("2020-06-15", "MO"), -1 - 7)
         group by mid_id
     ) last_week
     on this_week.mid_id = last_week.mid_id
where last_week.mid_id is null
;



--
--     `mid_id` string comment '设备id',
--     `brand` string comment '手机品牌',
--     `model` string comment '手机型号',
--     `login_date_first` string  comment '首次活跃时间',
--     `login_date_last` string  comment '末次活跃时间',
--     `login_day_count` bigint comment '当日活跃次数',
--     `login_count` bigint comment '累积活跃天数'


/*
需求定义：
流失用户：最近7天未活跃的设备
1）建表语句

 */

drop table if exists ads_wastage_count;
create external table ads_wastage_count
(
    `dt`            string COMMENT '统计日期',
    `wastage_count` bigint COMMENT '流失设备数'
) COMMENT '流失用户数'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_wastage_count';


insert into table ads_wastage_count
select "2020-06-15" dt,
       count
from (
         select mid_id,
                count(*) count
         from dwt_uv_topic
         where login_date_last < date_add("2020-06-15", -7)
         group by mid_id
     ) t;


/**
  最近连续三周活跃用户数
1）建表语句


 */

drop table if exists ads_continuity_wk_count;
create external table ads_continuity_wk_count
(
    `dt`               string COMMENT '统计日期,一般用结束周周日日期,如果每天计算一次,可用当天日期',
    `wk_dt`            string COMMENT '持续时间',
    `continuity_count` bigint COMMENT '活跃次数'
) COMMENT '最近连续三周活跃用户数'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_continuity_wk_count';


insert into table ads_continuity_wk_count

select '2020-06-25' dt,
       concat(date_add(next_day('2020-06-25', 'monday'), -7 * 3), '_', date_add(next_day('2020-06-25', 'monday'), -1)),
       count(*)

from (
         select mid_id,
                count(*) n
         from (
                  (
                      select mid_id
                      from dws_uv_detail_daycount
                      where dt >= date_add(next_day('2020-06-25', 'monday'), -7 * 1)
                        and dt <= date_add(next_day('2020-06-25', 'monday'), -1)
                      group by mid_id
                  )
                  union all
                  (
                      select mid_id
                      from dws_uv_detail_daycount
                      where dt >= date_add(next_day('2020-06-25', 'monday'), -7 * 2)
                        and dt <= date_add(next_day('2020-06-25', 'monday'), -1 - 7)
                      group by mid_id
                  )
                  union all
                  (
                      select mid_id
                      from dws_uv_detail_daycount
                      where dt >= date_add(next_day('2020-06-25', 'monday'), -7 * 3)
                        and dt <= date_add(next_day('2020-06-25', 'monday'), -1 - 7 - 7)
                      group by mid_id
                  )) t1
         group by mid_id
         having n = 3
     ) t1;

-- 7.2.8 最近七天内连续三天活跃用户数
-- 1）建表语句

drop table if exists ads_continuity_uv_count;
create external table ads_continuity_uv_count
(
    `dt`               string COMMENT '统计日期',
    `wk_dt`            string COMMENT '最近7天日期',
    `continuity_count` bigint
) COMMENT '最近七天内连续三天活跃用户数'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_continuity_uv_count';


insert into table ads_continuity_uv_count
select "2020-06-14",
       mid_id,
       count(*)
from (
         select mid_id
         from (
                  select t1.mid_id,
                         date_add(dt, -rk) diff
                  from (
                           select dt,
                                  mid_id,
                                  row_number() over (partition by mid_id order by dt) rk
                           from dws_uv_detail_daycount
                           where dt >= date_add("2020-06-14", -6)
                       ) t1
              ) t2
         group by t2.mid_id, diff
         having count(*) >= 3
     ) t3
group by mid_id


/*
7.3 会员主题
7.3.1 会员信息
1）建表
 */
drop table if exists ads_user_topic;
create external table ads_user_topic
(
    `dt`                    string COMMENT '统计日期',
    `day_users`             string COMMENT '活跃会员数',
    `day_new_users`         string COMMENT '新增会员数',
    `day_new_payment_users` string COMMENT '新增消费会员数',
    `payment_users`         string COMMENT '总付费会员数',
    `users`                 string COMMENT '总会员数',
    `day_users2users`       decimal(16, 2) COMMENT '会员活跃率',
    `payment_users2users`   decimal(16, 2) COMMENT '会员付费率',
    `day_new_users2users`   decimal(16, 2) COMMENT '会员新鲜度'
) COMMENT '会员信息表'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_user_topic';



insert into table ads_user_topic

select '2020-06-14',

       sum(if('2020-06-14' = login_date_last), 1, 0),
       sum(if('2020-06-14' = login_date_first), 1, 0),
       sum(if('2020-06-14' = payment_date_first), 1, 0),
       sum(if(payment_count > 0), 1, 0),

       count(*),
       sum(if('2020-06-14' = login_date_last), 1, 0) / count(*),
       sum(if(payment_count > 0), 1, 0) / count(*),
       sum(if(login_date_first = '2020-06-14', 1, 0)) / sum(if(login_date_last = '2020-06-14', 1, 0))
from dwt_user_topic;

/*
漏斗分析
统计“浏览首页->浏览商品详情页->加入购物车->下单->支付”的转化率
思路：统计各个行为的人数，然后计算比值。
1）建表语句
 */

drop table if exists ads_user_action_convert_day;
create external table ads_user_action_convert_day
(
    `dt`                             string COMMENT '统计日期',
    `home_count`                     bigint COMMENT '浏览首页人数',
    `good_detail_count`              bigint COMMENT '浏览商品详情页人数',
    `home2good_detail_convert_ratio` decimal(16, 2) COMMENT '首页到商品详情转化率',
    `cart_count`                     bigint COMMENT '加入购物车的人数',
    `good_detail2cart_convert_ratio` decimal(16, 2) COMMENT '商品详情页到加入购物车转化率',
    `order_count`                    bigint COMMENT '下单人数',
    `cart2order_convert_ratio`       decimal(16, 2) COMMENT '加入购物车到下单转化率',
    `payment_amount`                 bigint COMMENT '支付人数',
    `order2payment_convert_ratio`    decimal(16, 2) COMMENT '下单到支付的转化率'
) COMMENT '漏斗分析'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_user_action_convert_day/';



insert into table ads_user_action_convert_day
select '2020-06-14',
       t2.home_count,
       t2.good_detail_count,
       t2.good_detail_count / t2.home_count * 100,
       t3.cart_count,
       t3.cart_count / t2.good_detail_count * 100,
       t3.order_count,
       t3.order_count / t3.cart_count * 100,
       t3.payment_count,
       t3.payment_count / t3.order_count * 100
from (
         select '2020-06-14'                                       dt,
                sum(if(array_contains(page, "home"), 1, 0))        home_count,
                sum(if(array_contains(page, "good_detail"), 1, 0)) good_detail_count
         from (
                  select mid_id,
                         collect_set(page_id) page
                  from dwd_page_log
                  where dt = '2020-06-14'
                    and page_id in ("home", 'good_detail')
                  group by mid_id
              ) t1
     ) t2
         join
     (
         select "2020-06-14"                     dt,
                sum(if(cart_count > 0, 1, 0))    cart_count,
                sum(if(order_count > 0, 1, 0))   order_count,
                sum(if(payment_count > 0, 1, 0)) payment_count

         from dws_user_action_daycount
         where dt = '2020-06-14'
     ) t3
     on t2.dt = t3.dt;


/**
  商品个数信息
 */
create external table ads_product_info
(
    `dt`      string COMMENT '统计日期',
    `sku_num` string COMMENT 'sku个数',
    `spu_num` string COMMENT 'spu个数'
) COMMENT '商品个数信息'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_product_info';

insert into table ads_product_info
select '2020-06-14',
       sku_num,
       spu_num
from (
         select '2020-06-14' dt,
                count(*)     sku_num
         from dwt_sku_topic
     ) t1
         join
     (
         select '2020-06-14' dt,
                count(*)     spu_num
         from (
                  select spu_id
                  from dwt_sku_topic
                  group by spu_id
              ) temp
     ) t2
     on t1.dt = t2.dt;


/**
  商品销量排名
 */
create external table ads_product_sale_topN
(
    `dt`             string COMMENT '统计日期',
    `sku_id`         string COMMENT '商品ID',
    `payment_amount` bigint COMMENT '销量'
) COMMENT '商品销量排名'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_product_sale_topN';


insert into table ads_product_sale_topN

select '2020-06-14',
       sku_id,
       payment_amount
from dws_sku_action_daycount
where dt = '2020-06-14'
order by payment_amount desc
limit 10;


/**
  商品收藏排名
 */

drop table if exists ads_product_favor_topN;
create external table ads_product_favor_topN
(
    `dt`          string COMMENT '统计日期',
    `sku_id`      string COMMENT '商品ID',
    `favor_count` bigint COMMENT '收藏量'
) COMMENT '商品收藏排名'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_product_favor_topN';


insert into table ads_product_favor_topN

select '2020-06-14',
       sku_id,
       favor_count
from dws_sku_action_daycount
where dt = '2020-06-14'
order by payment_amount desc
limit 10;

/**
商品加入购物车排名
*/

drop table if exists ads_product_cart_topN;
create external table ads_product_cart_topN
(
    `dt`         string COMMENT '统计日期',
    `sku_id`     string COMMENT '商品ID',
    `cart_count` bigint COMMENT '加入购物车次数'
) COMMENT '商品加入购物车排名'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_product_cart_topN';


insert into table ads_product_cart_topN
select '2020-06-14' dt,
       sku_id,
       cart_count
from dws_sku_action_daycount
where dt = '2020-06-14'
order by cart_count desc
limit 10;


/**
  商品退款率排名（最近30天）
 */

drop table if exists ads_product_refund_topN;
create external table ads_product_refund_topN
(
    `dt`           string COMMENT '统计日期',
    `sku_id`       string COMMENT '商品ID',
    `refund_ratio` decimal(16, 2) COMMENT '退款率'
) COMMENT '商品退款率排名'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_product_refund_topN';


insert into table ads_product_refund_topN

select '2020-06-14',
       sku_id,
       refund_last_30d_count / payment_last_30d_count * 100 refund_ratio

from dwt_sku_topic
order by refund_ratio desc
limit 10;


/**
  商品差评率
 */

drop table if exists ads_appraise_bad_topN;
create external table ads_appraise_bad_topN
(
    `dt`                 string COMMENT '统计日期',
    `sku_id`             string COMMENT '商品ID',
    `appraise_bad_ratio` decimal(16, 2) COMMENT '差评率'
) COMMENT '商品差评率'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_appraise_bad_topN';


insert into table ads_appraise_bad_topN
select '2020-06-14',
       sku_id,
       appraise_bad_count / (appraise_mid_count + appraise_bad_count + appraise_default_count + appraise_good_count) *
       100 appraise_bad_ratio
from dws_sku_action_daycount
where dt = '2020-06-14'
order by appraise_bad_ratio desc
limit 10;


/**
  下单数目统计
需求分析：统计每日下单数，下单金额及下单用户数。

 */


create external table ads_order_daycount
(
    dt           string comment '统计日期',
    order_count  bigint comment '单日下单笔数',
    order_amount bigint comment '单日下单金额',
    order_users  bigint comment '单日下单用户数'
) comment '下单数目统计'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_order_daycount';


insert into table ads_order_daycount
select "2020-06-14",
       sum(order_count),
       sum(order_amount),
       sum(if(order_count > 0, 1, 0))

from dws_user_action_daycount
where dt = "2020-06-14";


/**
 支付信息统计
每日支付金额、支付人数、支付商品数、支付笔数以及下单到支付的平均时长（取自DWD）

 */

drop table if exists ads_payment_daycount;
create external table ads_payment_daycount
(
    dt                 string comment '统计日期',
    order_count        bigint comment '单日支付笔数',
    order_amount       bigint comment '单日支付金额',
    payment_user_count bigint comment '单日支付人数',
    payment_sku_count  bigint comment '单日支付商品数',
    payment_avg_time   decimal(16, 2) comment '下单到支付的平均时长，取分钟数'
) comment '支付信息统计'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_payment_daycount';


with day_pay as (
    select "2020-06-14"                     dt,
           sum(payment_count)               order_count,
           sum(payment_amount)              order_amount,
           sum(if(payment_count > 0, 1, 0)) payment_user_count
    from dws_user_action_daycount
    where dt = "2020-06-14"
),
     day_sku as (
         select "2020-06-14"       dt,

                sum(payment_count) payment_sku_count

         from dws_sku_action_daycount
         where dt = "2020-06-14"
     ),
     day_user as (
         select "2020-06-14"                                                                    dt,
                sum(unix_timestamp(payment_time) - unix_timestamp(create_time)) / count(*) / 60 payment_avg_time
         from dwd_fact_order_info
         where dt = "2020-06-14"
     )

insert
into table ads_payment_daycount
select "2020-06-14",
       order_count,
       order_amount,
       payment_user_count,
       payment_sku_count,
       payment_avg_time


from day_pay
         join day_sku
              on day_pay.dt = day_sku.dt
         join day_user
              on day_pay.dt = day_user.dt;


/**
品牌复购率
1）建表语句
 */

drop table ads_sale_tm_category1_stat_mn;
create external table ads_sale_tm_category1_stat_mn
(
    tm_id                 string comment '品牌id',
    category1_id          string comment '1级品类id ',
    category1_name        string comment '1级品类名称 ',
    buycount              bigint comment '购买人数',
    buy_twice_last        bigint comment '两次以上购买人数',
    buy_twice_last_ratio  decimal(16, 2) comment '单次复购率',
    buy_3times_last       bigint comment '三次以上购买人数',
    buy_3times_last_ratio decimal(16, 2) comment '多次复购率',
    stat_mn               string comment '统计月份',
    stat_date             string comment '统计日期'
) COMMENT '品牌复购率统计'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_sale_tm_category1_stat_mn/';

insert into table ads_sale_tm_category1_stat_mn
select tm_id,
       category1_id,
       category1_name,
       sum(if(order_count >= 1, 1, 0))                                   buycount,
       sum(if(order_count >= 2, 1, 0))                                   buy_twice_last,
       sum(if(order_count >= 2, 1, 0)) / sum(if(order_count >= 1, 1, 0)) buy_twice_last_ratio,
       sum(if(order_count >= 3, 1, 0))                                   buy_3times_last,
       sum(if(order_count >= 3, 1, 0)) / sum(if(order_count >= 1, 1, 0)) buy_3times_last_ratio,
       date_format("2020-06-14", "yyyy-MM")                              stat_mn,
       "2020-06-14"                                                      stat_date
from (
         select t1.user_id,
                t2.tm_id,
                category1_id,
                category1_name,
                sum(t1.order_count) order_count


         from (
                  select user_id,
                         sku_id,
                         count(*) order_count
                  from dwd_fact_order_detail
                  where date_format(dt, "yyyy-MM") = date_format("2020-06-14", "yyyy-MM")
                  group by user_id, sku_id
              ) t1
                  join
              (
                  select id,
                         tm_id,
                         category1_id,
                         category1_name

                  from dwd_dim_sku_info
                  where dt = "2020-06-15"
              ) t2
              on t1.sku_id = t2.id
         group by t1.user_id, t2.tm_id, category1_id, category1_name
     ) t3
group by t3.tm_id, category1_name, category1_id;


/**
  地区主题信息
 */

drop table if exists ads_area_topic;
create external table ads_area_topic
(
    `dt`                 string COMMENT '统计日期',
    `id`                 bigint COMMENT '编号',
    `province_name`      string COMMENT '省份名称',
    `area_code`          string COMMENT '地区编码',
    `iso_code`           string COMMENT 'iso编码',
    `region_id`          string COMMENT '地区ID',
    `region_name`        string COMMENT '地区名称',
    `login_day_count`    bigint COMMENT '当天活跃设备数',
    `order_day_count`    bigint COMMENT '当天下单次数',
    `order_day_amount`   decimal(16, 2) COMMENT '当天下单金额',
    `payment_day_count`  bigint COMMENT '当天支付次数',
    `payment_day_amount` decimal(16, 2) COMMENT '当天支付金额'
) COMMENT '地区主题信息'
    row format delimited fields terminated by '\t'
    location '/warehouse/gmall/ads/ads_area_topic/';


insert into table ads_area_topic

select "2020-06-14",
       id,
       area_code,
       province_name,
       iso_code,
       region_id,
       region_name,
       login_day_count,
       order_day_amount,
       order_day_amount,
       payment_day_count,
       payment_day_amount
from dwt_area_topic;


/**
  各个产品的型号订单排名
 */
select spu_id,
       t1.rk
from (
         select spu_id,
                rank() over (partition by spu_id order by order_count desc) rk
         from dwt_sku_topic
     ) t1
where t1.rk <= 10;







