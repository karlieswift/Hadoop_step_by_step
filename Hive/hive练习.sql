SELECT current_database();查看正在使用哪个库
SHOW CREATE DATABASE t3;）查看创建库的详细语句
DROP DATABASE t3 CASCADE;强制删除非空数据库

-- 统计视频观看数Top10
-- 统计视频类别热度Top10
-- 统计出视频观看数最高的20个视频的所属类别以及类别包含Top20视频的个数


SELECT videoid,category,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 20;-->t1

SELECT t1.videoid,category1
FROM (SELECT videoid,category,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 20)t1
lateral VIEW explode(t1.category) temp_table AS category1;-->t2

SELECT t2.category1,COUNT(t2.videoid) n2
FROM (SELECT t1.videoid,category1
FROM (SELECT videoid,category,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 20)t1
lateral VIEW explode(t1.category) temp_table AS category1)t2 GROUP BY t2.category1;
+----------------+-----+
|  t2.category1  | n2  |
+----------------+-----+
| Blogs          | 2   |
| Comedy         | 6   |
| Entertainment  | 6   |
| Music          | 5   |
| People         | 2   |
| UNA            | 1   |
+----------------+-----+




-- 统计视频观看数Top50所关联视频的所属类别Rank

SELECT videoid,relatedid,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 50;

SELECT  t1.videoid,relatedid1
FROM (SELECT videoid,relatedid,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 50)t1
lateral VIEW explode(t1.relatedid) temp_table AS relatedid1




SELECT t2.relatedid1,t3.category
FROM (SELECT  t1.videoid,relatedid1
FROM (SELECT videoid,relatedid,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 50)t1
lateral VIEW explode(t1.relatedid) temp_table AS relatedid1)t2 JOIN gulivideo_orc t3 ON t2.relatedid1=t3.videoid



SELECT t4.relatedid1,category1
FROM (SELECT t2.relatedid1,t3.category
FROM (SELECT  t1.videoid,relatedid1
FROM (SELECT videoid,relatedid,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 50)t1
lateral VIEW explode(t1.relatedid) temp_table AS relatedid1)t2 JOIN gulivideo_orc t3 ON t2.relatedid1=t3.videoid)t4 
lateral VIEW explode( t4.category) temp_table1 AS category1;

SELECT category1,COUNT(t5.relatedid1) AS number
FROM (SELECT t4.relatedid1,category1
FROM (SELECT t2.relatedid1,t3.category
FROM (SELECT  t1.videoid,relatedid1
FROM (SELECT videoid,relatedid,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 50)t1
lateral VIEW explode(t1.relatedid) temp_table AS relatedid1)t2 JOIN gulivideo_orc t3 ON t2.relatedid1=t3.videoid)t4 
lateral VIEW explode( t4.category) temp_table1 AS category1)
t5 GROUP BY t5.category1 ORDER BY number DESC LIMIT 50;


SELECT t6.category1,t6.number,rank() over(ORDER BY t6.number DESC) n
FROM (SELECT category1,COUNT(t5.relatedid1) AS number
FROM (SELECT t4.relatedid1,category1
FROM (SELECT t2.relatedid1,t3.category
FROM (SELECT  t1.videoid,relatedid1
FROM (SELECT videoid,relatedid,views 
FROM gulivideo_orc ORDER BY views DESC LIMIT 50)t1
lateral VIEW explode(t1.relatedid) temp_table AS relatedid1)t2 JOIN gulivideo_orc t3 ON t2.relatedid1=t3.videoid)t4 
lateral VIEW explode( t4.category) temp_table1 AS category1)
t5 GROUP BY t5.category1 ORDER BY number DESC LIMIT 50)t6;

+----------------+------------+-----+
|  t6.category1  | t6.number  |  n  |
+----------------+------------+-----+
| Comedy         | 237        | 1   |
| Entertainment  | 216        | 2   |
| Music          | 195        | 3   |
| People         | 51         | 4   |
| Blogs          | 51         | 4   |
| Film           | 47         | 6   |
| Animation      | 47         | 6   |
| Politics       | 24         | 8   |
| News           | 24         | 8   |
| Gadgets        | 22         | 10  |
| Games          | 22         | 10  |
| Sports         | 19         | 12  |
| DIY            | 14         | 13  |
| Howto          | 14         | 13  |
| UNA            | 13         | 15  |
| Places         | 12         | 16  |
| Travel         | 12         | 16  |
| Pets           | 11         | 18  |
| Animals        | 11         | 18  |
| Autos          | 4          | 20  |
| Vehicles       | 4          | 20  |
+----------------+------------+-----+


-- 统计每个类别中的视频热度Top10,以Music为例
SELECT videoid,views,category_name
FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name

SELECT t1.videoid,t1.views,t1.category_name
FROM ( SELECT videoid,views,category_name
FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name)t1 WHERE t1.category_name='Music';

SELECT t2.videoid,t2.views,t2.category_name
FROM (SELECT t1.videoid,t1.views,t1.category_name
FROM ( SELECT videoid,views,category_name
FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name)t1 WHERE t1.category_name='Music')t2 ORDER BY t2.views DESC LIMIT 10;
 
-- 统计每个类别视频观看数Top10
SELECT videoid,views,category_name
FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name


SELECT t2.videoid,t2.views,t2.category_name,
rank() over(PARTITION BY t2.category_name ORDER BY t2.views DESC) n
FROM (SELECT videoid,views,category_name
FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name)t2;

SELECT t3.videoid,t3.views,t3.category_name,t3.n
FROM (SELECT t2.videoid,t2.views,t2.category_name,
rank() over(PARTITION BY t2.category_name ORDER BY t2.views DESC) n
FROM (SELECT videoid,views,category_name
FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name)t2)t3 WHERE t3.n<11;



