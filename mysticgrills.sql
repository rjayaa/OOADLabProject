-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2023 at 10:50 AM
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
(1, 'Spaghetti Bolognese', 'Pasta with meat sauce', 12),
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
(2, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 22:16:16', 5),
(2, 1, 'Margherita Pizza', 'Pending', '2023-12-05 22:16:16', 5),
(3, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 22:17:34', 5),
(3, 1, 'Margherita Pizza', 'Pending', '2023-12-05 22:17:34', 5),
(4, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 22:18:01', 5),
(4, 1, 'Margherita Pizza', 'Pending', '2023-12-05 22:18:01', 5),
(5, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 22:19:40', 5),
(5, 1, 'Grilled Salmon', 'Pending', '2023-12-05 22:19:40', 5),
(6, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:13:08', 5),
(6, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:13:08', 23),
(7, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:13:52', 23),
(7, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:13:52', 12),
(8, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:15:40', 21),
(8, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:15:40', 32),
(9, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:17:15', 3),
(9, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:17:15', 2),
(10, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:17:55', 3),
(10, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:17:55', 12),
(11, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:18:54', 12),
(11, 1, 'Caesar Salad', 'Pending', '2023-12-05 23:18:54', 32),
(12, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:21:04', 21),
(12, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:21:04', 32),
(13, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:23:29', 4),
(13, 1, 'Caesar Salad', 'Pending', '2023-12-05 23:23:29', 2),
(14, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:24:34', 21),
(14, 1, 'Caesar Salad', 'Pending', '2023-12-05 23:24:34', 32),
(15, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:30:14', 3321),
(15, 1, 'Grilled Salmon', 'Pending', '2023-12-05 23:30:14', 1),
(16, 1, 'Chicken Alfredo', 'Pending', '2023-12-05 23:35:19', 21);

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
(2, 5, 5),
(2, 2, 5),
(3, 5, 5),
(3, 2, 5),
(4, 5, 5),
(4, 2, 5),
(5, 5, 5),
(5, 4, 5),
(6, 5, 5),
(6, 4, 23),
(7, 4, 23),
(7, 5, 12),
(8, 5, 21),
(8, 4, 32),
(9, 5, 3),
(9, 4, 2),
(10, 5, 3),
(10, 4, 12),
(11, 5, 12),
(11, 3, 32),
(12, 5, 21),
(12, 4, 32),
(13, 5, 4),
(13, 3, 2),
(14, 5, 21),
(14, 3, 32),
(15, 5, 3321),
(15, 4, 1),
(16, 5, 21);

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
(1, 0, 120, '2023-12-05 22:18:01', 'Credit'),
(6, 5, 145, '2023-12-05 22:19:40', 'Debit'),
(7, 6, 415, '2023-12-05 23:13:08', 'Debit'),
(8, 7, 513, '2023-12-05 23:13:52', 'Cash'),
(9, 8, 774, '2023-12-05 23:15:40', 'Debit'),
(10, 9, 72, '2023-12-05 23:17:15', 'Debit'),
(11, 10, 222, '2023-12-05 23:17:55', 'Debit'),
(12, 11, 424, '2023-12-05 23:18:54', 'Debit'),
(13, 12, 774, '2023-12-05 23:21:04', 'Debit'),
(14, 13, 72, '2023-12-05 23:23:29', 'Cash'),
(15, 14, 550, '2023-12-05 23:24:34', 'Debit'),
(16, 15, 46509, '2023-12-05 23:30:14', 'Debit'),
(17, 16, 294, '2023-12-05 23:35:19', 'Debit');

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
(1, 'Admin', 'admin', 'admin', 'admin'),
(2, 'Admin', 'abcd', 'abcd', 'abcd'),
(3, 'Customer', 'jajaja', 'jaja@gmail.com', '123456');

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
  ADD KEY `orderUser` (`orderUser`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD KEY `menuItemId` (`menuItemId`);

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
  MODIFY `menuItemId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`orderUser`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `orderitem` (`orderId`);

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
