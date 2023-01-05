## 新規登録
```
POST http://localhost:8080/api/register

{
  username: "john",
  email: "john@gmail.com",
  password: "iamjohn",
  role: "CHILD" (or PARENT)
}
@return
id, access_token
```

## ユーザー1人の情報
GET http://localhost:8080/api/user/id

@return
password以外

## ユーザー更新
```
PUT http://localhost:8080/api/user/id

{
  username: "username",
  email: "email",
  password: "password",
  newPassword: "newPassword"
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