## 新規登録
```
POST http://localhost:8080/api/register

{
  "username": "john",
  "email": "john@gmail.com",
  "password": "iamjohn",
  "role": "CHILD"
}
@return
id, access_token
```

## ユーザー更新
```
PUT http://localhost:8080/api/user/id HTTP/1.1

{
  "username": "username",
  "email": "email",
  "password": "password",
  "newPassword": "newPassword"
}

@return
msg
```

## ユーザー退会
```
DELETE http://localhost:8080/api/user/id

@return
msg
```