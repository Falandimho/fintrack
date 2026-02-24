-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fintrack
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
-- Table structure for table `tbl_categoria`
--

DROP TABLE IF EXISTS `tbl_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `default_category` bit(1) NOT NULL,
  `tipo` enum('DESPESA','RECEITA') DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2xkpmpwuuf5xr5pctyy64qgvg` (`usuario_id`),
  CONSTRAINT `FK2xkpmpwuuf5xr5pctyy64qgvg` FOREIGN KEY (`usuario_id`) REFERENCES `tbl_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_categoria`
--

LOCK TABLES `tbl_categoria` WRITE;
/*!40000 ALTER TABLE `tbl_categoria` DISABLE KEYS */;
INSERT INTO `tbl_categoria` VALUES (1,'contas',_binary '\0',NULL,NULL),(2,'roupa',_binary '\0',NULL,NULL),(3,'salario',_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `tbl_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_despesa`
--

DROP TABLE IF EXISTS `tbl_despesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_despesa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_despesa` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  `categoria_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKltlovfg8cmg9o15e4p9e6sxha` (`categoria_id`),
  KEY `FK3xy9yxey8roldl9kjpg6utnhd` (`usuario_id`),
  CONSTRAINT `FK3xy9yxey8roldl9kjpg6utnhd` FOREIGN KEY (`usuario_id`) REFERENCES `tbl_usuario` (`id`),
  CONSTRAINT `FKltlovfg8cmg9o15e4p9e6sxha` FOREIGN KEY (`categoria_id`) REFERENCES `tbl_categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_despesa`
--

LOCK TABLES `tbl_despesa` WRITE;
/*!40000 ALTER TABLE `tbl_despesa` DISABLE KEYS */;
INSERT INTO `tbl_despesa` VALUES (1,'2026-01-20','conta do mes 11','Conta de agua',12.00,2,1),(2,'2026-01-26','conta de luz mes 12','conta de luz',100.00,NULL,NULL),(3,'2026-01-26','conta de luz mes 12','conta de luz',100.00,1,1);
/*!40000 ALTER TABLE `tbl_despesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_receita`
--

DROP TABLE IF EXISTS `tbl_receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_receita` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_receita` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `valor` decimal(38,2) DEFAULT NULL,
  `categoria_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs7m1h3490g4rhc9qyppy1fmpr` (`categoria_id`),
  KEY `FKf8xixx4jy8ubufxs8cio10yvj` (`usuario_id`),
  CONSTRAINT `FKf8xixx4jy8ubufxs8cio10yvj` FOREIGN KEY (`usuario_id`) REFERENCES `tbl_usuario` (`id`),
  CONSTRAINT `FKs7m1h3490g4rhc9qyppy1fmpr` FOREIGN KEY (`categoria_id`) REFERENCES `tbl_categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_receita`
--

LOCK TABLES `tbl_receita` WRITE;
/*!40000 ALTER TABLE `tbl_receita` DISABLE KEYS */;
INSERT INTO `tbl_receita` VALUES (2,'2026-01-26','job que fiz hoje','job',100.00,3,NULL);
/*!40000 ALTER TABLE `tbl_receita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `saldo_atual` decimal(38,2) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,'anthon@gmail','Anhotny',0.00,'$2a$10$peAAqAK1bHWRDdR.xIp7K.l1veZdEfioaz5qdrug/VYYSrEjA9RxO');
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'fintrack'
--

--
-- Dumping routines for database 'fintrack'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-02 16:16:40
