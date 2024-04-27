from django.db import models
from GestionReservas.models import Reserva
from GestionUsuarios.models import Usuario, Hotel

# Aquí se encuentra el código de las clases Factura, Detalle, TipoPago y DetallePago

class Factura(models.Model):
    facturaId = models.AutoField(primary_key=True)
    nroFactura = models.CharField(max_length=10, blank=False, unique=True)
    hotelId = models.ForeignKey(Hotel, to_field="hotelId", on_delete=models.CASCADE)
    usuarioId = models.ForeignKey(Usuario, to_field="usuarioId", on_delete=models.CASCADE)
    class Meta:
        db_table = "Factura"
        verbose_name = "Facturas emitidas por el hotel correspondientes a las resvervas"
        verbose_name_plural = "Facturas"
    def __unicode__(self):
        return self.nroFactura
    def __str__(self) -> str:
        return f"Factura Nº {self.nroFactura}"
    
class Detalle(models.Model):
    detalleId = models.AutoField(primary_key=True)
    facturaId = models.ForeignKey(Factura, to_field="facturaId", on_delete=models.CASCADE)
    reservaId = models.ForeignKey(Reserva, to_field="reservaId", on_delete=models.CASCADE)
    descuento = models.DecimalField(max_digits=4, decimal_places=2, default=0, blank=False)
    importe = models.DecimalField(max_digits=20, decimal_places=2, blank=False)
    class Meta:
        db_table = "Detalle"
        verbose_name = "Detalle de las facturas emitidas por el hotel correspondientes a las resvervas"
        verbose_name_plural = "Detalles"
    def __unicode__(self):
        return self.detalleId
    def __str__(self) -> str:
        return f"El detalle corresponde a la factura {self.facturaId} de la reserva {self.reservaId}, con un importe total de $ {self.importe}"
    
class DetallePago(models.Model):
    detallePagoId = models.AutoField(primary_key=True)
    facturaId = models.ForeignKey(Factura, to_field="facturaId", on_delete=models.CASCADE)
    tipoPago = models.CharField(max_length=100, blank=False)
    porcentajePago = models.PositiveSmallIntegerField(blank=False, default=100)
    class Meta:
        db_table = "DetallePagp"
        verbose_name = "Detalle de pago de las facturas emitidas por el hotel"
        verbose_name_plural = "Detalles de pago"
        constraints = [
            models.CheckConstraint(
                check=models.Q(porcentajePago__lte=101),
                name='Valor de porcentaje entre 0 y 100'
            ),
        ]
    def __unicode__(self):
        return self.detallePagoId
    def __str__(self) -> str:
        return f"El detalle de pago correspondiente a la factura {self.facturaId} con un porcentaje de pago del {self.porcentajePago} en {self.tipoPago}."
    
