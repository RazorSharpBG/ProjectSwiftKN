-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(60) NOT NULL,
  `Loan` double NOT NULL,
  `Available` int(11) NOT NULL,
  `Available_days` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Title_UNIQUE` (`Title`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Avengers',5.5,5,3),(2,'Despicable me',4,3,5),(3,'Star wars',10,6,5),(4,'Fast and Furious',7.5,4,2),(5,'Harry Potter',7,3,4);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `gsm` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(100) NOT NULL,
  `tax` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `gsm_UNIQUE` (`gsm`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Kiril Tenchev','0898547621','abv@abv.bg','Sofiq,Mladost',0),(2,'Mladen','0898230065','kkkk@abv.bg','Sofiq',0),(3,'Petka','0897451244','abvabv@abv.bg','Plovdiv',0),(4,'Ivan','0898754121','wtf@abv.bg','Plovdiv',0),(5,'Nikola','0888546457','wow@abv.bg','Pernik',0),(6,'Nikolai','0888775544','wht@abv.bg','Petrich',0),(7,'Ivan Doinenski','0897542144','ivan@abv.bg','Plovdiv',0),(8,'Petar Ivanov','0897554466','pesho@gmail.com','Ruse',0);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_movies`
--

DROP TABLE IF EXISTS `person_movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_movies` (
  `id_pm` int(11) NOT NULL AUTO_INCREMENT,
  `id_person` int(11) NOT NULL,
  `id_movies` int(11) NOT NULL,
  `get_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `return_date` date DEFAULT NULL,
  PRIMARY KEY (`id_pm`),
  UNIQUE KEY `id_pm_UNIQUE` (`id_pm`),
  KEY `id_movies_idx` (`id_movies`),
  KEY `id_person_idx` (`id_person`),
  CONSTRAINT `id_movies` FOREIGN KEY (`id_movies`) REFERENCES `movies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_movies`
--

LOCK TABLES `person_movies` WRITE;
/*!40000 ALTER TABLE `person_movies` DISABLE KEYS */;
INSERT INTO `person_movies` VALUES (1,4,1,'2018-02-03 10:59:09',NULL),(2,4,1,'2018-02-03 15:31:08','2018-02-03'),(3,1,3,'2018-02-03 15:33:49','2018-02-03'),(4,1,2,'2018-02-03 15:36:01','2018-02-03'),(5,4,2,'2018-02-03 15:37:36','2018-02-03'),(6,4,3,'2018-02-03 15:44:29','2018-02-03'),(7,4,4,'2018-02-03 15:46:02','2018-02-03'),(8,4,1,'2018-02-03 15:49:51','2018-02-03'),(9,4,1,'2018-02-03 15:50:02','2018-02-03');
/*!40000 ALTER TABLE `person_movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-03 17:51:01
