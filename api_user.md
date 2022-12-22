## 新規登録
```
POST http://localhost:8080/api/register
{
  username: "username",
  email: "email",
  password: "password",
  role: "role" 1なら子ども 2なら親
}
@return
body id
header access_token
```

## ユーザー情報変更