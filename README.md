# AppSec Vulnerable App

A deliberately vulnerable Spring Boot app + Python security scanner.

## Vulnerabilities
- SQLi on /login
- XSS on /hello  
- IDOR on /user/{id}

## Run the app
```
./mvnw spring-boot:run
```

## Run the scanner
```
python3 scanner.py
```
