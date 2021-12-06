-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 06 Des 2021 pada 10.37
-- Versi Server: 5.7.36-0ubuntu0.18.04.1
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tgs_sms1`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `items`
--

CREATE TABLE `items` (
  `code` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `sell_price` int(7) NOT NULL,
  `buy_price` int(7) NOT NULL,
  `supplier_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `items`
--

INSERT INTO `items` (`code`, `name`, `sell_price`, `buy_price`, `supplier_id`) VALUES
('BYG', 'Baygon', 20000, 16000, 'KIOPK'),
('RTSSR', 'Roti Sisir', 850, 1000, 'MRPT'),
('STLJRUK', 'Stella Jeruk', 12000, 15000, 'KIOPK');

-- --------------------------------------------------------

--
-- Struktur dari tabel `suppliers`
--

CREATE TABLE `suppliers` (
  `id` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `contact` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `address`, `contact`) VALUES
('KIOPK', 'Putra ', 'Jember', '033245617'),
('MRPT', 'Merpati', 'Jember', '081098567489'),
('RHIL', 'Reva Hill', 'Jakarta Pusat', '087658098548');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transactions`
--

CREATE TABLE `transactions` (
  `code` varchar(15) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `total` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transactions`
--

INSERT INTO `transactions` (`code`, `user_id`, `date`, `time`, `total`) VALUES
('00.128.559.68', 'KSR1', '2021-12-05', '19:30:07', 0),
('00.130.355.78', 'KSR1', '2021-12-06', '00:32:59', 0),
('00.149.513.95', 'KSR1', '2021-12-05', '20:16:56', 0),
('00.152.422.7', 'KSR1', '2021-12-05', '20:19:06', 0),
('00.191.162.95', 'KSR1', '2021-12-05', '20:18:38', 0),
('00.204.654.77', 'KSR1', '2021-12-05', '20:18:51', 0),
('00.218.757.36', 'KSR1', '2021-12-05', '19:28:46', 40000),
('00.220.701.31', 'KSR1', '2021-12-05', '19:29:00', 0),
('00.309.796.67', 'KSR1', '2021-12-06', '00:34:32', 1700),
('00.342.783.56', 'KSR1', '2021-12-06', '00:30:21', 0),
('00.376.851.82', 'KSR1', '2021-12-06', '10:34:11', 24000),
('00.445.771.18', 'KSR1', '2021-12-05', '20:19:29', 0),
('00.659.673.67', 'KSR1', '2021-12-05', '19:28:04', 0),
('00.716.181.37', 'KSR1', '2021-12-05', '19:29:50', 2550),
('00.717.613.18', 'KSR1', '2021-12-06', '00:29:58', 20000),
('00.771.279.91', 'KSR1', '2021-12-06', '10:35:46', 0),
('00.814.912.45', 'KSR1', '2021-12-05', '20:20:05', 0),
('00.830.265.15', 'KSR1', '2021-12-05', '19:27:34', 40000),
('00.839.168.47', 'KSR1', '2021-12-06', '00:34:56', 0),
('00.892.442.60', 'KSR1', '2021-12-05', '20:16:26', 0),
('00.968.179.5', 'KSR1', '2021-12-05', '20:19:47', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaction_detail`
--

CREATE TABLE `transaction_detail` (
  `transaction_code` varchar(15) NOT NULL,
  `item_code` varchar(10) NOT NULL,
  `total` int(7) NOT NULL,
  `qty` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaction_detail`
--

INSERT INTO `transaction_detail` (`transaction_code`, `item_code`, `total`, `qty`) VALUES
('00.830.265.15', 'BYG', 40000, 2),
('00.218.757.36', 'BYG', 40000, 2),
('00.716.181.37', 'RTSSR', 2550, 3),
('00.717.613.18', 'BYG', 20000, 1),
('00.309.796.67', 'RTSSR', 1700, 2),
('00.376.851.82', 'STLJRUK', 24000, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` enum('Admin','Kasir') NOT NULL DEFAULT 'Kasir'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
('Admn1', 'admin', 'admin', 'Admin'),
('KSR1', 'Kasir', 'kasir', 'Kasir'),
('KSR2', 'Kasir2', 'kasir2', 'Kasir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`code`),
  ADD KEY `supplier_id` (`supplier_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`code`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD KEY `item_code` (`item_code`),
  ADD KEY `transaction_code` (`transaction_code`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `items_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD CONSTRAINT `transaction_detail_ibfk_1` FOREIGN KEY (`item_code`) REFERENCES `items` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_detail_ibfk_2` FOREIGN KEY (`transaction_code`) REFERENCES `transactions` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
