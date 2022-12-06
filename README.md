## 📄API仕様書
---
BASE_URL=http://localhost:8080/api

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
