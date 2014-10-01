-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2014 at 06:42 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `applesauce`
--

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE IF NOT EXISTS `departments` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `school` varchar(200) DEFAULT NULL,
  `manager` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`ID`, `school`, `manager`) VALUES
(1, 'SCSE', 'Prof. P.Anand'),
(2, 'SITE', 'Prof. Nita Murugeshwari'),
(3, 'SENSE', 'Prof. Sentil Kumar'),
(4, 'SMBS', 'Prof. Neeyati Goswami');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE IF NOT EXISTS `faculty` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `schoolid` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `designation` varchar(200) DEFAULT NULL,
  `uniqueid` varchar(200) DEFAULT NULL,
  `loggedin` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`ID`, `schoolid`, `name`, `username`, `password`, `designation`, `uniqueid`, `loggedin`) VALUES
(1, 1, 'Senthilnathan', 'snath03', 'd487dd0b55dfcacdd920ccbdaeafa351', 'Prof.', 'd487dd0b55dfcacdd920ccbdaeafa351', 1),
(2, 1, 'Mohan K', 'mk012', '48d6215903dff56238e52e8891380c8f', 'Prof.', '48d6215903dff56238e52e8891380c8f', 0),
(3, 2, 'M. Vishwanathan', 'mv412', 'bda9643ac6601722a28f238714274da4', 'Asst Prof.', 'bda9643ac6601722a28f238714274da4', 0),
(4, 3, 'P. Hilda', 'phil59', '1ffd9e753c8054cc61456ac7fac1ac89', 'Asst Prof.', '1ffd9e753c8054cc61456ac7fac1ac89', 0),
(5, 4, 'Nana Patekar', 'np57', 'f04af61b3f332afa0ceec786a42cd365', 'Asst Prof.', 'f04af61b3f332afa0ceec786a42cd365', 0);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `teacherid` int(11) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `frm` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pfaculty`
--

CREATE TABLE IF NOT EXISTS `pfaculty` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `schoolid` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `cabin` varchar(200) DEFAULT NULL,
  `contact` varchar(200) DEFAULT NULL,
  `opo` varchar(200) DEFAULT NULL,
  `opt` varchar(200) DEFAULT NULL,
  `notice` varchar(200) DEFAULT NULL,
  `available` varchar(200) DEFAULT NULL,
  `uniqueid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `pfaculty`
--

INSERT INTO `pfaculty` (`ID`, `schoolid`, `teacherid`, `name`, `cabin`, `contact`, `opo`, `opt`, `notice`, `available`, `uniqueid`) VALUES
(1, 1, 1, 'Senthilnathan', 'SJT 311', '9677455069', 'MON 02:00 - 3:00 PM', 'TUE 09:00 - 11:00 AM', 'Lets take a day off , shall we ?', 'Available', 'd487dd0b55dfcacdd920ccbdaeafa351'),
(2, 1, 2, 'Mohan K', 'SJT 513', '9871233456', 'MON 12:00 - 1:00 PM', 'FRI 09:00 - 11:00 AM', 'Today is the last day for assignment submission', 'unavailable', 'd487dd0b55dfcacdd920ccbdaeafa351'),
(3, 2, 3, 'M. Vishwanathan', 'SJT 413', '9845873456', 'MON 2:00 - 3:00 PM', 'THUR 10:00 - 11:00 AM', 'Todays class has been calcelled', 'unavailable', 'd487dd0b55dfcacdd920ccbdaeafa351'),
(4, 3, 4, 'P. Hilda', 'SJT 211', '9456773456', 'TUE 5:00 - 7:00 PM', 'THUR 8:00 - 10:00 AM', 'Todays class has been rescheduled to Saturday', 'unavailable', 'd487dd0b55dfcacdd920ccbdaeafa351'),
(5, 4, 5, 'Nana Patekar', 'SJT 313', '9369873456', 'THUR 6:00 - 7:00 PM', 'FRI 11:00 - 12:00 PM', 'Quiz 3 will be held on Thursday , next week', 'Available', 'd487dd0b55dfcacdd920ccbdaeafa351');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