+--------------+-----------+-------------------+-------+
|  t3.videoid  | t3.views  | t3.category_name  | t3.n  |
+--------------+-----------+-------------------+-------+
| 1dmVU08zVpA  | 16087899  | Entertainment     | 1     |
| RB-wUgnyGv0  | 15712924  | Entertainment     | 2     |
| vr3x_RRJdd4  | 10786529  | Entertainment     | 3     |
| lsO6D1rwrKc  | 10334975  | Entertainment     | 4     |
| ixsZy2425eY  | 7456875   | Entertainment     | 5     |
| RUCZJVJ_M8o  | 6952767   | Entertainment     | 6     |
| tFXLbXyXy6M  | 5810013   | Entertainment     | 7     |
| 7uwCEnDgd5o  | 5280504   | Entertainment     | 8     |
| 2KrdBUFeFtY  | 4676195   | Entertainment     | 9     |
| vD4OnHCRd_4  | 4230610   | Entertainment     | 10    |
| hr23tpWX8lM  | 4706030   | News              | 1     |
| YgW7or1TuFk  | 2899397   | News              | 2     |
| nda_OSWeyn8  | 2817078   | News              | 3     |
| 7SV2sfoPAY8  | 2803520   | News              | 4     |
| HBa9wdOANHw  | 2348709   | News              | 5     |
| xDh_pvv1tUM  | 2335060   | News              | 6     |
| p_YMigZmUuk  | 2326680   | News              | 7     |
| QCVxQ_3Ejkg  | 2318782   | News              | 8     |
| a9WB_PXjTBo  | 2310583   | News              | 9     |
| qSM_3fyiaxM  | 2291369   | News              | 10    |
| sdUUx5FdySs  | 5840839   | Film              | 1     |
| 6B26asyGKDo  | 5147533   | Film              | 2     |
| H20dhY01Xjk  | 3772116   | Film              | 3     |
| 55YYaJIrmzo  | 3356163   | Film              | 4     |
| JzqumbhfxRo  | 3230774   | Film              | 5     |
| eAhfZUZiwSE  | 3114215   | Film              | 6     |
| h7svw0m-wO0  | 2866490   | Film              | 7     |
| tAq3hWBlalU  | 2830024   | Film              | 8     |
| AJzU3NjDikY  | 2569611   | Film              | 9     |
| ElrldD02if0  | 2337238   | Film              | 10    |
| aRNzWyD7C9o  | 8825788   | UNA               | 1     |
| jtExxsiLgPM  | 5320895   | UNA               | 2     |
| PxNNR4symuE  | 4033376   | UNA               | 3     |
| 8cjTSvvoddc  | 3486368   | UNA               | 4     |
| LIhbap3FlGc  | 2849832   | UNA               | 5     |
| lCSTULqmmYE  | 2179562   | UNA               | 6     |
| UyTxWvp8upM  | 2106933   | UNA               | 7     |
| y6oXEWowirI  | 1666084   | UNA               | 8     |
| _x2-AmY8FI8  | 1403113   | UNA               | 9     |
| ICoDFooBXpU  | 1376215   | UNA               | 10    |
| -_CSo1gOd48  | 13199833  | Blogs             | 1     |
| D2kJZOfq7zk  | 11184051  | Blogs             | 2     |
| pa_7P5AbUww  | 5705136   | Blogs             | 3     |
| f4B-r8KJhlE  | 4937616   | Blogs             | 4     |
| LB84A3zcmVo  | 4866739   | Blogs             | 5     |
| tXNquTYnyg0  | 3613323   | Blogs             | 6     |
| EYppbbbSxjc  | 2896562   | Blogs             | 7     |
| LH7vrLlDZ6U  | 2615359   | Blogs             | 8     |
| bTV85fQhj0E  | 2192656   | Blogs             | 9     |
| eVFF98kNg8Q  | 1813803   | Blogs             | 10    |
| 2GWPOPSXGYI  | 3660009   | Animals           | 1     |
| xmsV9R8FsDA  | 3164582   | Animals           | 2     |
| 12PsUW-8ge4  | 3133523   | Animals           | 3     |
| OeNggIGSKH8  | 2457750   | Animals           | 4     |
| WofFb_eOxxA  | 2075728   | Animals           | 5     |
| AgEmZ39EtFk  | 1999469   | Animals           | 6     |
| a-gW3RbJd8U  | 1836870   | Animals           | 7     |
| 8CL2hetqpfg  | 1646808   | Animals           | 8     |
| QmroaYVD_so  | 1645984   | Animals           | 9     |
| Sg9x5mUjbH8  | 1527238   | Animals           | 10    |
| RjrEQaG5jPM  | 2803140   | Autos             | 1     |
| cv157ZIInUk  | 2773979   | Autos             | 2     |
| Gyg9U1YaVk8  | 1832224   | Autos             | 3     |
| 6GNB7xT3rNE  | 1412497   | Autos             | 4     |
| tth9krDtxII  | 1347317   | Autos             | 5     |
| 46LQd9dXFRU  | 1262173   | Autos             | 6     |
| pdiuDXwgrjQ  | 1013697   | Autos             | 7     |
| kY_cDpENQLE  | 956665    | Autos             | 8     |
| YtxfbxGz1u4  | 942604    | Autos             | 9     |
| aCamHfJwSGU  | 847442    | Autos             | 10    |
| hut3VRL5XRE  | 2684989   | DIY               | 1     |
| YYTpb-QXV0k  | 2492153   | DIY               | 2     |
| Pf3z935R37E  | 2096661   | DIY               | 3     |
| Yd99gyE4jCk  | 1918946   | DIY               | 4     |
| koQFjKwVFB0  | 1757071   | DIY               | 5     |
| f5Fg6KFcOsU  | 1751817   | DIY               | 6     |
| STQ3nhXuuEM  | 1713974   | DIY               | 7     |
| FtKuBKIaVvs  | 1520774   | DIY               | 8     |
| M0ODskdEPnQ  | 1503351   | DIY               | 9     |
| uFwCk4UPtlM  | 1500110   | DIY               | 10    |
| hr23tpWX8lM  | 4706030   | Politics          | 1     |
| YgW7or1TuFk  | 2899397   | Politics          | 2     |
| nda_OSWeyn8  | 2817078   | Politics          | 3     |
| 7SV2sfoPAY8  | 2803520   | Politics          | 4     |
| HBa9wdOANHw  | 2348709   | Politics          | 5     |
| xDh_pvv1tUM  | 2335060   | Politics          | 6     |
| p_YMigZmUuk  | 2326680   | Politics          | 7     |
| QCVxQ_3Ejkg  | 2318782   | Politics          | 8     |
| a9WB_PXjTBo  | 2310583   | Politics          | 9     |
| qSM_3fyiaxM  | 2291369   | Politics          | 10    |
| RjrEQaG5jPM  | 2803140   | Vehicles          | 1     |
| cv157ZIInUk  | 2773979   | Vehicles          | 2     |
| Gyg9U1YaVk8  | 1832224   | Vehicles          | 3     |
| 6GNB7xT3rNE  | 1412497   | Vehicles          | 4     |
| tth9krDtxII  | 1347317   | Vehicles          | 5     |
| 46LQd9dXFRU  | 1262173   | Vehicles          | 6     |
| pdiuDXwgrjQ  | 1013697   | Vehicles          | 7     |
| kY_cDpENQLE  | 956665    | Vehicles          | 8     |
| YtxfbxGz1u4  | 942604    | Vehicles          | 9     |
| aCamHfJwSGU  | 847442    | Vehicles          | 10    |
+--------------+-----------+-------------------+-------+
|  t3.videoid  | t3.views  | t3.category_name  | t3.n  |
+--------------+-----------+-------------------+-------+
| pFlcqWQVVuU  | 3651600   | Gadgets           | 1     |
| bcu8ZdJ2dQo  | 2617568   | Gadgets           | 2     |
| -G7h626wJwM  | 2565170   | Gadgets           | 3     |
| oMaTZFCLbq0  | 2554620   | Gadgets           | 4     |
| GxSdKF5Fd38  | 2468395   | Gadgets           | 5     |
| z1lj87UyvfY  | 2373875   | Gadgets           | 6     |
| KhCmfX_PQ7E  | 1967929   | Gadgets           | 7     |
| 2SVMFCZgvNM  | 1813794   | Gadgets           | 8     |
| gPutYwiiE0o  | 1633482   | Gadgets           | 9     |
| 7wt5FiZQrgM  | 1399531   | Gadgets           | 10    |
| QjA5faZF1A8  | 15256922  | Music             | 1     |
| tYnn51C3X_w  | 11823701  | Music             | 2     |
| pv5zWaTEVkI  | 11672017  | Music             | 3     |
| 8bbTtPL1jRs  | 9579911   | Music             | 4     |
| UMf40daefsI  | 7533070   | Music             | 5     |
| -xEzGIuY7kw  | 6946033   | Music             | 6     |
| d6C0bNDqf3Y  | 6935578   | Music             | 7     |
| HSoVKUVOnfQ  | 6193057   | Music             | 8     |
| 3URfWTEPmtE  | 5581171   | Music             | 9     |
| thtmaZnxk_0  | 5142238   | Music             | 10    |
| bNF_P281Uu4  | 5231539   | Travel            | 1     |
| s5ipz_0uC_U  | 1198840   | Travel            | 2     |
| 6jJW7aSNCzU  | 1143287   | Travel            | 3     |
| dVRUBIyRAYk  | 1000309   | Travel            | 4     |
| lqbt6X4ZgEI  | 921593    | Travel            | 5     |
| RIH1I1doUI4  | 879577    | Travel            | 6     |
| AlPqL7IUT6M  | 845180    | Travel            | 7     |
| _5QUdvUhCZc  | 819974    | Travel            | 8     |
| m9A_vxIOB-I  | 677876    | Travel            | 9     |
| CL6f3Cyh85w  | 611786    | Travel            | 10    |
| pFlcqWQVVuU  | 3651600   | Games             | 1     |
| bcu8ZdJ2dQo  | 2617568   | Games             | 2     |
| -G7h626wJwM  | 2565170   | Games             | 3     |
| oMaTZFCLbq0  | 2554620   | Games             | 4     |
| GxSdKF5Fd38  | 2468395   | Games             | 5     |
| z1lj87UyvfY  | 2373875   | Games             | 6     |
| KhCmfX_PQ7E  | 1967929   | Games             | 7     |
| 2SVMFCZgvNM  | 1813794   | Games             | 8     |
| gPutYwiiE0o  | 1633482   | Games             | 9     |
| 7wt5FiZQrgM  | 1399531   | Games             | 10    |
| sdUUx5FdySs  | 5840839   | Animation         | 1     |
| 6B26asyGKDo  | 5147533   | Animation         | 2     |
| H20dhY01Xjk  | 3772116   | Animation         | 3     |
| 55YYaJIrmzo  | 3356163   | Animation         | 4     |
| JzqumbhfxRo  | 3230774   | Animation         | 5     |
| eAhfZUZiwSE  | 3114215   | Animation         | 6     |
| h7svw0m-wO0  | 2866490   | Animation         | 7     |
| tAq3hWBlalU  | 2830024   | Animation         | 8     |
| AJzU3NjDikY  | 2569611   | Animation         | 9     |
| ElrldD02if0  | 2337238   | Animation         | 10    |
| dMH0bHeiRNg  | 42513417  | Comedy            | 1     |
| 0XxI-hvPRRA  | 20282464  | Comedy            | 2     |
| 49IDp76kjPw  | 11970018  | Comedy            | 3     |
| 5P6UU6m3cqk  | 10107491  | Comedy            | 4     |
| _BuRwH59oAo  | 9566609   | Comedy            | 5     |
| MNxwAU_xAMk  | 7066676   | Comedy            | 6     |
| pYak2F1hUYA  | 6322117   | Comedy            | 7     |
| h0zAlXr1UOs  | 5826923   | Comedy            | 8     |
| C8rjr4jmWd0  | 5587299   | Comedy            | 9     |
| R4cQ3BoHFas  | 5508079   | Comedy            | 10    |
| Ugrlzm7fySE  | 2867888   | Sports            | 1     |
| q8t7iSGAKik  | 2735003   | Sports            | 2     |
| 7vL19q8yL54  | 2527713   | Sports            | 3     |
| g3dXfFZ6SH0  | 2295871   | Sports            | 4     |
| P-bWsOK-h98  | 2268107   | Sports            | 5     |
| HD8f_Qgwc50  | 2165475   | Sports            | 6     |
| qjWQNwv-GJ4  | 2132591   | Sports            | 7     |
| eN0V-rJQSHE  | 2124653   | Sports            | 8     |
| fM38G1450Ew  | 2052778   | Sports            | 9     |
| 3PGzrfE8rJg  | 2013466   | Sports            | 10    |
| hut3VRL5XRE  | 2684989   | Howto             | 1     |
| YYTpb-QXV0k  | 2492153   | Howto             | 2     |
| Pf3z935R37E  | 2096661   | Howto             | 3     |
| Yd99gyE4jCk  | 1918946   | Howto             | 4     |
| koQFjKwVFB0  | 1757071   | Howto             | 5     |
| f5Fg6KFcOsU  | 1751817   | Howto             | 6     |
| STQ3nhXuuEM  | 1713974   | Howto             | 7     |
| FtKuBKIaVvs  | 1520774   | Howto             | 8     |
| M0ODskdEPnQ  | 1503351   | Howto             | 9     |
| uFwCk4UPtlM  | 1500110   | Howto             | 10    |
| bNF_P281Uu4  | 5231539   | Places            | 1     |
| s5ipz_0uC_U  | 1198840   | Places            | 2     |
| 6jJW7aSNCzU  | 1143287   | Places            | 3     |
| dVRUBIyRAYk  | 1000309   | Places            | 4     |
| lqbt6X4ZgEI  | 921593    | Places            | 5     |
| RIH1I1doUI4  | 879577    | Places            | 6     |
| AlPqL7IUT6M  | 845180    | Places            | 7     |
| _5QUdvUhCZc  | 819974    | Places            | 8     |
| m9A_vxIOB-I  | 677876    | Places            | 9     |
| CL6f3Cyh85w  | 611786    | Places            | 10    |
| -_CSo1gOd48  | 13199833  | People            | 1     |
| D2kJZOfq7zk  | 11184051  | People            | 2     |
| pa_7P5AbUww  | 5705136   | People            | 3     |
| f4B-r8KJhlE  | 4937616   | People            | 4     |
| LB84A3zcmVo  | 4866739   | People            | 5     |
| tXNquTYnyg0  | 3613323   | People            | 6     |
| EYppbbbSxjc  | 2896562   | People            | 7     |
| LH7vrLlDZ6U  | 2615359   | People            | 8     |
| bTV85fQhj0E  | 2192656   | People            | 9     |
| eVFF98kNg8Q  | 1813803   | People            | 10    |
+--------------+-----------+-------------------+-------+
|  t3.videoid  | t3.views  | t3.category_name  | t3.n  |
+--------------+-----------+-------------------+-------+
| 2GWPOPSXGYI  | 3660009   | Pets              | 1     |
| xmsV9R8FsDA  | 3164582   | Pets              | 2     |
| 12PsUW-8ge4  | 3133523   | Pets              | 3     |
| OeNggIGSKH8  | 2457750   | Pets              | 4     |
| WofFb_eOxxA  | 2075728   | Pets              | 5     |
| AgEmZ39EtFk  | 1999469   | Pets              | 6     |
| a-gW3RbJd8U  | 1836870   | Pets              | 7     |
| 8CL2hetqpfg  | 1646808   | Pets              | 8     |
| QmroaYVD_so  | 1645984   | Pets              | 9     |
| Sg9x5mUjbH8  | 1527238   | Pets              | 10    |
+--------------+-----------+-------------------+-------+


