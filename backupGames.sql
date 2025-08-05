CREATE DATABASE  IF NOT EXISTS `gestor_games` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestor_games`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: gestor_games
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clasificaciones`
--

DROP TABLE IF EXISTS `clasificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clasificaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clasificacion` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificaciones`
--

LOCK TABLES `clasificaciones` WRITE;
/*!40000 ALTER TABLE `clasificaciones` DISABLE KEYS */;
INSERT INTO `clasificaciones` VALUES (1,'E (Para Todos)'),(2,'T (Adolescentes)'),(3,'M (Maduro)'),(4,'RP (Pendiente)'),(5,'AO (Solo Adultos)');
/*!40000 ALTER TABLE `clasificaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `genero` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (1,'Accion'),(2,'Aventura'),(3,'Estrategia'),(4,'RPG'),(5,'Deportes');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegos`
--

DROP TABLE IF EXISTS `juegos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `desarrollador` varchar(50) NOT NULL,
  `calificacion` float NOT NULL,
  `fecha_salida` varchar(50) NOT NULL,
  `precio` decimal(11,2) NOT NULL,
  `id_genero` int NOT NULL,
  `id_clasificacion` int NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_genero` (`id_genero`),
  KEY `id_clasificacion` (`id_clasificacion`),
  CONSTRAINT `juegos_ibfk_1` FOREIGN KEY (`id_genero`) REFERENCES `generos` (`id`),
  CONSTRAINT `juegos_ibfk_2` FOREIGN KEY (`id_clasificacion`) REFERENCES `clasificaciones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegos`
--

LOCK TABLES `juegos` WRITE;
/*!40000 ALTER TABLE `juegos` DISABLE KEYS */;
INSERT INTO `juegos` VALUES (1,'Zelda: Breath of the Wild','Nintendo',9.8,'2017-03-03',49.99,2,1,200),(2,'StarCraft II','Blizzard Entertainment',8.9,'2010-07-27',29.99,3,2,80),(3,'FIFA 25','EA Sports',7.5,'2024-09-27',69.99,5,1,300),(4,'Sombras del Bosque','Desarrollos Omega',8.5,'2021-04-15',120000.00,1,2,50),(5,'Aventuras en Marte','Estudio Luna',7.2,'2020-10-30',150000.00,2,3,35),(6,'Estrategia Final','Pixel Games',9,'2019-07-21',180000.00,3,1,20),(7,'RPG Épico','Mundo Virtual',8.8,'2022-01-10',220000.00,4,4,40),(8,'Deportes Extremos','Action Sports',7.5,'2020-03-05',130000.00,5,5,60),(9,'Misión Oculta','Hidden Games',6.9,'2021-08-22',140000.00,1,1,25),(10,'Aventura Épica','Epic Studios',8.3,'2019-11-13',170000.00,2,2,30),(11,'Estratega Supremo','Mind Games',9.5,'2022-05-18',210000.00,3,3,15),(12,'RPG Legendario','Fantasy Labs',8.7,'2021-12-01',230000.00,4,4,45),(13,'Campeón de Deportes','Sportify',7.9,'2020-07-25',160000.00,5,5,55);
/*!40000 ALTER TABLE `juegos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inicio_prestamo` datetime NOT NULL,
  `fin_prestamo` datetime NOT NULL,
  `id_usuario` int NOT NULL,
  `id_juego` int NOT NULL,
  `total` decimal(11,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,'2023-01-05 10:00:00','2023-01-20 10:00:00',1,5,20000.00),(2,'2023-02-10 15:30:00','2023-02-18 15:30:00',2,3,15000.00),(3,'2023-03-12 09:00:00','2023-03-25 09:00:00',3,10,25000.00),(4,'2023-04-01 13:15:00','2023-04-15 13:15:00',4,1,18000.00),(5,'2023-05-20 11:45:00','2023-06-05 11:45:00',5,7,22000.00),(6,'2023-06-15 08:30:00','2023-06-29 08:30:00',6,8,19500.00),(7,'2023-07-22 17:00:00','2023-08-05 17:00:00',7,4,21000.00),(8,'2023-08-10 14:20:00','2023-08-24 14:20:00',8,9,23000.00),(9,'2023-09-01 12:10:00','2023-09-15 12:10:00',9,2,17000.00),(10,'2023-10-05 16:45:00','2023-10-20 16:45:00',10,6,24000.00);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `documento` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `numero_telefono` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Juan Pérez','1012345678','juan.perez@example.com','3101234567'),(2,'Maria García','1098765432','maria.garcia@example.com','3209876543'),(3,'Carlos Ruiz','1023456789','carlos.ruiz@example.com','3001112233'),(4,'Ana López','1034567890','ana.lopez@example.com',NULL),(5,'Pedro Gómez','1045678901','pedro.gomez@example.com','3114445566'),(6,'Carlos Delgado','8009212879','carlos.delgado@correo.com','316-207-2120'),(7,'Gabriela Rojas','4794385588','gabriela.rojas@correo.com','318-431-9182'),(8,'Rosa Romero','7566792947','rosa.romero@correo.com','316-906-9230'),(9,'Luis Martínez','5213876410','luis.martinez@correo.com','310-765-4321'),(10,'Diana Sánchez','9312547790','diana.sanchez@correo.com','311-234-5678'),(11,'Andrés Torres','1179384821','andres.torres@correo.com','313-890-1234'),(12,'Paula Herrera','2378945329','paula.herrera@correo.com','314-567-8910'),(13,'Esteban Gómez','3482984756','esteban.gomez@correo.com','315-222-3344'),(14,'Marta Jiménez','6592847361','marta.jimenez@correo.com','317-555-6677'),(15,'Ricardo Pineda','7093284712','ricardo.pineda@correo.com','312-444-8899');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gestor_games'
--

--
-- Dumping routines for database 'gestor_games'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-07  5:03:41
