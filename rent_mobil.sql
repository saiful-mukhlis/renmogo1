-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 30, 2013 at 02:37 AM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rent mobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `bos`
--

CREATE TABLE IF NOT EXISTS `bos` (
  `bos_id` bigint(20) NOT NULL,
  `jml` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bos`
--

INSERT INTO `bos` (`bos_id`, `jml`, `name`) VALUES
(1, 19917, 'jmllog'),
(2, 0, 'LHJWE');

-- --------------------------------------------------------

--
-- Table structure for table `hak_akses`
--

CREATE TABLE IF NOT EXISTS `hak_akses` (
  `hak_akses_id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `hak_akses` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hak_akses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hak_akses`
--

INSERT INTO `hak_akses` (`hak_akses_id`, `code`, `hak_akses`, `nama`) VALUES
(1, '000001', 'x1xx2xx3xx4xx5xx6xx7xx8xx9xx10xx11xx12xx13xx14xx15xx16xx17xx18xx19xx20xx21xx22xx23xx24xx25xx26xx27xx28xx29xx30xx31xx32xx33xx34xx35xx36xx37xx38xx39xx40xx41xx42xx43xx44xx45xx46xx47xx48xx49x', 'Administrator'),
(2, '000002', '', 'Pegawai');

-- --------------------------------------------------------

--
-- Table structure for table `kembali`
--

CREATE TABLE IF NOT EXISTS `kembali` (
  `kembali_id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `total_denda` decimal(19,2) DEFAULT NULL,
  `waktu_kembali` datetime DEFAULT NULL,
  `pegawai_id` bigint(20) DEFAULT NULL,
  `sewa_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`kembali_id`),
  KEY `FK31FADAAF15883A31` (`sewa_id`),
  KEY `FK31FADAAFE5F19663` (`pegawai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kembali`
--

INSERT INTO `kembali` (`kembali_id`, `code`, `tgl_kembali`, `total_denda`, `waktu_kembali`, `pegawai_id`, `sewa_id`) VALUES
(1, '000001', '1970-01-01', 60000.00, '1970-01-01 18:13:00', 1, 1),
(2, '000002', '1969-12-31', 140000.00, '1969-12-31 23:58:00', 1, 2),
(3, '000003', '1970-01-01', 40000.00, '1970-01-01 20:54:00', 1, 3),
(4, '000004', '2012-11-14', 110000.00, '2012-11-14 18:06:00', 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `kembalid`
--

CREATE TABLE IF NOT EXISTS `kembalid` (
  `kembalid_id` bigint(20) NOT NULL,
  `denda` decimal(19,2) DEFAULT NULL,
  `waktu_lebih` decimal(19,2) DEFAULT NULL,
  `kembali_id` bigint(20) DEFAULT NULL,
  `sewad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`kembalid_id`),
  KEY `FKD607B959B812DA3` (`sewad_id`),
  KEY `FKD607B9597959603` (`kembali_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kembalid`
--

INSERT INTO `kembalid` (`kembalid_id`, `denda`, `waktu_lebih`, `kembali_id`, `sewad_id`) VALUES
(2, 140000.00, 14.00, 2, 2),
(3, 40000.00, 4.00, 3, 3),
(4, 110000.00, 11.00, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE IF NOT EXISTS `mobil` (
  `mobil_id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `ket` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `type_mobil_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mobil_id`),
  KEY `FK4710403F6EE378E` (`type_mobil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`mobil_id`, `code`, `ket`, `status`, `type_mobil_id`) VALUES
(6, '000006', '-', 0, 2),
(7, '000007', '-', 0, 2),
(8, '000008', '-', 0, 2),
(9, '000009', '-', 0, 2),
(10, '000010', '-', 0, 2),
(11, '000011', '-', 0, 2),
(12, '000012', '-', 0, 2),
(13, '000013', '-', 0, 2),
(14, '000014', '-', 0, 2),
(15, '000015', '-', 0, 2),
(16, '000016', '-', 0, 2),
(17, '000017', '-', 0, 2),
(18, '000018', '-', 0, 2),
(19, '000019', '-', 0, 2),
(20, '000020', '-', 0, 2),
(21, '000021', '-', 0, 2),
(22, '000022', '-', 0, 2),
(23, '000023', '-', 0, 2),
(24, '000024', '-', 0, 2),
(25, '000025', '-', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE IF NOT EXISTS `pegawai` (
  `pegawai_id` bigint(20) NOT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `hp` varchar(255) DEFAULT NULL,
  `jenis_identitas` varchar(255) DEFAULT NULL,
  `kota` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `no_identitas` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `hak_akses_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pegawai_id`),
  KEY `FK3A2552D0D64C3F3E` (`hak_akses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`pegawai_id`, `alamat`, `code`, `hp`, `jenis_identitas`, `kota`, `nama`, `no_identitas`, `password`, `status`, `username`, `hak_akses_id`) VALUES
(1, '-', '000001', '-', '-', '-', 'Admin', '-', '21232f297a57a5a743894a0e4a801fc3', 2, 'admin', 1),
(2, 'Jl. TUnungan', '000002', '97423598798', 'KTP', 'Surabaya', 'Master1', '246579', '0cc175b9c0f1b6a831c399e269772661', 2, 'a', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE IF NOT EXISTS `pelanggan` (
  `pelanggan_id` bigint(20) NOT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `hp` varchar(255) DEFAULT NULL,
  `jenis_identitas` varchar(255) DEFAULT NULL,
  `kota` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `no_identitas` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`pelanggan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`pelanggan_id`, `alamat`, `code`, `hp`, `jenis_identitas`, `kota`, `nama`, `no_identitas`, `status`) VALUES
(1, 'Jl. akshdfkjas', '000001', '298749872348', 'KTP', 'Surabaya', 'Pelanggan 1', '23457983475', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sewa`
--

CREATE TABLE IF NOT EXISTS `sewa` (
  `sewa_id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `tgl_trx` date DEFAULT NULL,
  `pegawai_id` bigint(20) DEFAULT NULL,
  `pelanggan_id` bigint(20) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`sewa_id`),
  KEY `FK2744BC90876083` (`pelanggan_id`),
  KEY `FK2744BCE5F19663` (`pegawai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sewa`
--

INSERT INTO `sewa` (`sewa_id`, `code`, `status`, `tgl_trx`, `pegawai_id`, `pelanggan_id`, `total`) VALUES
(1, '000001', 1, '2012-11-13', 1, 1, NULL),
(2, '000002', 1, '2012-11-13', 1, 1, NULL),
(3, '000003', 1, '2012-11-14', 1, 1, 50000.00),
(4, '000004', 1, '2012-11-14', 1, 1, 60000.00);

-- --------------------------------------------------------

--
-- Table structure for table `sewad`
--

CREATE TABLE IF NOT EXISTS `sewad` (
  `sewad_id` bigint(20) NOT NULL,
  `jumlah_waktu` decimal(19,2) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `tgl_sewa` date DEFAULT NULL,
  `waktu_end` datetime DEFAULT NULL,
  `waktu_start` datetime DEFAULT NULL,
  `mobil_id` bigint(20) DEFAULT NULL,
  `sewa_id` bigint(20) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`sewad_id`),
  KEY `FK4C1532815883A31` (`sewa_id`),
  KEY `FK4C153281986BBC3` (`mobil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sewad`
--

INSERT INTO `sewad` (`sewad_id`, `jumlah_waktu`, `status`, `tgl_kembali`, `tgl_sewa`, `waktu_end`, `waktu_start`, `mobil_id`, `sewa_id`, `total`) VALUES
(2, 5.00, 1, '2012-11-13', '2012-11-13', '2012-11-13 08:58:34', '2012-11-13 03:58:34', 6, 2, 100000.00),
(3, 5.00, 1, '2012-11-14', '2012-11-14', '2012-11-14 16:52:59', '2012-11-14 11:52:59', 10, 3, 50000.00),
(4, 6.00, 1, '2012-11-14', '2012-11-14', '2012-11-14 06:06:33', '2012-11-14 00:06:33', 7, 4, 60000.00);

-- --------------------------------------------------------

--
-- Table structure for table `tableid`
--

CREATE TABLE IF NOT EXISTS `tableid` (
  `nama_table` varchar(255) NOT NULL,
  `incr` int(11) NOT NULL,
  `no` bigint(20) NOT NULL,
  `pref` varchar(255) DEFAULT NULL,
  `start_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`nama_table`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tableid`
--

INSERT INTO `tableid` (`nama_table`, `incr`, `no`, `pref`, `start_no`) VALUES
('hak_akses', 1, 3, '000000', 1),
('kembali', 1, 5, '000000', 1),
('kembalid', 1, 5, '000000', 1),
('mobil', 1, 26, '000000', 1),
('pegawai', 1, 3, '000000', 1),
('pelanggan', 1, 2, '000000', 1),
('sewa', 1, 5, '000000', 1),
('sewad', 1, 5, '000000', 1),
('type_mobil', 1, 3, '000000', 1);

-- --------------------------------------------------------

--
-- Table structure for table `type_mobil`
--

CREATE TABLE IF NOT EXISTS `type_mobil` (
  `type_mobil_id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `denda` decimal(19,2) DEFAULT NULL,
  `harga` decimal(19,2) DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `jumlah_tersedia` int(11) DEFAULT NULL,
  `ket` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_mobil_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_mobil`
--

INSERT INTO `type_mobil` (`type_mobil_id`, `code`, `denda`, `harga`, `jumlah`, `jumlah_tersedia`, `ket`, `nama`) VALUES
(2, '000002', 10000.00, 10000.00, 20, 20, '-', 'Mobil Roda 2');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kembali`
--
ALTER TABLE `kembali`
  ADD CONSTRAINT `FK31FADAAF15883A31` FOREIGN KEY (`sewa_id`) REFERENCES `sewa` (`sewa_id`),
  ADD CONSTRAINT `FK31FADAAFE5F19663` FOREIGN KEY (`pegawai_id`) REFERENCES `pegawai` (`pegawai_id`);

--
-- Constraints for table `kembalid`
--
ALTER TABLE `kembalid`
  ADD CONSTRAINT `FKD607B9597959603` FOREIGN KEY (`kembali_id`) REFERENCES `kembali` (`kembali_id`),
  ADD CONSTRAINT `FKD607B959B812DA3` FOREIGN KEY (`sewad_id`) REFERENCES `sewad` (`sewad_id`);

--
-- Constraints for table `mobil`
--
ALTER TABLE `mobil`
  ADD CONSTRAINT `FK4710403F6EE378E` FOREIGN KEY (`type_mobil_id`) REFERENCES `type_mobil` (`type_mobil_id`);

--
-- Constraints for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `FK3A2552D0D64C3F3E` FOREIGN KEY (`hak_akses_id`) REFERENCES `hak_akses` (`hak_akses_id`);

--
-- Constraints for table `sewa`
--
ALTER TABLE `sewa`
  ADD CONSTRAINT `FK2744BC90876083` FOREIGN KEY (`pelanggan_id`) REFERENCES `pelanggan` (`pelanggan_id`),
  ADD CONSTRAINT `FK2744BCE5F19663` FOREIGN KEY (`pegawai_id`) REFERENCES `pegawai` (`pegawai_id`);

--
-- Constraints for table `sewad`
--
ALTER TABLE `sewad`
  ADD CONSTRAINT `FK4C1532815883A31` FOREIGN KEY (`sewa_id`) REFERENCES `sewa` (`sewa_id`),
  ADD CONSTRAINT `FK4C153281986BBC3` FOREIGN KEY (`mobil_id`) REFERENCES `mobil` (`mobil_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