-- 统计上传视频最多的用户Top10以及他们上传的视频观看次数在前20的视频 


 uploader  | STRING     |          |
| videos    | INT        |          |
| friends 


SELECT  uploader,videos
FROM gulivideo_user_orc ORDER BY videos DESC LIMIT 10;

+---------------------+---------+
|      uploader       | videos  |
+---------------------+---------+
| expertvillage       | 86228   |
| TourFactory         | 49078   |
| myHotelVideo        | 33506   |
| AlexanderRodchenko  | 24315   |
| VHTStudios          | 20230   |
| ephemeral8          | 19498   |
| HSN                 | 15371   |
| rattanakorn         | 12637   |
| Ruchaneewan         | 10059   |
| futifu              | 9668    |
+---------------------+---------+



SELECT t1.uploader,t1.videos,t2.videoid,t2.views
FROM (SELECT  uploader,videos
FROM gulivideo_user_orc ORDER BY videos DESC LIMIT 10)t1 JOIN gulivideo_orc t2 ON t1.uploader =t2.uploader ;



SELECT t4.uploader,t4.videos,t4.videoid,t4.views, t4.n FROM(
SELECT t3.uploader,t3.videos,t3.videoid,t3.views,
rank() over(PARTITION BY t3.uploader ORDER BY t3.views DESC) n
FROM (SELECT t1.uploader,t1.videos,t2.videoid,t2.views
FROM (SELECT  uploader,videos
FROM gulivideo_user_orc ORDER BY videos DESC LIMIT 10)t1 JOIN gulivideo_orc t2 ON t1.uploader =t2.uploader )t3)t4 WHERE t4.n<21;





