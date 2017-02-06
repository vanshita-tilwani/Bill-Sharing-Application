-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: BillShare
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `Event`
--

DROP TABLE IF EXISTS `Event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Event` (
  `EName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Event`
--

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;
INSERT INTO `Event` VALUES ('Birthday Party'),('Holiday Trip'),('Anniversery'),('New Year'),('Christmas'),('Diwali'),('Holi');
/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Friend`
--

DROP TABLE IF EXISTS `Friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Friend` (
  `Lender_Name` varchar(30) DEFAULT NULL,
  `Receiver_Name` varchar(30) DEFAULT NULL,
  `Amount` double(10,3) DEFAULT '0.000'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friend`
--

LOCK TABLES `Friend` WRITE;
/*!40000 ALTER TABLE `Friend` DISABLE KEYS */;
INSERT INTO `Friend` VALUES ('shruti','shru',4.000),('shruti','guptaayushi9',0.000),('richa','shru',24.000),('guptaayushi9','shruti',20.000);
/*!40000 ALTER TABLE `Friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GroupT`
--

DROP TABLE IF EXISTS `GroupT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupT` (
  `G_ID` varchar(30) DEFAULT NULL,
  `GName` varchar(30) DEFAULT NULL,
  `EName` varchar(30) DEFAULT NULL,
  `DOJ` date DEFAULT NULL,
  `MName` varchar(30) DEFAULT NULL,
  `Invested_amt` double(10,5) DEFAULT '0.00000',
  `Desig` varchar(20) DEFAULT 'Member',
  `Shared_amt` double(10,3) DEFAULT '0.000',
  KEY `MName` (`MName`),
  CONSTRAINT `GroupT_ibfk_1` FOREIGN KEY (`MName`) REFERENCES `User` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GroupT`
--

LOCK TABLES `GroupT` WRITE;
/*!40000 ALTER TABLE `GroupT` DISABLE KEYS */;
INSERT INTO `GroupT` VALUES ('ABCD','1A',NULL,'2016-04-17','shruti',28.00000,'Admin',0.000),('','',NULL,NULL,NULL,0.00000,'Member',0.000),('102','b',NULL,NULL,NULL,0.00000,'Member',0.000),('102','b',NULL,NULL,NULL,0.00000,'Member',0.000),('ABCD','1A',NULL,'2016-04-18','deerga',40.00000,'Member',0.000),('Alpha','alp',NULL,NULL,NULL,0.00000,'Member',0.000),('ABCD','1A',NULL,'2016-04-18','anu',45.00000,'Member',0.000),('','',NULL,NULL,NULL,0.00000,'Member',0.000),('KINS','kin1','Party','2016-04-20','shruti',0.00000,'Admin',0.000),('Spy','Spykids','Diwali','2016-04-20','shruti',0.00000,'Admin',0.000),('101','a','Anniversary','2015-05-14','guptaayushi9',2.00000,'Member',0.000),('101','a','New Year','2016-05-14','shruti',0.00000,'Member',0.000),('12345','Asdf','Holi','2016-04-20','shruti',0.00000,'Admin',0.000),('12345','Asdf',NULL,'2016-04-18','anu',5.00000,'Member',0.000);
/*!40000 ALTER TABLE `GroupT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `FName` varchar(30) DEFAULT NULL,
  `LName` varchar(30) DEFAULT NULL,
  `Username` varchar(30) NOT NULL DEFAULT '',
  `Email_ID` varchar(30) DEFAULT NULL,
  `Password` varchar(30) DEFAULT NULL,
  `Current_Balance` double(6,3) DEFAULT NULL,
  `You_owe` double(6,3) DEFAULT NULL,
  `Owed` double(6,3) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('Aastha ','Bhardwaj','aastha','aasthabkn@gmail.com','aastha',100.000,59.000,29.000),('Aditi','Arora','aditi2896','aditiarora2896@gmail.com','aditi',126.000,23.000,10.000),('Anadi','Saxena','anadi','anadisaxena156@gmail.com','anadi',10.000,1.000,0.000),('Annu','Purohit','anu','annu@gmail.com','anu123',100.000,65.000,90.000),('Deerga','Sharma','deerga','deerga17@gmail.com','1710',540.000,121.000,45.000),('Ayushi','Gupta','guptaayushi9','guptaayu@gmail.com','ayushi1097',257.000,240.000,313.000),('Meha','Mittal','Meha','mehamittalmeha@gmail.com','meha',100.000,88.000,77.000),('Prakshi','Yadav','Pra','yprakshi@gmail.com','shruti',200.000,43.000,11.000),('Qwert','Sharma','qwert','quert@gmail.com','qwert',100.000,29.000,13.000),('Richa','Jain','richa','richajain2206@gmail.com','richa',700.000,480.000,51.000),('Shruti','Sharma','shru','shruti512@gmail.com','shruti',332.000,54.000,-6.000),('shruti','sharma','shruti','shruti@gmail.com','shruti',-47.000,334.000,230.000),('tina','sharma','tina','tina@gmail.com','tina',100.000,10.000,6.000),('Vanshita ','Tilwani','vanshita23','cutevanshi@gmail.com','vanshi123',850.000,500.000,320.000);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-10 18:26:38
