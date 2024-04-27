from django.db import models
from datetime import date
from GestionUsuarios.models import Hotel, Usuario

# Aquí se encuentra el código de las clases TipoHabitacion, Habitacion, Reserva y ReservaPorHabitacion

class Imagen(models.Model):
    imagen = models.ImageField(upload_to='img/habitaciones')

class Servicio(models.Model):
    servicioId = models.AutoField(primary_key=True)
    servicio = models.CharField(max_length=256)
    class Meta:
        db_table = "Servicio"
        verbose_name = "Servicios que contienen las habitaciones de un hotel"
        verbose_name_plural = "Servicios"
    def __unicode__(self):
        return self.servicioId
    def __str__(self) -> str:
        return self.servicio

class Habitacion(models.Model):
    habitacionId = models.AutoField(primary_key=True)
    numero = models.PositiveSmallIntegerField(blank=False, unique=True)
    piso = models.PositiveSmallIntegerField(blank=False)
    estado = models.CharField(max_length=50, default="Disponible", blank=False)
    precio = models.IntegerField(blank=False)
    hotelId = models.ForeignKey(Hotel, to_field='hotelId', on_delete=models.CASCADE)
    tipoHabitacion = models.CharField(max_length=100, blank=False)
    imagenes = models.ManyToManyField(Imagen)
    class Meta:
        db_table = "Habitacion"
        verbose_name = "Habitaciones de un hotel"
        verbose_name_plural = "Habitaciones"
    def __unicode__(self):
        return f"Habitacion {self.numero}"
    def __str__(self) -> str:
        return f"Habitacion {self.numero}"

class ServicioPorHabitacion(models.Model):
    servicioPorHabitacionId = models.AutoField(primary_key=True)
    servicioId = models.ForeignKey(Servicio, to_field='servicioId', on_delete=models.CASCADE)
    habitacionId = models.ForeignKey(Habitacion, to_field='habitacionId', on_delete=models.CASCADE)
    class Meta:
        db_table = "ServicioPorHabitacion"
        verbose_name = "Servicio de cada habitación"
        verbose_name_plural = "ServiciosPorHabitacion"
    def __unicode__(self):
        return self.servicioPorHabitacionId
    def __str__(self) -> str:
        return f"Servicio {self.servicioId} de la habitación {self.habitacionId}"
    
class Reserva(models.Model):
    reservaId = models.AutoField(primary_key=True)
    habitacionId = models.ForeignKey(Habitacion, to_field='habitacionId', on_delete=models.CASCADE)
    fechaReserva = models.DateField(default=date.today, blank=False)
    fechaIngreso = models.DateField(blank=False)
    fechaEgreso = models.DateField(blank=False)
    usuarioId = models.ForeignKey(Usuario, to_field='usuarioId', on_delete=models.CASCADE)
    class Meta:
        db_table = "Reserva"
        verbose_name = "Reservas de habitacinoes en un hotel"
        verbose_name_plural = "Reservas"
    def __unicode__(self):
        return f"Reserva {self.reservaId}"
    def __str__(self):
        return f"Reserva del cliente {self.usuarioId} el {self.fechaReserva} para la habitacion {self.habitacionId}"

