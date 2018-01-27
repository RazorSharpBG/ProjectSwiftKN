-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projekt
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
  `ID` int(11) NOT NULL,
  `Title` varchar(60) NOT NULL,
  `Loan` double NOT NULL,
  `Available` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Star Wars 1',2.5,3),(2,'Star Wars 2',3,4),(3,'Star Wars 3',4,5),(4,'The Godfather Trilogy',5,5),(5,'Fast and furious 1',4.5,10),(6,'Dispecable me 1',4,3),(7,'Harry Poter 1',5,2),(8,'Harry Poter 2',5,1),(9,'Harry Poter 3',5,1),(10,'Underworld Trilogy ',10,4);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `GSM` varchar(20) NOT NULL,
  `Adress` varchar(100) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Tax` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `GSM_UNIQUE` (`GSM`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Georgi Petkov','878888859','Sofia','abv@abv.bg',0),(2,'Petko Bozhinov','878888759','Sofia','bv@abv.bg',0),(3,'Kiril Tenchev','875888759','Sofia','v@abv.bg',0),(4,'Nikola Peshev','875848759','Sofia','np@abv.bg',0),(5,'Valentin Ivanov','875843759','Sofia','vi@abv.bg',0);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_movies`
--

DROP TABLE IF EXISTS `person_movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_movies` (
  `ID_PM` int(11) NOT NULL AUTO_INCREMENT,
  `ID-Person` int(11) NOT NULL,
  `ID-Movie` int(11) NOT NULL,
  PRIMARY KEY (`ID_PM`),
  UNIQUE KEY `ID_PM_UNIQUE` (`ID_PM`),
  KEY `ID-Movie_idx` (`ID-Movie`),
  KEY `ID-Person_idx` (`ID-Person`),
  CONSTRAINT `ID-Movie` FOREIGN KEY (`ID-Movie`) REFERENCES `movies` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ID-Person` FOREIGN KEY (`ID-Person`) REFERENCES `person` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_movies`
--

LOCK TABLES `person_movies` WRITE;
/*!40000 ALTER TABLE `person_movies` DISABLE KEYS */;
INSERT INTO `person_movies` VALUES (1,1,1),(2,1,2),(3,1,3);
/*!40000 ALTER TABLE `person_movies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-27  9:43:06
