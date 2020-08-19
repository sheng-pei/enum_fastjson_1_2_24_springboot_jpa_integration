CREATE DATABASE IF NOT EXISTS `enum_fastjson_1_2_24_springboot_jpa_integration` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `enum_fastjson_1_2_24_springboot_jpa_integration`;

--
-- Table structure for table `enum_fastjson_1_2_24_springboot_jpa_integration`
--
DROP TABLE IF EXISTS `enum_fastjson_1_2_24_springboot_jpa_integration`;
CREATE TABLE `enum_fastjson_1_2_24_springboot_jpa_integration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
