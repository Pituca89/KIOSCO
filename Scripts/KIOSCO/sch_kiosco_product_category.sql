-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sch_kiosco
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `CATEGORY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IS_ACTIVE` bit(1) DEFAULT NULL,
  `CATEGORY_NAME` varchar(255) NOT NULL,
  `PHOTO` varchar(255) DEFAULT NULL,
  `POSITION_` int(11) NOT NULL,
  `POSITIONINCASH` int(11) NOT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `VAT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  UNIQUE KEY `CATEGORY_NAME` (`CATEGORY_NAME`),
  KEY `PARENT_ID` (`PARENT_ID`),
  CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `product_category` (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'','Sin Clasificar',NULL,0,0,NULL,1),(2,'','MASALIN',NULL,0,0,NULL,3),(3,'','electronica',NULL,0,0,NULL,1),(4,'','ARCOR',NULL,0,0,NULL,1),(5,'\0','FLYN PAFF',NULL,0,0,NULL,1),(6,'','DOLCE',NULL,0,0,NULL,1),(7,'','KABE',NULL,0,0,NULL,1),(8,'','JUGETES',NULL,0,0,NULL,1),(9,'','ALMACEN',NULL,0,0,NULL,1),(10,'','BAGGIO',NULL,0,0,NULL,1),(11,'','ARIEL',NULL,0,0,NULL,1),(12,'','BEBIDAS',NULL,0,0,NULL,1),(13,'','COCA',NULL,0,0,NULL,1),(14,'','MARCELO',NULL,0,0,NULL,1),(15,'','TERRABUSI',NULL,0,0,NULL,1),(16,'','JUAN',NULL,0,0,NULL,1),(17,'','ARCORR',NULL,0,0,NULL,1),(18,'','KIOSCO',NULL,0,0,NULL,1),(19,'','IVESS',NULL,0,0,NULL,1),(20,'','KRACHITOS',NULL,0,0,NULL,1),(21,'','NEHUEN',NULL,0,0,NULL,1),(22,'','NOBLEZA',NULL,0,0,NULL,1),(23,'','PEPSICO',NULL,0,0,NULL,1),(24,'','PEPSY',NULL,0,0,NULL,1),(25,'','TABACO',NULL,0,0,NULL,1),(26,'\0','TARJETAS',NULL,0,0,NULL,1),(27,'','VILLAVICENCIO',NULL,0,0,NULL,1),(28,'\0','55',NULL,0,0,NULL,1),(29,'\0','45',NULL,0,0,NULL,1),(30,'\0','30',NULL,0,0,NULL,1),(31,'\0','100',NULL,0,0,NULL,1),(32,'\0','50',NULL,0,0,NULL,1),(33,'\0','20',NULL,0,0,NULL,1),(34,'','LIBRERIA',NULL,0,0,NULL,1),(35,'','LIBRERu00cdA',NULL,0,0,NULL,1),(36,'','JUGUETES',NULL,0,0,NULL,1),(37,'','SERENISIMA',NULL,0,0,NULL,1),(38,'\0','SUBE',NULL,0,0,NULL,1),(39,'','SUBE2',NULL,0,0,NULL,3),(40,'','asube',NULL,0,0,NULL,3),(41,'','AARIEL',NULL,0,0,NULL,3),(42,'','FIGURITAS',NULL,0,0,NULL,3),(43,'','ATARJETAS',NULL,0,0,NULL,3),(44,'','ATABACO',NULL,0,0,NULL,3),(45,'','ATERRABUSI',NULL,0,0,NULL,3);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-06 16:00:38