SELECT videoid ,category_name FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name LIMIT 10;


SELECT videoid ,category_name FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name;-->t1


SELECT t1.category_name,COUNT(t1.videoid) hot
FROM (SELECT videoid ,category_name FROM gulivideo_orc lateral VIEW explode(category) gulividdeo_temp AS category_name) t1 
GROUP BY t1.category_name ORDER BY hot DESC LIMIT 10;




-----------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------

userId	visitDate	visitCount



用户id	月份	小计	累积


+----------------+-------------------+--------------------+
| action.userid  | action.visitdate  | action.visitcount  |
+----------------+-------------------+--------------------+
| u01            | 2017/1/21         | 5                  |
| u02            | 2017/1/23         | 6                  |
| u03            | 2017/1/22         | 8                  |
| u04            | 2017/1/20         | 3                  |
| u01            | 2017/1/23         | 6                  |
| u01            | 2017/2/21         | 8                  |
| u02            | 2017/1/23         | 6                  |
| u01            | 2017/2/22         | 4                  |
+----------------+-------------------+--------------------+

split(visitDate,'/')[1]
 

step-1

SELECT userId,CONCAT(split(visitDate,'/')[0],'-',split(visitDate,'/')[1])AS visitdate  ,
visitcount
FROM ACTION;  -->t1
+---------+------------+-------------+
| userid  | visitdate  | visitcount  |
+---------+------------+-------------+
| u01     | 2017-1     | 5           |
| u02     | 2017-1     | 6           |
| u03     | 2017-1     | 8           |
| u04     | 2017-1     | 3           |
| u01     | 2017-1     | 6           |
| u01     | 2017-2     | 8           |
| u02     | 2017-1     | 6           |
| u01     | 2017-2     | 4           |
+---------+------------+-------------+


step-2
SELECT userId,visitdate  ,
SUM(visitCount)  month_total 
FROM (SELECT userId,CONCAT(split(visitDate,'/')[0],'-',split(visitDate,'/')[1])AS visitdate  ,
visitcount
FROM ACTION) t1 GROUP BY t1.userId ,t1.visitDate; -->t2

