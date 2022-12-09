## 📄 API仕様書

### ログイン(emailでの実装がまだ😣)

- resにaccess_token, refresh_token

```json
POST http://localhost:8080/api/login
Content-Type: application/x-www-form-urlencoded

{
	"email": "aaa@gmail.com",
	"password": "12345"
}
```

### 新規登録

- resにaccess_token, refresh_token

```json
POST http://localhost:8080/api/register
Content-Type: application/json

{
  "username": "alice",
  "password": "12345",
  "email": "aaa@gmail.com",
  "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
  ]
}
```

### リフレッシュトークン

- resに新しいaccess_token, refresh_token

```json
GET http://localhost:8080/api/token/refresh
Authorization: Bearer {refresh_token}
```

### ユーザー1人の情報

```json
GET http://localhost:8080/api/user/{id}
Authorization: Bearer {access_token}
```

### 子ども追加

- ROLE_ADMINのみAPI叩くことができる

```json
POST http://localhost:8080/api/user/save
Content-Type: application/json
Authorization: Bearer {access_token}
{
  "username": "new user",
  "password": "12345",
  "roles": [
      {
        "id": 1
      }
  ]
}
```

### ユーザー更新

```json
PATCH http://localhost:8080/api/user/{id}
Content-Type: application/json
Authorization: Bearer {access_token}
{
	"username": "alice",
	"password": "12345",
	"email": "aaa@gmail.com",
	"have_points": 100,
	"roles": [
		{
			"id": 1
		}
	]
}
```

### 商品表示

```json
GET http://localhost:8080/api/product
Authorization: Bearer {access_token}
```

### 商品追加
```json
POST http://localhost:8080/api/product/save
Content-Type: application/json
Authorization: Bearer {access_token}
{
  "product_name": "商品名",
  "necessary_points": 100,
  "user": {
      "id": 1
      }
}
```