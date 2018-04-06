#mysql的备份与还原
    备份
        mysqldump -u用户名 -p密码 数据库 > 路径：xxx.sql 
        mysqldump -umybatis -p123456 mybatis > D:/mybatis.sql
    还原
        1.登录到要导入的数据库
            1.1 mysql -umybatis -p123456
            1.2 use mybatis
        2.导入数据
            source xxx.sql
            source mybatis.sql