+---------+------------+--------------+
| userid  | visitdate  | month_total  |
+---------+------------+--------------+
| u01     | 2017-1     | 11           |
| u01     | 2017-2     | 12           |
| u02     | 2017-1     | 12           |
| u03     | 2017-1     | 8            |
| u04     | 2017-1     | 3            |
+---------+------------+--------------+

 

step-3
SELECT t2.userId,t2.visitdate,t2.month_total,
SUM(t2.month_total) over(PARTITION BY  t2.userId ORDER BY t2.visitdate )
FROM (SELECT userId,visitdate  ,
SUM(visitCount)  month_total 
FROM (SELECT userId,CONCAT(split(visitDate,'/')[0],'-',split(visitDate,'/')[1])AS visitdate  ,
visitcount
FROM ACTION) t1 GROUP BY t1.userId ,t1.visitDate) t2;


+------------+---------------+-----------------+---------------+
| t2.userid  | t2.visitdate  | t2.month_total  | sum_window_0  |
+------------+---------------+-----------------+---------------+
| u01        | 2017-1        | 11              | 11            |
| u01        | 2017-2        | 12              | 23            |
| u02        | 2017-1        | 12              | 12            |
| u03        | 2017-1        | 8               | 8             |
| u04        | 2017-1        | 3               | 3             |
+------------+---------------+-----------------+---------------+


-----------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------



1-1

+----------------+-------------+
| visit.user_id  | visit.shop  |
+----------------+-------------+
| u1             | a           |
| u2             | b           |
| u1             | b           |
| u1             | a           |
| u3             | c           |
| u4             | b           |
| u1             | a           |
| u2             | c           |
| u5             | b           |
| u4             | b           |
| u6             | c           |
| u2             | c           |
| u1             | b           |
| u2             | a           |
| u2             | a           |
| u3             | a           |
| u5             | a           |
| u5             | a           |
| u5             | a           |
+----------------+-------------+

SELECT shop,user_id,COUNT(user_id)  n1 FROM visit GROUP BY shop,user_id;-->t1

+-------+----------+-----+
| shop  | user_id  | n1  |
+-------+----------+-----+
| a     | u1       | 3   |
| b     | u1       | 2   |
| a     | u2       | 2   |
| b     | u2       | 1   |
| c     | u2       | 2   |
| a     | u3       | 1   |
| c     | u3       | 1   |
| b     | u4       | 2   |
| a     | u5       | 3   |
| b     | u5       | 1   |
| c     | u6       | 1   |
+-------+----------+-----+


SELECT t1.shop,SUM(t1.n1) n2
FROM (SELECT shop,user_id,COUNT(user_id)  n1 FROM visit GROUP BY shop,user_id) t1 GROUP BY t1.shop;
+----------+-----+
| t1.shop  | n2  |
+----------+-----+
| a        | 9   |
| b        | 6   |
| c        | 4   |
+----------+-----+

2-2


SELECT shop,user_id,COUNT(*) n1 FROM visit GROUP BY shop,user_id;
+-------+----------+-----+
| shop  | user_id  | n1  |
+-------+----------+-----+
| a     | u1       | 3   |
| b     | u1       | 2   |
| a     | u2       | 2   |
| b     | u2       | 1   |
| c     | u2       | 2   |
| a     | u3       | 1   |
| c     | u3       | 1   |
| b     | u4       | 2   |
| a     | u5       | 3   |
| b     | u5       | 1   |
| c     | u6       | 1   |
+-------+----------+-----+

SELECT  t2.shop, t2.user_id,t2.n1 FROM
(SELECT t1.shop, t1.user_id,t1.n1,
row_number() over(PARTITION BY t1.shop ORDER BY t1.n1 DESC ) n2
FROM (SELECT shop,user_id,COUNT(*) n1 FROM visit GROUP BY shop,user_id) t1) t2 WHERE t2.n2<4;

+----------+-------------+--------+
| t2.shop  | t2.user_id  | t2.n1  |
+----------+-------------+--------+
| a        | u1          | 3      |
| a        | u5          | 3      |
| a        | u2          | 2      |
| b        | u1          | 2      |
| b        | u4          | 2      |
| b        | u2          | 1      |
| c        | u2          | 2      |
| c        | u3          | 1      |
| c        | u6          | 1      |
+----------+-------------+--------+








LOAD DATA LOCAL inpath "/opt/data/ant/user_low_carbon.txt" INTO TABLE user_low_carbon;
LOAD DATA LOCAL inpath "/opt/data/ant/plant_carbon.txt" INTO TABLE plant_carbon;




1.蚂蚁森林植物申领统计
问题：假设2017年1月1日开始记录低碳数据（user_low_carbon），假设2017年10月1日之前满足申领条件的用户都申领了一颗p004-胡杨，
剩余的能量全部用来领取“p002-沙柳” 。
统计在10月1日累计申领“p002-沙柳” 排名前10的用户信息；以及他比后一名多领了几颗沙柳。
得到的统计结果如下表样式：
user_id  plant_count less_count(比后一名多领了几颗沙柳)
u_101    1000         100
u_088    900          400
u_103    500          …
+------------------------+--------------------------+--------------------------+
| plant_carbon.plant_id  | plant_carbon.plant_name  | plant_carbon.low_carbon  |
+------------------------+--------------------------+--------------------------+
| p001                   | 梭梭树                      | 17                       |
| p002                   | 沙柳                       | 19                       |
| p003                   | 樟子树                      | 146                      |
| p004                   | 胡杨                       | 215                      |
+------------------------+--------------------------+--------------------------+


SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon;

SELECT t1.user_id,SUM(t1.low_carbon) sum_carbon
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon)t1 
WHERE MONTH(t1.date1)<'10' GROUP BY t1.user_id ORDER BY sum_carbon DESC LIMIT 11;
+-------------+-------------+
| t1.user_id  | sum_carbon  |
+-------------+-------------+
| u_007       | 1470        |
| u_013       | 1430        |
| u_008       | 1240        |
| u_005       | 1100        |
| u_010       | 1080        |
| u_014       | 1060        |
| u_011       | 960         |
| u_009       | 930         |
| u_006       | 830         |
| u_002       | 659         |
| u_004       | 640         |
+-------------+-------------+

