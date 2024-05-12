-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-06-2023 a las 21:07:28
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

CREATE DATABASE IF NOT EXISTS hotelcalifornia;
USE hotelcalifornia;


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotelcalifornia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authtoken_token`
--

CREATE TABLE `authtoken_token` (
  `key` varchar(40) NOT NULL,
  `created` datetime(6) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_group`
--

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_group_permissions`
--

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auth_permission`
--

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auth_permission`
--

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1, 'Can add log entry', 1, 'add_logentry'),
(2, 'Can change log entry', 1, 'change_logentry'),
(3, 'Can delete log entry', 1, 'delete_logentry'),
(4, 'Can view log entry', 1, 'view_logentry'),
(5, 'Can add permission', 2, 'add_permission'),
(6, 'Can change permission', 2, 'change_permission'),
(7, 'Can delete permission', 2, 'delete_permission'),
(8, 'Can view permission', 2, 'view_permission'),
(9, 'Can add group', 3, 'add_group'),
(10, 'Can change group', 3, 'change_group'),
(11, 'Can delete group', 3, 'delete_group'),
(12, 'Can view group', 3, 'view_group'),
(13, 'Can add content type', 4, 'add_contenttype'),
(14, 'Can change content type', 4, 'change_contenttype'),
(15, 'Can delete content type', 4, 'delete_contenttype'),
(16, 'Can view content type', 4, 'view_contenttype'),
(17, 'Can add session', 5, 'add_session'),
(18, 'Can change session', 5, 'change_session'),
(19, 'Can delete session', 5, 'delete_session'),
(20, 'Can view session', 5, 'view_session'),
(21, 'Can add Token', 6, 'add_token'),
(22, 'Can change Token', 6, 'change_token'),
(23, 'Can delete Token', 6, 'delete_token'),
(24, 'Can view Token', 6, 'view_token'),
(25, 'Can add token', 7, 'add_tokenproxy'),
(26, 'Can change token', 7, 'change_tokenproxy'),
(27, 'Can delete token', 7, 'delete_tokenproxy'),
(28, 'Can view token', 7, 'view_tokenproxy'),
(29, 'Can add Detalle de las facturas emitidas por el hotel correspondientes a las resvervas', 8, 'add_detalle'),
(30, 'Can change Detalle de las facturas emitidas por el hotel correspondientes a las resvervas', 8, 'change_detalle'),
(31, 'Can delete Detalle de las facturas emitidas por el hotel correspondientes a las resvervas', 8, 'delete_detalle'),
(32, 'Can view Detalle de las facturas emitidas por el hotel correspondientes a las resvervas', 8, 'view_detalle'),
(33, 'Can add Detalle de pago de las facturas emitidas por el hotel', 9, 'add_detallepago'),
(34, 'Can change Detalle de pago de las facturas emitidas por el hotel', 9, 'change_detallepago'),
(35, 'Can delete Detalle de pago de las facturas emitidas por el hotel', 9, 'delete_detallepago'),
(36, 'Can view Detalle de pago de las facturas emitidas por el hotel', 9, 'view_detallepago'),
(37, 'Can add Facturas emitidas por el hotel correspondientes a las resvervas', 10, 'add_factura'),
(38, 'Can change Facturas emitidas por el hotel correspondientes a las resvervas', 10, 'change_factura'),
(39, 'Can delete Facturas emitidas por el hotel correspondientes a las resvervas', 10, 'delete_factura'),
(40, 'Can view Facturas emitidas por el hotel correspondientes a las resvervas', 10, 'view_factura'),
(41, 'Can add Habitaciones de un hotel', 11, 'add_habitacion'),
(42, 'Can change Habitaciones de un hotel', 11, 'change_habitacion'),
(43, 'Can delete Habitaciones de un hotel', 11, 'delete_habitacion'),
(44, 'Can view Habitaciones de un hotel', 11, 'view_habitacion'),
(45, 'Can add imagen', 12, 'add_imagen'),
(46, 'Can change imagen', 12, 'change_imagen'),
(47, 'Can delete imagen', 12, 'delete_imagen'),
(48, 'Can view imagen', 12, 'view_imagen'),
(49, 'Can add Servicios que contienen las habitaciones de un hotel', 13, 'add_servicio'),
(50, 'Can change Servicios que contienen las habitaciones de un hotel', 13, 'change_servicio'),
(51, 'Can delete Servicios que contienen las habitaciones de un hotel', 13, 'delete_servicio'),
(52, 'Can view Servicios que contienen las habitaciones de un hotel', 13, 'view_servicio'),
(53, 'Can add Servicio de cada habitación', 14, 'add_servicioporhabitacion'),
(54, 'Can change Servicio de cada habitación', 14, 'change_servicioporhabitacion'),
(55, 'Can delete Servicio de cada habitación', 14, 'delete_servicioporhabitacion'),
(56, 'Can view Servicio de cada habitación', 14, 'view_servicioporhabitacion'),
(57, 'Can add Reservas de habitacinoes en un hotel', 15, 'add_reserva'),
(58, 'Can change Reservas de habitacinoes en un hotel', 15, 'change_reserva'),
(59, 'Can delete Reservas de habitacinoes en un hotel', 15, 'delete_reserva'),
(60, 'Can view Reservas de habitacinoes en un hotel', 15, 'view_reserva'),
(61, 'Can add Todos los hoteles disponibles', 16, 'add_hotel'),
(62, 'Can change Todos los hoteles disponibles', 16, 'change_hotel'),
(63, 'Can delete Todos los hoteles disponibles', 16, 'delete_hotel'),
(64, 'Can view Todos los hoteles disponibles', 16, 'view_hotel'),
(65, 'Can add Todos los clientes registrados', 17, 'add_usuario'),
(66, 'Can change Todos los clientes registrados', 17, 'change_usuario'),
(67, 'Can delete Todos los clientes registrados', 17, 'delete_usuario'),
(68, 'Can view Todos los clientes registrados', 17, 'view_usuario'),
(69, 'Can add Todos los empleados registrados en el hotel', 18, 'add_empleado'),
(70, 'Can change Todos los empleados registrados en el hotel', 18, 'change_empleado'),
(71, 'Can delete Todos los empleados registrados en el hotel', 18, 'delete_empleado'),
(72, 'Can view Todos los empleados registrados en el hotel', 18, 'view_empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `usuarioId` int(11) NOT NULL,
  `imagen` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `usuario` varchar(254) NOT NULL,
  `fechaDeNacimiento` date NOT NULL,
  `telefono` bigint(20) UNSIGNED NOT NULL CHECK (`telefono` >= 0),
  `ciudad` varchar(256) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`password`, `last_login`, `is_superuser`, `usuarioId`, `imagen`, `nombre`, `apellido`, `usuario`, `fechaDeNacimiento`, `telefono`, `ciudad`, `is_staff`, `is_active`) VALUES
('pbkdf2_sha256$600000$NRLgkJ8c1ApdN9gHF1PpEy$e3dvj2PSk8W4QZ9Wd7sbCRtzQxU7GTxfZeXpEEi5VxY=', '2023-06-20 18:26:07.010110', 1, 1, '', 'admin', 'admin', 'admin@admin.com', '1990-01-01', 0, 'admin', 1, 1),
('pbkdf2_sha256$600000$5iUQDYf14yUtVnIkBMnlXr$Fc+Z00rEp07USJmTUyCa7qzVIrwIiikDp6G6Y59kaV0=', NULL, 0, 2, '', 'Romina', 'Martinez', 'rmartinez@gmail.com', '1985-02-14', 3514567890, 'Oliva', 0, 1),
('pbkdf2_sha256$600000$T7MXZTpsapWebhzgZnsJMf$ptjakqUI6X6XiHV+gJRN2a9cypBfLGB7QKqc0f1+ffo=', NULL, 0, 3, '', 'David', 'Gomenez', 'dgimenez@gmail.com', '1999-08-09', 3572789654, 'Oncativo', 0, 1),
('pbkdf2_sha256$600000$wpM88mDErF1ySRKbdJsr7P$oc+ti1tx0fVIPfwcjjU4KTa/sCGDvSwmGFmDikx061Q=', NULL, 0, 4, '', 'Soraya', 'Camilo', 'scamilo@gmail.com', '2001-09-25', 3514587962, 'Córdoba', 0, 1),
('pbkdf2_sha256$600000$UcVi2aCR1ZmYypYoyBWkXd$Vcx1UfrEg3UNwYJafIulRcdGJz68DuiKnAcLMruY3Ks=', NULL, 0, 5, '', 'Francisco', 'Lopez', 'flopez@gmail.com', '1980-06-30', 3519602154, 'Córdoba', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_groups`
--

