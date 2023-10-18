import socket_class
import funciones_rsa

alice_private_key = funciones_rsa.cargar_RSAKey_Privada("AlicePrivateKey.txt","password")

bob_public_key = funciones_rsa.cargar_RSAKey_Publica("BobPublicKey.txt")

K1 = bytes([1])

K1_cypher = funciones_rsa.cifrarRSA_OAEP(K1,bob_public_key)
K1_signed = funciones_rsa.firmarRSA_PSS(K1,alice_private_key)

socketserver = socket_class.SOCKET_SIMPLE_TCP('127.0.0.2', 5551)
socketserver.__init__('127.0.0.2', 5551)
socketserver.conectar()
socketserver.enviar(K1_cypher)
socketserver.enviar(K1_signed)
socketserver.cerrar()
