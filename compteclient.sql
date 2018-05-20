-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 20 Mai 2018 à 02:34
-- Version du serveur :  10.1.13-MariaDB
-- Version de PHP :  5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `compteclient`
--
CREATE DATABASE IF NOT EXISTS `compteclient` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `compteclient`;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `code` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`code`, `nom`) VALUES
(1, 'Hassan'),
(2, 'ALAE');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `type_cpte` varchar(2) NOT NULL,
  `code_compte` varchar(255) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `solde` double NOT NULL,
  `decouvert` double DEFAULT NULL,
  `taux` double DEFAULT NULL,
  `code_cli` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`type_cpte`, `code_compte`, `date_creation`, `solde`, `decouvert`, `taux`, `code_cli`) VALUES
('CC', 'c1', '2018-04-22 15:27:21', 424033, 6000, NULL, 1),
('CE', 'c2', '2018-04-22 15:27:21', 5940, NULL, 5.5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(24),
(24);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `type_op` varchar(1) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `date_operation` datetime DEFAULT NULL,
  `montant` double NOT NULL,
  `code_cpte` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `operation`
--

INSERT INTO `operation` (`type_op`, `numero`, `date_operation`, `montant`, `code_cpte`) VALUES
('V', 3, '2018-04-22 15:27:21', 900, 'c1'),
('V', 4, '2018-04-22 15:27:21', 650, 'c1'),
('V', 5, '2018-04-22 15:27:21', 230, 'c1'),
('R', 6, '2018-04-22 15:27:21', 900, 'c1'),
('V', 7, '2018-04-22 15:27:21', 2300, 'c2'),
('V', 8, '2018-04-22 15:27:21', 400, 'c2'),
('V', 9, '2018-04-22 15:27:21', 2300, 'c2'),
('V', 10, '2018-04-22 15:27:21', 3000, 'c2'),
('R', 11, '2018-04-22 15:29:12', 3000, 'c2'),
('V', 12, '2018-04-22 15:30:04', 111111, 'c1'),
('V', 13, '2018-04-22 15:55:04', 111111, 'c1'),
('V', 14, '2018-04-22 16:14:47', 111111, 'c1'),
('V', 15, '2018-04-22 18:35:16', 800, 'c1'),
('R', 19, '2018-04-22 18:40:00', 80, 'c1'),
('R', 20, '2018-04-22 18:40:16', 20, 'c1'),
('V', 21, '2018-04-22 18:40:16', 20, 'c2'),
('R', 22, '2018-04-22 19:21:18', 80, 'c2');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`role`) VALUES
('ADMIN'),
('USER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`username`, `active`, `password`) VALUES
('admin', b'1', '$2a$10$ZMiwYvmg9RU8/51RUmhVI.z0G1AsRAozUq6K3.mZ7KwNuVqIVNwUG'),
('user', b'1', '$2a$10$ZMiwYvmg9RU8/51RUmhVI.l2DPOuPmqbSqR49n..jUWw170n47oXC'),
('hassan', b'1', '$2a$10$ZMiwYvmg9RU8/51RUmhVI.n7V1zORrzBNPbBXExK3EP6nofE7GImW');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `username` varchar(25) NOT NULL,
  `password` varchar(250) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`username`, `password`, `active`) VALUES
('admin', '1234', 1),
('hassan', '81dc9bdb52d04dc20036dbd8313ed055', 1),
('user', '*A4B6157319038724E3560894F7F932C8886EBFCF', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `username` varchar(25) NOT NULL,
  `roles` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users_roles`
--

INSERT INTO `users_roles` (`username`, `roles`) VALUES
('admin', 'ADMIN'),
('admin', 'USER'),
('hassan', 'USER'),
('user', 'USER');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`code_compte`),
  ADD KEY `FK2hw4shqsxc782lychpkr52lmv` (`code_cli`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `FKkr9nfjf0ipqrw5xwcf9jqq6gv` (`code_cpte`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`username`,`roles`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
