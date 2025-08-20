# Get 方法 查詢「全部」
http://localhost:8080/api/students

# Get 方法 查詢「指定id」
http://localhost:8080/api/students/1

# Delete 方法 刪除id
http://localhost:8080/api/students/1

# Post 方法 新增
http://localhost:8080/api/students
選擇 Body
選擇 raw
選擇 JSON
* 程式有改的話需要重新啟動 
```json
{
"firstName": "Emma",
"lastName": "Lin",
"email": "Emma@yahoo.com.tw",
"birthday": "1998-05-11"    
}
```


# Put 方法 更新
http://localhost:8080/api/students/1
選擇 Body
選擇 raw
選擇 JSON
* 程式有改的話需要重新啟動
```json
{
"studentId": 1,
"firstName": "Tom",
"lastName": "Down",
"email": "tom@gmail.com",
"birthday": "2000-02-02"
}
```
