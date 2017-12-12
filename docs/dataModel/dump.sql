-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: queuebrain
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `qb_queues`
--

DROP TABLE IF EXISTS `qb_queues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_queues` (
  `QUEUE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUEUE_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`QUEUE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qb_queues`
--

LOCK TABLES `qb_queues` WRITE;
/*!40000 ALTER TABLE `qb_queues` DISABLE KEYS */;
INSERT INTO `qb_queues` VALUES (1,'first'),(2,'second');
/*!40000 ALTER TABLE `qb_queues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qb_users`
--

DROP TABLE IF EXISTS `qb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_users` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) DEFAULT NULL,
  `USER_CONTACTS` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qb_users`
--

LOCK TABLES `qb_users` WRITE;
/*!40000 ALTER TABLE `qb_users` DISABLE KEYS */;
INSERT INTO `qb_users` VALUES (1,'Mike',NULL),(2,'Kolya',NULL),(3,'Natali',NULL);
/*!40000 ALTER TABLE `qb_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qb_users_tables`
--

DROP TABLE IF EXISTS `qb_users_tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qb_users_tables` (
  `USER_ID` int(11) NOT NULL,
  `QUEUE_ID` int(11) NOT NULL,
  `REFERENCE_TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`QUEUE_ID`),
  KEY `fk_QB_USERS_has_QB_QUEUES_QB_QUEUES1_idx` (`QUEUE_ID`),
  KEY `fk_QB_USERS_has_QB_QUEUES_QB_USERS1_idx` (`USER_ID`),
  CONSTRAINT `fk_QB_USERS_has_QB_QUEUES_QB_QUEUES1` FOREIGN KEY (`QUEUE_ID`) REFERENCES `qb_queues` (`QUEUE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_QB_USERS_has_QB_QUEUES_QB_USERS1` FOREIGN KEY (`USER_ID`) REFERENCES `qb_users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qb_users_tables`
--

LOCK TABLES `qb_users_tables` WRITE;
/*!40000 ALTER TABLE `qb_users_tables` DISABLE KEYS */;
INSERT INTO `qb_users_tables` VALUES (1,1,0),(2,1,1),(3,1,1),(3,2,0);
/*!40000 ALTER TABLE `qb_users_tables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-11 17:54:51
