-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2023 at 04:06 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysticgrills`
--

-- --------------------------------------------------------

--
-- Table structure for table `fooditem`
--

CREATE TABLE `fooditem` (
  `menuItemID` varchar(100) NOT NULL,
  `menuItemName` varchar(100) NOT NULL,
  `menuItemDescription` varchar(100) NOT NULL,
  `menuItemPrice` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fooditem`
--

INSERT INTO `fooditem` (`menuItemID`, `menuItemName`, `menuItemDescription`, `menuItemPrice`) VALUES
('MN001', 'Nasi Goreng', 'Nasi goreng spesial', 25000),
('MN002', 'Mie Ayam', 'Mie ayam pangsit spesial', 20000),
('MN003', 'Sate Ayam', 'Sate ayam dengan bumbu kacang', 30000),
('MN004', 'Gado-gado', 'Gado-gado segar dengan lontong', 28000),
('MN005', 'Bakso', 'Bakso sapi pilihan dengan mie', 22000);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `orderID` varchar(100) NOT NULL,
  `userID` varchar(100) NOT NULL,
  `paymentType` varchar(100) NOT NULL,
  `paymentAmount` int(100) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE `orderdetails` (
  `orderDetailsID` varchar(100) NOT NULL,
  `orderID` varchar(100) NOT NULL,
  `menuID` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` varchar(100) DEFAULT NULL,
  `userRole` varchar(100) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `userEmail` varchar(100) DEFAULT NULL,
  `userPassword` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userRole`, `userName`, `userEmail`, `userPassword`) VALUES
('US197', 'Customer', 'rjayaa', 'raja@gmail.com', '123456'),
('US143', 'Customer', 'asd', 'asd', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `userEmail` (`userEmail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
