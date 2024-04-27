from rest_framework import serializers
from GestionReservas.models import Reserva, Habitacion, Servicio, Imagen
from Facturacion.models import Factura, Detalle, DetallePago

class HabitacionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Habitacion
        fields = '__all__'

class ReservaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Reserva
        fields = '__all__'

class FacturaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Factura
        fields = '__all__'

class DetalleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Detalle
        fields = '__all__'

class DetallePagoSerializer(serializers.ModelSerializer):
    class Meta:
        model = DetallePago
        fields = '__all__'

class ServicioSerializer(serializers.ModelSerializer):
    class Meta:
        model = Servicio
        fields = '__all__'

class ImagenSerializer(serializers.ModelSerializer):
    class Meta:
        model = Imagen
        fields = '__all__'