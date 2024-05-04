from email.message import EmailMessage
from dotenv import load_dotenv
import ssl
import smtplib
import os

def enviar_correo(mensaje, nombreRemitente, correoRemitente, correoDestinatario):
    em = EmailMessage()
    em["from"] = correoDestinatario
    em["to"] = correoDestinatario
    em["subject"] = "Consulta de " + nombreRemitente
    body = """
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
