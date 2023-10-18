import socket_class
import funciones_rsa

bob_private_key = funciones_rsa.cargar_RSAKey_Privada("BobPrivateKey.txt","password")

alice_public_key = funciones_rsa.cargar_RSAKey_Publica("AlicePublicKey.txt")

socketserver = socket_class.SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socketserver.__init__('127.0.0.1', 5551)
socketserver.escuchar()
K1_cypher = socketserver.recibir()
socketserver.escuchar()
K1_signed = socketserver.recibir()
socketserver.cerrar()

print(funciones_rsa.descifrarRSA_OAEP(K1_cypher,bob_private_key))

print(funciones_rsa.comprobarRSA_PSS(K1_cypher,K1_signed,alice_public_key))
