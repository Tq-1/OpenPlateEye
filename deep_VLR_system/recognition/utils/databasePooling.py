import pymysql
from dbutils.pooled_db import PooledDB
import threading
def get_db_conn():
    return PooledDB(
        creator=pymysql,  # 指定使用pymysql库
        host="112.124.65.81",
        user='root',
        password='Ty20020303',
        database='ry-vue',
        autocommit=True,
        charset='utf8',
        maxconnections=5
    )

class MySQLThread(threading.Thread):
    def __init__(self, db_pool):
        threading.Thread.__init__(self)
        self.query = None
        self.type = 0 
        self.result = None
        self.db_pool = db_pool
    #0表示查询，1表示插入，2表示删除
    def set_query(self, query, type = 0):
        self.query = query
        self.type = type

    def run(self):
        # 从连接池中获取连接
        connection = self.db_pool.connection()

        # 使用连接进行查询或插入
        try:
            with connection.cursor() as cursor:
                if self.type == 1:
                    try:
                        cursor.execute(self.query)
                        connection.commit()
                        self.result = "插入成功!"
                    except pymysql.Error as e:
                        connection.rollback()  # 发生异常时执行回滚
                        self.result = "插入失败 " + str(e)
                elif self.type == 0:
                    cursor.execute(self.query)
                    self.result = cursor.fetchall()
                elif self.type == 2:
                    try:
                        cursor.execute(self.query)
                        connection.commit()
                        self.result = "删除失败 " + str(e)
                    except pymysql.Error as e:
                        connection.rollback()
                #更新
                elif self.type == 3:
                    try:
                        cursor.execute(self.query)
                        connection.commit()
                        self.result = "更新成功!"
                    except pymysql.Error as e:
                        connection.rollback()
                        self.result = "更新失败 " + str(e)
        finally:
            # 将连接归还到连接池
            connection.close()

    def get_result(self):
        return self.result

# 创建线程对象
# thread = MySQLThread()

# # 设置查询语句并执行
# query1 = "SELECT * FROM table1"
# thread.set_query(query1)
# thread.start()
# thread.join()
# print("Query 1 result:", thread.get_result())

# # 设置另一个查询语句并执行
# query2 = "SELECT * FROM table2"
# thread.set_query(query2)
# thread.start()
# thread.join()
# print("Query 2 result:", thread.get_result())

# # 设置插入语句并执行
# insert_query = "INSERT INTO table1 (column1, column2) VALUES ('value1', 'value2')"
# thread.set_query(insert_query, is_insert=True)
# thread.start()
# thread.join()
# print("Insert result:", thread.get_result())