CREATE TABLE `cliente_groups` (
  `id` bigint(20) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_user_permissions`
--

CREATE TABLE `cliente_user_permissions` (
  `id` bigint(20) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `detalleId` int(11) NOT NULL,
  `descuento` decimal(4,2) NOT NULL,
  `importe` decimal(20,2) NOT NULL,
  `facturaId_id` int(11) NOT NULL,
  `reservaId_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`detalleId`, `descuento`, `importe`, `facturaId_id`, `reservaId_id`) VALUES
(1, 0.00, 9000.00, 1, 1),
(2, 0.00, 16000.00, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepagp`
--

CREATE TABLE `detallepagp` (
  `detallePagoId` int(11) NOT NULL,
  `tipoPago` varchar(100) NOT NULL,
  `porcentajePago` smallint(5) UNSIGNED NOT NULL CHECK (`porcentajePago` >= 0),
  `facturaId_id` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `detallepagp`
--

INSERT INTO `detallepagp` (`detallePagoId`, `tipoPago`, `porcentajePago`, `facturaId_id`) VALUES
(2, 'Transferencia', 100, 1),
(3, 'Tarjeta Crédito', 100, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_admin_log`
--

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext DEFAULT NULL,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) UNSIGNED NOT NULL CHECK (`action_flag` >= 0),
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `django_admin_log`
--

