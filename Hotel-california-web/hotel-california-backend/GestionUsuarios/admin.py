from django.contrib import admin
from .models import Hotel, Empleado, Usuario
from django.contrib.auth.admin import UserAdmin

class UsuarioAdmin(UserAdmin):
    list_display = ("usuarioId", "imagen", "nombre", "apellido", "usuario", "fechaDeNacimiento", "telefono", "ciudad")

    fieldsets = (
        (None, {"fields": ("usuario", "password")}),
        ("Personal info", {"fields": ("nombre", "apellido", "fechaDeNacimiento", "telefono", "ciudad")}),
        ("Permissions", {"fields": ("is_active", "is_staff", "is_superuser", 'groups', 'user_permissions')}),
    )
    
    filter_horizontal = []
    list_filter = []
    search_fields = ("nombre", "apellido", "usuario")
    ordering = ("usuarioId",)
    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': ('usuario', 'password1', 'password2', 'nombre', 'apellido', 'fechaDeNacimiento', 'telefono', 'ciudad', 'is_staff', 'is_superuser'),
        }),
    )

class HotelAdmin(admin.ModelAdmin):
    list_display = ("razonSocial", "cuil", "domicilio", "localidad", "provincia", "cp", "telefono", "categoria", "email")
class EmpleadoAdmin(admin.ModelAdmin):
    list_display = ("nombre", "apellido", "usuario", "password", "domicilio", "localidad", "provincia", "cp", "telefono", "hotelId", "rol")

admin.site.register(Usuario, UsuarioAdmin)
admin.site.register(Hotel, HotelAdmin)
admin.site.register(Empleado, EmpleadoAdmin)