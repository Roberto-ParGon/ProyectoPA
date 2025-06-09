-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.7.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para banco_db
CREATE DATABASE IF NOT EXISTS `banco_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `banco_db`;

-- Volcando estructura para tabla banco_db.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `ID_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellido_Paterno` varchar(50) NOT NULL,
  `Apellido_Materno` varchar(50) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contrasena` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla banco_db.cliente: ~5 rows (aproximadamente)
REPLACE INTO `cliente` (`ID_Cliente`, `Nombre`, `Apellido_Paterno`, `Apellido_Materno`, `Correo`, `Contrasena`) VALUES
	(1, 'Ana', 'Torres', 'Garcia', 'ana@gmail.com', '123'),
	(2, 'Carlos', 'Lopez', 'Martinez', 'Carlos@gmail.com', '456'),
	(3, 'Sofia', 'Rodriguez', 'Peña', 'Sofia@gmail.com', '789'),
	(4, 'Test1', 'Test2', 'Test3', 'test@gmail.com', '123'),
	(5, '111', '111', '', '111', '111');

-- Volcando estructura para tabla banco_db.tarjeta
CREATE TABLE IF NOT EXISTS `tarjeta` (
  `Num_Tarjeta` varchar(16) NOT NULL,
  `ID_Cliente` int(11) NOT NULL,
  `PIN` varchar(4) NOT NULL,
  `Saldo` decimal(10,2) NOT NULL DEFAULT 0.00,
  `Tipo_Tarjeta` enum('DEBITO','CREDITO') NOT NULL,
  `Limite_Credito` decimal(10,2) NOT NULL DEFAULT 0.00,
  `Comisiones_Acumuladas` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`Num_Tarjeta`),
  KEY `ID_Cliente` (`ID_Cliente`),
  CONSTRAINT `tarjeta_ibfk_1` FOREIGN KEY (`ID_Cliente`) REFERENCES `cliente` (`ID_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla banco_db.tarjeta: ~4 rows (aproximadamente)
REPLACE INTO `tarjeta` (`Num_Tarjeta`, `ID_Cliente`, `PIN`, `Saldo`, `Tipo_Tarjeta`, `Limite_Credito`, `Comisiones_Acumuladas`) VALUES
	('1111222233334444', 1, '1234', 14500.00, 'DEBITO', 0.00, 0.00),
	('1234567890123456', 3, '1122', 10000.00, 'CREDITO', 30000.00, 0.00),
	('5555666677778888', 2, '5678', 8500.00, 'DEBITO', 0.00, 0.00),
	('8962007503358551', 1, '1234', 2100.00, 'DEBITO', 0.00, 0.00),
	('9999888877776666', 2, '8765', 500.75, 'CREDITO', 1502.25, 0.00);

-- Volcando estructura para tabla banco_db.transaccion
CREATE TABLE IF NOT EXISTS `transaccion` (
  `ID_Transaccion` int(11) NOT NULL AUTO_INCREMENT,
  `Num_Tarjeta` varchar(16) NOT NULL,
  `Monto` decimal(10,2) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Tipo_Transaccion` enum('DEPOSITO','RETIRO') NOT NULL,
  PRIMARY KEY (`ID_Transaccion`),
  KEY `Num_Tarjeta` (`Num_Tarjeta`),
  CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`Num_Tarjeta`) REFERENCES `tarjeta` (`Num_Tarjeta`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla banco_db.transaccion: ~8 rows (aproximadamente)
REPLACE INTO `transaccion` (`ID_Transaccion`, `Num_Tarjeta`, `Monto`, `Fecha`, `Tipo_Transaccion`) VALUES
	(1, '1111222233334444', 500.00, '2025-05-20 10:30:00', 'DEPOSITO'),
	(2, '1111222233334444', 75.50, '2025-05-21 15:45:10', 'RETIRO'),
	(3, '5555666677778888', 1200.00, '2025-05-28 18:00:00', 'RETIRO'),
	(4, '9999888877776666', 350.25, '2025-06-01 06:11:35', 'RETIRO'),
	(5, '1234567890123456', 89.99, '2025-06-01 06:11:36', 'RETIRO'),
	(6, '1111222233334444', 100.00, '2025-06-08 22:01:27', 'DEPOSITO'),
	(7, '1111222233334444', 600.50, '2025-06-08 22:01:57', 'RETIRO'),
	(8, '8962007503358551', 2100.00, '2025-06-08 22:06:33', 'DEPOSITO');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
