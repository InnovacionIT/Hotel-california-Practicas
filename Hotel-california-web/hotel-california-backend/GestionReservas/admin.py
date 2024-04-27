from django.contrib import admin
from django import forms
from django.contrib.admin.widgets import FilteredSelectMultiple
from .models import Habitacion, Servicio, ServicioPorHabitacion, Reserva, Imagen

class ImagenAdmin(admin.ModelAdmin):
    pass

class ServicioAdmin(admin.ModelAdmin):
    list_display = ["servicio"]

class ServicioPorHabitacionAdmin(admin.ModelAdmin):
    list_display = ("servicioId", "habitacionId")

class HabitacionAdminForm(forms.ModelForm):
    class Meta:
        model = Habitacion
        fields = '__all__'
        widgets = {
            'imagenes': FilteredSelectMultiple('Imágenes', False),
        }
class HabitacionAdmin(admin.ModelAdmin):
    form = HabitacionAdminForm
    
class ReservaAdmin(admin.ModelAdmin):
    list_display = ("habitacionId", "fechaReserva", "fechaIngreso", "fechaEgreso", "usuarioId")

admin.site.register(Imagen, ImagenAdmin)
admin.site.register(Servicio, ServicioAdmin)
admin.site.register(ServicioPorHabitacion, ServicioPorHabitacionAdmin)
admin.site.register(Habitacion, HabitacionAdmin)
admin.site.register(Reserva, ReservaAdmin)
