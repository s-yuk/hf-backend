## 📄API仕様書
---
BASE_URL=http://localhost:8080/api

### 新規登録
レスポンスのボディにaccess_tokenとrefresh_tokenが返ってくる
```
POST /register HTTP/1.1
Content-Type: application/x-www-form-urlencoded

{
  "username": "alice",
  "password": "1234",
  "email": "aaa@gmail.com",
  "have_points": "100",
  "roles": [
      {
        "id": 1
      }
  ]
}
```

### ログイン
レスポンスのボディにaccess_tokenとrefresh_tokenが返ってくる
```
POST /login HTTP/1.1
Content-Type: application/x-www-form-urlencoded

{
  "username": "john",
  "password": "1234"
}
```

### リフレッシュトークン発行
レスポンスのボディにaccess_tokenとrefresh_tokenが返ってくる
```
GET /token/refresh HTTP/1.1
Authorization: Bearer {refresh_token}
```
