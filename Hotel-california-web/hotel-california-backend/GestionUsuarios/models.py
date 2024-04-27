from django.db import models
from django.core.validators import RegexValidator
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager, PermissionsMixin

class UsuarioManager(BaseUserManager):
    def create_user(self, usuario, password=None, **extra_fields):
        if not usuario:
            raise ValueError('El campo de correo electrónico es obligatorio')

        usuario = self.normalize_email(usuario)
        user = self.model(usuario=usuario, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, usuario, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)        
        return self.create_user(usuario, password, **extra_fields)

class Usuario(AbstractBaseUser, PermissionsMixin):
    usuarioId = models.AutoField(primary_key=True)
    imagen = models.ImageField(upload_to='img/perfil', blank=True)
    nombre = models.CharField(max_length=100, blank=False)
    apellido = models.CharField(max_length=100, blank=False)
    usuario = models.EmailField(max_length=254, unique=True)
    fechaDeNacimiento = models.DateField(blank=False)
    telefono = models.PositiveBigIntegerField()
    ciudad = models.CharField(max_length=256)
    # token = models.CharField(max_length=256)

    is_staff = models.BooleanField(default=False)
    is_active = models.BooleanField(default=True)

    objects = UsuarioManager()

    USERNAME_FIELD = 'usuario'
    REQUIRED_FIELDS = ['nombre', 'apellido', 'fechaDeNacimiento', 'telefono', 'ciudad', 'password']

    class Meta:
        db_table = "Cliente"
        verbose_name = "Todos los clientes registrados"
        verbose_name_plural = "Clientes"

    def __str__(self):
        return f"Cliente {self.apellido}, {self.nombre}"
          
class Hotel(models.Model):
    hotelId = models.AutoField(primary_key=True)
    razonSocial= models.CharField(max_length=150, blank=False)
    cuil = models.CharField(max_length=13,
                            blank=False, 
                            unique=True, 
                            validators=[RegexValidator(
                                regex="^(20|2[3-7]|30|3[3-4])(\d{8})(\d)$", 
                                message="CUIL inválido")])
    domicilio = models.CharField(max_length=150, blank=False)
    localidad = models.CharField(max_length=100,blank=False)
    provincia = models.CharField(max_length=100, blank=False)
    cp = models.PositiveSmallIntegerField(blank=False)
    telefono = models.PositiveBigIntegerField(blank=False)
    categoria = models.CharField(max_length=50, blank=False)
    email = models.EmailField(max_length=254, blank=False)
    class Meta:
        db_table = "Hotel"
        verbose_name = "Todos los hoteles disponibles"
        verbose_name_plural = "Hoteles"
    def __unicode__(self):
        return self.razonSocial
    def __str__(self) -> str:
        return self.razonSocial

class Empleado(models.Model):
    empleadoId = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=100, blank=False)
    apellido = models.CharField(max_length=100, blank=False)
    usuario = models.EmailField(max_length=254, blank=False, unique=True)
    password = models.CharField(max_length=150, blank=False)
    domicilio = models.CharField(max_length=150, blank=False)
    localidad = models.CharField(max_length=100, blank=False)
    provincia = models.CharField(max_length=100, blank=False)
    cp = models.PositiveSmallIntegerField(blank=False)
    telefono = models.PositiveBigIntegerField(blank=False)
    hotelId = models.ForeignKey(Hotel, to_field="hotelId", on_delete=models.CASCADE)
    rol = models.CharField(max_length=70, blank=False)
    class Meta:
        db_table = "Empleado"
        verbose_name = "Todos los empleados registrados en el hotel"
        verbose_name_plural = "Empleados"
    def __unicode__(self):
        return self.usuario
    def __str__(self) -> str:
        return f"Empleado {self.apellido}, {self.nombre}"