-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2024-06-21 11:20:46
-- サーバのバージョン： 10.4.28-MariaDB
-- PHP のバージョン: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `shoppingdb`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `itemsinfo`
--

CREATE TABLE `itemsinfo` (
  `user_id` varchar(20) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `item_kana` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `remark` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- テーブルのデータのダンプ `itemsinfo`
--

INSERT INTO `itemsinfo` (`user_id`, `isbn`, `item_name`, `item_kana`, `type`, `price`, `remark`) VALUES
('0001', '1111', '靴', 'くつ', '靴', 2000, '23.5センチです。');

-- --------------------------------------------------------

--
-- テーブルの構造 `mails`
--

CREATE TABLE `mails` (
  `user_id` varchar(20) NOT NULL,
  `sender` int(2) NOT NULL,
  `send_date` datetime NOT NULL,
  `received_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- テーブルの構造 `salesinfo`
--

CREATE TABLE `salesinfo` (
  `sales_id` int(11) NOT NULL,
  `sales_date` date NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `shipping_status` varchar(20) NOT NULL,
  `payment_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- テーブルの構造 `userinfo`
--

CREATE TABLE `userinfo` (
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_nickname` varchar(20) NOT NULL,
  `user_address` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `phone_num` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `authority` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- テーブルのデータのダンプ `userinfo`
--

INSERT INTO `userinfo` (`user_id`, `user_name`, `user_nickname`, `user_address`, `mail`, `phone_num`, `password`, `authority`) VALUES
('0001', '神田太郎', 'かんちゃん', '東京都●●区', 'kanda@kanda.co.jp', '09012345678', '1', ''),
('0002', '今野大輝', 'こんぴー', '東京都●●区', 'konno@konno.co.jp', '09012123434', '1', ''),
('1001', '田中圭', 'たな', '東京都●●区', 'tanaka@tanaka.co.jp', '09012341234', '1', '1');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `itemsinfo`
--
ALTER TABLE `itemsinfo`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `user_id` (`user_id`);

--
-- テーブルのインデックス `mails`
--
ALTER TABLE `mails`
  ADD KEY `user_id` (`user_id`);

--
-- テーブルのインデックス `salesinfo`
--
ALTER TABLE `salesinfo`
  ADD PRIMARY KEY (`sales_id`),
  ADD KEY `isbn` (`isbn`);

--
-- テーブルのインデックス `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`user_id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `salesinfo`
--
ALTER TABLE `salesinfo`
  MODIFY `sales_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- ダンプしたテーブルの制約
--

--
-- テーブルの制約 `itemsinfo`
--
ALTER TABLE `itemsinfo`
  ADD CONSTRAINT `itemsinfo_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- テーブルの制約 `mails`
--
ALTER TABLE `mails`
  ADD CONSTRAINT `mails_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- テーブルの制約 `salesinfo`
--
ALTER TABLE `salesinfo`
  ADD CONSTRAINT `salesinfo_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `itemsinfo` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
