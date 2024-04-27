from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status, generics
from rest_framework.authtoken.models import Token
from rest_framework.permissions import AllowAny, IsAuthenticated, IsAdminUser
from django.contrib.auth import login, logout, authenticate
from .serializer import UsuarioSerializer, LoginSerializer, UsuarioLogeadoSerializer
from GestionUsuarios.models import Usuario

class LoginView(generics.CreateAPIView):
    permission_classes = [AllowAny]

    serializer_class = LoginSerializer

    def post(self, request):  
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)

        user = serializer.validated_data['user']
        login(request, user)

        clienteLogueado = UsuarioLogeadoSerializer(user)
        # token, _ = Token.objects.get_or_create(user=user) # Crea el token
        # clienteLogueado.token = token.key

        return Response(clienteLogueado.data, status=status.HTTP_200_OK)

class LogoutView(APIView):
    permission_classes= [AllowAny]
    def post(self, request):
        logout(request)

        return Response(status=status.HTTP_200_OK)
    
class SingupView(generics.CreateAPIView):
    serializer_class = UsuarioSerializer

class ProfileView(generics.RetrieveUpdateAPIView):
    permission_classes = [IsAuthenticated] #Solo usuarios logueados pueden ver.
    serializer_class = UsuarioSerializer
    http_method_names = ['get', 'patch']
    def get_object(self):
        if self.request.user.is_authenticated:
            return self.request.user
        
class ListarUsuarios(generics.ListCreateAPIView):
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer
    http_method_names = ['get']
    permission_classes = [IsAdminUser]
    def list(self, request):
        queryset = self.get_queryset()
        serializer = UsuarioSerializer(queryset, many=True)
        if self.request.user.is_authenticated:
            return Response(serializer.data)