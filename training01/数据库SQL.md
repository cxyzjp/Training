#### 1、查询“111”课程比“112”课程成绩高的所有学生的学号 
	SELECT a.s# 
	FROM (SELECT t1.s#,t1.score FROM sc t1 WHERE t1.c# = 111) a
	INNER JOIN (SELECT t2.s#,t2.score FROM sc t2 WHERE t2.c# = 112) b
	  ON a.s# = b.s#
	WHERE a.score > b.score;
#### 2、查询平均成绩大于60分的同学的学号和平均成绩

```
fsfs
fsfsf
fsfsfs
```

	fsfs
	gsgs	

```
ggsgs
public
```