SELECT t2.user_id ,t2.sum_carbon,
FLOOR((t2.sum_carbon-(SELECT low_carbon FROM plant_carbon WHERE plant_id='p004'))/(SELECT low_carbon FROM plant_carbon WHERE plant_id='p002'))n
FROM (SELECT t1.user_id,SUM(t1.low_carbon) sum_carbon
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon)t1 
WHERE MONTH(t1.date1)<'10' GROUP BY t1.user_id ORDER BY sum_carbon DESC LIMIT 11)t2;
+-------------+----------------+-----+
| t2.user_id  | t2.sum_carbon  |  n  |
+-------------+----------------+-----+
| u_007       | 1470           | 66  |
| u_013       | 1430           | 63  |
| u_008       | 1240           | 53  |
| u_005       | 1100           | 46  |
| u_010       | 1080           | 45  |
| u_014       | 1060           | 44  |
| u_011       | 960            | 39  |
| u_009       | 930            | 37  |
| u_006       | 830            | 32  |
| u_002       | 659            | 23  |
| u_004       | 640            | 22  |
+-------------+----------------+-----+


SELECT t3.user_id ,t3.sum_carbon,t3.n,
lead(t3.n,1,0) over(ORDER BY t3.sum_carbon DESC) next_n
FROM (SELECT t2.user_id ,t2.sum_carbon,
FLOOR((t2.sum_carbon-(SELECT low_carbon FROM plant_carbon WHERE plant_id='p004'))/(SELECT low_carbon FROM plant_carbon WHERE plant_id='p002'))n
FROM (SELECT t1.user_id,SUM(t1.low_carbon) sum_carbon
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon)t1 
WHERE MONTH(t1.date1)<'10' GROUP BY t1.user_id ORDER BY sum_carbon DESC LIMIT 11)t2)t3;

+-------------+----------------+-------+---------+
| t3.user_id  | t3.sum_carbon  | t3.n  | next_n  |
+-------------+----------------+-------+---------+
| u_007       | 1470           | 66    | 63      |
| u_013       | 1430           | 63    | 53      |
| u_008       | 1240           | 53    | 46      |
| u_005       | 1100           | 46    | 45      |
| u_010       | 1080           | 45    | 44      |
| u_014       | 1060           | 44    | 39      |
| u_011       | 960            | 39    | 37      |
| u_009       | 930            | 37    | 32      |
| u_006       | 830            | 32    | 23      |
| u_002       | 659            | 23    | 22      |
| u_004       | 640            | 22    | 0       |
+-------------+----------------+-------+---------+
SELECT t4.user_id ,t4.sum_carbon,t4.n,t4.next_n,t4.n-t4.next_n n1
FROM (SELECT t3.user_id ,t3.sum_carbon,t3.n,
lead(t3.n,1,0) over(ORDER BY t3.sum_carbon DESC) next_n
FROM (SELECT t2.user_id ,t2.sum_carbon,
FLOOR((t2.sum_carbon-(SELECT low_carbon FROM plant_carbon WHERE plant_id='p004'))/(SELECT low_carbon FROM plant_carbon WHERE plant_id='p002'))n
FROM (SELECT t1.user_id,SUM(t1.low_carbon) sum_carbon
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon)t1 
WHERE MONTH(t1.date1)<'10' GROUP BY t1.user_id ORDER BY sum_carbon DESC LIMIT 11)t2)t3)t4;
+-------------+----------------+-------+------------+-----+
| t4.user_id  | t4.sum_carbon  | t4.n  | t4.next_n  | n1  |
+-------------+----------------+-------+------------+-----+
| u_007       | 1470           | 66    | 63         | 3   |
| u_013       | 1430           | 63    | 53         | 10  |
| u_008       | 1240           | 53    | 46         | 7   |
| u_005       | 1100           | 46    | 45         | 1   |
| u_010       | 1080           | 45    | 44         | 1   |
| u_014       | 1060           | 44    | 39         | 5   |
| u_011       | 960            | 39    | 37         | 2   |
| u_009       | 930            | 37    | 32         | 5   |
| u_006       | 830            | 32    | 23         | 9   |
| u_002       | 659            | 23    | 22         | 1   |
| u_004       | 640            | 22    | 0          | 22  |
+-------------+----------------+-------+------------+-----+



2、蚂蚁森林低碳用户排名分析
问题：查询user_low_carbon表中每日流水记录，条件为：
用户在2017年，连续三天（或以上）的天数里，
每天减少碳排放（low_carbon）都超过100g的用户低碳流水。
需要查询返回满足以上条件的user_low_carbon表中的记录流水。
例如用户u_002符合条件的记录如下，因为2017/1/2~2017/1/5连续四天的碳排放量之和都大于等于100g：



SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon;


SELECT t1.user_id,t1.date1,SUM(t1.low_carbon) SUM
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon
)t1 GROUP BY t1.user_id,t1.date1 ORDER BY t1.date1;


SELECT t2.user_id,t2.date1,t2.sum,
rank() over(PARTITION BY t2.user_id ORDER BY t2.date1) n
FROM (SELECT t1.user_id,t1.date1,SUM(t1.low_carbon) SUM
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon
)t1 GROUP BY t1.user_id,t1.date1 ORDER BY t1.date1)t2 WHERE t2.sum>100;

部分数据
+-------------+-------------+---------+----+
| t2.user_id  |  t2.date1   | t2.sum  | n  |
+-------------+-------------+---------+----+
| u_001       | 2017-01-02  | 270     | 1  |
| u_001       | 2017-01-06  | 135     | 2  |
| u_002       | 2017-01-02  | 220     | 1  |
| u_002       | 2017-01-03  | 110     | 2  |
| u_002       | 2017-01-04  | 150     | 3  |
| u_002       | 2017-01-05  | 101     | 4  |
| u_003       | 2017-01-02  | 160     | 1  |
| u_003       | 2017-01-03  | 160     | 2  |
| u_003       | 2017-01-05  | 120     | 3  |
| u_003       | 2017-01-07  | 120     | 4  |
| u_004       | 2017-01-01  | 110     | 1  |
| u_004       | 2017-01-03  | 120     | 2  |
| u_004       | 2017-01-06  | 120     | 3  |
| u_004       | 2017-01-07  | 130     | 4  |
| u_005       | 2017-01-02  | 130     | 1  |
| u_005       | 2017-01-03  | 180     | 2  |
| u_005       | 2017-01-04  | 190     | 3  |
| u_005       | 2017-01-06  | 280     | 4  |
| u_005       | 2017-01-07  | 160     | 5  |
| u_006       | 2017-01-02  | 180     | 1  |
| u_006       | 2017-01-03  | 220     | 2  |
| u_006       | 2017-01-07  | 290     | 3  |





