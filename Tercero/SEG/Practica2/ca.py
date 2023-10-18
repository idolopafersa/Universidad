import funciones_aes
import funciones_rsa


alice_public_key = funciones_rsa.crear_RSAKey()
alice_private_key = funciones_rsa.crear_RSAKey()

bob_private_key = funciones_rsa.crear_RSAKey()
bob_public_key = funciones_rsa.crear_RSAKey()


with open("AlicePublicKey.txt","wb") as file:
    file.write(alice_public_key.export_key())


with open("AlicePrivateKey.txt","wb") as file:
    file.write(alice_private_key.export_key(passphrase="password"))


with open("BobPublicKey.txt","wb") as file:
    file.write(bob_public_key.export_key())


with open("BobPrivateKey.txt","wb") as file:
    file.write(bob_private_key.export_key(passphrase="password"))        