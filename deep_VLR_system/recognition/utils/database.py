
import pymysql

# 得到数据库连接
def get_db_conn():
    # 打开数据库连接
    try:
        #这里可以填入自己的数据库信息
        db = pymysql.connect(host="112.124.65.81", user="root", password="Ty20020303", database="ry-vue", charset="utf8", autocommit=True)
        return db
    except:
        print('something wrong!')

# 获得游标对象
def get_cursor(db):
    cursor = db.cursor()
    return cursor
# 插入数据
def insert_data(conn, cursor, sql):
    try:
        # 执行sql语句
        cursor.execute(sql)
        # 提交到数据库执行
        conn.commit()
        print('insert success!')
    except pymysql.Error as e:
        # 如果发生错误则回滚
        conn.rollback()
        print(e.args[0], e.args[1])
        print('insert failed!')
# 关闭数据库连接
def close_db(conn):
    conn.close()
    
print(get_db_conn())