SELECT t3.user_id,t3.date1,t3.sum,t3.n,
DATE_SUB(t3.date1,t3.n) date2
FROM (SELECT t2.user_id,t2.date1,t2.sum,
rank() over(PARTITION BY t2.user_id ORDER BY t2.date1) n
FROM (SELECT t1.user_id,t1.date1,SUM(t1.low_carbon) SUM
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon
)t1 GROUP BY t1.user_id,t1.date1 ORDER BY t1.date1)t2 WHERE t2.sum>100)t3;

部分数据
+-------------+-------------+---------+-------+-------------+
| t3.user_id  |  t3.date1   | t3.sum  | t3.n  |    date2    |
+-------------+-------------+---------+-------+-------------+
| u_001       | 2017-01-02  | 270     | 1     | 2017-01-01  |
| u_001       | 2017-01-06  | 135     | 2     | 2017-01-04  |
| u_002       | 2017-01-02  | 220     | 1     | 2017-01-01  |
| u_002       | 2017-01-03  | 110     | 2     | 2017-01-01  |
| u_002       | 2017-01-04  | 150     | 3     | 2017-01-01  |
| u_002       | 2017-01-05  | 101     | 4     | 2017-01-01  |
| u_003       | 2017-01-02  | 160     | 1     | 2017-01-01  |
| u_003       | 2017-01-03  | 160     | 2     | 2017-01-01  |
| u_003       | 2017-01-05  | 120     | 3     | 2017-01-02  |
| u_003       | 2017-01-07  | 120     | 4     | 2017-01-03  |
| u_004       | 2017-01-01  | 110     | 1     | 2016-12-31  |
| u_004       | 2017-01-03  | 120     | 2     | 2017-01-01  |
| u_004       | 2017-01-06  | 120     | 3     | 2017-01-03  |
| u_004       | 2017-01-07  | 130     | 4     | 2017-01-03  |
| u_005       | 2017-01-02  | 130     | 1     | 2017-01-01  |
| u_005       | 2017-01-03  | 180     | 2     | 2017-01-01  |
| u_005       | 2017-01-04  | 190     | 3     | 2017-01-01  |
| u_005       | 2017-01-06  | 280     | 4     | 2017-01-02  |
| u_005       | 2017-01-07  | 160     | 5     | 2017-01-02  |
| u_006       | 2017-01-02  | 180     | 1     | 2017-01-01  |
| u_006       | 2017-01-03  | 220     | 2     | 2017-01-01  |
| u_006       | 2017-01-07  | 290     | 3     | 2017-01-04  |
| u_007       | 2017-01-01  | 130     | 1     | 2016-12-31  |
| u_007       | 2017-01-02  | 360     | 2     | 2016-12-31  |



SELECT  t4.user_id,t4.date1,t4.sum,t4.date2,
COUNT(t4.date2) over(PARTITION BY (t4.user_id,t4.date2)) n1
FROM (SELECT t3.user_id,t3.date1,t3.sum,t3.n,
DATE_SUB(t3.date1,t3.n) date2
FROM (SELECT t2.user_id,t2.date1,t2.sum,
rank() over(PARTITION BY t2.user_id ORDER BY t2.date1) n
FROM (SELECT t1.user_id,t1.date1,SUM(t1.low_carbon) SUM
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon
)t1 GROUP BY t1.user_id,t1.date1 ORDER BY t1.date1)t2 WHERE t2.sum>100)t3)t4;

部分数据
+-------------+-------------+---------+-------------+-----+
| t4.user_id  |  t4.date1   | t4.sum  |  t4.date2   | n1  |
+-------------+-------------+---------+-------------+-----+
| u_001       | 2017-01-02  | 270     | 2017-01-01  | 1   |
| u_001       | 2017-01-06  | 135     | 2017-01-04  | 1   |
| u_002       | 2017-01-02  | 220     | 2017-01-01  | 4   |
| u_002       | 2017-01-03  | 110     | 2017-01-01  | 4   |
| u_002       | 2017-01-04  | 150     | 2017-01-01  | 4   |
| u_002       | 2017-01-05  | 101     | 2017-01-01  | 4   |
| u_003       | 2017-01-02  | 160     | 2017-01-01  | 2   |
| u_003       | 2017-01-03  | 160     | 2017-01-01  | 2   |
| u_003       | 2017-01-05  | 120     | 2017-01-02  | 1   |
| u_003       | 2017-01-07  | 120     | 2017-01-03  | 1   |
| u_004       | 2017-01-01  | 110     | 2016-12-31  | 1   |
| u_004       | 2017-01-03  | 120     | 2017-01-01  | 1   |
| u_004       | 2017-01-07  | 130     | 2017-01-03  | 2   |
| u_004       | 2017-01-06  | 120     | 2017-01-03  | 2   |
| u_005       | 2017-01-03  | 180     | 2017-01-01  | 3   |
| u_005       | 2017-01-02  | 130     | 2017-01-01  | 3   |
| u_005       | 2017-01-04  | 190     | 2017-01-01  | 3   |
| u_005       | 2017-01-06  | 280     | 2017-01-02  | 2   |
| u_005       | 2017-01-07  | 160     | 2017-01-02  | 2   |
| u_006       | 2017-01-03  | 220     | 2017-01-01  | 2   |
| u_006       | 2017-01-02  | 180     | 2017-01-01  | 2   |
| u_006       | 2017-01-07  | 290     | 2017-01-04  | 1   |


SELECT  t5.user_id,t5.date1,t5.sum,t5.date2,t5.n1
FROM (SELECT  t4.user_id,t4.date1,t4.sum,t4.date2,
COUNT(t4.date2) over(PARTITION BY (t4.user_id,t4.date2)) n1
FROM (SELECT t3.user_id,t3.date1,t3.sum,t3.n,
DATE_SUB(t3.date1,t3.n) date2
FROM (SELECT t2.user_id,t2.date1,t2.sum,
rank() over(PARTITION BY t2.user_id ORDER BY t2.date1) n
FROM (SELECT t1.user_id,t1.date1,SUM(t1.low_carbon) SUM
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon
)t1 GROUP BY t1.user_id,t1.date1 ORDER BY t1.date1)t2 WHERE t2.sum>100)t3)t4)t5 WHERE t5.n1>2;

