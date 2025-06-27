import socket
import datetime
import random

def start_listener():
    """
    Starts a basic honeypot server on a randomly selected port.
    Logs incoming TCP connections and simulates a basic FTP service banner.
    """
    port = random.randint(1025, 65535)  # Select a random non-reserved port

    try:
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.bind(("0.0.0.0", port))  # Bind to all available network interfaces
        server.listen(5)  # Listen for incoming connections with a small backlog
        print(f"[+] Honeypot listening on random port {port}...")

        while True:
            client, address = server.accept()
            timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

            try:
                data = client.recv(1024).decode(errors="ignore").strip()
            except:
                data = "<no readable data>"

            print(f"[!] {timestamp} | Connection from {address[0]} | Data: {data}")

            # Log connection details to file for analysis
            with open("honeypot_log.txt", "a") as log:
                log.write(f"{timestamp} - {address[0]} - Port {port} - Data: {data}\n")

            # Simulate a basic FTP banner response
            client.sendall(b"220 Welcome to the FTP Server\r\n")
            client.close()

    except Exception as e:
        print(f"[!] Error: {e}")

if __name__ == "__main__":
    start_listener()

