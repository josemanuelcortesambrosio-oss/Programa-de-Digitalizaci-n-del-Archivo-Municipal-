CREATE DATABASE  IF NOT EXISTS `digitalizacion_municipio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `digitalizacion_municipio`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: digitalizacion_municipio
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `documento_municipal`
--

DROP TABLE IF EXISTS `documento_municipal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento_municipal` (
  `id_expediente` int NOT NULL AUTO_INCREMENT,
  `id_archivo` int NOT NULL,
  `clave_expediente` varchar(100) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `serie_documental` varchar(150) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `clasificacion_acceso` enum('Público','Reservado','Confidencial') NOT NULL DEFAULT 'Público',
  `soporte_documental` enum('Papel','Digital','Mixto') NOT NULL DEFAULT 'Papel',
  `fase_ciclo_vida` enum('Trámite','Concentración','Histórico') NOT NULL DEFAULT 'Trámite',
  `observaciones` text,
  PRIMARY KEY (`id_expediente`),
  UNIQUE KEY `clave_expediente` (`clave_expediente`),
  KEY `fk_documento_archivo` (`id_archivo`),
  KEY `idx_clave_expediente` (`clave_expediente`),
  KEY `idx_clasificacion` (`clasificacion_acceso`),
  KEY `idx_fase_ciclo` (`fase_ciclo_vida`),
  CONSTRAINT `fk_documento_archivo` FOREIGN KEY (`id_archivo`) REFERENCES `archivo_municipal` (`id_archivo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento_municipal`
--

LOCK TABLES `documento_municipal` WRITE;
/*!40000 ALTER TABLE `documento_municipal` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento_municipal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-30 21:33:43
