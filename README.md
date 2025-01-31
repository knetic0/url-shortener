# URL - Shortener Application

URL Shortener, uzun URL'leri kısaltan bir web uygulamasıdır. Bu proje **Java (Spring Boot)** ve **ReactJS** kullanılarak geliştirilmiştir.

## Özellikler
- Uzun URL'leri kısaltma
- Kısaltılmış URL'leri orijinal adreslerine yönlendirme
- URL geçmişi görüntüleme
- Kullanıcı dostu arayüz
- RESTful API entegrasyonu

## Kullanılan Teknolojiler

### Backend (Java - Spring Boot)
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Spring Security (Opsiyonel)
- Redis Caching

### Frontend (ReactJS)
- React.js
- Axios
- React Router
- Prime React Framework

## Kurulum

### Backend Kurulumu
1. **Java 17+** ve **Maven** yüklü olduğundan emin olun.
2. Projeyi klonlayın:
   ```bash
   git clone https://github.com/knetic0/url-shortener
   cd url-shortener/urlshortener
   ```
3. Bağımlılıkları yükleyin ve uygulamayı çalıştırın:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. API **http://localhost:8080** adresinde çalışacaktır.

### Frontend Kurulumu
1. **Node.js 18+** yüklü olduğundan emin olun.
2. Frontend dizinine gidin:
   ```bash
   cd ../frontend
   ```
3. Bağımlılıkları yükleyin ve projeyi çalıştırın:
   ```bash
   npm install
   npm start
   ```
4. Uygulama **http://localhost:3000** adresinde çalışacaktır.

## API Kullanımı
**Kısa URL oluşturma:**
```bash
POST /api/shorten
Body: { "originalUrl": "https://example.com" }
Response: { "shortUrl": "http://localhost:8080/abc123", "originalUrl": "https://example.com" }
```

**Kısa URL yönlendirme:**
```bash
GET /{shortUrl}
```

## Katkıda Bulunma
Projeye katkıda bulunmak isterseniz, fork alıp pull request gönderebilirsiniz.

---
- **Geliştirici:** Mehmet SOLAK 
- **GitHub:** [GitHub Profilim](https://github.com/knetic0) 