INSERT INTO `django_admin_log` (`id`, `action_time`, `object_id`, `object_repr`, `action_flag`, `change_message`, `content_type_id`, `user_id`) VALUES
(1, '2023-06-20 18:28:57.049617', '1', 'Hotel California', 1, '[{\"added\": {}}]', 16, 1),
(2, '2023-06-20 18:30:35.469607', '1', 'Empleado Perez, Juan', 1, '[{\"added\": {}}]', 18, 1),
(3, '2023-06-20 18:32:02.969888', '1', 'Imagen object (1)', 1, '[{\"added\": {}}]', 12, 1),
(4, '2023-06-20 18:32:08.909953', '1', 'Habitacion 101', 1, '[{\"added\": {}}]', 11, 1),
(5, '2023-06-20 18:32:40.949580', '2', 'Habitacion 102', 1, '[{\"added\": {}}]', 11, 1),
(6, '2023-06-20 18:33:07.729188', '3', 'Habitacion 103', 1, '[{\"added\": {}}]', 11, 1),
(7, '2023-06-20 18:33:26.679297', '4', 'Habitacion 104', 1, '[{\"added\": {}}]', 11, 1),
(8, '2023-06-20 18:33:57.349764', '5', 'Habitacion 202', 1, '[{\"added\": {}}]', 11, 1),
(9, '2023-06-20 18:34:28.669374', '6', 'Habitacion 203', 1, '[{\"added\": {}}]', 11, 1),
(10, '2023-06-20 18:34:45.159546', '7', 'Habitacion 204', 1, '[{\"added\": {}}]', 11, 1),
(11, '2023-06-20 18:35:02.314583', '8', 'Habitacion 201', 1, '[{\"added\": {}}]', 11, 1),
(12, '2023-06-20 18:48:32.714385', '1', 'Reserva del cliente Cliente Martinez, Romina el 2023-06-10 para la habitacion Habitacion 201', 1, '[{\"added\": {}}]', 15, 1),
(13, '2023-06-20 18:49:26.189301', '2', 'Reserva del cliente Cliente Gomenez, David el 2023-06-01 para la habitacion Habitacion 103', 1, '[{\"added\": {}}]', 15, 1),
(14, '2023-06-20 18:49:58.756150', '3', 'Reserva del cliente Cliente Gomenez, David el 2023-06-17 para la habitacion Habitacion 101', 1, '[{\"added\": {}}]', 15, 1),
(15, '2023-06-20 18:50:23.704663', '1', 'Frigobar', 1, '[{\"added\": {}}]', 13, 1),
(16, '2023-06-20 18:50:29.694407', '2', 'TV-LED', 1, '[{\"added\": {}}]', 13, 1),
(17, '2023-06-20 18:50:35.809466', '3', 'Microondas', 1, '[{\"added\": {}}]', 13, 1),
(18, '2023-06-20 18:50:39.979477', '4', 'Pava eléctrica', 1, '[{\"added\": {}}]', 13, 1),
(19, '2023-06-20 18:50:44.054989', '5', 'Caja de seguridad', 1, '[{\"added\": {}}]', 13, 1),
(20, '2023-06-20 18:50:48.229383', '6', 'Conexión a internet inalámbrica (WIFI)', 1, '[{\"added\": {}}]', 13, 1),
(21, '2023-06-20 18:51:08.909466', '1', 'Servicio Frigobar de la habitación Habitacion 101', 1, '[{\"added\": {}}]', 14, 1),
(22, '2023-06-20 18:51:13.994474', '2', 'Servicio TV-LED de la habitación Habitacion 101', 1, '[{\"added\": {}}]', 14, 1),
(23, '2023-06-20 18:51:23.559311', '3', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 101', 1, '[{\"added\": {}}]', 14, 1),
(24, '2023-06-20 18:51:29.369870', '4', 'Servicio Pava eléctrica de la habitación Habitacion 101', 1, '[{\"added\": {}}]', 14, 1),
(25, '2023-06-20 18:51:42.244212', '5', 'Servicio TV-LED de la habitación Habitacion 101', 1, '[{\"added\": {}}]', 14, 1),
(26, '2023-06-20 18:51:57.959709', '2', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 101', 2, '[{\"changed\": {\"fields\": [\"ServicioId\"]}}]', 14, 1),
(27, '2023-06-20 18:52:08.984316', '2', 'Servicio Microondas de la habitación Habitacion 101', 2, '[{\"changed\": {\"fields\": [\"ServicioId\"]}}]', 14, 1),
(28, '2023-06-20 18:52:26.494918', '6', 'Servicio Frigobar de la habitación Habitacion 102', 1, '[{\"added\": {}}]', 14, 1),
(29, '2023-06-20 18:52:32.699456', '7', 'Servicio TV-LED de la habitación Habitacion 102', 1, '[{\"added\": {}}]', 14, 1),
(30, '2023-06-20 18:52:44.948896', '8', 'Servicio TV-LED de la habitación Habitacion 102', 1, '[{\"added\": {}}]', 14, 1),
(31, '2023-06-20 18:52:56.651633', '8', 'Servicio Microondas de la habitación Habitacion 102', 2, '[{\"changed\": {\"fields\": [\"ServicioId\"]}}]', 14, 1),
(32, '2023-06-20 18:53:03.139813', '9', 'Servicio Pava eléctrica de la habitación Habitacion 102', 1, '[{\"added\": {}}]', 14, 1),
(33, '2023-06-20 18:53:08.349173', '10', 'Servicio Caja de seguridad de la habitación Habitacion 102', 1, '[{\"added\": {}}]', 14, 1),
(34, '2023-06-20 18:53:13.369388', '11', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 102', 1, '[{\"added\": {}}]', 14, 1),
(35, '2023-06-20 18:53:53.844593', '12', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 103', 1, '[{\"added\": {}}]', 14, 1),
(36, '2023-06-20 18:54:03.854644', '13', 'Servicio Microondas de la habitación Habitacion 103', 1, '[{\"added\": {}}]', 14, 1),
(37, '2023-06-20 18:54:10.819626', '14', 'Servicio TV-LED de la habitación Habitacion 103', 1, '[{\"added\": {}}]', 14, 1),
(38, '2023-06-20 18:54:16.779585', '15', 'Servicio TV-LED de la habitación Habitacion 104', 1, '[{\"added\": {}}]', 14, 1),
(39, '2023-06-20 18:54:25.634434', '16', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 104', 1, '[{\"added\": {}}]', 14, 1),
(40, '2023-06-20 18:54:34.384636', '17', 'Servicio Caja de seguridad de la habitación Habitacion 104', 1, '[{\"added\": {}}]', 14, 1),
(41, '2023-06-20 18:54:40.509743', '18', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 202', 1, '[{\"added\": {}}]', 14, 1),
(42, '2023-06-20 18:54:47.689484', '19', 'Servicio TV-LED de la habitación Habitacion 202', 1, '[{\"added\": {}}]', 14, 1),
(43, '2023-06-20 18:54:53.999395', '20', 'Servicio Frigobar de la habitación Habitacion 202', 1, '[{\"added\": {}}]', 14, 1),
(44, '2023-06-20 18:55:00.490138', '21', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 203', 1, '[{\"added\": {}}]', 14, 1),
(45, '2023-06-20 18:55:05.709744', '22', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(46, '2023-06-20 18:55:13.449625', '23', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(47, '2023-06-20 18:55:20.534003', '24', 'Servicio TV-LED de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(48, '2023-06-20 18:55:26.169578', '25', 'Servicio Frigobar de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(49, '2023-06-20 18:55:33.681706', '26', 'Servicio Microondas de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(50, '2023-06-20 18:55:38.509605', '27', 'Servicio Pava eléctrica de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(51, '2023-06-20 18:55:42.994564', '28', 'Servicio Caja de seguridad de la habitación Habitacion 204', 1, '[{\"added\": {}}]', 14, 1),
(52, '2023-06-20 18:55:48.094566', '29', 'Servicio Conexión a internet inalámbrica (WIFI) de la habitación Habitacion 201', 1, '[{\"added\": {}}]', 14, 1),
(53, '2023-06-20 18:56:29.689545', '30', 'Servicio TV-LED de la habitación Habitacion 201', 1, '[{\"added\": {}}]', 14, 1),
(54, '2023-06-20 18:57:34.019407', '1', 'Factura Nº 1000000036', 1, '[{\"added\": {}}]', 10, 1),
(55, '2023-06-20 18:57:47.269541', '2', 'Factura Nº 1000000037', 1, '[{\"added\": {}}]', 10, 1),
(56, '2023-06-20 18:58:10.489024', '1', 'El detalle corresponde a la factura Factura Nº 1000000036 de la reserva Reserva del cliente Cliente Martinez, Romina el 2023-06-10 para la habitacion Habitacion 201, con un importe total de $ 9000', 1, '[{\"added\": {}}]', 8, 1),
(57, '2023-06-20 18:58:38.417585', '2', 'El detalle corresponde a la factura Factura Nº 1000000037 de la reserva Reserva del cliente Cliente Gomenez, David el 2023-06-01 para la habitacion Habitacion 103, con un importe total de $ 16000', 1, '[{\"added\": {}}]', 8, 1),
(58, '2023-06-20 19:01:13.784292', '2', 'El detalle de pago correspondiente a la factura Factura Nº 1000000036 con un porcentaje de pago del 100 en Transferencia.', 1, '[{\"added\": {}}]', 9, 1),
(59, '2023-06-20 19:02:03.224315', '3', 'El detalle de pago correspondiente a la factura Factura Nº 1000000037 con un porcentaje de pago del 100 en Tarjeta Crédito.', 1, '[{\"added\": {}}]', 9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_content_type`
--

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `django_content_type`
--

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(1, 'admin', 'logentry'),
(3, 'auth', 'group'),
(2, 'auth', 'permission'),
(6, 'authtoken', 'token'),
(7, 'authtoken', 'tokenproxy'),
(4, 'contenttypes', 'contenttype'),
(8, 'Facturacion', 'detalle'),
(9, 'Facturacion', 'detallepago'),
(10, 'Facturacion', 'factura'),
(11, 'GestionReservas', 'habitacion'),
(12, 'GestionReservas', 'imagen'),
(15, 'GestionReservas', 'reserva'),
(13, 'GestionReservas', 'servicio'),
(14, 'GestionReservas', 'servicioporhabitacion'),
(18, 'GestionUsuarios', 'empleado'),
(16, 'GestionUsuarios', 'hotel'),
(17, 'GestionUsuarios', 'usuario'),
(5, 'sessions', 'session');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_migrations`
--

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `django_migrations`
--

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1, 'contenttypes', '0001_initial', '2023-06-20 18:10:29.104130'),
(2, 'contenttypes', '0002_remove_content_type_name', '2023-06-20 18:10:29.780675'),
(3, 'auth', '0001_initial', '2023-06-20 18:10:32.019347'),
(4, 'auth', '0002_alter_permission_name_max_length', '2023-06-20 18:10:32.664528'),
(5, 'auth', '0003_alter_user_email_max_length', '2023-06-20 18:10:32.704506'),
(6, 'auth', '0004_alter_user_username_opts', '2023-06-20 18:10:32.839181'),
(7, 'auth', '0005_alter_user_last_login_null', '2023-06-20 18:10:32.914345'),
(8, 'auth', '0006_require_contenttypes_0002', '2023-06-20 18:10:32.974278'),
(9, 'auth', '0007_alter_validators_add_error_messages', '2023-06-20 18:10:33.059638'),
(10, 'auth', '0008_alter_user_username_max_length', '2023-06-20 18:10:33.114173'),
(11, 'auth', '0009_alter_user_last_name_max_length', '2023-06-20 18:10:33.164573'),
(12, 'auth', '0010_alter_group_name_max_length', '2023-06-20 18:10:33.355022'),
(13, 'auth', '0011_update_proxy_permissions', '2023-06-20 18:10:33.399338'),
(14, 'auth', '0012_alter_user_first_name_max_length', '2023-06-20 18:10:33.454473'),
(15, 'GestionUsuarios', '0001_initial', '2023-06-20 18:10:37.429613'),
(16, 'GestionReservas', '0001_initial', '2023-06-20 18:10:39.584419'),
(17, 'Facturacion', '0001_initial', '2023-06-20 18:10:39.968774'),
(18, 'Facturacion', '0002_initial', '2023-06-20 18:10:43.489345'),
(19, 'GestionReservas', '0002_initial', '2023-06-20 18:10:45.749134'),
(20, 'admin', '0001_initial', '2023-06-20 18:10:47.259491'),
(21, 'admin', '0002_logentry_remove_auto_add', '2023-06-20 18:10:47.374280'),
(22, 'admin', '0003_logentry_add_action_flag_choices', '2023-06-20 18:10:47.429320'),
(23, 'authtoken', '0001_initial', '2023-06-20 18:10:48.189636'),
(24, 'authtoken', '0002_auto_20160226_1747', '2023-06-20 18:10:48.349549'),
(25, 'authtoken', '0003_tokenproxy', '2023-06-20 18:10:48.379515'),
(26, 'sessions', '0001_initial', '2023-06-20 18:10:48.769487');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `django_session`
--

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `django_session`
--

INSERT INTO `django_session` (`session_key`, `session_data`, `expire_date`) VALUES
('8lybjnog9g4ucm7ggkzwweyvckv5ikhm', '.eJxVjEEOgjAQRe_StWk6hTrUpXvO0MzQGUFNSSisjHdXEha6_e-9_zKJtnVMW5UlTdlcDJjT78Y0PKTsIN-p3GY7zGVdJra7Yg9abT9neV4P9-9gpDp-6zM2pIFa7ygGh01W9dzG0HnBCLmDTgFVRdCDbzk7RgcQPIdBKSKb9wfSWzej:1qBg3L:FV6EcUoOo6zBkdKPKa0RCBJYXeeN8GxX7ww0cSbK17E', '2023-07-04 18:26:07.044493');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `empleadoId` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `usuario` varchar(254) NOT NULL,
  `password` varchar(150) NOT NULL,
  `domicilio` varchar(150) NOT NULL,
  `localidad` varchar(100) NOT NULL,
  `provincia` varchar(100) NOT NULL,
  `cp` smallint(5) UNSIGNED NOT NULL CHECK (`cp` >= 0),
  `telefono` bigint(20) UNSIGNED NOT NULL CHECK (`telefono` >= 0),
  `rol` varchar(70) NOT NULL,
  `hotelId_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`empleadoId`, `nombre`, `apellido`, `usuario`, `password`, `domicilio`, `localidad`, `provincia`, `cp`, `telefono`, `rol`, `hotelId_id`) VALUES
(1, 'Juan', 'Perez', 'juan.perez@hotelcalifornia.com', 'jp.1234', 'San Martín 123', 'Villa Carlos Paz', 'Córdoba', 5152, 3541555111, 'Administrador', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `facturaId` int(11) NOT NULL,
  `nroFactura` varchar(10) NOT NULL,
  `hotelId_id` int(11) NOT NULL,
  `usuarioId_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`facturaId`, `nroFactura`, `hotelId_id`, `usuarioId_id`) VALUES
(1, '1000000036', 1, 2),
(2, '1000000037', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gestionreservas_imagen`
--

CREATE TABLE `gestionreservas_imagen` (
  `id` bigint(20) NOT NULL,
  `imagen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gestionreservas_imagen`
--

INSERT INTO `gestionreservas_imagen` (`id`, `imagen`) VALUES
(1, 'img/habitaciones/habitacion1.jfif');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `habitacionId` int(11) NOT NULL,
  `numero` smallint(5) UNSIGNED NOT NULL CHECK (`numero` >= 0),
  `piso` smallint(5) UNSIGNED NOT NULL CHECK (`piso` >= 0),
  `estado` varchar(50) NOT NULL,
  `precio` int(11) NOT NULL,
  `tipoHabitacion` varchar(100) NOT NULL,
  `hotelId_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`habitacionId`, `numero`, `piso`, `estado`, `precio`, `tipoHabitacion`, `hotelId_id`) VALUES
(1, 101, 1, 'Disponible', 7000, 'Doble', 1),
(2, 102, 1, 'En reperación', 7000, 'Doble', 1),
(3, 103, 1, 'Disponible', 9000, 'Cuadruple', 1),
(4, 104, 1, 'Disponible', 5000, 'Simple', 1),
(5, 202, 2, 'No Disponible', 9000, 'Cuadruple', 1),
(6, 203, 2, 'Disponible', 5000, 'Simple', 1),
(7, 204, 2, 'Disponible', 5000, 'Simple', 1),
(8, 201, 1, 'Disponible', 9000, 'Cuadruple', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion_imagenes`
--

CREATE TABLE `habitacion_imagenes` (
  `id` bigint(20) NOT NULL,
  `habitacion_id` int(11) NOT NULL,
  `imagen_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion_imagenes`
--

INSERT INTO `habitacion_imagenes` (`id`, `habitacion_id`, `imagen_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `hotelId` int(11) NOT NULL,
  `razonSocial` varchar(150) NOT NULL,
  `cuil` varchar(13) NOT NULL,
  `domicilio` varchar(150) NOT NULL,
  `localidad` varchar(100) NOT NULL,
  `provincia` varchar(100) NOT NULL,
  `cp` smallint(5) UNSIGNED NOT NULL CHECK (`cp` >= 0),
  `telefono` bigint(20) UNSIGNED NOT NULL CHECK (`telefono` >= 0),
  `categoria` varchar(50) NOT NULL,
  `email` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`hotelId`, `razonSocial`, `cuil`, `domicilio`, `localidad`, `provincia`, `cp`, `telefono`, `categoria`, `email`) VALUES
(1, 'Hotel California', '30140752391', 'Diagonal San Martín', 'La Falda', 'Córdoba', 5152, 351987654, '4 estrellas', 'administracion@hotelcalifornia.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `reservaId` int(11) NOT NULL,
  `fechaReserva` date NOT NULL,
  `fechaIngreso` date NOT NULL,
  `fechaEgreso` date NOT NULL,
  `habitacionId_id` int(11) NOT NULL,
  `usuarioId_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`reservaId`, `fechaReserva`, `fechaIngreso`, `fechaEgreso`, `habitacionId_id`, `usuarioId_id`) VALUES
(1, '2023-06-10', '2023-06-20', '2023-06-27', 8, 2),
(2, '2023-06-01', '2023-06-21', '2023-06-24', 3, 3),
(3, '2023-06-17', '2023-06-30', '2023-07-06', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `servicioId` int(11) NOT NULL,
  `servicio` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`servicioId`, `servicio`) VALUES
(1, 'Frigobar'),
(2, 'TV-LED'),
(3, 'Microondas'),
(4, 'Pava eléctrica'),
(5, 'Caja de seguridad'),
(6, 'Conexión a internet inalámbrica (WIFI)');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicioporhabitacion`
--

CREATE TABLE `servicioporhabitacion` (
  `servicioPorHabitacionId` int(11) NOT NULL,
  `habitacionId_id` int(11) NOT NULL,
  `servicioId_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicioporhabitacion`
--

INSERT INTO `servicioporhabitacion` (`servicioPorHabitacionId`, `habitacionId_id`, `servicioId_id`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 1, 6),
(4, 1, 4),
(5, 1, 2),
(6, 2, 1),
(7, 2, 2),
(8, 2, 3),
(9, 2, 4),
(10, 2, 5),
(11, 2, 6),
(12, 3, 6),
(13, 3, 3),
(14, 3, 2),
(15, 4, 2),
(16, 4, 6),
(17, 4, 5),
(18, 5, 6),
(19, 5, 2),
(20, 5, 1),
(21, 6, 6),
(22, 7, 6),
(23, 7, 6),
(24, 7, 2),
(25, 7, 1),
(26, 7, 3),
(27, 7, 4),
(28, 7, 5),
(29, 8, 6),
(30, 8, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authtoken_token`
--
ALTER TABLE `authtoken_token`
  ADD PRIMARY KEY (`key`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indices de la tabla `auth_group`
--
ALTER TABLE `auth_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  ADD KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`);

--
-- Indices de la tabla `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`usuarioId`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indices de la tabla `cliente_groups`
--
ALTER TABLE `cliente_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Cliente_groups_usuario_id_group_id_7160c369_uniq` (`usuario_id`,`group_id`),
  ADD KEY `Cliente_groups_group_id_8f6f7d92_fk_auth_group_id` (`group_id`);

--
-- Indices de la tabla `cliente_user_permissions`
--
ALTER TABLE `cliente_user_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Cliente_user_permissions_usuario_id_permission_id_b6d1e428_uniq` (`usuario_id`,`permission_id`),
  ADD KEY `Cliente_user_permiss_permission_id_757b08ba_fk_auth_perm` (`permission_id`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`detalleId`),
  ADD KEY `Detalle_facturaId_id_2619d9b2_fk_Factura_facturaId` (`facturaId_id`),
  ADD KEY `Detalle_reservaId_id_5b10a91e_fk_Reserva_reservaId` (`reservaId_id`);

--
-- Indices de la tabla `detallepagp`
--
ALTER TABLE `detallepagp`
  ADD PRIMARY KEY (`detallePagoId`),
  ADD KEY `DetallePagp_facturaId_id_80d2130f_fk_Factura_facturaId` (`facturaId_id`);

--
-- Indices de la tabla `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  ADD KEY `django_admin_log_user_id_c564eba6_fk_Cliente_usuarioId` (`user_id`);

--
-- Indices de la tabla `django_content_type`
--
ALTER TABLE `django_content_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`);

--
-- Indices de la tabla `django_migrations`
--
ALTER TABLE `django_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `django_session`
--
ALTER TABLE `django_session`
  ADD PRIMARY KEY (`session_key`),
  ADD KEY `django_session_expire_date_a5c62663` (`expire_date`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`empleadoId`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `Empleado_hotelId_id_c5fb98b5_fk_Hotel_hotelId` (`hotelId_id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`facturaId`),
  ADD UNIQUE KEY `nroFactura` (`nroFactura`),
  ADD KEY `Factura_hotelId_id_10ba648f_fk_Hotel_hotelId` (`hotelId_id`),
  ADD KEY `Factura_usuarioId_id_06fa760c_fk_Cliente_usuarioId` (`usuarioId_id`);

--
-- Indices de la tabla `gestionreservas_imagen`
--
ALTER TABLE `gestionreservas_imagen`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`habitacionId`),
  ADD UNIQUE KEY `numero` (`numero`),
  ADD KEY `Habitacion_hotelId_id_956e340d_fk_Hotel_hotelId` (`hotelId_id`);

--
-- Indices de la tabla `habitacion_imagenes`
--
ALTER TABLE `habitacion_imagenes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Habitacion_imagenes_habitacion_id_imagen_id_7c1e4bb0_uniq` (`habitacion_id`,`imagen_id`),
  ADD KEY `Habitacion_imagenes_imagen_id_a2954f7c_fk_GestionRe` (`imagen_id`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotelId`),
  ADD UNIQUE KEY `cuil` (`cuil`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`reservaId`),
  ADD KEY `Reserva_habitacionId_id_1b11ea9c_fk_Habitacion_habitacionId` (`habitacionId_id`),
  ADD KEY `Reserva_usuarioId_id_59c77e52_fk_Cliente_usuarioId` (`usuarioId_id`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`servicioId`);

--
-- Indices de la tabla `servicioporhabitacion`
--
ALTER TABLE `servicioporhabitacion`
  ADD PRIMARY KEY (`servicioPorHabitacionId`),
  ADD KEY `ServicioPorHabitacio_habitacionId_id_190e0c18_fk_Habitacio` (`habitacionId_id`),
  ADD KEY `ServicioPorHabitacio_servicioId_id_2f06cc50_fk_Servicio_` (`servicioId_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auth_group`
--
ALTER TABLE `auth_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `auth_permission`
--
ALTER TABLE `auth_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `usuarioId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `cliente_groups`
--
ALTER TABLE `cliente_groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente_user_permissions`
--
ALTER TABLE `cliente_user_permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `detalleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detallepagp`
--
ALTER TABLE `detallepagp`
  MODIFY `detallePagoId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `django_admin_log`
--
ALTER TABLE `django_admin_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT de la tabla `django_content_type`
--
ALTER TABLE `django_content_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `django_migrations`
--
ALTER TABLE `django_migrations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `empleadoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `facturaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `gestionreservas_imagen`
--
ALTER TABLE `gestionreservas_imagen`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `habitacionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `habitacion_imagenes`
--
ALTER TABLE `habitacion_imagenes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `hotelId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `reservaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `servicioId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `servicioporhabitacion`
--
ALTER TABLE `servicioporhabitacion`
  MODIFY `servicioPorHabitacionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authtoken_token`
--
ALTER TABLE `authtoken_token`
  ADD CONSTRAINT `authtoken_token_user_id_35299eff_fk_Cliente_usuarioId` FOREIGN KEY (`user_id`) REFERENCES `cliente` (`usuarioId`);

--
-- Filtros para la tabla `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`);

--
-- Filtros para la tabla `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`);

--
-- Filtros para la tabla `cliente_groups`
--
ALTER TABLE `cliente_groups`
  ADD CONSTRAINT `Cliente_groups_group_id_8f6f7d92_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  ADD CONSTRAINT `Cliente_groups_usuario_id_8aefd148_fk_Cliente_usuarioId` FOREIGN KEY (`usuario_id`) REFERENCES `cliente` (`usuarioId`);

--
-- Filtros para la tabla `cliente_user_permissions`
--
ALTER TABLE `cliente_user_permissions`
  ADD CONSTRAINT `Cliente_user_permiss_permission_id_757b08ba_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `Cliente_user_permiss_usuario_id_324905e6_fk_Cliente_u` FOREIGN KEY (`usuario_id`) REFERENCES `cliente` (`usuarioId`);

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `Detalle_facturaId_id_2619d9b2_fk_Factura_facturaId` FOREIGN KEY (`facturaId_id`) REFERENCES `factura` (`facturaId`),
  ADD CONSTRAINT `Detalle_reservaId_id_5b10a91e_fk_Reserva_reservaId` FOREIGN KEY (`reservaId_id`) REFERENCES `reserva` (`reservaId`);

--
-- Filtros para la tabla `detallepagp`
--
ALTER TABLE `detallepagp`
  ADD CONSTRAINT `DetallePagp_facturaId_id_80d2130f_fk_Factura_facturaId` FOREIGN KEY (`facturaId_id`) REFERENCES `factura` (`facturaId`);

--
-- Filtros para la tabla `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  ADD CONSTRAINT `django_admin_log_user_id_c564eba6_fk_Cliente_usuarioId` FOREIGN KEY (`user_id`) REFERENCES `cliente` (`usuarioId`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `Empleado_hotelId_id_c5fb98b5_fk_Hotel_hotelId` FOREIGN KEY (`hotelId_id`) REFERENCES `hotel` (`hotelId`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `Factura_hotelId_id_10ba648f_fk_Hotel_hotelId` FOREIGN KEY (`hotelId_id`) REFERENCES `hotel` (`hotelId`),
  ADD CONSTRAINT `Factura_usuarioId_id_06fa760c_fk_Cliente_usuarioId` FOREIGN KEY (`usuarioId_id`) REFERENCES `cliente` (`usuarioId`);

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `Habitacion_hotelId_id_956e340d_fk_Hotel_hotelId` FOREIGN KEY (`hotelId_id`) REFERENCES `hotel` (`hotelId`);

--
-- Filtros para la tabla `habitacion_imagenes`
--
ALTER TABLE `habitacion_imagenes`
  ADD CONSTRAINT `Habitacion_imagenes_habitacion_id_f2ec6948_fk_Habitacio` FOREIGN KEY (`habitacion_id`) REFERENCES `habitacion` (`habitacionId`),
  ADD CONSTRAINT `Habitacion_imagenes_imagen_id_a2954f7c_fk_GestionRe` FOREIGN KEY (`imagen_id`) REFERENCES `gestionreservas_imagen` (`id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `Reserva_habitacionId_id_1b11ea9c_fk_Habitacion_habitacionId` FOREIGN KEY (`habitacionId_id`) REFERENCES `habitacion` (`habitacionId`),
  ADD CONSTRAINT `Reserva_usuarioId_id_59c77e52_fk_Cliente_usuarioId` FOREIGN KEY (`usuarioId_id`) REFERENCES `cliente` (`usuarioId`);

--
-- Filtros para la tabla `servicioporhabitacion`
--
ALTER TABLE `servicioporhabitacion`
  ADD CONSTRAINT `ServicioPorHabitacio_habitacionId_id_190e0c18_fk_Habitacio` FOREIGN KEY (`habitacionId_id`) REFERENCES `habitacion` (`habitacionId`),
  ADD CONSTRAINT `ServicioPorHabitacio_servicioId_id_2f06cc50_fk_Servicio_` FOREIGN KEY (`servicioId_id`) REFERENCES `servicio` (`servicioId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
