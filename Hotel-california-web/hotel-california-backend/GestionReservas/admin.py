from django.contrib import admin
from django import forms
from django.contrib.admin.widgets import FilteredSelectMultiple
from django.contrib import messages
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

    # def has_add_permission(self, request):
    #     # Limitar la creación de habitaciones a un máximo de 5
    #     if Habitacion.objects.count() >= 5:
    #         return False
    #     return True
    
class ReservaAdmin(admin.ModelAdmin):
    list_display = ("habitacionId", "fechaReserva", "fechaIngreso", "fechaEgreso", "usuarioId")

    def save_model(self, request, obj, form, change):
        # Verificar si hay reservas existentes para esta habitación y período de tiempo
        reservas_existente = Reserva.objects.filter(
            habitacionId=obj.habitacionId,
            fechaIngreso__lte=obj.fechaEgreso,
            fechaEgreso__gte=obj.fechaIngreso
        ).exclude(
            pk=obj.pk  # Utilizamos pk en lugar de id
        )

        if reservas_existente.exists():
            messages.error(request, 'Ya existe una reserva para esta habitación en este período de tiempo')
            return

        super().save_model(request, obj, form, change)


admin.site.register(Imagen, ImagenAdmin)
admin.site.register(Servicio, ServicioAdmin)
admin.site.register(ServicioPorHabitacion, ServicioPorHabitacionAdmin)
admin.site.register(Habitacion, HabitacionAdmin)
admin.site.register(Reserva, ReservaAdmin)
