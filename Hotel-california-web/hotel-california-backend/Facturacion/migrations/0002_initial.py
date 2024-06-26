# Generated by Django 4.2.2 on 2023-06-20 18:10

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('Facturacion', '0001_initial'),
        ('GestionUsuarios', '0001_initial'),
        ('GestionReservas', '0001_initial'),
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.AddField(
            model_name='factura',
            name='hotelId',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='GestionUsuarios.hotel'),
        ),
        migrations.AddField(
            model_name='factura',
            name='usuarioId',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
        ),
        migrations.AddField(
            model_name='detallepago',
            name='facturaId',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Facturacion.factura'),
        ),
        migrations.AddField(
            model_name='detalle',
            name='facturaId',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Facturacion.factura'),
        ),
        migrations.AddField(
            model_name='detalle',
            name='reservaId',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='GestionReservas.reserva'),
        ),
        migrations.AddConstraint(
            model_name='detallepago',
            constraint=models.CheckConstraint(check=models.Q(('porcentajePago__lte', 101)), name='Valor de porcentaje entre 0 y 100'),
        ),
    ]
