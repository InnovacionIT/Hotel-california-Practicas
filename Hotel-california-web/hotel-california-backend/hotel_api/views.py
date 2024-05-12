from rest_framework import status, generics
from rest_framework.response import Response
from .serializers import ReservaSerializer, FacturaSerializer, DetalleSerializer, DetallePagoSerializer, HabitacionSerializer, ServicioSerializer, ImagenSerializer
from GestionReservas.models import Reserva, Habitacion, Servicio, ServicioPorHabitacion
from Facturacion.models import Factura, Detalle, DetallePago
from rest_framework.views import APIView
from django.db.models import Q
import emailHelper

#######################################################################################################
# Métodos propios
def validarFecha(serializer):
        fechaReserva = serializer.validated_data['fechaReserva']
        fechaIngreso = serializer.validated_data['fechaIngreso']
        fechaEgreso = serializer.validated_data['fechaEgreso']

        if fechaEgreso <= fechaIngreso:
            return Response({'error': 'Fecha de egreso debe ser posterior a la fecha de ingreso.'}, status=status.HTTP_400_BAD_REQUEST)

        if fechaIngreso < fechaReserva:
            return Response({'error': 'No se puede reservar una habitación para una fecha anterior a la fecha de reserva.'}, status=status.HTTP_400_BAD_REQUEST)
        
def tryGetById(clase, claseId):
    try:
        return clase.objects.get(pk=claseId)
    except clase.DoesNotExist:
        return None
def tryGetByUserId(clase, userId):
    try:
        return clase.objects.filter(usuarioId_id=userId)
    except clase.DoesNotExist:
        return None

