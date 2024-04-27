from django.urls import path
from .views import ReservaView, FacturaView, DetallePagoView, DetalleView, HabitacionView, ServicioView, ServicioPorHabitacionView

urlpatterns = [
    path('habitacion/', HabitacionView.as_view(), name='habitaciones'),
    path('habitacion/<int:habitacionId>/', HabitacionView.as_view(), name='habitacion'),
    path('habitacion/<str:estado>/', HabitacionView.as_view(), name='estado'),
    path('reserva/', ReservaView.as_view(), name='reserva_lista'),
    path('reserva/<int:reservaId>/', ReservaView.as_view(), name='reserva'),
    path('factura/', FacturaView.as_view(), name='factura_lista'),
    path('factura/<int:facturaId>/', FacturaView.as_view(), name='factura'),
    path('detalle/', DetalleView.as_view(), name='detalle_lista'),
    path('detalle/<int:detalleId>/', DetalleView.as_view(), name='detalle'),
    path('detallePago/', DetallePagoView.as_view(), name='detalle_pago_lista'),
    path('detallePago/<int:detallePagoId>/', DetallePagoView.as_view(), name='detalle_pago'),
    path('servicio/', ServicioView.as_view(), name='servicio'),
    path('servicio/<int:habitacionId>/', ServicioPorHabitacionView.as_view(), name='servicio_por_habitacion'),
]