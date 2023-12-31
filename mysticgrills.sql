-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2023 at 08:39 PM
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
-- Table structure for table `menuitem`
--

CREATE TABLE `menuitem` (
  `menuItemId` int(11) NOT NULL,
  `menuItemName` varchar(100) NOT NULL,
  `menuItemDescription` varchar(100) NOT NULL,
  `menuItemPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menuitem`
--

INSERT INTO `menuitem` (`menuItemId`, `menuItemName`, `menuItemDescription`, `menuItemPrice`) VALUES
(2, 'Margherita Pizza', 'Pizza topped with tomato, mozzarella, and basil', 10),
(3, 'Caesar Salad', 'Romaine lettuce, croutons, parmesan cheese, and Caesar dressing', 8),
(4, 'Grilled Salmon', 'Salmon fillet with lemon butter sauce', 15),
(5, 'Chicken Alfredo', 'Pasta with chicken and creamy Alfredo sauce', 14);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `orderId` int(11) NOT NULL,
  `orderUser` int(11) NOT NULL,
  `orderItems` varchar(100) NOT NULL,
  `orderStatus` varchar(100) NOT NULL,
  `orderDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `orderTotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`orderId`, `orderUser`, `orderItems`, `orderStatus`, `orderDate`, `orderTotal`) VALUES
(2, 13, 'Chicken Alfredo,Grilled Salmon', 'Prepared', '2023-12-20 19:00:34', 5);

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `orderId` int(11) NOT NULL,
  `menuItemId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitem`
--

INSERT INTO `orderitem` (`orderId`, `menuItemId`, `quantity`) VALUES
(2, 5, 3),
(2, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `receiptId` int(11) NOT NULL,
  `receiptOrder` int(11) NOT NULL,
  `receiptPaymentAmount` int(11) NOT NULL,
  `receiptPaymentDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `receiptPaymentType` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`receiptId`, `receiptOrder`, `receiptPaymentAmount`, `receiptPaymentDate`, `receiptPaymentType`) VALUES
(1, 2, 71, '2023-12-20 19:00:34', 'Credit');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `userRole` varchar(100) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `userEmail` varchar(100) NOT NULL,
  `userPassword` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userRole`, `userName`, `userEmail`, `userPassword`) VALUES
(12, 'Admin', 'admin', 'admin', 'admin'),
(13, 'Customer', 'customer', 'customer', 'customer'),
(14, 'Chef', 'chef', 'chef', 'chef'),
(15, 'Waiter', 'waiter', 'waiter', 'waiter'),
(16, 'Cashier', 'cashier', 'cashier', 'cashier');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menuitem`
--
ALTER TABLE `menuitem`
  ADD PRIMARY KEY (`menuItemId`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `orderUser` (`orderUser`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD KEY `menuItemId` (`menuItemId`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receiptId`),
  ADD KEY `receipt_ibfk_1` (`receiptOrder`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menuitem`
--
ALTER TABLE `menuitem`
  MODIFY `menuItemId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `receiptId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`orderUser`) REFERENCES `user` (`userId`);

--
-- Constraints for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`menuItemId`) REFERENCES `menuitem` (`menuItemId`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`receiptOrder`) REFERENCES `order` (`orderId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