##########################################################################################################
# Habitaciones
class HabitacionView(APIView):
    def get(self, request, habitacionId=None,estado=None):
        if habitacionId is not None:  
            return self.get_by_id(request, habitacionId)
        if estado is not None:  
            return self.get_by_estado(request, estado)
        habitaciones = Habitacion.objects.all()
        serializer = HabitacionSerializer(habitaciones, many=True)
        data = serializer.data
        # Obtener la imagen para cada habitación
        for habitacion_data in data:
            habitacion = Habitacion.objects.get(habitacionId=habitacion_data['habitacionId'])
            imagenes = habitacion.imagenes.all()
            imagen_serializer = ImagenSerializer(imagenes, many=True)
            habitacion_data['imagenes'] = imagen_serializer.data

        return Response(data, status=status.HTTP_200_OK)

    def get_by_id(self, request, habitacionId):
        habitaciones = tryGetById(Habitacion, habitacionId)
        if habitaciones is None:
            return Response({'error': 'Habitacion no encontrada'}, status=status.HTTP_404_NOT_FOUND)
        serializer = HabitacionSerializer(habitaciones)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    def get_by_estado(self, request, estado):
        habitaciones = Habitacion.objects.filter(estado=estado)
        if not habitaciones:
            return Response({'error': 'No se encontraron habitaciones con el estado especificado'}, status=status.HTTP_404_NOT_FOUND)
        serializer = HabitacionSerializer(habitaciones, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    # def post(self, request):
    #     # Verificar si ya hay 5 habitaciones creadas
    #     if Habitacion.objects.count() >= 5:
    #         return Response({'error': 'No se pueden crear más de 5 habitaciones'}, status=status.HTTP_400_BAD_REQUEST)
        
    #     serializer = HabitacionSerializer(data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response(serializer.data, status=status.HTTP_201_CREATED)
    #     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

###############################################################################################################SERVICIOS
#Servicios de Habitaciones

class ServicioView(APIView):
    def get(self, request):
        servicios = Servicio.objects.all()
        serializer = ServicioSerializer(servicios, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

class ServicioPorHabitacionView(APIView):
    def get(self, request, habitacionId):
        try:
            servicios = ServicioPorHabitacion.objects.filter(habitacionId=habitacionId)
            serviciosLista = [servicio.servicioId.servicio for servicio in servicios]
            return Response(serviciosLista, status=status.HTTP_200_OK)
        except ServicioPorHabitacion.DoesNotExist:
            return Response({'error': 'No se encontraron servicios para la habitación especificada'}, status=status.HTTP_404_NOT_FOUND)
        
    
####################################################################################################################
#Reservas

class ReservaView(APIView):
    def get(self, request, reservaId=None, usuarioId=None):
        if reservaId is not None:  # Check if reservaId is provided
            return self.get_by_id(request, reservaId)
        if usuarioId is not None:
            return self.get_by_user_id(request, usuarioId)
        reservas = Reserva.objects.all()
        serializer = ReservaSerializer(reservas, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    def get_by_id(self, request, reservaId):
        reserva = tryGetById(Reserva, reservaId)
        if reserva is None:
            return Response({'error': 'Reserva not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = ReservaSerializer(reserva)
        return Response(serializer.data, status=status.HTTP_200_OK)
    def get_by_user_id(self, request, userId):
        print(userId)
        reserva = tryGetByUserId(Reserva, userId)
        if reserva is None:
            return Response({'error': 'Reserva not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = ReservaSerializer(reserva, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    def post(self, request):
        serializer = ReservaSerializer(data=request.data)
        if serializer.is_valid():
            fecha_reserva = serializer.validated_data['fechaReserva']
            fecha_ingreso = serializer.validated_data['fechaIngreso']
            fecha_egreso = serializer.validated_data['fechaEgreso']
            habitacion = serializer.validated_data['habitacionId']

            # Verificar si hay reservas existentes para esta habitación y período de tiempo
            reservas_existente = Reserva.objects.filter(
                habitacionId=habitacion,
                fechaIngreso__lte=fecha_egreso,
                fechaEgreso__gte=fecha_ingreso
            ).exclude(
                Q(fechaIngreso__gte=fecha_egreso) | Q(fechaEgreso__lte=fecha_ingreso)
            )

            if reservas_existente.exists():
                return Response({'error': 'Ya existe una reserva para esta habitación en este período de tiempo'}, status=status.HTTP_400_BAD_REQUEST)

            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def put(self, request, reservaId):
        reserva = tryGetById(Reserva, reservaId)
        if reserva is None:
            return Response({'error': 'Reserva not found'}, status=status.HTTP_404_NOT_FOUND)
        serializer = ReservaSerializer(reserva, data=request.data)
        if serializer.is_valid():
            validarFecha(serializer)
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, reservaId):
        reserva = tryGetById(Reserva, reservaId)
        if reserva is None:
            return Response({'error': 'Reserva not found'}, status=status.HTTP_404_NOT_FOUND)        
        reserva.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

#######################################################################################################
# Facturación

class FacturaView(APIView):
    def get(self, request, facturaId=None):
        if facturaId is not None:  # Check if reservaId is provided
            return self.get_by_id(request, facturaId)
        facturas = Factura.objects.all()
        serializer = FacturaSerializer(facturas, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    def get_by_id(self, request, facturaId):
        factura = tryGetById(Factura, facturaId)
        if factura is None:
            return Response({'error': 'Factura no encontrada'}, status=status.HTTP_404_NOT_FOUND)
        serializer = FacturaSerializer(factura)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    def post(self, request):
        serializer = FacturaSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class DetalleView(APIView):
    def get(self, request, detalleId=None):
        if detalleId is not None:  # Check if reservaId is provided
            return self.get_by_id(request, detalleId)
        detalles = Detalle.objects.all()
        serializer = DetalleSerializer(detalles, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    def get_by_id(self, request, detalleId):
        detalle = tryGetById(Detalle, detalleId)
        if detalle is None:
            return Response({'error': 'Detalle no encontrado'}, status=status.HTTP_404_NOT_FOUND)
        serializer = DetalleSerializer(detalle)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    def post(self, request):
        serializer = DetalleSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
class DetallePagoView(APIView):
    def get(self, request, detallePagoId=None):
        if detallePagoId is not None:  # Check if reservaId is provided
            return self.get_by_id(request, detallePagoId)
        detallesDePago = DetallePago.objects.all()
        serializer = DetallePagoSerializer(detallesDePago, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    def get_by_id(self, request, detallePagoId):
        detallePago = tryGetById(DetallePago, detallePagoId)
        if detallePago is None:
            return Response({'error': 'Detalle de pago no encontrado'}, status=status.HTTP_404_NOT_FOUND)
        serializer = DetallePagoSerializer(detallePago)
        return Response(serializer.data, status=status.HTTP_200_OK)
    
    def post(self, request):
        serializer = DetallePagoSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    queryset = Reserva.objects.all()
    serializer_class = ReservaSerializer
    lookup_field = 'pk'

#######################################################################################################
# Consulta / Envío de mails
class ConsultaView(APIView):
    def post(self, request):
        # Obtenemos los datos del formulario
        nombre = request.data.get('nombre')
        email = request.data.get('email')
        mensaje = request.data.get('mensaje')

        # Enviamos el correo electrónico
        try:
            emailHelper.enviar_correo_hotel(mensaje, nombre, email, "hcalifornia.info@gmail.com") 
            emailHelper.enviar_correo_cliente(mensaje, nombre, email, "hcalifornia.info@gmail.com") 
            return Response({"mensaje": "Correo enviado correctamente"}, status=status.HTTP_200_OK)
        except Exception as e:
            return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)