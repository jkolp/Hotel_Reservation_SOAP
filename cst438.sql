CREATE DATABASE  IF NOT EXISTS `u7a99i86l8a63osd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `u7a99i86l8a63osd`;
-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: rnr56s6e2uk326pj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com    Database: u7a99i86l8a63osd
-- ------------------------------------------------------
-- Server version	5.7.23-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `Rooms`
--

DROP TABLE IF EXISTS `Rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Rooms` (
  `roomID` int(11) NOT NULL AUTO_INCREMENT,
  `hotelID` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `imageURL` varchar(300) NOT NULL,
  `imageURL_2` varchar(300) NOT NULL,
  PRIMARY KEY (`roomID`),
  KEY `hotelID_idx` (`hotelID`),
  CONSTRAINT `hotelID` FOREIGN KEY (`hotelID`) REFERENCES `hotels` (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rooms`
--

LOCK TABLES `Rooms` WRITE;
/*!40000 ALTER TABLE `Rooms` DISABLE KEYS */;
INSERT INTO `Rooms` VALUES (1,6,'Standard',179,'https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/59cca7d7_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/1cd6fa7b_b.jpg'),(2,6,'Intermediate',227,'https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/e00ff4a3_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/57312a6a_b.jpg'),(3,6,'Luxury',320,'https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/787d974d_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/7a548674_b.jpg'),(4,1,'Standard',160,'https://exp.cdn-hotels.com/hotels/1000000/20000/18200/18152/2f7f00c6_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/18200/18152/0303c3ab_b.jpg'),(5,1,'Intermediate',214,'https://exp.cdn-hotels.com/hotels/1000000/20000/18200/18152/13a6d0a2_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/18200/18152/dc621c92_b.jpg'),(6,1,'Luxury',320,'https://exp.cdn-hotels.com/hotels/2000000/1440000/1437400/1437353/6e72a9c5_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/18200/18152/14948339_b.jpg'),(7,2,'Standard',155,'https://exp.cdn-hotels.com/hotels/1000000/20000/12400/12377/f33b141e_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/12400/12377/435f7ad8_b.jpg'),(8,2,'Intermediate',190,'https://exp.cdn-hotels.com/hotels/1000000/20000/12400/12377/0d81ae88_b.jpg','https://exp.cdn-hotels.com/hotels/21000000/20120000/20112900/20112885/f592a4a2_b.jpg'),(9,2,'Luxury',230,'https://exp.cdn-hotels.com/hotels/1000000/180000/170300/170260/4b587cc2_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/12400/12377/afff9894_b.jpg'),(10,3,'Standard',126,'https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/73dba7e8_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/2c8b8ca6_b.jpg'),(11,3,'Intermediate',165,'https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/03d89469_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/a2b79dbd_b.jpg'),(12,3,'Luxury',210,'https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/ed988ae3_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/d9efec17_b.jpg'),(13,4,'Standard',193,'https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/8c1910ec_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/ee8e33b7_b.jpg'),(14,4,'Intermediate',233,'https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/16462e09_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/8db26e2b_b.jpg'),(15,4,'Luxury',310,'https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/36f4d265_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/4ccb70d1_b.jpg'),(16,5,'Standard',119,'https://exp.cdn-hotels.com/hotels/3000000/2170000/2163100/2163014/5e3e4bc3_b.jpg','https://exp.cdn-hotels.com/hotels/3000000/2170000/2163100/2163014/de11b480_b.jpg'),(17,5,'Intermediate',159,'https://exp.cdn-hotels.com/hotels/3000000/2170000/2163100/2163014/e47bd2d2_b.jpg','https://exp.cdn-hotels.com/hotels/3000000/2170000/2163100/2163014/cadf2295_b.jpg'),(18,5,'Luxury',220,'https://exp.cdn-hotels.com/hotels/1000000/30000/24600/24548/114655c0_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/30000/24600/24548/cf5b66f5_b.jpg'),(19,7,'Standard',491,'https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/89f6e5e8_b.jpg','https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/b49f24dc_b.jpg'),(20,7,'Intermediate',900,'https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/48376889_b.jpg','https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/cabba6e0_b.jpg'),(21,7,'Luxury',1493,'https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/1797687d_b.jpg','https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/c3e13cdc_b.jpg'),(22,8,'Standard',209,'https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/8e885362_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/92d5ee23_b.jpg'),(23,8,'Intermediate',279,'https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/8462b16b_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/718d308a_b.jpg'),(24,8,'Luxury',330,'https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/7e0b0248_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/b63bcd05_b.jpg'),(25,9,'Standard',390,'https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/f39f260b_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/0f85231b_b.jpg'),(26,9,'Intermediate',890,'https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/486e3e01_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/2c01cfde_b.jpg'),(27,9,'Luxury',1698,'https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/35eb0ba3_b.jpg','https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/a75bcd28_b.jpg');
/*!40000 ALTER TABLE `Rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'kolp','jasper','kolpjasper@gmail.com'),(2,'terrill','roger','email@email.com'),(3,'lee','michael','michael9j2lee@gmail.com'),(4,'hurt','bill','billhurt@yahoo.com'),(5,'kid','billy','billkid@yahoo.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotels` (
  `hotelID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `description` text NOT NULL,
  `rating` decimal(4,1) DEFAULT NULL,
  `imageURL` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
INSERT INTO `hotels` VALUES (1,'Hyatt Regency Monterey Hotel & Spa','Monterey','1 Old Golf Course Rd, Monterey CA 93940 USA','Book HyettRegency Monterey For Instant Savings! - Book Now. Great Rates in Seconds. Real Guest Reviews. 24/7 Customer Service. Check savings and offers. Deals up to 50% off. Amenities: Secure Incredible Value, Expert Advice & Support, Book Online or Call.',4.0,'https://exp.cdn-hotels.com/hotels/1000000/20000/18200/18152/b12c5aaa_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(2,'The Monterey Hotel','Monterey','407 Calle Principal, Monterey CA 93940 USA','The Monterey Hotel. Price Guarantee. Browse Reviews & Photos. Our Customer Service Team is on Hand Day and Night to Personally Guide You. Save with Secret Prices. Family-Friendly Hotels. Flexible Payment Options. Last Minute Hotel Deals. Deals of the Day.',4.0,'https://exp.cdn-hotels.com/hotels/1000000/20000/12400/12377/d1fdaaee_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(3,'Casa Munras Garden Hotel & Spa','Monterey','700 Munras Ave, Monterey CA 93940 USA','Currently Remains Open & Committed to a Safe Hospitality Experience in a Nature! Centrally Located In Historic Downtown Monterey, Minutes From World-Renowned Attractions. Pet-Friendly Hotel. Family Friendly Hotel. Commitment to Your Safety. We are Open.',3.8,'https://exp.cdn-hotels.com/hotels/1000000/40000/35900/35893/9e85d352_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(4,'Omni San Diego Hotel','San Diego','675 L St, San Diego CA 92101 USA','Located in the heart of the historic Gaslamp Quarter and across from the convention center, our hotel puts you close to the city\'s most popular tourist attractions.',4.5,'https://exp.cdn-hotels.com/hotels/1000000/990000/982500/982408/b3023fcb_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(5,'Westgate Hotel','San Diego','1055 Second Ave, San Diego CA 92101 USA','Save up to 55% on your Attraction Tickets, Shuttle Transportation and More. Find the Lowest Villa Packages when you Book with Westgate Reservations. Package deals. Best price guarantee. Expert friendly advice. Pet friendly. Make your reservation.',4.8,'https://exp.cdn-hotels.com/hotels/1000000/30000/24600/24548/cda2e716_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(6,'Rancho Bernardo Inn San Diego','San Diego','17550 Bernardo Oaks Dr, San Diego CA 92128 USA','Summer Call for an Escape to Rancho Bernardo to Recharge - Your 4th Night Is on Us & More! Drive Into San Diego This Summer for a Perfect Getaway. Free Parking & Daily Credit! Gift Cards Available. Take A Tour. Book Online. Amenities: Swimming Pool, Spa, Restaurant.',4.3,'https://exp.cdn-hotels.com/hotels/1000000/10000/3100/3007/76a62f4d_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(7,'Gaia Hawaii at Trump Waikiki Hotel','Honolulu','223 Saratoga Road, Honolulu HI 96815 USA','Gaia Hawaii at Trump Waikiki Hotel. Gaia Hawaii at Trump Waikiki Hotel offers 7 air-conditioned accommodations with irons/ironing boards and complimentary toiletries. Flat-screen televisions are featured in guestrooms. Guests can surf the web using the complimentary wireless Internet access.',4.9,'https://exp.cdn-hotels.com/hotels/20000000/19250000/19240400/19240301/e4eb4ece_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(8,'Lotus Honolulu at Diamond Head','Honolulu','2885 Kalakaua Ave, Honolulu HI 96815 USA','Opposite Kapiolani Park, this polished hotel with views of the Pacific Ocean and Diamond Head volcanic peak is 3 miles from Ala Moana, a shopping mall, and 4 miles from the Honolulu Museum of Art. \nStylish, airy rooms offer WiFi, iPod docks, flat-screens, coffeemakers and private lanais. Most add ocean views. A penthouse suite features 2 bedrooms, a kitchen and a wraparound terrace.',4.2,'https://exp.cdn-hotels.com/hotels/1000000/70000/63200/63177/12bef01d_z.jpg?impolicy=fcrop&w=773&h=530&q=high'),(9,'Outrigger Waikiki Beach Resort','Honolulu','2335 Kalakaua Ave, Honolulu HI 96815 USA','Set on Waikiki Beach, this upscale oceanfront resort is 0.8 miles from the Honolulu Zoo, 1 mile from the Waikiki Aquarium and 1.9 miles from Ala Moana Beach Park.\nThe understated rooms include tropical wood furniture, coffeemakers and free Wi-Fi; some have balconies with ocean views. Suites add panoramic ocean vistas, whirlpool tubs and pull-out sofas.',4.6,'https://exp.cdn-hotels.com/hotels/1000000/20000/13800/13791/284f3af6_z.jpg?impolicy=fcrop&w=773&h=530&q=high');
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `resID` int(11) NOT NULL AUTO_INCREMENT,
  `hotelID` int(11) NOT NULL,
  `customerID` int(11) DEFAULT NULL,
  `hotel_name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `customer_email` varchar(100) DEFAULT NULL,
  `check_in` varchar(25) DEFAULT NULL,
  `check_out` varchar(25) DEFAULT NULL,
  `num_days` int(11) DEFAULT NULL,
  `num_standard_room` int(11) DEFAULT NULL,
  `num_intermediate_room` int(11) DEFAULT NULL,
  `num_luxury_room` int(11) DEFAULT NULL,
  `total_rooms` int(11) DEFAULT NULL,
  `price_standard_room` int(11) NOT NULL,
  `price_intermediate_room` int(11) DEFAULT NULL,
  `price_luxury_room` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `cancelled` int(11) DEFAULT NULL,
  PRIMARY KEY (`resID`,`price_standard_room`),
  KEY `hotelID` (`hotelID`),
  KEY `reservations_ibfk_2_idx` (`customerID`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`hotelID`) REFERENCES `hotels` (`hotelID`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,7,1,'Gaia Hawaii at Trump Waikiki Hotel','223 Saratoga Road, Honolulu HI 96815 USA','kolpjasper@gmail.com','08/13/2020','08/21/2020',8,2,0,0,2,982,0,0,7856,0),(2,1,2,'Hyatt Regency Monterey Hotel & Spa','1 Old Golf Course Rd, Monterey CA 93940 USA','email@email.com','1/1/2020','1/2/2020',1,1,0,0,1,160,0,0,160,0),(3,5,3,'Westgate Hotel','1055 Second Ave, San Diego CA 92101 USA','michael9j2lee@gmail.com','08/14/2020','08/16/2020',2,0,0,1,1,0,0,220,220,1),(4,1,3,'Hyatt Regency Monterey Hotel & Spa','1 Old Golf Course Rd, Monterey CA 93940 USA','michael9j2lee@gmail.com','1/1/2020','1/2/2020',1,0,0,1,1,0,0,320,320,0),(5,3,4,'Casa Munras Garden Hotel & Spa','700 Munras Ave, Monterey CA 93940 USA','billhurt@yahoo.com','08/14/2020','08/15/2020',1,0,0,1,1,0,0,210,210,0),(6,7,5,'Gaia Hawaii at Trump Waikiki Hotel','223 Saratoga Road, Honolulu HI 96815 USA','billkid@yahoo.com','08/15/2020','08/21/2020',6,5,1,0,6,2455,900,0,20130,1),(7,7,1,'Gaia Hawaii at Trump Waikiki Hotel','223 Saratoga Road, Honolulu HI 96815 USA','kolpjasper@gmail.com','08/14/2020','08/20/2020',6,3,0,0,3,1473,0,0,8838,0);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'u7a99i86l8a63osd'
--
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-14 17:01:39
