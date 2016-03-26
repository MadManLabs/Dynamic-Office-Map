-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: dynamaps
-- ------------------------------------------------------
-- Server version	5.5.46

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
-- Table structure for table `desk`
--

DROP TABLE IF EXISTS `desk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `desk` (
  `id` varchar(255) NOT NULL,
  `version_number` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `inactive` bit(1) NOT NULL,
  `xlayout` varchar(255) NOT NULL,
  `ylayout` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desk`
--

LOCK TABLES `desk` WRITE;
/*!40000 ALTER TABLE `desk` DISABLE KEYS */;
/*!40000 ALTER TABLE `desk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `floor` (
  `id` varchar(255) NOT NULL,
  `version_number` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `inactive` bit(1) NOT NULL,
  `map` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor_desk`
--

DROP TABLE IF EXISTS `floor_desk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `floor_desk` (
  `id` varchar(255) NOT NULL,
  `version_number` bigint(20) NOT NULL,
  `inactive` bit(1) NOT NULL,
  `desk_id` varchar(255) DEFAULT NULL,
  `floor_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kpb2whhovex893o8m43fh9ovq` (`desk_id`),
  KEY `FK_a6plda6gytgyaw8srxwisupdh` (`floor_id`),
  CONSTRAINT `FK_a6plda6gytgyaw8srxwisupdh` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`id`),
  CONSTRAINT `FK_kpb2whhovex893o8m43fh9ovq` FOREIGN KEY (`desk_id`) REFERENCES `desk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor_desk`
--

LOCK TABLES `floor_desk` WRITE;
/*!40000 ALTER TABLE `floor_desk` DISABLE KEYS */;
/*!40000 ALTER TABLE `floor_desk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` varchar(255) NOT NULL,
  `version_number` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `inactive` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_desk`
--

DROP TABLE IF EXISTS `person_desk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_desk` (
  `id` varchar(255) NOT NULL,
  `version_number` bigint(20) NOT NULL,
  `inactive` bit(1) NOT NULL,
  `desk_id` varchar(255) DEFAULT NULL,
  `person_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pexn3n05dgna35o7si9ilhvpi` (`desk_id`),
  KEY `FK_88dvjbe71d53vx4m2eqijtu2r` (`person_id`),
  CONSTRAINT `FK_88dvjbe71d53vx4m2eqijtu2r` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_pexn3n05dgna35o7si9ilhvpi` FOREIGN KEY (`desk_id`) REFERENCES `desk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_desk`
--

LOCK TABLES `person_desk` WRITE;
/*!40000 ALTER TABLE `person_desk` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_desk` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-26 15:39:14