+-------------+-------------+---------+-------------+--------+
| t5.user_id  |  t5.date1   | t5.sum  |  t5.date2   | t5.n1  |
+-------------+-------------+---------+-------------+--------+
| u_002       | 2017-01-02  | 220     | 2017-01-01  | 4      |
| u_002       | 2017-01-03  | 110     | 2017-01-01  | 4      |
| u_002       | 2017-01-04  | 150     | 2017-01-01  | 4      |
| u_002       | 2017-01-05  | 101     | 2017-01-01  | 4      |
| u_005       | 2017-01-03  | 180     | 2017-01-01  | 3      |
| u_005       | 2017-01-02  | 130     | 2017-01-01  | 3      |
| u_005       | 2017-01-04  | 190     | 2017-01-01  | 3      |
| u_008       | 2017-01-04  | 260     | 2017-01-01  | 4      |
| u_008       | 2017-01-06  | 160     | 2017-01-01  | 4      |
| u_008       | 2017-01-05  | 360     | 2017-01-01  | 4      |
| u_008       | 2017-01-07  | 120     | 2017-01-01  | 4      |
| u_009       | 2017-01-04  | 270     | 2017-01-01  | 3      |
| u_009       | 2017-01-02  | 140     | 2017-01-01  | 3      |
| u_009       | 2017-01-03  | 170     | 2017-01-01  | 3      |
| u_010       | 2017-01-04  | 170     | 2017-01-02  | 4      |
| u_010       | 2017-01-05  | 180     | 2017-01-02  | 4      |
| u_010       | 2017-01-06  | 190     | 2017-01-02  | 4      |
| u_010       | 2017-01-07  | 180     | 2017-01-02  | 4      |
| u_011       | 2017-01-02  | 200     | 2016-12-31  | 3      |
| u_011       | 2017-01-01  | 110     | 2016-12-31  | 3      |
| u_011       | 2017-01-03  | 120     | 2016-12-31  | 3      |
| u_013       | 2017-01-02  | 200     | 2017-01-01  | 4      |
| u_013       | 2017-01-03  | 150     | 2017-01-01  | 4      |
| u_013       | 2017-01-04  | 550     | 2017-01-01  | 4      |
| u_013       | 2017-01-05  | 350     | 2017-01-01  | 4      |
| u_014       | 2017-01-05  | 250     | 2017-01-02  | 3      |
| u_014       | 2017-01-06  | 120     | 2017-01-02  | 3      |
| u_014       | 2017-01-07  | 290     | 2017-01-02  | 3      |
+-------------+-------------+---------+-------------+--------+
28 ROWS selected (38.562 seconds)


SELECT t6.user_id,t6.date1,t7.low_carbon
FROM (SELECT  t5.user_id,t5.date1,t5.sum,t5.date2,t5.n1
FROM (SELECT  t4.user_id,t4.date1,t4.sum,t4.date2,
COUNT(t4.date2) over(PARTITION BY (t4.user_id,t4.date2)) n1
FROM (SELECT t3.user_id,t3.date1,t3.sum,t3.n,
DATE_SUB(t3.date1,t3.n) date2
FROM (SELECT t2.user_id,t2.date1,t2.sum,
rank() over(PARTITION BY t2.user_id ORDER BY t2.date1) n
FROM (SELECT t1.user_id,t1.date1,SUM(t1.low_carbon) SUM
FROM (SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon
)t1 GROUP BY t1.user_id,t1.date1 ORDER BY t1.date1)t2 WHERE t2.sum>100)t3)t4)t5 WHERE t5.n1>2)t6 JOIN
(SELECT user_id,DATE_FORMAT(regexp_replace(data_dt,'/','-'),'yyyy-MM-dd') date1,low_carbon FROM user_low_carbon)t7
ON t6.user_id=t7.user_id AND t6.date1=t7.date1;



+-------------+-------------+----------------+
| t6.user_id  |  t6.date1   | t7.low_carbon  |
+-------------+-------------+----------------+
| u_002       | 2017-01-02  | 150            |
| u_002       | 2017-01-02  | 70             |
| u_002       | 2017-01-03  | 30             |
| u_002       | 2017-01-03  | 80             |
| u_002       | 2017-01-04  | 150            |
| u_002       | 2017-01-05  | 101            |
| u_005       | 2017-01-02  | 80             |
| u_005       | 2017-01-02  | 50             |
| u_005       | 2017-01-03  | 180            |
| u_005       | 2017-01-04  | 180            |
| u_005       | 2017-01-04  | 10             |
| u_008       | 2017-01-04  | 260            |
| u_008       | 2017-01-05  | 360            |
| u_008       | 2017-01-06  | 160            |
| u_008       | 2017-01-07  | 60             |
| u_008       | 2017-01-07  | 60             |
| u_009       | 2017-01-02  | 70             |
| u_009       | 2017-01-02  | 70             |
| u_009       | 2017-01-03  | 170            |
| u_009       | 2017-01-04  | 270            |
| u_010       | 2017-01-04  | 90             |
| u_010       | 2017-01-04  | 80             |
| u_010       | 2017-01-05  | 90             |
| u_010       | 2017-01-05  | 90             |
| u_010       | 2017-01-06  | 190            |
| u_010       | 2017-01-07  | 90             |
| u_010       | 2017-01-07  | 90             |
| u_011       | 2017-01-01  | 110            |
| u_011       | 2017-01-02  | 100            |
| u_011       | 2017-01-02  | 100            |
| u_011       | 2017-01-03  | 120            |
| u_013       | 2017-01-02  | 50             |
| u_013       | 2017-01-02  | 150            |
| u_013       | 2017-01-03  | 150            |
| u_013       | 2017-01-04  | 550            |
| u_013       | 2017-01-05  | 350            |
| u_014       | 2017-01-05  | 250            |
| u_014       | 2017-01-06  | 120            |
| u_014       | 2017-01-07  | 270            |
| u_014       | 2017-01-07  | 20             |
+-------------+-------------+----------------+
40 ROWS selected (54.874 seconds)











