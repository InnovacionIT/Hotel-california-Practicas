from django.contrib import admin
from .models import Factura, Detalle, DetallePago

class FacturaAdmin(admin.ModelAdmin):
    list_display = ("nroFactura", "hotelId", "usuarioId")
class DetalleAdmin(admin.ModelAdmin):
    list_display = ("facturaId", "reservaId", "descuento", "importe")
class DetallePagoAdmin(admin.ModelAdmin):
    list_display = ("facturaId", "tipoPago", "porcentajePago")

admin.site.register(Factura, FacturaAdmin)
admin.site.register(Detalle, DetalleAdmin)
admin.site.register(DetallePago, DetallePagoAdmin)
