from email.message import EmailMessage
from dotenv import load_dotenv
import ssl
import smtplib
import os

def enviar_correo_hotel(mensaje, nombreRemitente, correoRemitente, correoDestinatario):
    em = EmailMessage()
    em["from"] = correoDestinatario # Se envía correo a la misma empresa con la info de la consulta
    em["to"] = correoDestinatario 
    em["subject"] = "Consulta de " + nombreRemitente
    body = f"""
    De: {correoRemitente}
    Mensaje: {mensaje}
    """
    em.set_content(body)
    
    context = ssl.create_default_context()
    load_dotenv()
    password = os.getenv("PASSWORD_EMAIL")
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context= context) as smpt:
        smpt.login(correoDestinatario, password)
        smpt.sendmail(correoDestinatario, correoDestinatario, em.as_string())

def enviar_correo_cliente(mensaje, nombreRemitente, correoRemitente, correoDestinatario):
    em = EmailMessage()
    em["from"] = correoDestinatario # Se envía correo a la misma empresa con la info de la consulta
    em["to"] = correoRemitente 
    em["subject"] = "Consulta enviada a Hotel California"
    body = f"""
    Muchas gracias por comunicarse con el equipo de Hotel California.
    Ud. ha enviado el siguiente mensaje:
    {mensaje}

    En breve recibirá una respuesta.
    Saluda atte.
    Hotel California
    """
    em.set_content(body)
    
    context = ssl.create_default_context()
    load_dotenv()
    password = os.getenv("PASSWORD_EMAIL")
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context= context) as smpt:
        smpt.login(correoDestinatario, password)
        smpt.sendmail(correoDestinatario, correoRemitente, em.as_string())