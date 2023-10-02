from Crypto.Random import get_random_bytes
from Crypto.Cipher import DES, AES
from Crypto.Util.Padding import pad,unpad
from Crypto.Util import Counter



def  Ecb_mode(plaintext):
    key = get_random_bytes(16) #Bits para AES,ECB
    BLOCK_SIZE = 16
    print("----------------MODO ECB----------------")

    #Creacion cifrado
    cypher = AES.new(key,AES.MODE_ECB)

    #Ciframos y creacion padding
    cypher_text = cypher.encrypt(pad(plaintext,BLOCK_SIZE))

    print(cypher_text)

    # Creamos Desciframos
    decipher_AES = AES.new(key, AES.MODE_ECB)

    #Desciframos

    new_plain_text = unpad(decipher_AES.decrypt(cypher_text),BLOCK_SIZE).decode("utf-8","ignore")

    print(new_plain_text)

def Ctr_mode(plaintext):   #Preguntar profesor error en el padding
    key = get_random_bytes(16)
    nonce = get_random_bytes(8) #Modo CTR usa nonce en vez de IV
    BLOCK_SIZE = 16
    
    print("----------------MODO CTR----------------")
    
    #creamos cifrado
    cypher = AES.new(key,AES.MODE_CTR,nonce)

    #aplicamos cifrado
    cypher_text = cypher.encrypt(pad(plaintext,BLOCK_SIZE))

    print(cypher_text)

    #Creamos Descifrado
    decipher_CTR = AES.new(key,AES.MODE_CTR,nonce)

    #Desfiframos
    new_plain_text = unpad(decipher_CTR.decrypt(cypher_text),BLOCK_SIZE).decode("utf-8","ignore")

    print(new_plain_text)

def Ofb_mode(plaintext):
    key = get_random_bytes(16)
    IV = get_random_bytes(16) #Utilizamos IV, mismo tamaño que el Block_Size 
    BLOCK_SIZE = 16

    print("----------------MODO OFB----------------")

    #creamos cifrado
    cypher = AES.new(key,AES.MODE_OFB,IV)

    #ciframos
    cypher_text = cypher.encrypt(pad(plaintext,BLOCK_SIZE))

    print(cypher_text)

    #creamos descifrado
    decipher_OFb = AES.new(key,AES.MODE_OFB,IV)

    #Desxiframos

    new_plain_text = unpad(decipher_OFb.decrypt(cypher_text),BLOCK_SIZE).decode("utf-8","ignore")

    print(new_plain_text)

def Cfb_mode(plaintext):
    key = get_random_bytes(16)
    IV = get_random_bytes(16) #Utilizamos IV, mismo tamaño que el Block_Size 
    BLOCK_SIZE = 16

    print("----------------MODO CFB----------------")

    #creamos cifrado
    cypher = AES.new(key,AES.MODE_CFB,IV)

    #ciframos
    cypher_text = cypher.encrypt(pad(plaintext,BLOCK_SIZE))

    print(cypher_text)

    #creamos descifrado
    decipher_OFb = AES.new(key,AES.MODE_CFB,IV)

    #Desxiframos

    new_plain_text = unpad(decipher_OFb.decrypt(cypher_text),BLOCK_SIZE).decode("utf-8","ignore")

    print(new_plain_text)

def Gcm_mode(plaintext):
    key = get_random_bytes(16)
    nonce = get_random_bytes(16)  #PREGUNTAR POR QUE EN LA DOCUMENTACION SALE NONCE 8 Y EN LA PRACTICA SALE IV VON 16
    BLOCK_SIZE = 16
    mac_len = 16

    print("----------------MODO GCM----------------")

    #creamos cifrado
    cypher = AES.new(key,AES.MODE_GCM,nonce,mac_len)

    #ciframos
    cypher_text,mac_cifrado = cypher.encrypt_and_digest(pad(plaintext,BLOCK_SIZE))

    print(cypher_text)

    try:
        #creamos descifrado
        decipher_OFb = AES.new(key,AES.MODE_GCM,nonce)

        #Desxiframos

        new_plain_text = unpad(decipher_OFb.decrypt_and_verify(cypher_text,mac_cifrado),BLOCK_SIZE).decode("utf-8","ignore")

        print(new_plain_text)
    except (ValueError,KeyError) as e:
        print("ERROR")

plaintext = "Hola Amigos de Seguridad".encode("utf-8")
print("Hola Amigos de Seguridad")
Ecb_mode(plaintext)
#Ctr_mode(plaintext)
Ofb_mode(plaintext)
Cfb_mode(plaintext)
#Gcm_mode(plaintext)