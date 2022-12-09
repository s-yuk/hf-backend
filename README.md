## 📄API仕様書

### ログイン(emailでの実装できてない)
- resにaccess_tokenとrefresh_tokenが返ってくる
```
POST http://localhost:8080/api/login HTTP/1.1
Content-Type: application/x-www-form-urlencoded

{
  "email": "aaa@gmail.com",
  "password": "12345"
}
```
### 新規登録
- resにaccess_tokenとrefresh_tokenが返ってくる
```
POST http://localhost:8080/api/register HTTP/1.1
Content-Type: application/json

{
  "username": "alice",
  "password": "12345",
  "email": "aaa@gmail.com",
  "roles": [
      {
        "id": 2,
        "name": "ROLE_ADMIN"
      }
  ]
}
```
### リフレッシュトークン発行
resに新しいaccess_tokenとrefresh_tokenが返ってくる
```
GET http://localhost:8080/api/token/refresh HTTP/1.1
Authorization: Bearer {refresh_token}
```
### 商品一覧
- resに商品一覧
```
GET http://localhost:8080/api/product HTTP/1.1
Authorization: Bearer {access_token}
```
### 商品追加
```
POST http://localhost:8080/api/product/save HTTP/1.1
Content-Type: application/json

{
  "product_name": "商品",
  "necessary_points": "100"
}
```
### ユーザー追加
```
POST http://localhost:8080/api/user/save HTTP/1.1
Content-Type: application/json

{
  "username": "new user",
  "password": "12345",
  "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
  ]
}
```