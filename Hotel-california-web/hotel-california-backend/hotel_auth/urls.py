
from django.urls import path
from django.contrib.auth.views import LoginView
from .views import LoginView, LogoutView, SingupView, ProfileView, ListarUsuarios

urlpatterns = [
    path('auth/login/', LoginView.as_view(), name='auth_login'),
    path('auth/logout/', LogoutView.as_view(), name='auth_logout'),
    path('auth/singup/', SingupView.as_view(), name='auth_singup'),
    path('user/profile/',
         ProfileView.as_view(), name='user_profile'),
    path('usuarios/',
         ListarUsuarios.as_view(), name='listar_usuarios'),
]